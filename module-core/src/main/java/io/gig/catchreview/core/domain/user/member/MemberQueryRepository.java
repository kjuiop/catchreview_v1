package io.gig.catchreview.core.domain.user.member;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.gig.catchreview.core.domain.user.LoginUser;
import io.gig.catchreview.core.domain.user.UserStatus;
import io.gig.catchreview.core.domain.user.member.dto.MemberDetailDto;
import io.gig.catchreview.core.domain.user.member.dto.MemberListDto;
import io.gig.catchreview.core.domain.user.member.dto.MemberSearchDto;
import io.gig.catchreview.core.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;

import static io.gig.catchreview.core.domain.user.member.QMember.member;


/**
 * @author : Jake
 * @date : 2021/08/27
 * @author : Jake
 * @date : 2021-09-02
 *
 */
@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberQueryRepository {

    private final JPAQueryFactory queryFactory;


    public Page<MemberListDto> getMemberPageListBySearch(MemberSearchDto searchDto) {

        BooleanBuilder where = new BooleanBuilder();
        where.and(eqUsername(searchDto.getUsername()));
        where.and(eqNickname(searchDto.getNickname()));
        where.and(eqUserStatus(searchDto.getUserStatus()));


        JPAQuery<MemberListDto> contentQuery = this.queryFactory
                .select(Projections.constructor(MemberListDto.class,
                        member))
                .from(member)
                .where(where)
                .limit(searchDto.getPageableWithSort().getPageSize())
                .offset(searchDto.getPageableWithSort().getOffset());

        JPAQuery<Long> countQuery = this.queryFactory.selectDistinct(member.id)
                .from(member)
                .where(where);

        long total = countQuery.fetchCount();
        List<MemberListDto> content = contentQuery.fetch();

        return new PageImpl<>(content, searchDto.getPageableWithSort(), total);
    }

    public Optional<MemberDetailDto> getDetailDto(Long id) {

        Optional<MemberDetailDto> fetch = Optional.ofNullable(this.queryFactory
            .select(Projections.constructor(MemberDetailDto.class,
                    member))
                .from(member)
                .where(eqMemberId(id))
                .fetchFirst()
        );

        return fetch;
    }

    public Optional<MemberDetailDto> getDetailDtoByUsername(String username) {

        Optional<MemberDetailDto> fetch = Optional.ofNullable(this.queryFactory
                .select(Projections.constructor(MemberDetailDto.class,
                        member
                        ))
                .from(member)
                .where(eqUsername(username))
                .fetchFirst()
        );

        return fetch;
    }

    private BooleanExpression eqMemberId(Long id) {
        return id != null ? member.id.eq(id) : null;
    }

    private BooleanExpression eqUsername(String username) {
        return StringUtils.hasText(username) ? member.username.eq(username) : null;
    }

    private BooleanExpression eqNickname(String nickname) {
        return StringUtils.hasText(nickname) ? member.nickname.eq(nickname) : null;
    }

    private BooleanExpression eqUserStatus(UserStatus status) {
        return status != null ? member.status.eq(status) : null;
    }

    public Member getMember(String username) {
        Member fetchOne = queryFactory.selectFrom(member)
                .leftJoin(member.roles).fetchJoin()
                .where(eqUsername(username))
                .fetchOne();

        if (fetchOne == null) {
            throw new NotFoundException(MessageFormat.format("{0} 해당회원을 찾을 수 없습니다.", username));
        }

        return fetchOne;
    }

    public Member getMember(LoginUser loginUser) {
        Member fetchOne = queryFactory.selectFrom(member)
                .leftJoin(member.roles).fetchJoin()
                .where(eqUsername(loginUser.getUsername()))
                .fetchOne();

        if (fetchOne == null) {
            throw new NotFoundException(MessageFormat.format("{0} 해당회원을 찾을 수 없습니다.", loginUser.getUsername()));
        }

        return fetchOne;
    }

    public Member getMember(String username, UserStatus status) {
        Member fetchOne = queryFactory.selectFrom(member)
                .leftJoin(member.roles).fetchJoin()
                .where(eqUsername(username),
                        eqUserStatus(status))
                .fetchOne();

        return fetchOne;
    }

    public Member getMemberByUsernameWithPreferTags(String username) {
        Member fetchOne = queryFactory.selectFrom(member)
                .leftJoin(member.roles).fetchJoin()
                .where(eqUsername(username))
                .fetchOne();

        if (fetchOne == null) {
            throw new NotFoundException(MessageFormat.format("{0} 해당회원을 찾을 수 없습니다.", username));
        }

        return fetchOne;
    }

    public Member getMemberByUsernameWithPreferZones(String username) {
        Member fetchOne = queryFactory.selectFrom(member)
                .leftJoin(member.roles).fetchJoin()
                .where(eqUsername(username))
                .fetchOne();

        if (fetchOne == null) {
            throw new NotFoundException(MessageFormat.format("{0} 해당회원을 찾을 수 없습니다.", username));
        }

        return fetchOne;
    }


    public Member getMember(Long memberId) {
        Member fetchOne = queryFactory.selectFrom(member)
                .leftJoin(member.roles).fetchJoin()
                .where(eqMemberId(memberId))
                .fetchOne();

        if (fetchOne == null) {
            throw new NotFoundException(MessageFormat.format("{0} 해당회원을 찾을 수 없습니다.", memberId));
        }

        return fetchOne;
    }

    public Member getMemberByProviderEmail(String email) {
        Member fetchOne = queryFactory.selectFrom(member)
                .leftJoin(member.roles).fetchJoin()
                .where(eqProviderEmail(email))
                .fetchOne();

        return fetchOne;
    }

    private BooleanExpression eqProviderEmail(String email) {
        return member.providerEmail.eq(email);
    }
}
