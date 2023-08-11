package bear.blog.repositories;

import bear.blog.models.VerificationCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationRepository extends JpaRepository<VerificationCode, Integer> {
    VerificationCode findByEmailAddress(String emailAddress);
}
