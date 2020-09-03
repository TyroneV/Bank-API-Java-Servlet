package com.banking.dao.imp;

import com.banking.dao.RoleDao;
import com.banking.model.Role;
import com.banking.util.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleDaoImp implements RoleDao {
    @Override
    public Role findRole(int roleId) {
        Role role = new Role();
        String sql = "select role_id,role from "
                +ConnectionManager.SCHEMA+
                ".role where role_id = ?";
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1,roleId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                role.setRoleId(rs.getInt("role_id"));
                role.setRole(rs.getString("role"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return role;
    }

    @Override
    public List<Role> findRoles() {
        List<Role> roleList = new ArrayList<>();
        String sql = "select role_id,role from "
                +ConnectionManager.SCHEMA+
                ".role";
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                roleList.add(new Role(rs.getInt("role_id"),rs.getString("role")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roleList;
    }
}
