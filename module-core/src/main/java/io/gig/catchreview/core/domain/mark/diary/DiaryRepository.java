package io.gig.catchreview.core.domain.mark.diary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : Jake
 * @date : 2021-11-16
 */
@Repository
public interface DiaryRepository extends JpaRepository<Diary, Long> {
}
