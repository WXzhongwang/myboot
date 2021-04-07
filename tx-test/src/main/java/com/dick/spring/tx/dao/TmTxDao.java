package com.dick.spring.tx.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Map;

/**
 * @author dick <18668485565@163.com>
 * @version V1.0.0
 * @description
 * @date created on 2020/6/15
 */
public class TmTxDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @SuppressWarnings("rawtypes")
    public Map getStudent(int id) {
        String sql = "select * from jdbc_student where id = ?";
        Map map = jdbcTemplate.queryForMap(sql, id);
        System.out.println("student.id=" + id + ": " + map);
        return map;
    }

    public int update(int id, String name){
        String sql = "update jdbc_student set name = ? where id = ?";
        int count = jdbcTemplate.update(sql, name, id);
        System.out.println("sql: " + sql + ", para: " + name + ", " + id);
        System.out.println("Affected rows : " + count);
        return count;
    }

    public int insert(int id, int age){
        String name = "user" + id;
        String sql = "insert into jdbc_student(id, name, age) values (?, ?, ?)";
        int count = jdbcTemplate.update(sql, id, name, age);
        System.out.println("sql: " + sql);
        System.out.println(String.format("para: %d, %s, %d", id, name, age));
        System.out.println("Affected rows : " + count);
        return count;
    }

    public void withTx() {
        DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
        // definition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

        TransactionStatus status = transactionManager.getTransaction(definition);
        System.out.println("事务开始 === 》》》==== withTx start...");
        try{
            //this.insert(15,31);
            //this.getStudent(14);
            this.update(14, "user14-tx-updated");
            this.insert(15,31);
            transactionManager.commit(status);
        }catch(Exception e){
            System.out.println("回滚");
            transactionManager.rollback(status);
        }
        //
        this.getStudent(14);
        this.getStudent(15);
        System.out.println("事务结束 === 》》》==== withTx end...");
    }

    public void withoutTx() {
        System.out.println("withoutTx start...");
        try{
            this.getStudent(14);
            this.update(14, "user14-tx");
            this.insert(15,31);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        //
        this.getStudent(14);
        this.getStudent(15);
        //
        System.out.println("withoutTx end...");
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void withTransactionTemplate() {
        System.out.println("withTransactionTemplate start...");
        final TmTxDao txDao = this;
        transactionTemplate.execute(new TransactionCallback() {
            public Object doInTransaction(TransactionStatus status) {
                txDao.getStudent(14);
                txDao.update(14, "user14-tx-template-updated");
                txDao.insert(15,31);
                return null;
            }
        });
        this.getStudent(14);
        this.getStudent(15);
        System.out.println("withTransactionTemplate end...");
    }

//    @Transactional(rollbackFor = Exception.class)
    @Transactional
    public void saveWithAnotationTx() throws FileNotFoundException {
        System.out.println("注解式事务 start...");
//        try{
//            this.getStudent(14);
//            this.update(14, "user14-tx-15");
//            this.insert(15,31);
//        } catch (Exception ex){
//            System.out.println(ex.getMessage());
//        }
        this.update(14, "user14-tx-test-01");
        FileInputStream fis = new FileInputStream("D://a.txt");
        System.out.println("注解式事务 end...");
    }
}
