package comgetit.user.dto;

import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonFormat;

public class UserDTO {
	
    @NotBlank
    @NotNull
    @Size(min = 3, max = 40)
    private String firstname;
 	
    @NotNull
    @Size(min = 3, max = 50)
    private String lastname;
    
    @NotNull
    @Size(max = 50)
    private String phone;
    
    @NotNull
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthdate;
    
    @Size(min = 10, max = 50)
    private String address;
    
    @NotNull
    private Long workAreaId;
    
    private int score;

    @NotNull
    @Size(max = 50)
    @Email(regexp = ".+@.+\\..+")
    private String email;

    @NotNull
    @Size(min = 8, max = 128)
    private String password;
    
    private String image;

    public UserDTO(String firstname, String lastname, String phone, Date birthdate, 
                   String address, Long workAreaId, int score, String email, String password, String image) {
        this.firstname = firstname;
        this.lastname = lastname;
	    this.phone = phone;
	    this.birthdate = birthdate;
	    this.address = address;
	    this.workAreaId = workAreaId;
	    this.score = score;
	    this.email = email;
        this.password = password;
        this.image = image;
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

    public void setWorkArea(Long workAreaId) {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getImage() {
    	return image;
    }
}
