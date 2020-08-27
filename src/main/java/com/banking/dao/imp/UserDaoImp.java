package com.banking.dao.imp;

import com.banking.dao.RoleDao;
import com.banking.dao.UserAccountDao;
import com.banking.dao.UserDao;
import com.banking.model.User;
import com.banking.util.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImp implements UserDao {

    @Override
    public User createUser(User user) {
        //Unprepared Sql Statement
        String sql = "insert into "+ConnectionManager.SCHEMA+".user " +
                "(username,password,first_name,last_name,email,role_id) " +
                "values (?,?,?,?,?,?) returning user_id";
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)){

            ps.setString(1,user.getUsername());
            ps.setString(2,user.getPassword());
            ps.setString(3,user.getFirstName());
            ps.setString(4,user.getLastName());
            ps.setString(5,user.getEmail());
            ps.setInt(6,user.getRole().getRoleId());

            ResultSet rs = ps.executeQuery();
            // Executes the query and returns a result set
            while (rs.next()){
                user.setUserId(rs.getInt("user_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User updateUser(User user) {
        //Updates the user
        String sql = "update "+ConnectionManager.SCHEMA+".user set username = ?, password = ?, " +
                "first_name = ?, last_name = ?, email = ?, role_id = ? where user_id = ?";
        try(Connection connection = ConnectionManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){

            ps.setString(1,user.getUsername());
            ps.setString(2,user.getPassword());
            ps.setString(3,user.getFirstName());
            ps.setString(4,user.getLastName());
            ps.setString(5,user.getEmail());
            ps.setInt(6,user.getRole().getRoleId());
            ps.setInt(7,user.getUserId());

            int affectedRows = ps.executeUpdate();
            if(affectedRows>0){
                try(ResultSet rs = ps.getGeneratedKeys()) {
                    RoleDao roleDao = new RoleDaoImp();
                    while (rs.next()) {
                        user.setUserId(rs.getInt("user_id"));
                        user.setUsername(rs.getString("username"));
                        user.setPassword(rs.getString("password"));
                        user.setFirstName(rs.getString("first_name"));
                        user.setLastName(rs.getString("last_name"));
                        user.setEmail(rs.getString("email"));
                        user.setRole(roleDao.findRole(rs.getInt("role_id")));
                    }
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> findUsers() {
        String sql = "select * from "+ConnectionManager.SCHEMA+".user";
        List<User> userList = new ArrayList<>();
        try(Connection connection = ConnectionManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            RoleDao roleDao = new RoleDaoImp();
            UserAccountDao userAccountDao = new UserAccountDaoImp();

            while (rs.next()){
                userList.add(new User(
                rs.getInt("user_id"),
                rs.getString("username"),
                rs.getString("password"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getString("email"),
                roleDao.findRole(rs.getInt("role_id")),
                userAccountDao.findAccountsByUser(rs.getInt("user_id"))
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public User findUserById(int userId) {
        String sql = "select * from "+ConnectionManager.SCHEMA+".user where user_id = ?";
        User user = null;
        try(Connection connection = ConnectionManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1,userId);
            ResultSet rs = ps.executeQuery();
            RoleDao roleDao = new RoleDaoImp();
            UserAccountDao userAccountDao = new UserAccountDaoImp();

            while (rs.next()){
                user = new User(
                        rs.getInt("user_id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        roleDao.findRole(rs.getInt("role_id")),
                        userAccountDao.findAccountsByUser(rs.getInt("user_id"))
                );
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User deleteUser(User user) {
        String sql = "delete from "+ConnectionManager.SCHEMA+".user where user_id = ?";
        try(Connection connection = ConnectionManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return user;
    }
}
