package io.gig.catchreview.core.domain.mark.mark;

import io.gig.catchreview.core.domain.mark.mark.dto.MarkCoordinateDto;
import io.gig.catchreview.core.domain.mark.mark.dto.MarkCreateForm;
import io.gig.catchreview.core.domain.mark.mark.dto.MarkDetailDto;
import io.gig.catchreview.core.domain.user.member.Member;
import io.gig.catchreview.core.domain.user.member.MemberService;
import io.gig.catchreview.core.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

/**
 * @author : Jake
 * @date : 2021-11-13
 */
@Service
@RequiredArgsConstructor
public class MarkService {

    private final MarkRepository markRepository;
    private final MarkQueryRepository queryRepository;

    private final MarkDetailRepository markDetailRepository;

    private final MemberService memberService;

    @Transactional
    public Long createByMember(@NotNull MarkCreateForm createForm,
                               String username) {

        Member loginMember = memberService.getUser(username);
        Mark newMark = Mark.createByMember(createForm, loginMember);
        MarkDetail newMarkDetail = MarkDetail.createByMember(createForm, newMark, loginMember);

        newMark.addMarkDetail(newMarkDetail);

        return markRepository.save(newMark).getId();
    }

    @Transactional(readOnly = true)
    public MarkDetailDto getMarkDetailDto(Long markDetailId) {
        return queryRepository.getMarkDetailDto(markDetailId);
    }

    public MarkDetail getMarkDetailEntity(Long markDetailId) {

        Optional<MarkDetail> findMarkDetail = markDetailRepository.findById(markDetailId);

        if (findMarkDetail.isEmpty()) {
            throw new NotFoundException("마크 상세 내용을 찾을 수 없습니다.");
        }

        return findMarkDetail.get();
    }

    @Transactional(readOnly = true)
    public List<MarkCoordinateDto> getMarkListForMap() {
        return queryRepository.getMarkListForMap();
    }
}
