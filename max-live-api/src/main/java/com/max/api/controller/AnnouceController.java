package com.max.api.controller;

import com.max.api.entity.AnnounceParam;
import com.max.api.entity.WebResDTO;
import com.max.dto.AnnounceDTO;
import com.max.inter.IAnnounceRPCService;
import com.max.live.page.PageBean;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Max
 * @description
 * @date 2025/3/10 18:42
 */
@RestController
@RequestMapping("/notice")
@CrossOrigin
public class AnnouceController {
    @DubboReference
    private IAnnounceRPCService announceService;

    /**
     * 查询
     * @param announceParam
     * @return
     */
    @RequestMapping("/Announcements")
    public WebResDTO getAnnounceList(@RequestBody AnnounceDTO announceParam){

        PageBean<AnnounceDTO> pageBean = announceService.getAnnounceList(announceParam);
        return WebResDTO.success(pageBean);

    }
}
