package iweb.server;

import iweb.cla.*;
import iweb.dao.*;
import iweb.util.IOManager;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.sql.Date;
import java.util.LinkedList;

public class ServerThread extends Thread {
    static UserDaoImpl userDao = new UserDaoImpl();
    static ProductDaoImpl productDao = new ProductDaoImpl();
    static OrderDaoImpl orderDao = new OrderDaoImpl();
    static CategoryDaoImpl categoryDao = new CategoryDaoImpl();
    static AddressDaoImpl addressDao = new AddressDaoImpl();
    static PropertyDaoImpl propertyDao = new PropertyDaoImpl();
    static PropertyValueDaoImpl propertyValueDao = new PropertyValueDaoImpl();
    Socket socket;
    User admin;
    private static LinkedList<Property> propertyList = new LinkedList<>();
    private static LinkedList<Address> addressList = new LinkedList<>();
    private static LinkedList<User> userList = new LinkedList<>();
    private static LinkedList<Product> productList = new LinkedList<>();
    private static LinkedList<Order> orderList = new LinkedList<>();
    private static LinkedList<Category> categoryList = new LinkedList<>();
    private static LinkedList<PropertyValue> propertyValueList = new LinkedList<>();
    static {
        userList= (LinkedList<User>) userDao.listAll();
        productList = (LinkedList<Product>) productDao.listAll();
        orderList = (LinkedList<Order>) orderDao.listAll();
        categoryList=(LinkedList<Category>) categoryDao.listAll();
        addressList = (LinkedList<Address>) addressDao.listAll();
        propertyList = (LinkedList<Property>) propertyDao.listAll();
        propertyValueList = (LinkedList<PropertyValue>) propertyValueDao.listAll();
        System.out.println("服务器启动");
    }
    public ServerThread( Socket socket) {
        this.socket = socket;
    }
    @Override
    public synchronized void run() {
            String res = null;
                while (true) {
                    try {
                    if (socket.isConnected()) {
                        String readStr = IOManager.read(socket);
                        System.out.println(readStr);
                        String[] strs = readStr.split(",");
                        for (String s: strs) {
                            if(s.equals("")){
                                res="输入有误请重新输入";
                                throw new Exception();
                            }
                        }
                        switch (strs[0]){
                            case "0":
                                res = productList.toString();
                                break;
                            case "1":
                                res = logIn(strs[1],strs[2]);
                                break;
                            case "2":
                                res=enroll(strs);
                                break;
                            case "3":
                                res=addProduct(strs);
                                break;
                            case "4":
                                res=deleteProduct(strs);
                                break;
                            case "5":
                                res=detailProduct(strs);
                                break;
                            case "6":
                                res=updateProduct(strs);
                                break;
                            case "7":
                                res=orderList.toString();
                                break;
                            case "8":
                                res=sendOrder(strs);
                                break;
                            case "9":
                                res=receiveOrder(strs);
                                break;
                            case "10":
                                res=detailOrder(strs);
                                break;
                            case "11":
                                res=addCategory(strs);
                                break;
                            case "12":
                                res=deleteCategory(strs);
                                break;
                            case "13":
                                res=updateCategory(strs);
                                break;
                            case "14":
                                res=flushedCategory();
                                break;
                            case "15":
                                res=categoryList.toString();
                                break;
                            case "16":
                                res=addAddress(strs);
                                break;
                            case "17":
                                res=deleteAddress(strs);
                                break;
                            case "18":
                                res=updateAddress(strs);
                                break;
                            case "19":
                                res=addressList.toString();
                                break;
                            case "20":
                                res=flushedAddress();
                                break;
                            case "21":
                                res=propertyList.toString();
                                break;
                            case "22":
                                res=addProperty(strs);
                                break;
                            case "23":
                                res=deleteProperty(strs);
                                break;
                            case "24":
                                res=updateProperty(strs);
                                break;
                            case "25":
                                res=flushedProperty();
                                break;
                            case "26":
                                res=propertyValueList.toString();
                                break;
                            case "27":
                                res=addPropertyValue(strs);
                                break;
                            case "28":
                                res=deletePropertyValue(strs);
                                break;
                            case "29":
                                res=updatePropertyValue(strs);
                                break;
                            case "30":
                                res=flushedPropertyValue();
                                break;
                            case "31":
                                res=flushedProduct();
                                break;
                            default:
                                System.out.println("");
                        }

                    }else{
                        userList.add(admin);
                    }
                } catch (Exception e) {
                }finally {
                        try {
                            IOManager.write(res,socket);
                        } catch (IOException e) { }
                }
                }

    }
    public String logIn(String name,String password){
        String res="登入失败";
        for (int i = 0; i < userList.size(); i++) {
            User u = userList.get(i);
            if (u.getUserName().equals(name) && u.getPassword().equals(password)) {
                if(u.getAuthority().equals("admin")) {
                    res = "admin登入成功";
                }else if(u.getAuthority().equals("user")){
                    res = "user登入成功";
                }
                admin = userList.remove(i);

                break;
            }
        }
        return res;
    }
    public String enroll(String[] users){
        if(users.length!=6){
            System.out.println("请正确输入信息");
            return "请正确输入信息";
        }
        if(users[3].length()!=11){
            System.out.println("电话号格式不正确");
            return "电话号格式不正确";
        }
        for (int i = 0; i < users[3].length(); i++) {
            char c =users[3].charAt(i);
            if(!Character.isDigit(c)){
                return "电话号格式不正确";
            }
        }
        for (int i = 0; i < userList.size(); i++) {
            User u = userList.get(i);
            if (u.getUserName().equals(users[1])) {
                System.out.println("用户名重复");
                return "用户名重复";
            }
        }
        if(!("user".equals(users[5])||"admin".equals(users[5]))){
            return "权限输入不正确";
        }
        double money = Double.parseDouble(users[4]);
        User user = new User(userList.size()+1,users[1],users[2],users[3],money,users[5]);
        userList.add(user);
        userDao.insert(user);
        return "注册成功";
    }
    public String addProduct(String[] strs){
        if(strs.length!=7){
            System.out.println("请正确输入信息");
            return "请正确输入信息";
        }
        String res = "添加失败";
        try {
            double origibakprice=Double.parseDouble(strs[2]);
            int cid = Integer.parseInt(strs[3]);
            int stock = Integer.parseInt(strs[4]);
            Date date = new Date(System.currentTimeMillis());
            double promoteprice = Double.parseDouble(strs[5]);
            for (Category c: categoryList) {
                if(c.getCid()==cid){
                    Product product = new Product(productList.size(), strs[1], origibakprice,cid,stock,date,promoteprice,strs[6]);
                    productDao.insert(product);
                    res="添加成功";
                }
            }
        }catch (Exception e){
        }
        return res;
    }
    public String deleteProduct(String[] strs){
        if(strs.length!=2){
            System.out.println("请正确输入信息");
            return "请正确输入信息";
        }
        String res = "删除失败";
        try {
            int id=Integer.parseInt(strs[1]);
            for (Product p: productList) {
                if(p.getId()==id){
                    productDao.delete(p);
                    res="删除成功";
                }
            }
        }catch (Exception e){
            res = "删除失败";
        }
        return res;
    }
    public String updateProduct(String[] strs){
        if(strs.length!=7){
            System.out.println("请正确输入信息");
            return "请正确输入信息";
        }
        String res = "修改失败";
        try {
            int id=Integer.parseInt(strs[1]);
            for (int i = 0; i < productList.size(); i++) {
                Product p = productList.get(i);
                if(p.getId()==id){
                    p.setName(strs[2]);
                    p.setOrigibakprice(Double.parseDouble(strs[3]));
                    p.setCid(Integer.parseInt(strs[4]));
                    p.setStock(Integer.parseInt(strs[5]));
                    p.setPromoteprice(Double.parseDouble(strs[6]));
                    productDao.update(p);
                    res="修改成功";
                }
            }
        }catch (Exception e){
            res = "修改失败";
        }
        return res;

    }
    public String detailProduct(String[] strs){
        if(strs.length!=2){
            System.out.println("请正确输入信息");
            return "请正确输入信息";
        }
        String res = "查询失败";
        try {
            int id=Integer.parseInt(strs[1]);
            for (Product p: productList) {
                if(p.getId()==id){
                    res=productDao.detail(p);
                }
            }
        }catch (Exception e){
            res = "查询失败";
        }
        return res;
    }
    public String flushedProduct(){
        productList = (LinkedList<Product>) productDao.listAll();
        return productList.toString();
    }

