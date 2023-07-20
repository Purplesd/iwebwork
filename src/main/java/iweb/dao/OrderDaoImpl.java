package iweb.dao;

import iweb.cla.Order;
import iweb.util.DataManager;

import java.sql.*;
import java.util.Collection;
import java.util.LinkedList;

public class OrderDaoImpl implements Dao<Order>{
    @Override
    public Collection listAll() {
        LinkedList<Order> orderList = new LinkedList<>();
        String sql="select * from `order`";
        try (Connection c= DataManager.getConnection();
             PreparedStatement p = c.prepareStatement(sql);
        ){
            ResultSet res = p.executeQuery();
            while (res.next()){
                int order_id = res.getInt("order_id");
                int user_id = res.getInt("user_id");
                int address_id = res.getInt("address_id");
                Date order_date=res.getDate("order_date");
                String order_status=res.getString("order_status");
                Order order = new Order(order_id,user_id,address_id,order_date,order_status);
                orderList.add(order);
            }
            System.out.println(orderList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderList;
    }

    @Override
    public void insert(Order order) {

    }

    @Override
    public void delete(Order order) {
    }

    @Override
    public void update(Order order) {
        String sql = "update `order` set order_status=?where order_id=?";
        try (Connection c= DataManager.getConnection();
             PreparedStatement p = c.prepareStatement(sql)
        ){
            if(order==null){
                System.out.println("参数有误，请检查");
            }
            p.setString(1,order.getOrder_status());
            p.setDouble(2,order.getOrder_id());
            p.execute();
            System.out.println("修改成功");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public String detail(Order order) {
        String str="";
        String sql = "select * from order_detail LEFT JOIN product on order_detail.pid=product.id where oid=?";
        try (Connection c= DataManager.getConnection();
             PreparedStatement p = c.prepareStatement(sql)
        ){
            if(order==null){
                System.out.println("参数有误，请检查");
            }
            p.setDouble(1,order.getOrder_id());
            ResultSet res = p.executeQuery();
            while (res.next()){
                int pid = res.getInt("pid");
                String name = res.getString("name");
                int quantity = res.getInt("quantity");
                str+="[商品:"+name+","+"数量:"+quantity+"],";

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String sql2 = "select * from address where rid=?";
        try (Connection c= DataManager.getConnection();
             PreparedStatement p = c.prepareStatement(sql2)
        ){
            if(order==null){
                System.out.println("参数有误，请检查");
            }
            p.setDouble(1,order.getAddress_id());
            ResultSet res = p.executeQuery();
            while (res.next()){
                String province_addr = res.getString("province_addr");
                String city_addr = res.getString("city_addr");
                String detail_addr = res.getString("detail_addr");
                str+="地址："+province_addr+city_addr+detail_addr;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return str;
    }
}
