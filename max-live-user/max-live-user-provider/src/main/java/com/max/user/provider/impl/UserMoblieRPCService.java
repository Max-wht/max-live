package com.max.user.provider.impl;

import com.max.dto.UserLoginDTO;
import com.max.inter.IUserMoblieRPCService;
import com.max.user.provider.service.UserMoblieService;
import jakarta.annotation.Resource;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * @author Max
 * @date 2021/4/12 16:08
 * @description
 */
@DubboService
public class UserMoblieRPCService implements IUserMoblieRPCService {

    @Resource
    private UserMoblieService userMoblieService;

    @Override
    public UserLoginDTO login(String moblie) {
        return userMoblieService.login(moblie);
    }
}
