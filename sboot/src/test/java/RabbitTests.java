import com.library.sboot.LibraryApplication;
import com.library.sboot.rabbitmq.Sender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * Created by Administrator on 2018/11/14.
 */
@RunWith(value = SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = LibraryApplication.class)
public class RabbitTests {
    @Autowired
    private Sender sender;

    @Test
    public void sendTest() throws InterruptedException {
        while (true) {
            String msg = new Date().toString();
            sender.send(msg);
            Thread.sleep(1000);
        }
    }
}
