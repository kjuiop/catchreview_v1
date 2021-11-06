package io.gig.catchreview.core.domain.common.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author : Jake
 * @date : 2021-08-26
 */
@Getter
@Setter
public class BaseSearchDto {
    private int page = 0;
    private int size = 10;
    private String keyword;
}
