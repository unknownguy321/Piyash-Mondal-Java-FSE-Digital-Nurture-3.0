import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Main class containing the repositories
public class RepositoryContainer {

    @Repository
    @Primary
    public interface PrimaryRepository extends JpaRepository<PrimaryEntity, Long> {
    }

    @Repository
    public interface SecondaryRepository extends JpaRepository<SecondaryEntity, Long> {
    }
}
