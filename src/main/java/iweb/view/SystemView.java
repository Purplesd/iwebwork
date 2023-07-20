package iweb.view;

import iweb.dao.ProductDaoImpl;
import iweb.util.IOManager;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class SystemView {
    Socket socket;

    public SystemView(Socket socket) {
        this.socket=socket;
        Scanner sc = new Scanner(System.in);
        if (socket.isConnected()) {
            System.out.println("登入-1 注册-2");
            System.out.print("请选择操作：");
            String choose = sc.nextLine();
            switch (choose) {
                case "1":
                    logIn();
                    break;
                case "2":
                    enroll();
                    break;
                default:
                    System.out.println("请正确选择");
                    new SystemView(socket);
                    break;
            }
        }
    }
    public void logIn(){
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入用户名: ");
        String name = sc.nextLine();
        System.out.print("请输入密码:");
        String password = sc.nextLine();
        String str = 1+","+name + "," + password;
        try {
            IOManager.write(str,socket);
            String res = IOManager.read(socket);
            if("admin登入成功".equals(res)){
                System.out.println("admin登入成功");
                new AdminMainView(socket);
            }else if("user登入成功".equals(res)){
                System.out.println("user登入成功");
                new UserMainView(socket);
            } else{
                System.out.println("登入失败");
                logIn();
            }
        } catch (IOException e) {
        }
    }
    public void enroll(){
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入用户名: ");
        String inName = sc.nextLine();
        System.out.print("请输入密码: ");
        String inPassword = sc.nextLine();
        System.out.print("请输入电话号: ");
        String inPhone =sc.nextLine();
        System.out.print("请输入余额: ");
        String inMoney = sc.nextLine();
        System.out.print("请输入权限: ");
        String inAuthority = sc.nextLine();
        String str = 2+","+inName+","+inPassword+","+inPhone+","+inMoney+","+inAuthority;
        try {
            IOManager.write(str,socket);
            String res = IOManager.read(socket);
            if("注册成功".equals(res)){
                System.out.println("注册成功");
                logIn();
            }else{
                System.out.println(res);
                enroll();
            }
        } catch (IOException e) {
        }
    }

}
