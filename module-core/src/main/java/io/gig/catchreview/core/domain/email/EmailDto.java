package io.gig.catchreview.core.domain.email;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;

/**
 * @author : Jake
 * @date : 2021/10/07
 */
@Builder
@Getter
public class EmailDto {

    private String receiver;

    private EmailInfo info;

    private Map<String, Object> payload;

    public static EmailDto create(String receiver, EmailInfo info, Map<String, Object> payload) {
        return EmailDto.builder()
                .receiver(receiver)
                .info(info)
                .payload(payload)
                .build();
    }

}
