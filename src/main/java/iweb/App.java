package iweb;

import iweb.view.AddressView;
import iweb.view.CategoryView;
import iweb.view.UserMainView;

import java.sql.Date;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        UserMainView userMainView = new UserMainView();
        userMainView.manageAddress();
}
}
