package io.gig.catchreview.core.domain.email;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Locale;

/**
 * @author : Jake
 * @date : 2021/10/06
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class EmailSender {

    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;

    public void send(EmailDto dto) throws Exception {

//        MessageSendResponseDto response = new MessageSendResponseDto();
        if (dto != null) {
            if (dto.getPayload() == null)
                throw new Exception(">>>>>ExtraData Not Found<<<<<");

            final Context context = new Context(Locale.KOREA);
            String title = dto.getInfo().getTitle();
            for (String key : dto.getPayload().keySet()) {
                context.setVariable(key, dto.getPayload().get(key));
            }
//            context.setVariable("domain", domain);
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();

            try {
                MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
                mimeMessageHelper.setFrom(new InternetAddress("noreply@catchreview.io", "CatchReview", "UTF-8"));
                mimeMessageHelper.setTo(dto.getReceiver().split(","));
                mimeMessageHelper.setSubject(title);

                /**
                 * Thymeleaf Mail Form.
                 */

                String template = "email_templates/" + dto.getInfo().getTemplate();

                final String contextMessage = templateEngine.process(template, context);
                mimeMessageHelper.setText(contextMessage, true);
                javaMailSender.send(mimeMessage);

            } catch (Exception multiException) {
                log.error(multiException.getMessage(), multiException);
            }
        }
    }


}
