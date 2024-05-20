/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DAO.AccountDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;
import java.util.Random;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
import model.User;
import view.Login.RegisterForm;
import view.Login.VeriFyMail;
import view.Login.loginAndRegister;


/**
 *
 * @author Admin
 */
public class RegisterController {
    private final RegisterForm registerForm;
    private VeriFyMail veriFyMail;
    private final AccountDAO accountDAO;
    private int code;
    private Thread thrSendmail;
    public RegisterController(RegisterForm register)
    {
        this.registerForm = register;
        accountDAO = new AccountDAO();
        registerForm.setVisible(true);
        registerForm.setActionListenerForRegisterButton(new clickforSubmitBtn());
    }
    
    private void MailVerify(int code,int verifiedCode)
    {
        if(code == verifiedCode)
        {
            JOptionPane.showMessageDialog(registerForm, "Đăng ký thành công");
            this.registerForm.setVisible(false);
            accountDAO.CreateUser(registerForm.getUser());
            java.awt.EventQueue.invokeLater(() -> {
                new LoginController(new loginAndRegister());
            });
        }
    }
    private void SendMail(User newUser, int code)
    {
        String from = "tomato.uit@gmail.com";
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        String username = "tomato.uit@gmail.com";
        String password = "ulxl qlna ktam upbx";
        Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
          try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(newUser.getEmail()));
            message.setSubject("Verify Code");
            message.setText(code + " là mã xác minh tài khoản");
            Transport.send(message);
        } catch (MessagingException e) {
            if (e.getMessage().equals("Invalid Addresses")) {
                JOptionPane.showMessageDialog(registerForm,e.getMessage());
            } else {
                JOptionPane.showMessageDialog(registerForm,"fail to send mail");
            }
        }
    }
    class clickforSubmitBtn implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
             thrSendmail = new Thread(()->{
                Random random = new Random();
                code = random.nextInt(9000) + 1000;
                SendMail( registerForm.getUser(),code);
            });
            thrSendmail.start();
            veriFyMail = new VeriFyMail();
            veriFyMail.setActionListenerForEnterButton(new clickForEntercodeButton());
            registerForm.setComponent(veriFyMail);
        }
      }
    class clickForEntercodeButton implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                thrSendmail.join();
            } catch (InterruptedException ex) {
            }
            MailVerify(Integer.parseInt(veriFyMail.getVerifyCode()), code);
        }
    }
    
}
