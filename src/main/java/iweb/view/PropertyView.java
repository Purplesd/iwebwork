package iweb.view;

import iweb.util.IOManager;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class PropertyView {

    Socket socket;
    public PropertyView(Socket socket) {
        String str=21+",";
        try {
            IOManager.write(str,socket);
            String res = IOManager.read(socket);
            String[] ss = res.split(String.valueOf("\\),"));
            for (String s: ss) {
                System.out.println(s);
            }
        } catch (IOException e) {
        }
        this.socket=socket;
        propertyView();
    }
    /**
     * 选择地址方法
     * 1.输入一个地址的ID,如果此ID在地址表中存在，则返回此ID
     *
     */

    public void propertyView(){
        Scanner sc = new Scanner(System.in);
        System.out.println("增加-1 删除-2 修改-3 刷新-4 返回-5");
        System.out.print("请输入要对属性进行的操作:");
        String choose = sc.nextLine();
        switch (choose){
            case "1":
                addProperty();
                break;
            case "2":
                deleteProperty();
                break;
            case "3":
                updateProperty();
                break;
            case "4":
                flushedProperty();
                break;
            case "5":
                new AdminMainView(socket);
                break;
            default:
                System.out.println("输入错误");
                propertyView();
        }
    }
    public void addProperty(){
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入分类ID:");
        String cid=sc.nextLine();
        System.out.print("请输入属性名:");
        String name=sc.nextLine();

        if(cid.equals("")||name.equals("")){
            propertyView();
        }
        String str = 22+","+cid+","+name;
        try {
            IOManager.write(str,socket);
            String res = IOManager.read(socket);
            if("添加成功".equals(res)){
                System.out.println("属性添加成功");
            }else{
                System.out.println(res);
            }
            propertyView();
        } catch (IOException e) {
        }
    }
    public void deleteProperty(){
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入属性ID:");
        String id=sc.nextLine();
        if(id.equals("")){
            propertyView();
        }
        String str = 23+","+id;
        try {
            IOManager.write(str,socket);
            String res = IOManager.read(socket);
            if("删除成功".equals(res)){
                System.out.println("属性删除成功");
            }else{
                System.out.println(res);
            }
            propertyView();
        } catch (IOException e) {
        }
    }
    public void updateProperty(){
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入属性ID:");
        String id=sc.nextLine();
        System.out.print("请输入分类ID:");
        String cid=sc.nextLine();
        System.out.print("请输入属性名:");
        String name=sc.nextLine();
        if(id.equals("")||cid.equals("")||name.equals("")){
            propertyView();
        }
        String str = 24+","+id+","+cid+","+name;
        try {
            IOManager.write(str,socket);
            String res = IOManager.read(socket);
            if("修改成功".equals(res)){
                System.out.println("属性修改成功");
            }else{
                System.out.println(res);
            }
            propertyView();
        } catch (IOException e) {
        }
    }
    public void flushedProperty(){
        String str = 25+",";
        try {
            IOManager.write(str,socket);
            String res = IOManager.read(socket);
            String[] ss = res.split(String.valueOf("\\),"));
            for (String s: ss) {
                System.out.println(s);
            }
            propertyView();
        } catch (IOException e) {
        }
    }
}
