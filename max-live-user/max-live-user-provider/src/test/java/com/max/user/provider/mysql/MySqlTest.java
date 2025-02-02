package com.max.user.provider.mysql;

import com.max.user.provider.mapper.UserMapper;
import jakarta.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MySqlTest {

    Logger log = LoggerFactory.getLogger(MySqlTest.class);

    @Autowired
    private UserMapper userMapper;

    @Test
    public void test() {
        log.info(userMapper.selectById(1L).toString());
    }
}
