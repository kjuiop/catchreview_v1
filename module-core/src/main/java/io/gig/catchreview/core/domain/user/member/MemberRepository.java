package io.gig.catchreview.core.domain.user.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author : Jake
 * @date : 2021/08/12
 */
@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByUsername(String username);

    Optional<Member> findByNickname(String nickname);

    boolean existsByUsername(String value);

    boolean existsByNickname(String nickname);

    boolean existsByProviderEmail(String providerEmail);
}
