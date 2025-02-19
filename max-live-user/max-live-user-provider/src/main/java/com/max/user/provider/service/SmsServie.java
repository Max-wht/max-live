package com.max.user.provider.service;

import com.cloopen.rest.sdk.BodyType;
import com.cloopen.rest.sdk.CCPRestSmsSDK;
import com.max.common.redis.SMSCatchKeyBuilder;
import com.max.common.redis.UserCatchKeyBuilder;
import com.max.dto.CheckLoginDTO;
import com.max.user.provider.config.SMSCCPConfig;
import com.max.user.provider.config.ThreadPoolManager;
import com.max.user.provider.entity.SmsDO;
import com.max.user.provider.mapper.SmsMapper;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
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
    private SMSCCPConfig sMSCCPConfig;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private UserCatchKeyBuilder userCatchKeyBuilder;

    Logger log = LoggerFactory.getLogger(SmsServie.class);




    public boolean sendLoginCode(String mobile) {
        //校验数据
        if(mobile == null && mobile.length() != 11){
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
            //TOLambdaQueryWrapper<UserPhoneDO> wrapper = new LambdaQueryWrapper<>();DO使用异步线程池发送短信
            ThreadPoolManager.commonAsyncPool.execute(()->{
                //发送验证码
                for (int i = 0; i < 3; i++) {
                    boolean sendSMS = sendSms(mobile , smsCode);
                    if(sendSMS){
                        log.info("发送验证码成功");
                        insertSMSRecord(mobile, smsCode);
                        break;
                    }
                }
            });
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
        // 生成记录id:由mybatis自动生成
        smsMapper.insert(smsDO);

        //加载到数据库中
        String smsKey = smsCatchKeyBuilder.buildSmsLoginCodeKey(mobile);
        String smsCode = smsRecord.toString();
        redisTemplate.opsForValue().set(smsKey, smsCode, 1, TimeUnit.MINUTES);
    }

    private boolean sendSms(String mobile, int smsCode) {
        log.info("向13632871232发送验证码");
        if(sMSCCPConfig.getTest()){
            return true;
        }else{
            try {

                //生产环境请求地址：app.cloopen.com
                String serverIp = sMSCCPConfig.getServerIP();
                //请求端口
                String serverPort = sMSCCPConfig.getPort();
                //主账号,登陆云通讯网站后,可在控制台首页看到开发者主账号ACCOUNT SID和主账号令牌AUTH TOKEN
                String accountSId = sMSCCPConfig.getAccountSid();
                String accountToken = sMSCCPConfig.getAccountToken();
                //请使用管理控制台中已创建应用的APPID
                String appId = sMSCCPConfig.getAppID();
                CCPRestSmsSDK maxSms = new CCPRestSmsSDK();

                maxSms.init(serverIp, serverPort);
                maxSms.setAccount(accountSId, accountToken);
                maxSms.setAppId(appId);
                maxSms.setBodyType(BodyType.Type_JSON);
                String to = sMSCCPConfig.getTestPhone();
                String templateId= sMSCCPConfig.getTemplateID();
                int code = new Random().nextInt(8999) + 1000;
                log.info("*******发送验证码{}", code);
                String[] datas = {String.valueOf(code)};
                String subAppend="1234";  //可选	扩展码，四位数字 0~9999
                String reqId="max";  //可选 第三方自定义消息id，最大支持32位英文数字，同账号下同一自然天内不允许重复
                HashMap<String, Object> result = maxSms.sendTemplateSMS(to,templateId,datas);
                //HashMap<String, Object> result = maxSms.sendTemplateSMS(to,templateId,datas,subAppend,reqId);
                if("000000".equals(result.get("statusCode"))){
                    //向redis中添加验证码
                    String phoneKey = smsCatchKeyBuilder.buildSmsLoginCodeKey(to);
                    redisTemplate.opsForValue().set(phoneKey, code, 100, TimeUnit.MINUTES);

                    //正常返回输出data包体信息（map）
                    HashMap<String,Object> data = (HashMap<String, Object>) result.get("data");
                    Set<String> keySet = data.keySet();
                    for(String key:keySet){
                        Object object = data.get(key);
                        System.out.println(key +" = "+object);
                    }
                }else{
                    //异常返回输出错误码和错误信息
                    System.out.println("错误码=" + result.get("statusCode") +" 错误信息= "+result.get("statusMsg"));
                }
            } catch (Exception e) {
                log.error("sendSMSToCCP errot");
                throw new RuntimeException(e);
            }finally{
                log.info("sendSMSToCCP end");
                return false;
            }
        }

    }

    /**
     * 校验验证码
     * @param moblie
     * @param code
     * @return
     */
    public CheckLoginDTO checkLoginCode(String moblie, int code) {
        //参数校验
        if(!StringUtils.hasText(moblie) || code < 1000 || code > 9999){
            log.info("手机号{}或者验证码{}错误", moblie, code);
            return new CheckLoginDTO(false, "参数错误");
        }
        //从redis中比较验证码是否正确
        String smskey = smsCatchKeyBuilder.buildSmsLoginCodeKey(moblie);
        Integer smsCode = (Integer)redisTemplate.opsForValue().get(smskey);
        if (smsCode == null) {
            log.info("手机号{}验证码已过期", moblie);
            return new CheckLoginDTO(false, "验证码已过期");
        }
        if (smsCode.equals(code)) {
            redisTemplate.delete(smskey);
            log.info("手机号{}验证码正确", moblie);
            return new CheckLoginDTO(true, "验证成功");
        } else {
            log.info("手机号{}验证码错误", moblie);
            return new CheckLoginDTO(false, "验证码错误");
        }
    }

    /**
     * 创建Cookies
     * @param userId
     * @return
     */
    public String createCookies(Long userId){
        //生成Token
        String token = UUID.randomUUID().toString();
        //将Token与用户信息绑定
        String userLoginTokenKey = userCatchKeyBuilder.buildUserPhoneLoginKey(token);
        redisTemplate.opsForValue().set(userLoginTokenKey, userId, 1, TimeUnit.DAYS);
        return token;
    }
}
