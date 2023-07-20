package iweb.cla;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class User {
    private int id;
    private String userName;
    private String password;
    private String Phone;
    private double money;
    private String authority;

}
