import com.review.monitoring.MonitoringSystem.monitor.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;


public interface JPAMemberRepository extends JpaRepository<Member, Long> {
    Member findById(String email);
}
