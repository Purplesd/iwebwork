package iweb.cla;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private int order_id;
    private int user_id;
    private int address_id;
    private Date order_date;
    private String order_status;

}
