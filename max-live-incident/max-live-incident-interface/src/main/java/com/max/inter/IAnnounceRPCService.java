package com.max.inter;

import com.max.dto.AnnounceDTO;
import com.max.live.page.PageBean;

import java.util.List;

/**
 * @author Max
 * @description
 * @date 2025/3/10 18:33
 */
public interface IAnnounceRPCService {

    PageBean<AnnounceDTO> getAnnounceList(AnnounceDTO announceDTO);

    void updateAnnounce(AnnounceDTO announceDTO);

    void batchDelete(Integer id);

    void save(AnnounceDTO announceDTO);
}