    public String sendOrder(String[] strs){
        if(strs.length!=2){
            System.out.println("请正确输入信息");
            return "请正确输入信息";
        }
        String res = "修改失败";
        try {
            int id=Integer.parseInt(strs[1]);
            for (int i = 0; i < orderList.size(); i++) {
                Order order = orderList.get(i);
                if(order.getOrder_id()==id){
                    order.setOrder_status("已发货");
                    orderDao.update(order);
                    res="修改成功";
                }
            }
        }catch (Exception e){
            res = "修改失败";
        }
        return res;
    }
    public String receiveOrder(String[] strs){
        if(strs.length!=2){
            System.out.println("请正确输入信息");
            return "请正确输入信息";
        }
        String res = "修改失败";
        try {
            int id=Integer.parseInt(strs[1]);
            for (int i = 0; i < orderList.size(); i++) {
                Order order = orderList.get(i);
                if(order.getOrder_id()==id){
                    order.setOrder_status("已退货");
                    orderDao.update(order);
                    res="修改成功";
                }
            }
        }catch (Exception e){
            res = "修改失败";
        }
        return res;
    }
    public String detailOrder(String[] strs){
        if(strs.length!=2){
            System.out.println("请正确输入信息");
            return "请正确输入信息";
        }
        String res = "查询失败";
        try {
            int id=Integer.parseInt(strs[1]);
            for (int i = 0; i < orderList.size(); i++) {
                Order order = orderList.get(i);
                if(order.getOrder_id()==id){
                    res=orderDao.detail(order);
                }
            }
        }catch (Exception e){
            res = "查询失败";
        }
        return res;
    }

