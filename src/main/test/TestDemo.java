import com.sofency.ticket.dto.ResultMsg;
import com.sofency.ticket.enums.Code;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author sofency
 * @date 2020/5/2 23:21
 * @package IntelliJ IDEA
 * @description
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext-*.xml")
public class TestDemo {
    @Autowired
    RedisTemplate<String,Object> redisTemplate;

    @Test
    public void RedisTest(){

    }
}
