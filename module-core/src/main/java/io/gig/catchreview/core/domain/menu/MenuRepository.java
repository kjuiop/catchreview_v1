package io.gig.catchreview.core.domain.menu;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : Jake
 * @date : 2021-08-18
 */
@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
}
