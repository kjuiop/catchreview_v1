package io.gig.cathreview.web.eventHandler;

import io.gig.catchreview.core.domain.email.EmailDto;
import io.gig.catchreview.core.domain.email.EmailSender;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author : Jake
 * @date : 2021-12-03
 */
@Component
@RequiredArgsConstructor
public class MailEventHandler {

    private final EmailSender emailSender;

    @Async
    @EventListener
    public void sendEmail(EmailDto emailDto) {
        try {
            emailSender.send(emailDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
