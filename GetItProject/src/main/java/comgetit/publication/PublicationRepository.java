package comgetit.publication;

import comgetit.workarea.WorkArea;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicationRepository extends JpaRepository<Publication, Long> {

    List<Publication> findAllByWorkAreaOrderByCreatedDesc(WorkArea workArea);
}
