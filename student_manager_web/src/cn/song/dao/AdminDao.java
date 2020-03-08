package cn.song.dao;

import cn.song.model.Admin;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDao extends BaseDao{

    public Admin login(String name,String password){
        String sql = "select id,name,password,status from s_admin where name = '" + name + "' and password = '" + password +"'";
        ResultSet resultSet = super.query(sql);

        try {
            if (resultSet != null && resultSet.next() && resultSet.getInt("status") ==1){
                Admin admin = new Admin();
                admin.setId(resultSet.getInt("id"));
                admin.setName(resultSet.getString("name"));
                admin.setPassword(resultSet.getString("password"));
                admin.setStatus(1);
                return admin;
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }finally {
            try {   //释放结果集
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            super.release();
        }
        return null;


    }


    public boolean editPassword(Admin admin,String newPassword) {
        String sql = "update s_admin set password = '"+newPassword+"' where id = " + admin.getId();
        return update(sql);
    }

}
