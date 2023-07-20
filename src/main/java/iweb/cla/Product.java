package iweb.cla;

import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class Product {
    private int id;
    private String name;
    private double origibakprice;
    private int cid;
    private int stock;
    private Date creatDate;
    private double promoteprice;
    private String subtitle;

}
