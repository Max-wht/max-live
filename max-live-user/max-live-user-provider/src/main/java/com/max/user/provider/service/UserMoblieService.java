package com.max.user.provider.service;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.max.common.redis.UserCatchKeyBuilder;
import com.max.dto.UserDTO;
import com.max.dto.UserLoginDTO;
import com.max.dto.UserPhoneDTO;
import com.max.live.CommonStatusEnum;
import com.max.live.utils.ConvertBeanUtil;
import com.max.user.provider.entity.UserPhoneDO;
import com.max.user.provider.mapper.UserMapper;
import com.max.user.provider.mapper.UserPhoneMapper;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

@Service
public class UserMoblieService {
    @Resource
    private UserCatchKeyBuilder userCatchKeyBuilder;
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @Resource
    private UserPhoneMapper userPhoneMapper;
    @Resource
    private UserService userService;
    public UserLoginDTO login(String moblie) {
        //参数校验
        if(!StringUtils.hasText(moblie)){
            return null;
        }
        //检查手机号是否注册过
        UserPhoneDTO user =this.queryUserByMoblie(moblie);
        //如果注册过，就生成cookie
        if(null != user){
            return UserLoginDTO.UserLoginSuccess(user.getUserId());
        }
        //如果没有注册过，需要生成user记录，插入手机号绑定
        return registerAndLogin(moblie);
    }

    private UserLoginDTO registerAndLogin(String moblie) {
        //生成用户信息，并绑定手机号码(DB)
        UserLoginDTO userLoginDTO =userService.generateDefaultUserByMoblie(moblie);

        //删除缓存
        redisTemplate.delete(userCatchKeyBuilder.buildUserPhoneLoginKey(moblie));

        return userLoginDTO;
    }

    private UserPhoneDTO queryUserByMoblie(String moblie) {
        //通过redis保护mysql的流量
        //获得与手机号相关的一个key
        String userPhonDTOKey = userCatchKeyBuilder.buildUserPhoneLoginKey(moblie);

        UserPhoneDTO userPhoneDTO = (UserPhoneDTO)redisTemplate.opsForValue().get(userPhonDTOKey);
        if(null != userPhoneDTO){
            return userPhoneDTO;
        }
        //如果redis没有，就从数据库中查

        //1.数据库有
        userPhoneDTO = this.queryByUserPhoneFromDB(moblie);
        if(userPhoneDTO != null){
            redisTemplate.opsForValue().set(userPhonDTOKey, userPhoneDTO, 30, TimeUnit.MINUTES);
            return userPhoneDTO;
        }
        //2.数据库无，设置空值
        userPhoneDTO = new UserPhoneDTO();
        userPhoneDTO.setId(-10L);
        redisTemplate.opsForValue().set(userPhonDTOKey, userPhoneDTO, 5, TimeUnit.MINUTES);
        return null;
    }

    private UserPhoneDTO queryByUserPhoneFromDB(String moblie) {

        LambdaQueryWrapper<UserPhoneDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserPhoneDO::getPhone, moblie);//getPhone == moblie
        wrapper.eq(UserPhoneDO::getStatus, CommonStatusEnum.VALID_STATUS.getCode());//getStatus == VALID_STSTUS.getCode
        wrapper.last( "limit 1");

        UserPhoneDO userPhoneDO = userPhoneMapper.selectOne(wrapper);
        return ConvertBeanUtil.convert(userPhoneDO, UserPhoneDTO.class);
    }
}
