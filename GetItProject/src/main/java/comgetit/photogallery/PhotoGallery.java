package comgetit.photogallery;
import comgetit.user.User;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class PhotoGallery {
	@Id
    private Long id;

    private String description;
    
    private byte[] image;
    
    @ManyToOne
    @JsonIgnore
    private User user;

    private Date created = new Date();
    
    private String postId;

    protected PhotoGallery() {}

    public PhotoGallery(Long id, String description, User user, Date created, String image, String postId) {
        this.id = id;
        this.description = description;
        this.user = user;
        this.created = created;
        this.image = image.getBytes();
        this.postId = postId;
    }

    public Long getId() {
        return id;
    }
   
    public String getDescription() {
        return description;
    }

    public User getUser() {
        return user;
    }

    public Date getCreated() {
        return created;
    }
    
    public byte[] getImage() {
    	return image;
    }
    
    public String getPostId() {
        return postId;
    }
}
