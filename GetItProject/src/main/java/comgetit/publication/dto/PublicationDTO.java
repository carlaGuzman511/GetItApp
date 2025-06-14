package comgetit.publication.dto;

import comgetit.publication.Publication;
import comgetit.user.dto.UserDTO;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PublicationDTO {

    @NotNull
    private Long adId;

    @NotBlank
    private String type;

    @NotNull
    private String workAreaName;

    private int fee;

    @Size(min = 10, max = 50)
    private String address;
    
    private String image;

    private int requiredTime;

    @NotBlank
    @Size(min = 10, max = 100)
    private String description;

    @NotNull
    private UserDTO user;

    private Date createdAt;

    public PublicationDTO(final Publication publication) {
        this.adId = publication.getId();
        this.type = publication.getPublicationType().name();
        this.workAreaName = publication.getWorkArea().getName();
        this.fee = publication.getTariff();
        this.address = publication.getAddress();
        this.requiredTime = publication.getTimeRequiredOrOffered();
        this.description = publication.getDescription();
        this.image = new String(publication.getImage(), StandardCharsets.UTF_8);
        this.createdAt = publication.getCreated();
        this.user = new UserDTO(publication.getUser().getFirstname(),
            publication.getUser().getLastname(), publication.getUser().getPhone(),
            publication.getUser().getBirthdate(), publication.getUser().getAddress(), publication.getUser().getWorkArea().getId(),
            publication.getUser().getScore(), publication.getUser().getEmail(),
            publication.getUser().getAddress(), publication.getUser().getImage());
    }

    public Long getAdId() {
        return adId;
    }

    public String getType() {
        return type;
    }

    public String getWorkAreaName() {
        return workAreaName;
    }

    public int getFee() {
        return fee;
    }

    public String getAddress() {
        return address;
    }

    public int getRequiredTime() {
        return requiredTime;
    }

    public String getDescription() {
        return description;
    }

    public UserDTO getUser() {
        return user;
    }

    public Date getCreatedAt() {
        return createdAt;
    }
    
    public String getImage() {
    	return image;
    }
}
