package com.mail.test;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ResourceUtils;
import sun.misc.BASE64Encoder;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailTestApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Autowired
	JavaMailSender javaMailSender;
	@Test
	public void sendSimpleMail() {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setSubject("这是一封测试邮件");
		message.setFrom("shengwangzhong@hengtiansoft.com");
		message.setTo("shengwangzhong@hengtiansoft.com");
		message.setCc("shengwangzhong@hengtiansoft.com");
		message.setBcc("shengwangzhong@hengtiansoft.com");
		message.setSentDate(new Date());
		message.setText("这是测试邮件的正文");
		javaMailSender.send(message);
	}

	@Test
	public void sendAttachFileMail() throws javax.mail.MessagingException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);
		helper.setSubject("这是一封测试邮件");
		helper.setFrom("shengwangzhong@hengtiansoft.com");
		helper.setTo("shengwangzhong@hengtiansoft.com");
		helper.setCc("shengwangzhong@hengtiansoft.com");
		helper.setBcc("shengwangzhong@hengtiansoft.com");
		helper.setSentDate(new Date());
		helper.setText("这是测试邮件的正文");
		helper.addAttachment("javaboy.jpg",new File("C:\\Users\\shengwangzhong\\Desktop\\钟晨曦出生证明.pdf"));
		javaMailSender.send(mimeMessage);
	}



	@Test
	public void sendImgResMail() throws javax.mail.MessagingException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
		helper.setFrom("shengwangzhong@hengtiansoft.com");
		helper.setTo("shengwangzhong@hengtiansoft.com");
		helper.setCc("shengwangzhong@hengtiansoft.com");
		helper.setBcc("shengwangzhong@hengtiansoft.com");
		helper.setSentDate(new Date());
		helper.setText("<p>hello 大家好，这是一封测试邮件，这封邮件包含两种图片，分别如下</p><p>第一张图片：</p><img src='cid:p01'/><p>第二张图片：</p><img src='cid:p02'/>",true);
		helper.addInline("p01",new FileSystemResource(new File("C:\\Users\\shengwangzhong\\Desktop\\钟晨曦出生证明.pdf")));
		helper.addInline("p02",new FileSystemResource(new File("C:\\Users\\shengwangzhong\\Desktop\\钟晨曦出生证明.pdf")));
		javaMailSender.send(mimeMessage);
	}

	@Test
	public void sendFreemarkerMail() throws IOException, TemplateException, javax.mail.MessagingException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
		helper.setFrom("shengwangzhong@hengtiansoft.com");
		helper.setTo("shengwangzhong@hengtiansoft.com");
		helper.setCc("shengwangzhong@hengtiansoft.com");
		helper.setBcc("shengwangzhong@hengtiansoft.com");
		helper.setSentDate(new Date());
		//构建 Freemarker 的基本配置
		Configuration configuration = new Configuration(Configuration.VERSION_2_3_0);
		// 配置模板位置
		ClassLoader loader = MailTestApplication.class.getClassLoader();
		configuration.setClassLoaderForTemplateLoading(loader, "templates");
		//加载模板
		Template template = configuration.getTemplate("main.ftl");
		Member member = new Member();
		member.setUsername("dick");
		member.setEmail("shengwangzhong@hengtiansoft.com");
		member.setBirthday(new Date());
		File file = ResourceUtils.getFile("classpath:images/image.png");
		String imgBase64 = this.imageToBase64(file);
		member.setImgBase64(imgBase64);
		template = configuration.getTemplate("main.ftl");
		StringWriter out = new StringWriter();
		//模板渲染，渲染的结果将被保存到 out 中 ，将out 中的 html 字符串发送即可
		template.process(member, out);
		System.out.println(imgBase64);
		helper.setText(out.toString(),true);
		javaMailSender.send(mimeMessage);
		//模板渲染，渲染的结果将被保存到 out 中 ，将out 中的 html 字符串发送即可
		template.process(member, out);
		helper.setText(out.toString(),true);
		javaMailSender.send(mimeMessage);
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
