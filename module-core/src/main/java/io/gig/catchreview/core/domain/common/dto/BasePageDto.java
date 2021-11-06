package io.gig.catchreview.core.domain.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;

/**
 * @author : Jake
 * @date : 2021/08/27
 */
@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor
public class BasePageDto {

    private int page = 0;

    private int size = 10;

    public PageRequest getPageRequest() {
        return PageRequest.of(this.page, this.size);
    }

}
