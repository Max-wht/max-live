package com.max.mapper;

import com.max.dto.AnnounceDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author Max
 * @description
 * @date 2025/3/10 19:34
 */

@Mapper
public interface AnnouncementMapper {
    /**
     * 查询公告
     */
    List<AnnounceDTO> getAnnounceList(@Param("name") String name,
                                      @Param("startTime") Date startTime,
                                      @Param("endTime") Date endTime);
}
