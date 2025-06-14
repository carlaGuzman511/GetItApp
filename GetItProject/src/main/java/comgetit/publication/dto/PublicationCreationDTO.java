package comgetit.publication.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;

public class PublicationCreationDTO {

    @NotBlank
    private String typePublication;

    @NotNull
    private Long workAreaId;

    private int tariff;

    @Pattern(regexp = "^$|^(\\w+){10}$", message = "must be a min size of 10")
    @Length(max = 150)

    private String address;

    private int timeRequiredOrOffered;

    @NotBlank
    @Size(min = 10, max = 250)
    private String description;

    @NotNull
    private Long userId;
    
    private String image;

    public PublicationCreationDTO(@NotBlank String typePublication,
        @NotNull Long workAreaId, int tariff,
        @Size(min = 10, max = 50) String address, int timeRequiredOrOffered,
        @NotBlank @Size(min = 10, max = 100) String description,
        @NotNull Long userId, String image) {
        this.typePublication = typePublication;
        this.workAreaId = workAreaId;
        this.tariff = tariff;
        this.address = address;
        this.timeRequiredOrOffered = timeRequiredOrOffered;
        this.description = description;
        this.image = image;
        this.userId = userId;
    }

	public String getTypePublication() {
        return typePublication;
    }

    public Long getWorkAreaId() {
        return workAreaId;
    }

    public int getTariff() {
        return tariff;
    }

    public String getAddress() {
        return address;
    }

    public int getTimeRequiredOrOffered() {
        return timeRequiredOrOffered;
    }

    public String getDescription() {
        return description;
    }

    public Long getUserId() {
        return userId;
    }
    
    public String getImage() {
    	return image;
    }
}
