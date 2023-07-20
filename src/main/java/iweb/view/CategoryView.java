package iweb.view;

import iweb.cla.Address;
import iweb.cla.Category;
import iweb.cla.User;
import iweb.dao.AddressDaoImpl;
import iweb.dao.CategoryDaoImpl;
import iweb.util.IOManager;

import java.io.*;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Scanner;

public class CategoryView {

    Socket socket;
    public CategoryView(Socket socket) {
        String str=15+",";
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
        categoryView();
    }
    public void categoryView() {
        Scanner sc = new Scanner(System.in);
        System.out.println("增加-1 删除-2 修改-3 查询-4 返回-5");
        System.out.print("请输入要对分类进行的操作:");
        String choose = sc.nextLine();
        switch (choose){
            case "1":
                addCategory();
                break;
            case "2":
                deleteCategory();
                break;
            case "3":
                updateCategory();
                break;
            case "4":
                flushed();
                break;
            case "5":
                new AdminMainView(socket);
                break;
            default:
                System.out.println("输入错误");
                categoryView();
        }
    }
    public void addCategory(){
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入分类名:");
        String name=sc.nextLine();
        if(name.equals("")){
            categoryView();
        }
        String str = 11+","+name;
        try {
            IOManager.write(str,socket);
            String res = IOManager.read(socket);
            if("添加成功".equals(res)){
                System.out.println("分类添加成功");
            }else{
                System.out.println(res);
            }
            categoryView();
        } catch (IOException e) {
        }
    }
    public void deleteCategory(){
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入分类ID:");
        String rid=sc.nextLine();
        if(rid.equals("")){
            categoryView();
        }
        String str = 12+","+rid;
        try {
            IOManager.write(str,socket);
            String res = IOManager.read(socket);
            if("删除成功".equals(res)){
                System.out.println("分类删除成功");
            }else{
                System.out.println(res);
            }
            categoryView();
        } catch (IOException e) {
        }

    }
    public void updateCategory(){
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入分类ID:");
        String rid=sc.nextLine();
        System.out.print("请输入分类名:");
        String name=sc.nextLine();

        if(rid.equals("")||name.equals("")){
            categoryView();
        }
        String str = 13+","+rid+","+name;
        try {
            IOManager.write(str,socket);
            String res = IOManager.read(socket);
            if("更新成功".equals(res)){
                System.out.println("分类更新成功");
            }else{
                System.out.println(res);
            }
            categoryView();
        } catch (IOException e) {
        }
    }
    public void flushed(){
        String str = 14+",";
        try {
            IOManager.write(str,socket);
            String res = IOManager.read(socket);
            String[] ss = res.split(String.valueOf("\\),"));
            for (String s: ss) {
                System.out.println(s);
            }
            categoryView();
        } catch (IOException e) {
        }
    }
}
