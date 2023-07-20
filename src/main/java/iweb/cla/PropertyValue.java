package iweb.cla;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jxy
 * @date
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PropertyValue {
    private int id;
    private int pid;
    private int ptid;
    private String value;
}
