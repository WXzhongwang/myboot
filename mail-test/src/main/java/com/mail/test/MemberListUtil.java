package com.mail.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @author dick <18668485565@163.com>
 * @version V1.0.0
 * @description Member List
 * @date created on 2020/5/18
 */
@Component
@XmlRootElement(name = "members")
public class MemberListUtil {

    private final Logger LOGGER = LoggerFactory.getLogger(MemberListUtil.class);

    private List<Member> memberList;

    @XmlElement(name = "member")
    public List<Member> getMemberList() {
        return memberList;
    }

    public void setMemberList(List<Member> memberList) {
        this.memberList = memberList;
    }

    @Value("${email.file.name}")
    private String fileName;


    @PostConstruct
    public void init() {
        try {
            ClassPathResource classPathResource = new ClassPathResource(fileName);
            JAXBContext context = JAXBContext.newInstance(MemberListUtil.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Object unmarshal = unmarshaller.unmarshal(
                    classPathResource.getFile());
            MemberListUtil memberListUtil = (MemberListUtil) unmarshal;
            List<Member> list = memberListUtil.getMemberList();
            memberList = list;
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("人员名单解析失败"+ e.getMessage());
        }
    }
}
