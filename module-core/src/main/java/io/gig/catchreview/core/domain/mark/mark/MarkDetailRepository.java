package io.gig.catchreview.core.domain.mark.mark;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : Jake
 * @date : 2021-11-13
 */
@Repository
public interface MarkDetailRepository extends JpaRepository<MarkDetail, Long> {
}
