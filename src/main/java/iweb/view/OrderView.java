package iweb.view;

import iweb.cla.Order;
import iweb.dao.OrderDaoImpl;
import iweb.util.IOManager;

import java.io.*;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Scanner;

public class OrderView {
    Socket socket;



    public OrderView(Socket socket) {
        this.socket = socket;
        order();
    }
    public void order(){
        Scanner sc = new Scanner(System.in);
        String str=7+",";
        try {
            IOManager.write(str,socket);
            String res = IOManager.read(socket);
            String[] ss = res.split(String.valueOf("\\),"));
            for (String s: ss) {
                System.out.println(s);
            }
        } catch (IOException e) {
        }
        System.out.println("发货-1 退货-2 订单详情-3 返回-4");
        System.out.print("请输入要对订单进行的操作:");
        String choose = sc.nextLine();
        switch (choose) {
            case "1":
                sendOrder();
                break;
            case "2":
                receiveOrder();
                break;
            case "3":
                detailOrder();
                break;
            case "4":
                new AdminMainView(socket);
                break;
            default:
                System.out.println("输入错误");
                new OrderView(socket);
        }
    }
    public void sendOrder(){
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入要发货的订单ID:");
        String order_id=sc.nextLine();
        String str = 8+","+order_id;
        try {
            IOManager.write(str,socket);
            String res = IOManager.read(socket);
            if("发货成功".equals(res)){
                System.out.println("发货修改成功");
            }else{
                System.out.println(res);
            }
            order();
        } catch (IOException e) {
        }
    }
    public void receiveOrder(){
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入要退货的订单ID:");
        String order_id=sc.nextLine();
        String str = 9+","+order_id;
        try {
            IOManager.write(str,socket);
            String res = IOManager.read(socket);
            if("退货成功".equals(res)){
                System.out.println("退货修改成功");
            }else{
                System.out.println(res);
            }
            order();
        } catch (IOException e) {
        }
    }
    public void detailOrder(){
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入要查询的订单ID:");
        String order_id=sc.nextLine();
        String str = 10+","+order_id;
        try {
            IOManager.write(str,socket);
            String res = IOManager.read(socket);
            if("查询失败".equals(res)){
                System.out.println("订单查询失败");
            }else{
                System.out.println(res);
            }
            order();
        } catch (IOException e) {
        }
    }
}
