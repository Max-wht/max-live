package com.max.inter;

import com.max.dto.AnnounceDTO;
import com.max.live.page.PageBean;

/**
 * @author Max
 * @description
 * @date 2025/3/10 18:33
 */
public interface IAnnounceRPCService {

    PageBean<AnnounceDTO> getAnnounceList(AnnounceDTO announceDTO);
}
