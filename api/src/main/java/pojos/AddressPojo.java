package pojos;

import lombok.Data;

@Data
public class AddressPojo {
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private GeoPojo geo;
}
