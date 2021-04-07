package com.mail.test;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import sun.misc.BASE64Encoder;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author dick <18668485565@163.com>
 * @version V1.0.0
 * @description 党员政治生日定时任务
 * @date created on 2020/5/15
 */
@Component
@EnableScheduling
public class PartyMembersPoliticalBirthdayTask {


    private final Logger LOGGER = LoggerFactory.getLogger(PartyMembersPoliticalBirthdayTask.class);

    @Autowired
    private MemberListUtil memberListUtil;


    @Value("${email.sender}")
    private String sender;

    @Autowired
    JavaMailSender javaMailSender;


    /**
     * 每月1号8点执行
     */
    //@Scheduled(cron = "0 0 8 1 * ?")
    @Scheduled(cron = "0 13 10 * * ?")
    public void configureTasks() {
        LOGGER.info(">>>>>>>>>>>=========任务启动=========<<<<<<<<<<<");
        for (Member member : this.memberListUtil.getMemberList()) {
            if(compareMonth(member.getBirthday())){
                sendEmail(member);
            }
        }
        LOGGER.info(">>>>>>>>>>>=========任务结束=========<<<<<<<<<<<");
    }

    private boolean compareMonth(Date d1){
        SimpleDateFormat fmt = new SimpleDateFormat("MM");
        return fmt.format(d1).equals(fmt.format(new Date()));
    }

    private void sendEmail(Member member){
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();


        MimeMessageHelper helper = null;
        try {
            // 防止成为垃圾邮件，披上outlook的马甲
            mimeMessage.addHeader("X-Priority", "3");
            mimeMessage.addHeader("X-MSMail-Priority", "Normal");
            mimeMessage.addHeader("X-Mailer", "Microsoft Outlook Express 6.00.2900.2869");
            mimeMessage.addHeader("X-MimeOLE", "Produced By Microsoft MimeOLE V6.00.2900.2869");
            mimeMessage.addHeader("ReturnReceipt", "1");

            helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(sender);
            helper.setTo(member.getEmail());
            List<String> ccList= new ArrayList<String>();
            String[] emailCCList;
            for (Member u: memberListUtil.getMemberList()) {
                if(!member.getEmail().equalsIgnoreCase(u.getEmail())){
                    ccList.add(u.getEmail());
                }
            }
            emailCCList = ccList.toArray(new String[ccList.size()]);
            helper.setSubject("党员政治生日祝福-" + member.getUsername());
            helper.setCc(emailCCList);
            helper.setSentDate(new Date());
            freemarker.template.Configuration configuration = new freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_0);
            // 配置模板位置
            ClassLoader loader = MailTestApplication.class.getClassLoader();
            configuration.setClassLoaderForTemplateLoading(loader, "templates");
            //加载模板
            Template template = null;
            try {
                File file = ResourceUtils.getFile("classpath:images/image.png");
                String imgBase64 = imageToBase64(file);
                member.setImgBase64(imgBase64);
                template = configuration.getTemplate("main.ftl");
                StringWriter out = new StringWriter();
                template.process(member, out);
                helper.setText(out.toString(),true);
                javaMailSender.send(mimeMessage);
            } catch (IOException | TemplateException e) {
                e.printStackTrace();
                LOGGER.error(e.getMessage());
            }
        } catch (MessagingException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
    }

    private String imageToBase64(File file){
        byte[] data = null;
        try {
            FileInputStream in = new FileInputStream(file);
            data = new byte[in.available()];
            in.read(data);
            in.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);
    }

}
