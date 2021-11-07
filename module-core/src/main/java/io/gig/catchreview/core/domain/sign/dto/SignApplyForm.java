package io.gig.catchreview.core.domain.sign.dto;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author : Jake
 * @date : 2021-11-07
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignApplyForm {

    @NotEmpty
    private String title;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDateTime startDateTime;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDateTime endDateTime;

    private int point;

    private String content;

    private String note;
}
