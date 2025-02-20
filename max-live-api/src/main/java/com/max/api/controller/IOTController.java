package com.max.api.controller;

import com.max.api.entity.WebResDTO;
import com.max.live.utils.CheckNameUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Max
 * @description
 * @date 2025/2/20 19:00
 */


@RestController
@RequestMapping("/iot")
public class IOTController {


    Logger log = LoggerFactory .getLogger(IOTController.class);
//    public WebResDTO binTCPConnection(@RequestParam("file") MultipartFile file){
//        String originalFileName = file.getOriginalFilename();
//
//        log.info("成功接收到文件={}" , originalFileName);
//        if(originalFileName == null || !CheckNameUtil.checkWhetherBinOrHex(originalFileName)){
//            return WebResDTO.error("文件不合法，请上传合法文件");
//        }
//    }


}
