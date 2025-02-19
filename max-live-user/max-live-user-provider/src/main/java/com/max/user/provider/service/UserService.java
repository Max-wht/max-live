package com.max.user.provider.service;

import com.max.common.redis.SMSCatchKeyBuilder;
import com.max.common.redis.UserCatchKeyBuilder;
import com.max.dto.UserDTO;
import com.max.dto.UserLoginDTO;
import com.max.id.inter.IGenerateIDRPCService;
import com.max.inter.IUserRPCService;
import com.max.live.utils.ConvertBeanUtil;
import com.max.user.provider.entity.SmsDO;
import com.max.user.provider.entity.UserDO;
import com.max.user.provider.entity.UserPhoneDO;
import com.max.user.provider.mapper.SmsMapper;
import com.max.user.provider.mapper.UserMapper;
import com.max.user.provider.mapper.UserPhoneMapper;
import com.max.user.provider.utils.UserRedisKeyBuilder;
import jakarta.annotation.Resource;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author Max
 * @date 2023/8/16 16:06
 * @description
 */
@Service
public class UserService{
    @Autowired
    private UserMapper userMapper;

    @DubboReference
    private IGenerateIDRPCService generateIDRPCService;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private UserRedisKeyBuilder userRedisKeyBuilder;

    @Resource
    private UserPhoneMapper userPhoneMapper;

    @Resource
    private UserCatchKeyBuilder userCatchKeyBuilder;

    Logger log = LoggerFactory.getLogger(UserService.class);
    /**
     * 根据id查询用户信息
     */
    public UserDTO getUserById(Long userId) {
        //校验数据
        if(userId == null || userId < 0){
            return null;
        }
        UserDO userDO;
        String userKey = userRedisKeyBuilder.buildUserKey(userId);
        //如果Redis中存在该用户信息，则从Redis中获取，否则从数据库中获取
        UserDTO userDTO = (UserDTO)redisTemplate.opsForValue().get(userKey);
        if(userDTO != null && userDTO.getUserId() >= 0){
            return userDTO;
        }else if(userDTO != null && userDTO.getUserId() < 0){
            return null;
        }
        //userDTO == null 的情况，则从数据库中获取用户信息
        userDO = userMapper.selectById(userId);
        if(userDO != null){ //如果数据库中存在该用户信息，则将用户信息添加到Redis中并返回
            userDTO = ConvertBeanUtil.convert(userDO, UserDTO.class);
            //将用户数据添加到Redis中
            redisTemplate.opsForValue().set(userKey, userDTO, 30, TimeUnit.MINUTES);
            return userDTO;
        }else {
            UserDTO noExistUser = new UserDTO();
            noExistUser.setUserId(-10L);
            noExistUser.setNickName("该用户不存在");
            //将这个UserKey添加一个空值，防止缓存穿透
            redisTemplate.opsForValue().set(userKey, noExistUser, 20, TimeUnit.MINUTES);
        }
        //如果数据库中不存在该用户信息，则返回null
        //缓存击穿问题 1.布隆过滤器 2.设置空值
        return null;
    }


    /**
     * 根据手机号生成默认用户信息，并且绑定手机号码
     * @param moblie
     * @return
     */
    public UserLoginDTO generateDefaultUserByMoblie(String moblie) {
        UserDO userDO = new UserDO();
        //主键分配
        Long userId = generateIDRPCService.gerSeqId();//通过Dubbo调用id-generate服务生成主键

        userDO.setUserId(userId);
        userDO.setNickName("新用户-"+ userId);
        userMapper.insert(userDO);

        UserPhoneDO userPhoneDO = new UserPhoneDO();
        userPhoneDO.setUserId(userId);
        userPhoneDO.setPhone(moblie);
        userPhoneDO.setStatus(1);
        userPhoneMapper.insert(userPhoneDO);

        return UserLoginDTO.UserLoginSuccess(userId);
    }

    public Boolean checkToken(String token) {
        //TODO token查询
        String tokenKey = userCatchKeyBuilder.buildUserPhoneLoginKey(token);
        return redisTemplate.hasKey(tokenKey);
    }
}
