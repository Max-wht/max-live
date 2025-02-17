package com.max.id.generate.service;

import com.max.id.generate.utils.IDConstants;
import com.max.id.inter.IGenerateIDRPCService;
import jakarta.annotation.Resource;
import me.ahoo.cosid.provider.IdGeneratorProvider;
import org.apache.dubbo.config.annotation.DubboService;

import static com.max.id.generate.utils.IDConstants.ID_SEQUENCE;
import static com.max.id.generate.utils.IDConstants.ID_SNOWFLAKE;

/**
 * @author Max
 * @description
 * @date 2025/2/7 16:13
 */

@DubboService
public class GenerateIDRPCService implements IGenerateIDRPCService {

    @Resource
    private IdGeneratorProvider idGeneratorProvider;
    @Override
    public Long gerSeqId() {
        return idGeneratorProvider.getRequired(ID_SEQUENCE).generate();
    }

    @Override
    public Long getUnSeqId() {
        return idGeneratorProvider.getRequired(ID_SNOWFLAKE).generate();
    }
}