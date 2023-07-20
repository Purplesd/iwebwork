package iweb;

import static org.junit.Assert.assertTrue;

import iweb.view.UserMainView;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{

    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void run1(){
        UserMainView userMainView = new UserMainView();
        userMainView.manageAddress();
    }
    @Test
    public void run2(){
//        AddressView addressView = new AddressView();
//        addressView.addAddress();
    }
}
