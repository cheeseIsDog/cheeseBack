package cheese.cheese.service;

import cheese.cheese.dto.Enum.Consts;
import cheese.cheese.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Random;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailService {
    private final HashMap<String, String> authNumberMap = new HashMap<>();
    private final JavaMailSender javaMailSender;

    public Boolean sendEmail (String to) throws Exception {
        MimeMessage message = this.createMessage(to);
        Boolean result = false;

        try {
            this.javaMailSender.send(message);
            result = true;
        } catch(MailException es) {
            result = false;
            throw new Exception("Get Exception during sendEmail :" + es.getMessage());
        }

        return result;
    }

    public Boolean sendAuth(UserDto.authEmail dto) throws Exception {
        Boolean result = false;
        if ( this.authNumberMap.get(dto.getEmail()).equals(dto.getAuthNumber())) {
            this.authNumberMap.remove(dto.getEmail());
            result = true;
        }
        return result;
    }

    private MimeMessage createMessage (String to) throws Exception {
        String authNumber = this.createKey();
        this.authNumberMap.put(to, authNumber);

        log.info("보내는 대상 : "+ to);
        log.info("인증번호 : "+ authNumber);
        MimeMessage message = javaMailSender.createMimeMessage();

        message.addRecipients(Message.RecipientType.TO, to);
        message.setSubject("Cheeze 인증번호가 도착했습니다.");

        String msg= Consts.EMAIL_MESSAGE_START + authNumber + Consts.EMAIL_MESSAGE_END;
        message.setText(msg, "utf-8", "html");
        message.setFrom(new InternetAddress("cheesedev4@gmail.com","Cheeze"));

        return message;
    }

    private String createKey() {
        StringBuffer key = new StringBuffer();
        Random rnd = new Random();

        for (int i = 0; i < 8; i++) {
            int index = rnd.nextInt(3);
            switch (index) {
                case 0:
                    key.append((char) ((int) (rnd.nextInt(26)) + 97));
                    break;
                case 1:
                    key.append((char) ((int) (rnd.nextInt(26)) + 65));
                    break;
                case 2:
                    key.append((rnd.nextInt(10)));
                    break;
            }
        }

        return key.toString().toUpperCase();
    }
}
