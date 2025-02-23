package com.max.user.provider.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.max.dto.CheckSendBinDTO;
import com.max.dto.IOTBinFile;
import com.max.inter.IUserIotRPCService;
import com.max.user.provider.service.IotService;
import jakarta.annotation.Resource;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Max
 * @description
 * @date 2025/2/23 14:52
 */

@DubboService
public class UserIotRPCService implements IUserIotRPCService {

    @Resource
    private IotService iotService;
    @Override
    public CheckSendBinDTO sendBin(IOTBinFile file) {
        return iotService.sendBin(file);
    }
}
