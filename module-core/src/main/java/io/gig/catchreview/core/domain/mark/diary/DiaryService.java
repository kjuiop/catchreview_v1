package io.gig.catchreview.core.domain.mark.diary;

import io.gig.catchreview.core.domain.mark.diary.dto.DiaryCreateForm;
import io.gig.catchreview.core.domain.mark.diary.dto.DiaryDetailDto;
import io.gig.catchreview.core.domain.mark.diary.dto.DiaryListDto;
import io.gig.catchreview.core.domain.mark.mark.MarkDetail;
import io.gig.catchreview.core.domain.mark.mark.MarkService;
import io.gig.catchreview.core.domain.user.LoginUser;
import io.gig.catchreview.core.domain.user.member.Member;
import io.gig.catchreview.core.domain.user.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;

/**
 * @author : Jake
 * @date : 2021-11-14
 */
@Service
@RequiredArgsConstructor
public class DiaryService {

    private final DiaryRepository diaryRepository;
    private final DiaryQueryRepository queryRepository;

    private final MemberService memberService;
    private final MarkService markService;

    @Transactional
    public Long createByMember(@NotNull DiaryCreateForm createForm,
                               String username) {

        Member loginMember = memberService.getUser(username);
        MarkDetail markDetail = markService.getMarkDetailEntity(createForm.getMarkDetailId());
        Diary newDiary = Diary.createByMember(createForm, markDetail, loginMember);

        return diaryRepository.save(newDiary).getId();
    }

    @Transactional(readOnly = true)
    public Page<DiaryListDto> getDiaryPageList(Long markDetailId, PageRequest pageRequest, LoginUser loginUser) {
        Member loginMember = memberService.getUser(loginUser.getUsername());
        return queryRepository.getDiaryPageList(markDetailId, pageRequest, loginMember.getId());
    }

    @Transactional(readOnly = true)
    public DiaryDetailDto getDiaryDetailDto(Long markDetailId, Long diaryId) {
        return queryRepository.getDiaryDetailDto(markDetailId, diaryId);
    }
}
