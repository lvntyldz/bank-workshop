package com.ba.dao;

import com.ba.dto.UserDTO;
import com.ibatis.sqlmap.client.SqlMapClient;

import java.util.List;

public interface UserDao {
    public List<UserDTO> addUsers(List<UserDTO> users, SqlMapClient client);

    public List<UserDTO> listUsers(SqlMapClient client);

    public UserDTO addUser(UserDTO user, SqlMapClient client);

    public UserDTO getUserById(Integer id, SqlMapClient client);

    public void deleteUserById(Integer id, SqlMapClient client);
}
