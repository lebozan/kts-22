package ftn.kts.repository;

import ftn.kts.model.PasswordResetCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordResetRepository extends JpaRepository<PasswordResetCode, Long> {
    PasswordResetCode findByCodeAndUserIdAndActiveTrue(String code, Long userId);


}
