package iweb.view;

import iweb.dao.ProductDaoImpl;
import iweb.util.IOManager;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ProductView {

    Socket socket;
    public ProductView(Socket socket){
        this.socket=socket;
        String str=0+",";
        try {
            IOManager.write(str,socket);
            String res = IOManager.read(socket);
            String[] ss = res.split(String.valueOf("\\),"));
            for (String s: ss) {
                System.out.println(s);
            }
        } catch (IOException e) {
        }
        productView();

    }
    public void productView(){
        Scanner sc = new Scanner(System.in);

        System.out.println("增加-1 删除-2 修改-3 查询-4 刷新-5 返回-6");
        System.out.print("请输入要对商品进行的操作:");
        String choose = sc.nextLine();
        switch (choose){
            case "1":
                addProduct();
                break;
            case "2":
                deleteProduct();
                break;
            case "3":
                updateProduct();
                break;
            case "4":
                detailProduct();
                break;
            case "6":
                new AdminMainView(socket);
                break;
            case "5":
                flushedProduct();
                break;
            default:
                System.out.println("输入错误");
                productView();
        }
    }
    public void addProduct(){
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入商品名:");
        String name=sc.nextLine();
        System.out.print("请输入原价:");
        String origibakprice =sc.nextLine();
        System.out.print("请输入类别:");
        String cid=sc.nextLine();
        System.out.print("请输入发布数量:");
        String stock=sc.nextLine();
        System.out.print("请输入折扣价:");
        String promoteprice=sc.nextLine();
        System.out.print("请输入广告词:");
        String subtitle=sc.nextLine();
        String str = 3+","+name+","+origibakprice+","+cid+","+stock+","+promoteprice+","+subtitle;
        try {
            IOManager.write(str,socket);
            String res = IOManager.read(socket);
            if("商品添加".equals(res)){
                System.out.println("商品添加成功");
            }else{
                System.out.println(res);
            }
            productView();
        } catch (IOException e) {
        }
    }
    public void deleteProduct(){
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入删除序号:");
        String id=sc.nextLine();
        String str = 4+","+id;
        try {
            IOManager.write(str,socket);
            String res = IOManager.read(socket);
            if("删除成功".equals(res)){
                System.out.println("商品删除成功");
            }else{
                System.out.println(res);
            }
            productView();
        } catch (IOException e) {
        }
    }
    public void updateProduct(){
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入要修改商品的ID:");
        String id=sc.nextLine();
        System.out.print("请输入商品名:");
        String name=sc.nextLine();
        System.out.print("请输入原价:");
        String origibakprice =sc.nextLine();
        System.out.print("请输入类别:");
        String cid=sc.nextLine();
        System.out.print("请输入发布数量:");
        String stock=sc.nextLine();
        System.out.print("请输入折扣价:");
        String promoteprice=sc.nextLine();
        String str = 6+","+id+","+name+","+origibakprice+","+cid+","+stock+","+promoteprice;
        try {
            IOManager.write(str,socket);
            String res = IOManager.read(socket);
            if("修改成功".equals(res)){
                System.out.println("商品修改成功");
            }else{
                System.out.println(res);
            }
            productView();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void detailProduct(){
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入查询序号:");
        String id=sc.nextLine();
        String str = 5+","+id;
        try {
            IOManager.write(str,socket);
            String res = IOManager.read(socket);
            if("失败成功".equals(res)){
                System.out.println("查询失败");
            }else{
                String[] strs=res.split(",");
                for (int i = 0; i < strs.length; i++) {
                    System.out.println(strs[i]);
                }
            }
            productView();
        } catch (IOException e) {
        }
    }
    public void flushedProduct(){
        String str = 31+",";
        try {
            IOManager.write(str,socket);
            String res = IOManager.read(socket);
            String[] ss = res.split(String.valueOf("\\),"));
            for (String s: ss) {
                System.out.println(s);
            }
            productView();
        } catch (IOException e) {
        }
    }
}
