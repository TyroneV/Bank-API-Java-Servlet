package com.banking.dao;

import com.banking.model.Role;

import java.util.List;

public interface RoleDao {
    //Read
    Role findRole(int roleId);
    List<Role> findRoles();
}
