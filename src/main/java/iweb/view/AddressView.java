package iweb.view;

import iweb.cla.Address;
import iweb.cla.Product;
import iweb.cla.User;
import iweb.dao.AddressDaoImpl;
import iweb.util.IOManager;

import java.io.*;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author 79840
 *
 */
public class AddressView {
    Socket socket;
    public AddressView(Socket socket) {
        this.socket=socket;
        String str=19+",";
        try {
            IOManager.write(str,socket);
            String res = IOManager.read(socket);
            String[] ss = res.split(String.valueOf("\\),"));
            for (String s: ss) {
                System.out.println(s);
            }
        } catch (IOException e) {
        }
        addressView();
    }
    /**
     * 选择地址方法
     * 1.输入一个地址的ID,如果此ID在地址表中存在，则返回此ID
     *
     */
    public void addressView(){
        Scanner sc = new Scanner(System.in);
        System.out.println("增加-1 删除-2 修改-3 查询-4 返回-5");
        System.out.print("请输入要对地址进行的操作:");
        String choose = sc.nextLine();
        switch (choose){
            case "1":
                addAddress();
                break;
            case "2":
                deleteAddress();
                break;
            case "3":
                updateAddress();
                break;
            case "4":
                flushedAddress();
                break;
            case "5":
                new AdminMainView(socket);
                break;
            default:
                System.out.println("输入错误");
                addressView();
        }
    }
    public void addAddress(){
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入用户:");
        String uid=sc.nextLine();
        System.out.print("请输入省份:");
        String province_addr=sc.nextLine();
        System.out.print("请输入城市:");
        String city_addr =sc.nextLine();
        System.out.print("请输入详细地址:");
        String detail_addr=sc.nextLine();

        String str = 16+","+uid+","+province_addr+","+city_addr+","+detail_addr;
        try {
            IOManager.write(str,socket);
            String res = IOManager.read(socket);
            if("添加成功".equals(res)){
                System.out.println("地址添加成功");
            }else{
                System.out.println(res);
            }
            addressView();
        } catch (IOException e) {
        }
    }
    public void deleteAddress(){
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入地址ID:");
        String rid=sc.nextLine();
        String str = 17+","+rid;
        try {
            IOManager.write(str,socket);
            String res = IOManager.read(socket);
            if("删除成功".equals(res)){
                System.out.println("地址删除成功");
            }else{
                System.out.println(res);
            }
            addressView();
        } catch (IOException e) {
        }
    }
    public void updateAddress(){
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入地址ID:");
        String rid=sc.nextLine();
        System.out.print("请输入用户:");
        String uid=sc.nextLine();
        System.out.print("请输入省份:");
        String province_addr=sc.nextLine();
        System.out.print("请输入城市:");
        String city_addr =sc.nextLine();
        System.out.print("请输入详细地址:");
        String detail_addr=sc.nextLine();
        String str = 18+","+rid+","+uid+","+province_addr+","+city_addr+","+detail_addr;
        try {
            IOManager.write(str,socket);
            String res = IOManager.read(socket);
            if("修改成功".equals(res)){
                System.out.println("地址修改成功");
            }else{
                System.out.println(res);
            }
            addressView();
        } catch (IOException e) {
        }
    }
    public void flushedAddress(){
        String str = 20+",";
        try {
            IOManager.write(str,socket);
            String res = IOManager.read(socket);
            String[] ss = res.split(String.valueOf("\\),"));
            for (String s: ss) {
                System.out.println(s);
            }
            addressView();
        } catch (IOException e) {
        }
    }

}
