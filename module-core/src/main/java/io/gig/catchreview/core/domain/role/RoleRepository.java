package io.gig.catchreview.core.domain.role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author : JAKE
 * @date : 2021/08/18
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

    boolean existsByName(String name);

    List<Role> findAllByOrderBySortOrderAsc();

    List<Role> findAllByNameIn(List<String> roleNames);
}
