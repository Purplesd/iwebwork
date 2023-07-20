package iweb.view;

import java.net.Socket;
import java.util.Scanner;

public class AdminMainView {

    Socket socket;
    public AdminMainView(Socket socket){
        this.socket=socket;
        Scanner sc = new Scanner(System.in);
        System.out.println("商品-1 分类-2 订单-3 地址-4 属性-5 属性值-6");
        System.out.print("请选择要进行的操作: ");
        String choose = sc.nextLine();
        switch (choose){
            case "1":
                new ProductView(socket);
                break;
            case "2":
                new CategoryView(socket);
                break;
            case "3":
                new OrderView(socket);
                break;
            case "4":
                new AddressView(socket);
                break;
            case "5":
                new PropertyView(socket);
                break;
            case "6":
                new PropertyValueView(socket);
                break;
            default:
                System.out.println("输入错误");
                new AdminMainView(socket);
        }
    }
}
