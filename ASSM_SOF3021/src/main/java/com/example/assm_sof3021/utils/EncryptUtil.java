package com.example.assm_sof3021.utils;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.context.annotation.Bean;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

public class EncryptUtil {
    public static String mhPass(String pass) {
        String encrypted = BCrypt.hashpw(pass,
                BCrypt.gensalt());

        return encrypted;
    }

    public static boolean checkpass(
            String pass, String passnew) {
        return BCrypt.checkpw(pass, passnew);
    }
//    @Bean
//    public JavaMailSender getJavaMailSender() {
//        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//        mailSender.setHost("smtp.gmail.com");
//        mailSender.setPort(587);
//
//        mailSender.setUsername("cuongndph14605@fpt.edu.vn");
//        mailSender.setPassword("Duycuong1");
//
//        Properties props = mailSender.getJavaMailProperties();
//        props.put("mail.transport.protocol", "smtp");
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.debug", "true");

//        return mailSender;
//    }
}
