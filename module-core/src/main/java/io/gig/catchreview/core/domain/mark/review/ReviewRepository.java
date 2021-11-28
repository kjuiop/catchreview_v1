package io.gig.catchreview.core.domain.mark.review;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : Jake
 * @date : 2021-11-28
 */
@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
}
