package com.max.api.controller;

import com.max.api.DTO.WebResDTO;
import com.max.dto.CheckSendBinDTO;
import com.max.dto.IOTBinFile;
import com.max.inter.IUserIotRPCService;
import com.max.live.utils.CheckNameUtil;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Max
 * @description
 * @date 2025/2/20 19:00
 */


@RestController
@RequestMapping("/iot")
@CrossOrigin
public class IOTController {

    @DubboReference
    private IUserIotRPCService UserIotRPCService;

    Logger log = LoggerFactory .getLogger(IOTController.class);

    @PostMapping("/sendBin")
    public WebResDTO binTCPConnection(@RequestParam("file") MultipartFile file){
        String originalFileName = file.getOriginalFilename();

        log.info("成功接收到文件={}" , originalFileName);
        if(originalFileName == null || !CheckNameUtil.checkWhetherBinOrHex(originalFileName)){
            return WebResDTO.error("文件不合法，请上传合法文件");
        }
        try{
            log.info("开始发送文件");
            IOTBinFile iotBinFile = new IOTBinFile();
            iotBinFile.setFileName(originalFileName);
            iotBinFile.setFileContent(file.getBytes());

            CheckSendBinDTO checkSendBinDTO = UserIotRPCService.sendBin(iotBinFile);
            return WebResDTO.success(checkSendBinDTO);
        } catch (Exception e){
            log.error("发送文件失败", e);
            return WebResDTO.error("发送文件失败");
        }
    }


}
