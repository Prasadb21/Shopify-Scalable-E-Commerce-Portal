package com.SpringBoot.Ecomm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(String toName , String name , String course, double amount)
    {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setTo(toName);
        mailMessage.setSubject("✅ Payment updated successfully for Order ID: "+course);
        mailMessage.setText(
                "Hi " + name + ",\n\n" +
                        "🎉 Thank you for enrolling in *" + course + "*!\n\n" +
                        "We’ve successfully received your payment of ₹" + amount + ".\n\n" +
                        "🚀 You're officially enrolled in the **CloudPro 1.0: Azure Cloud Engineer Bootcamp**.\n" +
                        "This program is designed to make you job-ready with hands-on Azure labs, cloud projects, certification assistance, and more.\n\n" +
                        "📅 Course Duration: 3 Months\n" +
                        "🎓 Live Hours: 100+ with top cloud experts\n" +
                        "🧠 Features: Resume building, mock interviews, and 24/7 doubt-solving support\n\n" +
                        "If you have any questions, just reply to this email — we’re here to help!\n\n" +
                        "All the best,\n" +
                        "Team CodeSpark ☁️"
        );



        javaMailSender.send(mailMessage);


    }

}
