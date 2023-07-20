package iweb.dao;

import iweb.cla.Address;
import iweb.cla.User;
import iweb.util.DataManager;

import java.sql.*;
import java.util.Collection;
import java.util.LinkedList;

public class AddressDaoImpl implements Dao<Address> {
    //
    UserDaoImpl userDao = new UserDaoImpl();
    LinkedList<User> userList = (LinkedList<User>) userDao.listAll();
    @Override
    public Collection listAll() {
        LinkedList<Address> addressList = new LinkedList<>();
        String sql="select * from address";
        try (Connection c= DataManager.getConnection();
             PreparedStatement p = c.prepareStatement(sql);
        ){
            ResultSet res = p.executeQuery();
            while (res.next()){
                int rid = res.getInt("rid");
                int uid = res.getInt("uid");
                String province_addr=res.getString("province_addr");
                String city_addr=res.getString("city_addr");
                String detail_addr=res.getString("detail_addr");
                for (int i = 0; i < userList.size(); i++) {
                    User u = userList.get(i);
                    if(u.getId()==uid){
                        Address address = new Address(rid,u,province_addr,city_addr,detail_addr);
                        addressList.add(address);
                    }
                }

            }
            System.out.println(addressList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return addressList;
    }

    @Override
    public void insert(Address address) {
        String sql="insert into address(uid,province_addr,city_addr,detail_addr) value(?,?,?,?)";
        try (Connection c= DataManager.getConnection();
             PreparedStatement p = c.prepareStatement(sql)
        ){
            if(address==null){
                System.out.println("参数有误，请检查");
                return ;
            }
            p.setInt(1,address.getUser().getId());
            p.setString(2,address.getProvince());
            p.setString(3,address.getCity());
            p.setString(4,address.getDetail());
            p.execute();
            System.out.println("地址添加成功");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Address address) {
        String sql="delete from address where rid=?";
        try (Connection c= DataManager.getConnection();
             PreparedStatement p = c.prepareStatement(sql)
        ){
            if(address==null){
                System.out.println("参数有误，请检查");
                return ;
            }
            p.setInt(1,address.getAddrId());
            p.execute();
            System.out.println("地址删除成功");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Address address) {
        String sql = "update address set uid=? , province_addr=? ,city_addr=? , detail_addr=? where rid=?";
        try (Connection c= DataManager.getConnection();
             PreparedStatement p = c.prepareStatement(sql)
        ){
            p.setInt(1,address.getUser().getId());
            p.setString(2,address.getProvince());
            p.setString(3,address.getCity());
            p.setString(4,address.getDetail());
            p.setInt(5,address.getAddrId());
            p.execute();
            System.out.println("地址修改成功");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String detail(Address address) {
        return null;
    }
}
