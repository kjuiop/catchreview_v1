package io.gig.catchreview.core.domain.mark.mark.dto;

import io.gig.catchreview.core.domain.mark.mark.Mark;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : Jake
 * @date : 2021-11-13
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MarkCoordinateDto {

    private Long markId;

    private String x;

    private String y;

    List<MarkInfoWindowDto> infoWindows = new ArrayList<>();

    public MarkCoordinateDto(Mark m) {
        this.markId = m.getId();
        this.x      = m.getCoordinateX();
        this.y      = m.getCoordinateY();
        this.infoWindows = m.getMarkDetails().stream().map(MarkInfoWindowDto::new).collect(Collectors.toList());
    }
}
