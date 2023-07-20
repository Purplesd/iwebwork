package iweb.view;

import iweb.cla.Address;
import iweb.dao.AddressDaoImpl;

import java.net.Socket;
import java.util.LinkedList;
import java.util.Scanner;

public class UserMainView {
    Socket socket;
    AddressDaoImpl addressDao = new AddressDaoImpl();

    public UserMainView(){

    }
    public UserMainView(Socket socket){
        this.socket=socket;
        new AddressView(socket);
    }
    public int manageAddress() {
        LinkedList<Address> addressLinkedList = (LinkedList<Address>) addressDao.listAll();
        Scanner sc = new Scanner(System.in);
        System.out.print("请选择地址ID:");
        String choose = sc.nextLine();
        int res = Integer.parseInt(choose);
        for (Address a: addressLinkedList) {
            if(a.getAddrId()==res){
                System.out.println("获取成功");
                return res;
            }
        }
        System.out.println("获取失败");
        return manageAddress();
    }
}
