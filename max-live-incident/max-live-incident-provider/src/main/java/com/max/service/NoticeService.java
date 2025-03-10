package com.max.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.max.dto.AnnounceDTO;
import com.max.live.page.PageBean;
import com.max.mapper.AnnouncementMapper;
import jakarta.annotation.Resource;
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
    public PageBean<AnnounceDTO> getList(AnnounceDTO announceDTO) {
        //设置分页参数
        PageHelper.startPage(announceDTO.getCurrent(), 8);
        //调用Mapper层查询分页数据
        String name = announceDTO.getName();
        Date startTime = announceDTO.getStartTime();
        Date endTime = announceDTO.getEndTime();
        List<AnnounceDTO> announcementList = announcementMapper.getAnnounceList(name, startTime, endTime);
        Page<AnnounceDTO> p = (Page<AnnounceDTO>) announcementList;
        //封装PageBean对象
        PageBean<AnnounceDTO> pageBean = new PageBean<>(p.getResult());
        return pageBean;

    }
}
