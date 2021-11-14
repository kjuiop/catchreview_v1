package io.gig.catchreview.core.domain.mark.dto;

import io.gig.catchreview.core.domain.mark.types.MarkType;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
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
public class MarkCreateForm {

    @NotEmpty
    private String title;

    @NotEmpty
    private String coordinateX;

    @NotEmpty
    private String coordinateY;

    private MarkType markType;

    private String bannerImg;

    private String shortDescription;

    private String content;

    private String zipCode;

    private String address;

    private String addressDetail;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDateTime startDateTime;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDateTime endDateTime;

    private int point;

    public MarkCreateForm initCreateForm(String x, String y) {
        return MarkCreateForm.builder()
                .coordinateX(x)
                .coordinateY(y)
                .build();
    }
}
