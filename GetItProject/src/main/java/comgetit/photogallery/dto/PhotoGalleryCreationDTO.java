package comgetit.photogallery.dto;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PhotoGalleryCreationDTO {

    @NotBlank
    @Size(min = 10, max = 250)
    private String description;

    @NotNull
    private Long userId;
    
    @NotNull
    private String postId;
    
    private String image;

    public PhotoGalleryCreationDTO(@NotBlank @Size(min = 10, max = 250) String description,
        @NotNull Long userId, String image, String postId) {
        this.description = description;
        this.image = image;
        this.userId = userId;
        this.postId = postId;
    }

    public String getDescription() {
        return description;
    }

    public Long getUserId() {
        return userId;
    }
    
    public String getPostId() {
        return postId;
    }
    
    public String getImage() {
    	return image;
    }
}
