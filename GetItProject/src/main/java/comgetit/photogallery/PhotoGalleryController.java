package comgetit.photogallery;

import comgetit.photogallery.dto.PhotoGalleryCreationDTO;
import comgetit.photogallery.dto.PhotoGalleryDTO;

import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PhotoGalleryController {
	private PhotoGalleryService photoGalleryService;
	
    @Autowired
    public PhotoGalleryController(PhotoGalleryService photoGalleryService) {
        this.photoGalleryService = photoGalleryService;
    }

    @PostMapping("/photo-gallery")
    public ResponseEntity<Long> createPhotoGallery(
        @RequestBody @Valid final PhotoGalleryCreationDTO photoGalleryCreationDTO
    ) {
        PhotoGallery photoGallery = photoGalleryService.createPhotoGallery(photoGalleryCreationDTO);
        return new ResponseEntity(photoGallery.getId(), HttpStatus.CREATED);
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/photos-gallery/{id}")    
    public List<List<PhotoGalleryDTO>> getPhotosGalleryByUserId(@PathVariable Long id) {
        return photoGalleryService.getPhotosGalleryByUserId(id);
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/photos-gallery-post/{id}")    
    public List<PhotoGalleryDTO> getPhotosGalleryByPostId(@PathVariable String id) {
        return photoGalleryService.getPhotosGalleryByPostId(id);
    }
    
}
