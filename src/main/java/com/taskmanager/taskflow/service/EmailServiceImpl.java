package com.taskmanager.taskflow.service;

import com.taskmanager.taskflow.model.User;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender emailSender;

    public void sendConfirmationMessage(User user, String linkConfirmation) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(user.getEmail());
        helper.setSubject("[CONFIRMAÇÃO DE CONTA] - TaskFlow");
        helper.setText(getHtmlContent(user.getFullName().split(" ")[0], linkConfirmation), true);
        System.out.println(linkConfirmation);
        emailSender.send(message);
    }

    private String getHtmlContent(String firstName, String confirmationLink) {
        String htmlTemplate = """
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Confirmação de Conta</title>
    <style>
        body {
            font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif;
            background-color: #f6f6f6;
            margin: 0;
            padding: 0;
        }
        .container {
            width: 100%;
            max-width: 600px;
            margin: 0 auto;
            background-color: #ffffff;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            margin-top: 50px;
        }
        .header {
            text-align: center;
            padding: 20px 0;
            background-color: #007BFF;
            border-radius: 10px 10px 0 0;
            color: #ffffff;
        }
        .header h1 {
            margin: 0;
            font-size: 24px;
        }
        .content {
            padding: 20px;
            text-align: center;
        }
        .content p {
            font-size: 16px;
            color: #333333;
            line-height: 1.5;
        }
        .button {
            display: inline-block;
            padding: 12px 24px;
            font-size: 16px;
            color: #ffffff;
            background-color: #28a745;
            border-radius: 5px;
            text-decoration: none;
            margin-top: 20px;
        }
        .footer {
            text-align: center;
            margin-top: 30px;
            font-size: 14px;
            color: #999999;
        }
        .footer p {
            margin: 0;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="header">
            <h1>Bem-vindo ao TaskFlow!</h1>
        </div>
        <div class="content">
            <p>Olá ${first_name},</p>
            <p>Obrigado por se registrar em nosso serviço. Para concluir seu registro, por favor, confirme sua conta clicando no botão abaixo:</p>
            <a href="http://127.0.0.1:8080/user/activation/${confirmation_link}" class="button">Confirmar Conta</a>
            <p>Se o botão acima não funcionar, copie e cole o seguinte link no seu navegador:</p>
            <p>localhost:8080/user/activation/${confirmation_link}</p>
        </div>
        <div class="footer">
            <p>Se você não solicitou esta confirmação, ignore este e-mail.</p>
        </div>
    </div>
</body>
</html>
""";
        htmlTemplate = htmlTemplate.replace("${confirmation_link}", confirmationLink);
        htmlTemplate = htmlTemplate.replace("${first_name}", firstName);
        return htmlTemplate;
    }
}
