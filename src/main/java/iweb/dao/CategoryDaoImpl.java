package iweb.dao;

import iweb.cla.Category;
import iweb.util.DataManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

public class CategoryDaoImpl implements Dao<Category>{


    @Override
    public Collection<Category> listAll() {
        LinkedList<Category> addressList = new LinkedList<>();
        String sql="select * from category";
        try (Connection c= DataManager.getConnection();
             PreparedStatement p = c.prepareStatement(sql);
        ){
            ResultSet res = p.executeQuery();
            while (res.next()){
                int cid = res.getInt("id");
                String name=res.getString("name");
                Category category = new Category(cid,name);
                addressList.add(category);
            }
            for (Category a: addressList) {
                System.out.println(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return addressList;
    }

    @Override
    public void insert(Category category) {
        String sql="insert into category(name) value(?)";
        try (Connection c= DataManager.getConnection();
             PreparedStatement p = c.prepareStatement(sql)
        ){
            if(category==null){
                System.out.println("参数有误，请检查");
                return ;
            }
            p.setString(1,category.getCname());
            p.execute();
            System.out.println("分类添加成功");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void delete(Category category) {
        String sql="delete from category where id=?";
        try (Connection c= DataManager.getConnection();
             PreparedStatement p = c.prepareStatement(sql)
        ){
            if(category==null){
                System.out.println("参数有误，请检查");
                return ;
            }
            p.setInt(1,category.getCid());
            p.execute();
            System.out.println("分类删除成功");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Category category) {
        String sql = "update category set name=? where id=?";
        try (Connection c= DataManager.getConnection();
             PreparedStatement p = c.prepareStatement(sql)
        ){
            p.setString(1,category.getCname());
            p.setInt(2,category.getCid());
            p.execute();
            System.out.println("分类修改成功");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String detail(Category category) {
        return null;
    }
}
