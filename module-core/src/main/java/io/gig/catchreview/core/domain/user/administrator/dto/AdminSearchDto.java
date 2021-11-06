package io.gig.catchreview.core.domain.user.administrator.dto;

import io.gig.catchreview.core.domain.common.dto.BaseSearchDto;
import io.gig.catchreview.core.domain.user.UserStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

/**
 * @author : Jake
 * @date : 2021-08-26
 */
@Getter
@Setter
@NoArgsConstructor
public class AdminSearchDto extends BaseSearchDto {

    private String username;

    private String name;

    private UserStatus userStatus;

    public PageRequest getPageableWithSort() {
        return PageRequest.of(getPage(), getSize(), Sort.by(new Sort.Order(Sort.Direction.DESC, "id")));
    }
}
