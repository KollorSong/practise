package cn.song.dao;

import cn.song.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Description: 封装基本操作
 * @author: syq
 * @Date: 2020/3/7
 * @param:
 * @return:
 * @throws：
 */
public class BaseDao {

    private DbUtil dbUtil = new DbUtil();
    PreparedStatement preparedStatement = null;

    /**
     * @Description: 获得连接
     * @author: syq
     * @Date: 2020/3/8
     * @param:
     * @return:
     * @throws：
     */
    public Connection getConnection(){
        return dbUtil.getConnection();
    }

    /**
     * @Description: 关闭数据库连接，释放资源
     * @author: syq
     * @Date: 2020/3/7
     * @param:
     * @return:
     * @throws：
     */
    public void release(){  //关闭声明和连接
        if(preparedStatement != null){
            try {
                //关闭声明
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        //关闭连接
        dbUtil.closeCon();
    }

    /**
     * @Description: 基础查询
     * @author: syq
     * @Date: 2020/3/7
     * @param:
     * @return:
     * @throws：
     */
    public ResultSet query(String sql){
        try {
            preparedStatement = dbUtil.getConnection().prepareStatement(sql);
             return preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @Description: 改变数据库内容操作
     * @author: syq
     * @Date: 2020/3/8
     * @param:
     * @return:
     * @throws：
     */
    public boolean update(String sql){
        try {
            return dbUtil.getConnection().prepareStatement(sql).executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }




}
