package com.max.impl;

import com.max.dto.AnnounceDTO;
import com.max.inter.IAnnounceRPCService;
import com.max.live.page.PageBean;
import com.max.service.NoticeService;
import jakarta.annotation.Resource;
import org.apache.dubbo.config.annotation.DubboService;
import org.slf4j.Logger;

/**
 * @author Max
 * @description
 * @date 2025/3/10 19:17
 */
@DubboService
public class AnnounceRPCService implements IAnnounceRPCService {

    Logger log = org.slf4j.LoggerFactory.getLogger(AnnounceRPCService.class);
    @Resource
    private NoticeService noticeService;
    @Override
    public PageBean<AnnounceDTO> getAnnounceList(AnnounceDTO announceDTO) {
        try {
            log.info("正在查询");
            return noticeService.getList(announceDTO);
        } catch (Exception e) {
            log.info("查询失败");
            //11111
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateAnnounce(AnnounceDTO announceDTO) {
        try {
            log.info("正在修改");
            noticeService.updateAnnounce(announceDTO);
        } catch (Exception e) {
            log.info("修改失败");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void batchDelete(Integer id) {
        try {
            log.info("正在删除");
            noticeService.delete(id);
        } catch (Exception e) {
            log.info("删除失败");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(AnnounceDTO announceDTO) {
        try {
            log.info("正在保存");
            noticeService.save(announceDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
