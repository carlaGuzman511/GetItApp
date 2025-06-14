package comgetit.photogallery;

import comgetit.photogallery.dto.PhotoGalleryDTO;
import comgetit.photogallery.dto.PhotoGalleryCreationDTO;
import comgetit.user.User;
import comgetit.user.UserRepository;
import comgetit.user.exception.UserNotFoundException;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PhotoGalleryService {

    private final PhotoGalleryRepository photoGalleryRepository;

    private final UserRepository userRepository;

    @Autowired
    public PhotoGalleryService(PhotoGalleryRepository photoGalleryRepository, 
    		UserRepository userRepository) {
        this.photoGalleryRepository = photoGalleryRepository;
        this.userRepository = userRepository;
    }

    public PhotoGallery createPhotoGallery(final PhotoGalleryCreationDTO photoGalleryCreationDTO) {
        User user = userRepository.findById(photoGalleryCreationDTO.getUserId())
        		.orElseThrow(UserNotFoundException::new);
         
        PhotoGallery photoGallery = new PhotoGallery(UUID.randomUUID().getMostSignificantBits(),
        	photoGalleryCreationDTO.getDescription(), user, new Date(),
            photoGalleryCreationDTO.getImage(),
            photoGalleryCreationDTO.getPostId());
        return photoGalleryRepository.save(photoGallery);
    }

    public List<List<PhotoGalleryDTO>> getPhotosGalleryByUserId(Long userId) {
    	List<List<PhotoGalleryDTO>> answer = new ArrayList<List<PhotoGalleryDTO>>();
    	List<PhotoGalleryDTO> photos = photoGalleryRepository.findPhotosGalleryByUserIdOrderByCreatedDesc(userId)
                .stream().map(PhotoGalleryDTO::new)
                .collect(Collectors.toList());
        
        Set<String> posts = new HashSet<String>(getPostsId(photos));
        
        for(String postId : posts) {
        	answer.add(getPhotosGalleryByPostId(postId));
        	}
        return answer;
    }
	
	private List<String> getPostsId(List<PhotoGalleryDTO> photos) {
		List<String> postsId = new ArrayList<String>();
        for(PhotoGalleryDTO photo : photos) {
        	postsId.add(photo.getPostId());
        }
        return postsId;
	}
	
	public List<PhotoGalleryDTO> getPhotosGalleryByPostId(String postId) {
		return  photoGalleryRepository.findPhotosGalleryByPostIdOrderByCreatedDesc(postId)
                .stream().map(PhotoGalleryDTO::new)
                .collect(Collectors.toList());
	}
}
