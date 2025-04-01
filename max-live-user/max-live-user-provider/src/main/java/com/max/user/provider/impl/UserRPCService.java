package com.max.user.provider.impl;

import com.max.dto.CheckLoginDTO;
import com.max.dto.StudentDTO;
import com.max.dto.UserDTO;
import com.max.inter.IUserRPCService;
import com.max.user.provider.service.SmsServie;
import com.max.user.provider.service.UserService;
import jakarta.annotation.Resource;
import org.apache.dubbo.config.annotation.DubboService;

import java.util.List;
import java.util.Map;


@DubboService
public class UserRPCService implements IUserRPCService{

    @Resource
    private UserService userService;

    @Resource
    private SmsServie smsServie;

    @Override
    public UserDTO getUserById(Long userId) {
        return userService.getUserById(userId);
    }

    /**
     * 发送短信验证码
     * @param mobile
     * @return
     */
    @Override
    public boolean sendLoginCode(String mobile) {
        return smsServie.sendLoginCode(mobile);
    }

    @Override
    public CheckLoginDTO checkLoginCode(String moblie, int code) {
        //调用本地短信服务校验验证码
        CheckLoginDTO checkStatus = smsServie.checkLoginCode(moblie, code);
        return checkStatus;
    }

    @Override
    public String createCookies(Long userId) {
        return smsServie.createCookies(userId);
    }

    @Override
    public Boolean checkToken(String token) {
        return userService.checkToken(token);
    }

    @Override
    public List<StudentDTO> queryStudents(String userName, String sortBy, Integer page, Integer pageSize) {
        return userService.queryStudents(userName,sortBy,page,pageSize);
    }

    @Override
    public int queryStudentsTotal(String userName) {
        return userService.queryStudentsTotal(userName);
    }

    @Override
    public Map<Integer, String> getClassMap() {
        return userService.getClassMap();
    }

}
