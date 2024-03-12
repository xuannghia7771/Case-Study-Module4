package com.codegym.case_study_team_2_study.service.account;

import com.codegym.case_study_team_2_study.dto.account.IAccountDto;
import com.codegym.case_study_team_2_study.model.account.Account;
import com.codegym.case_study_team_2_study.repository.account.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.util.Random;

@Service
public class AccountService implements IAccountService {
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Autowired
    IAccountRepository accountRepository;
    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public Page<IAccountDto> searchByEmail(Pageable pageable, String keyword) {
        return accountRepository.findAccountByEmailContaining(pageable, "%" + keyword + "%");
    }

    @Override
    public void deleteAccount(int deleteId) {
        accountRepository.deleteAccount(deleteId);
    }

    @Override
    public void addAccount(Account account) {
        String newPass = passwordEncoder.encode(account.getPassword());
        accountRepository.addAccount(account.getEmail(), newPass, account.isStatus());
    }

    @Override
    public Account findByEmail(String email) {
        return accountRepository.findAccountByEmail(email);
    }

    @Override
    public void change(Account account) {
        accountRepository.save(account);
    }

    @Override
    public boolean testPass(String email, String pass) {
        Account account = accountRepository.findAccountByEmail(email);
        System.out.println(email);
        String oldPassEncoder = account.getPassword();
        boolean isMath = passwordEncoder.matches(pass, oldPassEncoder);
        System.out.println(isMath);
        return isMath;
    }

    @Override
    public void changePass(String email, String newPassword) {
        Account account = accountRepository.findAccountByEmail(email);
        System.out.println(passwordEncoder.encode(newPassword));
        account.setPassword(passwordEncoder.encode(newPassword));
        accountRepository.save(account);
    }

    @Override
    public void sendEmail(String to, String subject, String body) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body, true);
            javaMailSender.send(message);
        } catch (javax.mail.MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String sendEmailAndReturnCode(String to) {
        // Sinh mã ngẫu nhiên
        String code = generateRandomCode(4); // Mã có độ dài 4
        // Tạo nội dung email
        String body = "<fieldset style=\"border: 2px solid #17a2b8 ;color: #00394f;background-image:url('https://i.pinimg.com/1200x/a2/b6/60/a2b66084d37dffa45982a5d477187146.jpg');height: 430px;width:600px\">\n" +
                "    <legend style=\"text-align: center\">\n" +
                "        <h1 style=\"color: #00394f\">Code Panda</h1>\n" +
                "    </legend>\n" +
                "    <legend>\n" +
                "        <h4>\n" +
                "            Chào bạn,\n" +
                "            <br/>\n" +
                "            <br/>\n" +
                "            Chúng tôi rất vui được gửi đến bạn mã xác nhận tài khoản tại Code Panda. <br/><br/>\n" +
                "\n" +
                "            <span style=\"font-weight: bold; color: #008000\"> Đây là mã xác nhận đặc biệt dành riêng cho bạn: " + code + "</span>\n" +
                "        </h4>\n" +
                "        <h4>\n" +
                "            Xin lưu ý không chia sẻ mã này với bất kỳ ai khác để đảm bảo an toàn \n" +
                "        <h4>và bảo mật cho tài khoản của bạn!\n" +
                "        </h4>\n" +
                "        <h4>Mọi thắc mắc hoặc yêu cầu hỗ trợ, hãy liên hệ với chúng tôi.\n" +
                "        <h4>Chúng tôi sẽ sẵn lòng giúp bạn.\n" +
                "        <h4>Xin chân thành cảm ơn và trân trọng!\n" +
                "        <h4>Code Panda\n" +
                "    </legend>\n" +
                "</fieldset>";
        // Cấu hình subject
        String subject = "CODE PANDA - Thay đổi mật khẩu!";
        sendEmail(to, subject, body);
        return code;
    }

    @Override
    public void forgotPassword(Account account) {
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        accountRepository.save(account);
    }

    @Override
    public void createAccount(Account newAccount, int role) {
        String newPass = passwordEncoder.encode(newAccount.getPassword());
        newAccount.setStatus(true);
        accountRepository.createAccount(newAccount.getEmail(), newPass, newAccount.isStatus(), role);
    }

    private String generateRandomCode(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder code = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            code.append(characters.charAt(index));
        }
        return code.toString();
    }
}
