import com.dubbo.demo.ConsumerApplication;
import com.dubbo.demo.PayFacade;
import com.dubbo.entity.LockerCas;
import org.apache.dubbo.config.annotation.DubboReference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ConsumerApplication.class})
public class BizDubbo {

    private static final Logger logger = LoggerFactory.getLogger(BizDubbo.class);

    @DubboReference(check = false)
    PayFacade payFacade;

    @Test
    public void recvPay(){
        logger.info("****test process");
        LockerCas lockerCas = payFacade.recvPay("10");
        logger.info(lockerCas.toString());
    }
}
