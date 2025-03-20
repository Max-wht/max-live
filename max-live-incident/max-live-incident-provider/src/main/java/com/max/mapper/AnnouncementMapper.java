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
                                      @Param("endTime") Date endTime,
                                      @Param("offset") int offset,
                                      @Param("pageSize") int pageSize);

    void updateAnnounce(@Param("name") String name,
                        @Param("content") String content,
                        @Param("id") int id);

    void delete(Integer id);

    void saveAnnounce(@Param("id") Long announcementID,
                      @Param("name") String name,
                      @Param("content") String content);
}
