package iweb.dao;

import iweb.cla.Product;
import iweb.util.DataManager;
import java.sql.*;
import java.util.Collection;
import java.util.LinkedList;
public class ProductDaoImpl implements Dao<Product>{

    @Override
    public Collection<Product> listAll() {
        LinkedList<Product> productLinkedList = new LinkedList<>();
        String sql="select * from product";
        try (Connection c= DataManager.getConnection();
             PreparedStatement p = c.prepareStatement(sql);
        ){
            ResultSet res = p.executeQuery();
            while (res.next()){
                int id = res.getInt("id");
                String name = res.getString("name");
                double origibakprice = res.getDouble("originalprice");
                int cid=res.getInt("cid");
                int stock=res.getInt("stock");
                Date creatDate=res.getDate("creatDAte");
                double promoteprice=res.getDouble("promoteprice");
                String subtitle=res.getString("subtitle");
                Product product = new Product(id,name,origibakprice,cid,stock,creatDate,promoteprice,subtitle);
                productLinkedList.add(product);
            }
            for (Product a: productLinkedList) {
                System.out.println(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productLinkedList;
    }
    @Override
    public void insert(Product product) {
        String sql="insert into product(name,originalprice,cid,stock,creatDate,promoteprice,subtitle) value(?,?,?,?,?,?,?)";
        try (Connection c= DataManager.getConnection();
             PreparedStatement p = c.prepareStatement(sql)
        ){
            if(product==null||product.getName()==null||product.getName().equals("")){
                System.out.println("参数有误，请检查");
                return ;
            }
            p.setString(1,product.getName());
            p.setDouble(2,product.getOrigibakprice());
            p.setInt(3,product.getCid());
            p.setInt(4,product.getStock());
            p.setDate(5,product.getCreatDate());
            p.setDouble(6,product.getPromoteprice());
            p.setString(7,product.getSubtitle());
            p.execute();
            System.out.println("商品添加成功");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void delete(Product product) {
        String sql="delete from product where name=?";
        try (Connection c= DataManager.getConnection();
             PreparedStatement p = c.prepareStatement(sql)
        ){
            if(product==null||product.getName()==null||product.getName().equals("")){
                System.out.println("参数有误，请检查");
                return ;
            }
            p.setString(1,product.getName());
            p.execute();
            System.out.println("商品删除成功");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Product product) {
        String sql = "update product set name=? , originalprice=? ,cid=? , stock=? ,promoteprice=? where id=?";
        try (Connection c= DataManager.getConnection();
             PreparedStatement p = c.prepareStatement(sql)
        ){
            if(product==null||product.getName()==null||product.getName().equals("")){
                System.out.println("参数有误，请检查");
            }
            p.setString(1,product.getName());
            p.setDouble(2,product.getOrigibakprice());
            p.setInt(3,product.getCid());
            p.setInt(4,product.getStock());
            p.setDouble(5,product.getPromoteprice());
            p.setInt(6,product.getId());
            p.execute();
            System.out.println("商品修改成功");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String detail(Product product) {
        String str="";
        String sql="select property.`name`as property,propertyvalue.value from  " +
                "property LEFT JOIN propertyvalue on propertyvalue.ptid=property.id where pid=?";
        try (Connection c= DataManager.getConnection();
             PreparedStatement p = c.prepareStatement(sql)
        ){
            if(product==null||product.getName()==null||product.getName().equals("")){
                System.out.println("参数有误，请检查");
                return "";
            }
            p.setInt(1,product.getId());
            ResultSet res=p.executeQuery();
            while (res.next()){
                String property= res.getString("property");
                String value= res.getString("value");
                str+=property+":"+value+",";
            }
            str=str.substring(0,str.length()-1);
            System.out.println("商品查询成功");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return str;
    }
}
