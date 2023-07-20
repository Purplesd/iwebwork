package iweb.dao;

import iweb.cla.Property;
import iweb.util.DataManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

public class PropertyDaoImpl implements Dao<Property> {

    @Override
    public Collection<Property> listAll() {
        LinkedList<Property> propertyList = new LinkedList<>();
        String sql="select * from property";
        try (Connection c= DataManager.getConnection();
             PreparedStatement p = c.prepareStatement(sql);
        ){
            ResultSet res = p.executeQuery();
            while (res.next()){
                int id = res.getInt("id");
                int cid = res.getInt("cid");
                String name=res.getString("name");
                Property property = new Property(id,cid,name);
                propertyList.add(property);
            }
            for (Property a: propertyList) {
                System.out.println(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return propertyList;
    }

    @Override
    public void insert(Property property) {
        String sql="insert into property(cid,name) value(?,?)";
        try (Connection c= DataManager.getConnection();
             PreparedStatement p = c.prepareStatement(sql)
        ){
            if(property==null){
                System.out.println("参数有误，请检查");
                return ;
            }
            p.setInt(1,property.getCid());
            p.setString(2,property.getName());
            p.execute();
            System.out.println("属性添加成功");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Property property) {
        String sql="delete from property where id=?";
        try (Connection c= DataManager.getConnection();
             PreparedStatement p = c.prepareStatement(sql)
        ){
            if(property==null){
                System.out.println("参数有误，请检查");
                return ;
            }
            p.setInt(1,property.getId());
            p.execute();
            System.out.println("属性删除成功");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Property property) {
        String sql = "update property set cid=?,name=? where id=?";
        try (Connection c= DataManager.getConnection();
             PreparedStatement p = c.prepareStatement(sql)
        ){
            p.setString(2,property.getName());
            p.setInt(1,property.getCid());
            p.setInt(3,property.getId());
            p.execute();
            System.out.println("属性更新成功");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String detail(Property property) {
        return null;
    }
}
