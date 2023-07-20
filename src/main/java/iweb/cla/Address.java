package iweb.cla;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Address {
    private int addrId;
    private User user;
    private String province;
    private String city;
    private String detail;

}
