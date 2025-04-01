package com.max.api.controller;

import com.alibaba.dubbo.container.page.Page;
import com.max.api.DTO.MobileLoginParam;
import com.max.api.DTO.QueryUserParam;
import com.max.api.DTO.SearchMethods;
import com.max.api.DTO.WebResDTO;
import com.max.api.VO.StudentVO;
import com.max.api.VO.UserVO;
import com.max.dto.CheckLoginDTO;
import com.max.dto.StudentDTO;
import com.max.dto.UserDTO;
import com.max.dto.UserLoginDTO;
import com.max.inter.IUserMoblieRPCService;
import com.max.inter.IUserRPCService;
import com.max.live.page.PageBean;
import com.max.live.utils.ConvertBeanUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    Logger log = LoggerFactory.getLogger(UserController.class);

    //引入dubbo服务
    @DubboReference(check = true)
    public IUserRPCService userRPCService;

    @DubboReference(check = true)
    public IUserMoblieRPCService userMoblieRPCService;

    /**
     * 查询用户信息
     * @param queryUser
     * @return
     */
    @RequestMapping("/queryUser")
    public WebResDTO getUserById(@RequestBody QueryUserParam queryUser) {
        log.info("查询用户信息={}", queryUser.toString());
        //调用dubbo的RPC
        UserDTO user = userRPCService.getUserById(queryUser.getUserId());
        if (user != null) {
            return WebResDTO.success(user);
        } else {
            return WebResDTO.error("查询用户信息失败");
        }
    }

    /**
     * 发送短信验证码，并保存到缓存及数据库
     * @param mobile
     * @return
     */
    @CrossOrigin(origins = "*")
    @PostMapping("/sendmSMS")
    public WebResDTO sendSMS(@RequestBody String mobile) {
        if(!StringUtils.hasText(mobile)){
            return WebResDTO.error("手机号不能为空");
        }
        //调用远程服务，发送短信验证码
        if(userRPCService.sendLoginCode(mobile)){
            return WebResDTO.success();
        }else{
            return WebResDTO.error("发送短信验证码失败，请重试");
        }
    }

    /**
     * 用户手机号登录注册
     * @param mobileLoginParam
     * @return
     */
    @PostMapping("/mobileLogin")
    public WebResDTO mobileLogin(@RequestBody MobileLoginParam mobileLoginParam, HttpServletResponse response) {
        //1.参数校验
        String moblie = mobileLoginParam.getMobile();
        int code = mobileLoginParam.getCode();
        if(!StringUtils.hasText(moblie)){
            return WebResDTO.error("手机号不能为空");
        }
        if(code < 1000 || code > 9999){
            return WebResDTO.error("验证码格式错误");
        }

        //2.检验验证码 -调用RPC服务
        CheckLoginDTO checkLoginDTO = userRPCService.checkLoginCode(moblie, code);
        if(false == checkLoginDTO.isCheckStatus()){
            return WebResDTO.error("验证码错误，请输入正确验证码");
        }
        //3.通过后，用手机号完成登录
        //4.如果之前没有登陆过就直接注册 - 调用RPC服务
        UserLoginDTO login = userMoblieRPCService.login(moblie);

        //给前端返回cookies("cokk" + token(通过方法调用))
        String token = userRPCService.createCookies(login.getUserId());
        Cookie cookie = new Cookie("cokk", token);
        cookie.setMaxAge(24 * 60 * 60);

        //在响应头中添加cookies
        response.addCookie(cookie);
        //5.返回登录状态
        return WebResDTO.success(login);
    }

    /**
     * 在用户登录之后给网页返回用户信息
     * @param userId
     * @return
     */
    @GetMapping("/auth")
    public WebResDTO sendUserInfo(@RequestParam("userId") Long userId){
        if( null == userId || userId <= 0){
            return WebResDTO.error("用户id为空，或者id不正确");
        }
        try {
            UserDTO userById = userRPCService.getUserById(userId);

            UserVO userVO = new UserVO();
            userVO.setId(userById.getId().toString());
            userVO.setUserName(userById.getNickName());
            userVO.setUserAvatar(userById.getAvatar());
            userVO.setSubCount(userById.getSubCount());
            userVO.setUserRole(userById.getUserCharacter());
            if(null == userById){
                return WebResDTO.error("用户信息为空");
            }else{
                return WebResDTO.success(userVO);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 查询所有用户信息，按需求排序
     * @param userName
     * @param sortBy
     * @return
     */
    @GetMapping("/queryStudents")
    private WebResDTO sendAllMemberInfo(@RequestParam(required = false) String userName,
                                        //nnnnnn
                                        @RequestParam String sortBy,
                                        @RequestParam Integer page,
                                        @RequestParam Integer pageSize){
        if(sortBy == null) {
            return WebResDTO.error("排序字段为空,请刷新页面");
        }
        try {
            SearchMethods.valueOf(sortBy);
        } catch (IllegalArgumentException e){
            return WebResDTO.error("无效的排序方式");
        }
        PageBean<StudentVO> pageBean = new PageBean<>();
        try {
            List<StudentDTO> studentsListDTO =userRPCService.queryStudents(userName, sortBy, page, pageSize);
            Map<Integer, String> classMap = userRPCService.getClassMap();
            List<StudentVO> studentsListVO = studentsListDTO.stream()
                    .map(dto -> {
                                StudentVO vo = ConvertBeanUtil.convert(dto , StudentVO.class);
                                vo.setClassName(classMap.getOrDefault(dto.getClassesId(), "管理员"));
                                return vo;
                            })
                    .collect(Collectors.toList());
            pageBean.setRow(studentsListVO);
            pageBean.setTotal(userRPCService.queryStudentsTotal(userName));
            return WebResDTO.success(pageBean);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
