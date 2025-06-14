package comgetit.user.dto;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UsersDTO {
	
    private String id;
	
    @NotBlank
    @NotNull
    @Size(min = 3, max = 50)
    private String firstname;
 	
    @NotNull
    @Size(min = 3, max = 50)
    private String lastname;
    
    @NotNull
    @Size(max = 50)
    private String phone;
    
    @NotNull
    private Date birthdate;
    
    @NotNull
    private String address;
    
    private Long workAreaId;

    private int score;

    @NotNull
    @Size(max = 50)
    private String email;
    
    private String image;

    public UsersDTO(String id, String firstname, String lastname, String phone, Date birthdate,
                    String address, Long workAreaId, int score, String email, byte[] image) {
        super();
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
        this.birthdate = birthdate;
        this.address = address;
        this.workAreaId = workAreaId;
        this.score = score;
        this.email = email;
        this.image = new String(image, StandardCharsets.UTF_8);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getWorkAreaId() {
        return workAreaId;
    }

    public void setWorkAreaId(Long workAreaId) {
        this.workAreaId = workAreaId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getImage() {
    	return image;
    }
}
