package com.max.user.provider.impl;

import com.max.dto.UserDTO;
import com.max.inter.IUserRPCService;
import com.max.user.provider.service.SmsServie;
import com.max.user.provider.service.UserService;
import jakarta.annotation.Resource;
import org.apache.dubbo.config.annotation.DubboService;


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

}
