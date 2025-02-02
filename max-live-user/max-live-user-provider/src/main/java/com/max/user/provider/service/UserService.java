package com.max.user.provider.service;

import com.max.common.redis.SMSCatchKeyBuilder;
import com.max.dto.UserDTO;
import com.max.inter.IUserRPCService;
import com.max.live.utils.ConvertBeanUtil;
import com.max.user.provider.entity.SmsDO;
import com.max.user.provider.entity.UserDO;
import com.max.user.provider.mapper.SmsMapper;
import com.max.user.provider.mapper.UserMapper;
import com.max.user.provider.utils.UserRedisKeyBuilder;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class UserService{
    @Autowired
    private UserMapper userMapper;



    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private UserRedisKeyBuilder userRedisKeyBuilder;



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



}
