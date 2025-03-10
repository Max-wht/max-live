package com.max.impl;

import com.max.dto.AnnounceDTO;
import com.max.inter.IAnnounceRPCService;
import com.max.live.page.PageBean;
import com.max.service.NoticeService;
import jakarta.annotation.Resource;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * @author Max
 * @description
 * @date 2025/3/10 19:17
 */
@DubboService
public class AnnounceRPCService implements IAnnounceRPCService {
    @Resource
    private NoticeService noticeService;
    @Override
    public PageBean<AnnounceDTO> getAnnounceList(AnnounceDTO announceDTO) {
        return noticeService.getList(announceDTO);
    }
}
