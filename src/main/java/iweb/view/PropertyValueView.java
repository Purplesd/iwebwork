package iweb.view;

import iweb.util.IOManager;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class PropertyValueView {

    Socket socket;
    public PropertyValueView(Socket socket) {
        String str=26+",";
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
        propertyValueView();
    }
    /**
     * 选择地址方法
     * 1.输入一个地址的ID,如果此ID在地址表中存在，则返回此ID
     *
     */

    public void propertyValueView(){
        Scanner sc = new Scanner(System.in);
        System.out.println("增加-1 删除-2 修改-3 刷新-4 返回-5");
        System.out.print("请输入要对属性值进行的操作:");
        String choose = sc.nextLine();
        switch (choose){
            case "1":
                addPropertyValue();
                break;
            case "2":
                deletePropertyValue();
                break;
            case "3":
                updatePropertyValue();
                break;
            case "4":
                flushedPropertyValue();
                break;
            case "5":
                new AdminMainView(socket);
                break;
            default:
                System.out.println("输入错误");
                propertyValueView();
        }
    }
    public void addPropertyValue(){
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入商品ID:");
        String pid=sc.nextLine();
        System.out.print("请输入属性ID:");
        String ptid=sc.nextLine();
        System.out.print("请输入属性值:");
        String value=sc.nextLine();

        if(pid.equals("")||ptid.equals("")||value.equals("")){
            propertyValueView();
        }
        String str = 27+","+pid+","+ptid+","+value;
        try {
            IOManager.write(str,socket);
            String res = IOManager.read(socket);
            if("添加成功".equals(res)){
                System.out.println("属性值添加成功");
            }else{
                System.out.println(res);
            }
            propertyValueView();
        } catch (IOException e) {
        }
    }
    public void deletePropertyValue(){
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入属性值ID:");
        String id=sc.nextLine();
        if(id.equals("")){
            propertyValueView();
        }
        String str = 28+","+id;
        try {
            IOManager.write(str,socket);
            String res = IOManager.read(socket);
            if("删除成功".equals(res)){
                System.out.println("属性删除成功");
            }else{
                System.out.println(res);
            }
            propertyValueView();
        } catch (IOException e) {
        }
    }
    public void updatePropertyValue(){
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入属性值ID:");
        String id=sc.nextLine();
        System.out.print("请输入商品ID:");
        String pid=sc.nextLine();
        System.out.print("请输入属性ID:");
        String ptid=sc.nextLine();
        System.out.print("请输入属性值:");
        String value=sc.nextLine();
        if(pid.equals("")||ptid.equals("")||value.equals("")||id.equals("")){
            propertyValueView();
        }
        String str = 29+","+id+","+pid+","+ptid+","+value;
        try {
            IOManager.write(str,socket);
            String res = IOManager.read(socket);
            if("修改成功".equals(res)){
                System.out.println("属性值修改成功");
            }else{
                System.out.println(res);
            }
            propertyValueView();
        } catch (IOException e) {
        }
    }
    public void flushedPropertyValue(){
        String str = 30+",";
        try {
            IOManager.write(str,socket);
            String res = IOManager.read(socket);
            String[] ss = res.split(String.valueOf("\\),"));
            for (String s: ss) {
                System.out.println(s);
            }
            propertyValueView();
        } catch (IOException e) {
        }
    }
}
