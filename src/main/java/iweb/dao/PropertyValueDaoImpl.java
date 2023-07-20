package iweb.dao;

import iweb.cla.PropertyValue;
import iweb.util.DataManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

public class PropertyValueDaoImpl implements Dao<PropertyValue> {


    @Override
    public Collection<PropertyValue> listAll() {
        LinkedList<PropertyValue> propertyValueList = new LinkedList<>();
        String sql="select * from propertyvalue";
        try (Connection c= DataManager.getConnection();
             PreparedStatement p = c.prepareStatement(sql);
        ){
            ResultSet res = p.executeQuery();
            while (res.next()){
                int id = res.getInt("id");
                int pid = res.getInt("pid");
                int ptid = res.getInt("ptid");
                String value=res.getString("value");
                PropertyValue propertyValue = new PropertyValue(id,pid,ptid,value);
                propertyValueList.add(propertyValue);
            }
            for (PropertyValue a: propertyValueList) {
                System.out.println(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return propertyValueList;
    }

    @Override
    public void insert(PropertyValue propertyValue) {
        String sql="insert into propertyvalue(pid,ptid,`value`) value(?,?,?)";
        try (Connection c= DataManager.getConnection();
             PreparedStatement p = c.prepareStatement(sql)
        ){
            if(propertyValue==null){
                System.out.println("参数有误，请检查");
                return ;
            }
            p.setInt(1,propertyValue.getPid());
            p.setInt(2,propertyValue.getPtid());
            p.setString(3,propertyValue.getValue());
            p.execute();
            System.out.println("属性值添加成功");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(PropertyValue propertyValue) {
        String sql="delete from propertyvalue where id=?";
        try (Connection c= DataManager.getConnection();
             PreparedStatement p = c.prepareStatement(sql)
        ){
            if(propertyValue==null){
                System.out.println("参数有误，请检查");
                return ;
            }
            p.setInt(1,propertyValue.getId());
            p.execute();
            System.out.println("属性值删除成功");
        } catch (SQLException e) {
        }
    }

    @Override
    public void update(PropertyValue propertyValue) {
        String sql = "update propertyvalue set pid=?,ptid=?,`value`=? where id=?";
        try (Connection c= DataManager.getConnection();
             PreparedStatement p = c.prepareStatement(sql)
        ){
            p.setString(3,propertyValue.getValue());
            p.setInt(1,propertyValue.getPid());
            p.setInt(2,propertyValue.getPtid());
            p.setInt(4,propertyValue.getId());
            p.execute();
            System.out.println("属性值更新成功");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String detail(PropertyValue propertyValue) {
        return null;
    }
}
