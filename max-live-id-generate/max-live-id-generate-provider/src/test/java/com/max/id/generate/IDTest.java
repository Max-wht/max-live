package com.max.id.generate;

import jakarta.annotation.Resource;
import me.ahoo.cosid.provider.IdGeneratorProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.max.id.generate.utils.IDConstants.ID_SEQUENCE;
import static com.max.id.generate.utils.IDConstants.ID_SNOWFLAKE;

/**
 * @author Max
 * @description
 * @date 2025/2/7 12:06
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class IDTest {


    @Resource
    private IdGeneratorProvider idGeneratorProvider;

    @Test
    public void idTest() {
        for (int i = 0; i < 3; i++) {
            System.out.println(idGeneratorProvider.getRequired(ID_SNOWFLAKE).generate());
            System.out.println(idGeneratorProvider.getRequired(ID_SEQUENCE).generate());
        }
    }
}
