package io.gig.catchreview.core.domain.mark.review;

import io.gig.catchreview.core.domain.mark.mark.MarkDetail;
import io.gig.catchreview.core.domain.mark.mark.MarkService;
import io.gig.catchreview.core.domain.mark.review.dto.*;
import io.gig.catchreview.core.domain.user.LoginUser;
import io.gig.catchreview.core.domain.user.member.Member;
import io.gig.catchreview.core.domain.user.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : Jake
 * @date : 2021-11-28
 */
@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewQueryRepository queryRepository;

    private final MemberService memberService;
    private final MarkService markService;


    @Transactional(readOnly = true)
    public Page<ReviewListDto> getReviewPageList(Long markDetailId, PageRequest pageRequest, LoginUser loginUser) {
        Member loginMember = memberService.getUser(loginUser.getUsername());
        return queryRepository.getReviewPageList(markDetailId, pageRequest, loginMember.getId());
    }

    public Long createByMember(@NotNull ReviewCreateForm createForm,
                               String username) {

        Member loginMember = memberService.getUser(username);
        MarkDetail markDetail = markService.getMarkDetailEntity(createForm.getMarkDetailId());

        Review newReview = Review.createByMember(createForm, markDetail, loginMember);
        List<ReviewCard> cardList = getReviewCardList(newReview, createForm.getReviewCardList(), loginMember);
        newReview.addReviewCard(cardList);

        return reviewRepository.save(newReview).getId();
    }

    public List<ReviewCard> getReviewCardList(Review review, List<ReviewCardCreateForm> reviewCardDtoList, Member loginMember) {
        List<ReviewCard> reviewCardList = new ArrayList<>();
        for (ReviewCardCreateForm card : reviewCardDtoList) {
            ReviewCard reviewCard = ReviewCard.createByMember(review, card, loginMember);
            reviewCardList.add(reviewCard);
        }
        return reviewCardList;
    }

    public ReviewDetailDto getReviewDetailDto(Long markDetailId, Long reviewId) {
        return queryRepository.getReviewDetailDto(markDetailId, reviewId);
    }
}
