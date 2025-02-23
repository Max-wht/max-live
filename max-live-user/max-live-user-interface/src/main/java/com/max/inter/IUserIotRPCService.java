package com.max.inter;

import com.max.dto.CheckSendBinDTO;
import com.max.dto.IOTBinFile;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Max
 * @description
 * @date 2025/2/23 14:45
 */
public interface IUserIotRPCService {

    CheckSendBinDTO sendBin(IOTBinFile file);
}
