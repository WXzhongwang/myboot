import com.dick.spring.tx.dao.TmTxDao;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.FileNotFoundException;

/**
 * @author dick <18668485565@163.com>
 * @version V1.0.0
 * @description
 * @date created on 2020/6/15
 */
public class TxTests {

    @Test
    public void test() {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("jdbc-tx.xml");
        TmTxDao tmTxDao = (TmTxDao) context.getBean("tmTxDao");


        //tmTxDao.withTx();

        //tmTxDao.withoutTx();

        //tmTxDao.withTransactionTemplate();

        try {
            tmTxDao.saveWithAnotationTx();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        context.close();
    }
}
