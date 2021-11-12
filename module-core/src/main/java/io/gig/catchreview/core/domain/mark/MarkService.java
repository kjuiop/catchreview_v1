package io.gig.catchreview.core.domain.mark;

import io.gig.catchreview.core.domain.mark.dto.MarkCreateForm;
import io.gig.catchreview.core.domain.menu.Menu;
import io.gig.catchreview.core.domain.menu.dto.MenuCreateForm;
import io.gig.catchreview.core.domain.user.member.Member;
import io.gig.catchreview.core.domain.user.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;

/**
 * @author : Jake
 * @date : 2021-11-13
 */
@Service
@RequiredArgsConstructor
public class MarkService {

    private final MarkRepository markRepository;
    private final MemberService memberService;

    @Transactional
    public Long createByMember(@NotNull MarkCreateForm createForm,
                               String username) {

        Member loginMember = memberService.getUser(username);
        Mark newMark = Mark.createByMember(createForm, loginMember);

        return markRepository.save(newMark).getId();
    }
}
