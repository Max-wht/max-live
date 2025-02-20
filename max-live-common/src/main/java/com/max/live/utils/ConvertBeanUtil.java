package com.max.live.utils;

import org.springframework.beans.BeanUtils;

/**
 * @author max
 * @date 2025/2/8
 * @desc DO 和 DTO 文件转换
 */
public class ConvertBeanUtil {

    public static <T> T convert(Object source, Class<T> target){
        if(source == null){
            return null;
        }
        T targetObject = null;
        try{
            targetObject = target.newInstance();
            BeanUtils.copyProperties(source, targetObject);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return targetObject;
    }
}