    public String addCategory(String[] strs){

        if(strs.length!=2){
            return "添加失败";
        }
        Category category = new Category(categoryList.size()+1,strs[1]);
        categoryDao.insert(category);
        return "添加成功";
    }
    public String deleteCategory(String[] strs){
        if(strs.length!=2){
            return "删除失败";
        }
        System.out.println("aaaa");
        try {
            int id = Integer.parseInt(strs[1]);
            for (Category address : categoryList) {
                if (address.getCid() == id) {
                    categoryDao.delete(address);
                    return "删除成功";
                }
            }
        }catch (Exception e) {
            return "删除失败";
        }
        return "删除失败";
    }
    public String updateCategory(String[] strs){
        if(strs.length!=3){
            return "更新失败";
        }
        try {
            Category category = new Category(Integer.parseInt(strs[1]), strs[2]);
            categoryDao.update(category);
            return "更新成功";
        }catch (Exception e){
            return "更新失败";
        }
    }
    public String flushedCategory(){
        categoryList = (LinkedList<Category>) categoryDao.listAll();
        return categoryList.toString();
    }

    public String addAddress(String[] strs){
        if(strs.length!=5){
            return "添加失败";
        }
        try {
            User u = new User();
            u.setId(Integer.parseInt(strs[1]));
            for (User e: userList) {
                if(e.getId()==u.getId()){
                    Address address = new Address(addressList.size() + 1, u, strs[2], strs[3], strs[4]);
                    addressDao.insert(address);
                    return "添加成功";
                }
            }
        }catch (Exception e){
            return  "添加失败";
        }
        return  "添加失败";
    }
    public String deleteAddress(String[] strs){
        if(strs.length!=2){
            return "删除失败";
        }
        try {
            int id = Integer.parseInt(strs[1]);
            for (Address address : addressList) {
                if (address.getAddrId() == id) {
                    addressDao.delete(address);
                    return "删除成功";
                }
            }
        }catch (Exception e){
            return "删除失败";
        }
        return "删除失败";
    }
    public String updateAddress(String[] strs){
        if(strs.length!=6){
            return "更新失败";
        }
        try {
            User u = new User();
            u.setId(Integer.parseInt(strs[2]));
            Address address = new Address(Integer.parseInt(strs[1]), u, strs[3], strs[4], strs[5]);
            addressDao.update(address);
            return "更新成功";
        }catch (Exception e){
            return "更新失败";
        }
    }
    public String flushedAddress(){
        addressList = (LinkedList<Address>) addressDao.listAll();
        return addressList.toString();
    }

