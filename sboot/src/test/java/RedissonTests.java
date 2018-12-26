import com.library.sboot.LibraryApplication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName: RedissonTests
 * @Description: 模拟秒杀场景
 * @author feifei.liu
 * @date 2018/12/26 15:10
 */
@RunWith(value = SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = LibraryApplication.class)
public class RedissonTests {
    @Autowired
    Redisson redisson;
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    String lockKey = "testRedisson";//分布式锁的key

    @Test
    public void addGoods() {
        //设置一个key，goods商品的库存数量为100
        stringRedisTemplate.opsForValue().set("goods", "100");
        Assert.assertEquals("100", stringRedisTemplate.opsForValue().get("goods"));
    }

    @Test
    public void secKill() {
        class Run1 implements Runnable {
            @Override
            public void run() {
                //执行的业务代码
                for (int i = 0; i < 55; i++) {
                    RLock lock = redisson.getLock(lockKey);
                    lock.lock(60, TimeUnit.SECONDS); //设置60秒自动释放锁（默认是30秒自动过期）
                    int stock = Integer.parseInt(stringRedisTemplate.opsForValue().get("goods").toString());
                    if (stock > 0) {
                        stringRedisTemplate.opsForValue().set("goods", (stock-1)+"");
                        System.out.println("test1_:lockKey:"+lockKey+",stock:"+(stock-1));
                    }
                    lock.unlock();
                }
            }
        }
        class Run2 implements Runnable {
            @Override
            public void run() {
                //执行的业务代码
                for (int i = 0; i < 55; i++) {
                    RLock lock = redisson.getLock(lockKey);
                    lock.lock(60, TimeUnit.SECONDS); //设置60秒自动释放锁（默认是30秒自动过期）
                    int stock = Integer.parseInt(stringRedisTemplate.opsForValue().get("goods").toString());
                    if (stock > 0) {
                        stringRedisTemplate.opsForValue().set("goods", (stock-1)+"");
                        System.out.println("test2_:lockKey:"+lockKey+",stock:"+(stock-1));
                    }
                    lock.unlock();
                }
            }
        }
        Thread thread1 = new Thread(new Run1(), "run1");
        Thread thread2 = new Thread(new Run2(), "run2");

        thread1.start();
        thread2.start();
    }
}
