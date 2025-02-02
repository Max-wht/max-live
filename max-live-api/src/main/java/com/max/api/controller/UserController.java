package com.max.api.controller;

import com.max.api.entity.WebResDTO;
import com.max.dto.QueryUser;
import com.max.dto.UserDTO;
import com.max.inter.IUserRPCService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    Logger log = LoggerFactory.getLogger(UserController.class);

    //引入dubbo服务
    @DubboReference
    public IUserRPCService userRPCService;

    /**
     * 查询用户信息
     * @param queryUser
     * @return
     */
    @RequestMapping("/queryUser")
    public WebResDTO getUserById(@RequestBody QueryUser queryUser) {
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
     * 发送短信验证码
     * @param mobile
     * @return
     */
    @PostMapping("/sendmSMS")
    public WebResDTO sendSMS(String mobile) {
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

}