    public String addProperty(String[] strs){
        if(strs.length!=3){
            return "添加失败";
        }
        try {
            for (Category c: categoryList) {
                if(c.getCid()==Integer.parseInt(strs[1])){
                    Property property = new Property(propertyList.size() + 1, Integer.parseInt(strs[1]), strs[2]);
                    propertyDao.insert(property);
                    return "添加成功";
                }
            }
        }catch (Exception e){
            return "添加失败";
        }
        return "添加失败";
    }
    public String deleteProperty(String[] strs){
        if(strs.length!=2){
            return "删除失败";
        }
        try {
            int id = Integer.parseInt(strs[1]);
            for (Property address : propertyList) {
                if (address.getId() == id) {
                    propertyDao.delete(address);
                    return "删除成功";
                }
            }
        }catch (Exception e){
            return "删除失败";
        }
        return "删除失败";
    }
    public String updateProperty(String[] strs){
        if(strs.length!=4){
            return "更新失败";
        }
        try {
            Property property = new Property(Integer.parseInt(strs[1]), Integer.parseInt(strs[2]), strs[3]);
            propertyDao.update(property);
            return "更新成功";
        }catch (Exception e){
            return "更新失败";
        }
    }
    public String flushedProperty(){
        propertyList = (LinkedList<Property>) propertyDao.listAll();
        return propertyList.toString();
    }

    public String addPropertyValue(String[] strs){
        if(strs.length!=4){
            return "添加失败";
        }
        try {
            int pid=Integer.parseInt(strs[1]);
            int ptid=Integer.parseInt(strs[2]);
            for (Product p: productList) {
                if(p.getId()==pid){
                    for (Property ps: propertyList) {
                        if(ps.getId()==ptid){
                            PropertyValue propertyValue = new PropertyValue(propertyValueList.size() + 1, pid, ptid, strs[3]);
                            propertyValueDao.insert(propertyValue);
                            return "添加成功";
                        }
                    }
                }
            }

            return "添加失败";
        }catch (Exception e){
            return "添加失败";
        }
    }
    public String deletePropertyValue(String[] strs){
        if(strs.length!=2){
            return "删除失败";
        }
        try {
            int id = Integer.parseInt(strs[1]);
            for (PropertyValue address : propertyValueList) {
                if (address.getId() == id) {
                    propertyValueDao.delete(address);
                    return "删除成功";
                }
            }
            return "删除失败";
        }catch (Exception e){
            return "删除失败";
        }
    }
    public String updatePropertyValue(String[] strs){
        if(strs.length!=5){
            return "更新失败";
        }
        try {
            PropertyValue propertyValue = new PropertyValue(Integer.parseInt(strs[1]),
                    Integer.parseInt(strs[2]), Integer.parseInt(strs[3]), strs[4]);
            propertyValueDao.update(propertyValue);
            return "更新成功";
        }catch (Exception e){
            return "更新失败";
        }
    }
    public String flushedPropertyValue(){
        propertyValueList = (LinkedList<PropertyValue>) propertyValueDao.listAll();
        return propertyValueList.toString();
    }

}
