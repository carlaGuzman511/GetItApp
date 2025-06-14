package comgetit.photogallery;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoGalleryRepository extends JpaRepository<PhotoGallery, Long> {

    List<PhotoGallery> findPhotosGalleryByUserIdOrderByCreatedDesc(Long userId);
    
    List<PhotoGallery> findPhotosGalleryByPostIdOrderByCreatedDesc(String postId);
}
