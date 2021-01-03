package com.ba.dao;

import com.ba.dto.UserDTO;
import com.ibatis.sqlmap.client.SqlMapClient;

import java.util.List;

public interface UserDao {
    public List<UserDTO> addUsers(List<UserDTO> users, SqlMapClient sqlmapClient);

    public UserDTO addUser(UserDTO user, SqlMapClient sqlmapClient);

    public UserDTO getUserById(Integer id, SqlMapClient sqlmapClient);

    public void deleteUserById(Integer id, SqlMapClient sqlmapClient);
}
