package com.max.id.inter;

/**
 * @author Max
 * @description
 * @date 2025/2/6 15:58
 */
public interface IGenerateIDRPCService {

    /**
     * 严格有序的id
     * @return
     */
    Long gerSeqId();

    /**
     * 非严格有序的id
     * @return
     */
    Long getUnSeqId();
}
