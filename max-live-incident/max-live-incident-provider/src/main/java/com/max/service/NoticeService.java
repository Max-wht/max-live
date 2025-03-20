package com.max.service;

import com.max.dto.AnnounceDTO;
import com.max.id.inter.IGenerateIDRPCService;
import com.max.live.page.PageBean;
import com.max.mapper.AnnouncementMapper;
import jakarta.annotation.Resource;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author Max
 * @description
 * @date 2025/3/10 19:19
 */

@Service
public class NoticeService {
    @Resource
    private AnnouncementMapper announcementMapper;

    @DubboReference
    private IGenerateIDRPCService generateIDRPCService;
    public PageBean<AnnounceDTO> getList(AnnounceDTO announceDTO) {
        String name = announceDTO.getName();
        Date startTime = announceDTO.getStartTime();
        Date endTime = announceDTO.getEndTime();
        Integer current = announceDTO.getCurrent();
        int pageSize = 8;
        if(null == current || current < 1){
            current = 1;
        }
        int offset = (current - 1) * pageSize;
        List<AnnounceDTO> announcementList = announcementMapper.getAnnounceList(name, startTime, endTime,offset, pageSize);
        //将announcementList封装到PageBean中
        return new PageBean<>(announcementList);

    }

    public void updateAnnounce(AnnounceDTO announceDTO) {
        int id = announceDTO.getId();
        String name = announceDTO.getName();
        String content = announceDTO.getContent();
        announcementMapper.updateAnnounce(name , content, id);
    }

    public void delete(Integer id) {
        announcementMapper.delete(id);
    }

    public void save(AnnounceDTO announceDTO) {
        //调用CosID模块
        String name = announceDTO.getName();
        String content = announceDTO.getContent();
        if (name == null || content == null) {
            throw new IllegalArgumentException("标题和内容不能为空");
        }
        Long announcementID = generateIDRPCService.gerSeqId();//通过Dubbo调用id-generate服务生成主键
        announcementMapper.saveAnnounce(announcementID, name, content);
    }
}
