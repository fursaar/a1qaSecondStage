package pojos;

import lombok.Data;

import java.util.List;

@Data
public class UserPojo {
    private int id;
    private String name;
    private String username;
    private String email;
    private AddressPojo address;
    private String phone;
    private String website;
    private CompanyPojo company;

}
