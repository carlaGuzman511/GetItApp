package comgetit.publication;

import comgetit.user.User;
import comgetit.workarea.WorkArea;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Publication {

    @Id
    private Long id;

    @Enumerated(EnumType.STRING)
    private PublicationType publicationType;

    @ManyToOne
    @JsonIgnore
    private WorkArea workArea;

    private Integer tariff;

    private String address;

    private Integer timeRequiredOrOffered;

    private String description;
    
    private byte[] image;
    @ManyToOne
    @JsonIgnore
    private User user;

    private Date created = new Date();

    protected Publication() {
    }

    public Publication(Long id, PublicationType publicationType, WorkArea workArea,
        Integer tariff, String address, Integer timeRequiredOrOffered, String description,
        User user, Date created, String image) {
        this.id = id;
        this.publicationType = publicationType;
        this.workArea = workArea;
        this.tariff = tariff;
        this.address = address;
        this.timeRequiredOrOffered = timeRequiredOrOffered;
        this.description = description;
        this.user = user;
        this.created = created;
        this.image = image.getBytes();
    }

    public Long getId() {
        return id;
    }

    public PublicationType getPublicationType() {
        return publicationType;
    }

    public WorkArea getWorkArea() {
        return workArea;
    }

    public Integer getTariff() {
        return tariff;
    }

    public String getAddress() {
        return address;
    }

    public Integer getTimeRequiredOrOffered() {
        return timeRequiredOrOffered;
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
}
