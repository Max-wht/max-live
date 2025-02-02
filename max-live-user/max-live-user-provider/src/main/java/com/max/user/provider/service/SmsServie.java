package com.max.user.provider.service;

import com.max.common.redis.SMSCatchKeyBuilder;
import com.max.user.provider.entity.SmsDO;
import com.max.user.provider.mapper.SmsMapper;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author max
 *
 */
@Service
public class SmsServie {
    @Autowired
    private SmsMapper smsMapper;

    @Resource
    private SMSCatchKeyBuilder smsCatchKeyBuilder;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    Logger log = LoggerFactory .getLogger(SmsServie.class);


    public boolean sendLoginCode(String mobile) {
        //校验数据
        if(mobile == null || mobile.length() != 11){
            return false;
        }
        //生成一个和手机号相关联的RedisKey
        String smskey = smsCatchKeyBuilder.buildSmsLoginCodeKey(mobile);
        //去Redis查询是否有这个手机号对应的验证码
        Object smsRecord = redisTemplate.opsForValue().get(smskey);

        if(smsRecord == null){//如果没有，则生成一个验证码，并把验证码和手机号保存到Redis中，并返回true
            //生成一个1000-9999的随机验证码
            int smsCode = new Random().nextInt(8999) + 1000;
            log.info("手机号{}生成验证码{}", mobile, smsCode);
            redisTemplate.opsForValue().set(smskey, smsCode, 1, TimeUnit.MINUTES);
            //发送验证码
            boolean sendSMS = sendSms(mobile , smsCode);

            log.info("手机号{}发送验证码{}", mobile, smsCode);
            //保存短信发送记录
            insertSMSRecord(mobile, smsRecord);

            return true;
        }else {//如果有，则表明已经发送过验证码，则返回false
            log.info("手机号{}已经发送过验证码", mobile);
            return false;
        }
    }

    private void insertSMSRecord(String mobile, Object smsRecord) {
        SmsDO smsDO = new SmsDO();
        smsDO.setPhone(mobile);
        smsDO.setCode((Integer) smsRecord);
        //TODO 生成记录id
        smsMapper.insert(smsDO);
    }

    private boolean sendSms(String mobile, int smsCode) {
        return true;
    }
}
