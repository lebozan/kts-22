package ftn.kts.repository;

import ftn.kts.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByRoles_Id(Long id);

    List<User> findByRoles_IdAndActiveTrue(Long id);

    Optional<User> findByEmailAndActiveTrue(String email);

    List<User> findAllByActiveTrue();

    Page<User> findPageByActiveTrue(Pageable pageable);





}
