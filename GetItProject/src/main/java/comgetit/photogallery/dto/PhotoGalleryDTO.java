package comgetit.photogallery.dto;
import comgetit.photogallery.PhotoGallery;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class PhotoGalleryDTO {
    
    @NotNull
    private Long photoGalleryId;
    
    @NotNull
    private String image;

    @NotBlank
    @Size(min = 10, max = 250)
    private String description;
    
    @NotNull
    private Date createdAt;
    
    @NotNull
    private String postId;

    public PhotoGalleryDTO(final PhotoGallery photoGallery) {
        this.photoGalleryId = photoGallery.getId();
        this.description = photoGallery.getDescription();
        this.image = new String(photoGallery.getImage(), StandardCharsets.UTF_8);
        this.createdAt = photoGallery.getCreated();
        this.postId = photoGallery.getPostId();
    }

    public Long getPhotoGalleryId() {
        return photoGalleryId;
    }

    public String getDescription() {
        return description;
    }

    public Date getCreatedAt() {
        return createdAt;
    }
    
    public String getImage() {
    	return image;
    }
    
    public String getPostId() {
    	return postId;
    }
}
