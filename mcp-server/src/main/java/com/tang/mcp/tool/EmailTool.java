package com.tang.mcp.tool;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

/**
 * ProjectName: SpringAI-MCP-RAG-Dev
 * Package: com.tang.mcp.tool
 * ClassName: EmailTool
 * Author: tmj
 * Date: 2026/6/30 16:04
 * version: 1.0
 * Description:
 */
@Component
@Slf4j
public class EmailTool {

    private final JavaMailSender mailSender;
    private final String from;

    @Autowired
    public EmailTool(JavaMailSender mailSender, @Value("${spring.mail.username}") String from) {
        this.mailSender = mailSender;
        this.from = from;
    }

    @Data
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class EmailRequest{

        @ToolParam(description = "收件人邮箱地址")
        private String email;
        @ToolParam(description = "邮件主题")
        private String subject;
        @ToolParam(description = "邮件内容")
        private String message;
    }

    @Tool(description = "给指定邮箱发送邮件，email为收件人邮箱地址, subject为邮件主题, message为邮件内容")
    public void sendMailMessage(EmailRequest emailRequest){
        log.info("发送邮件: {}", emailRequest.toString());

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);

        try {
            helper.setFrom(from);
            helper.setTo(emailRequest.getEmail());
            helper.setSubject(emailRequest.subject);
            helper.setText(emailRequest.message);

            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
//            throw new RuntimeException(e);
            log.error("发送邮件失败: {}", e.getMessage());
        }


    }
}
