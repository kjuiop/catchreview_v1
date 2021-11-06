package io.gig.catchreview.core.domain.user.administrator;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : JAKE
 * @date : 2021/08/15
 */
@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, Long> {

    Administrator findByUsername(String username);

    boolean existsByUsername(String value);
}
