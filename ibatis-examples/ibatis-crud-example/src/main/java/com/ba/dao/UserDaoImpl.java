package com.ba.dao;

import com.ba.dto.UserDTO;
import com.ibatis.sqlmap.client.SqlMapClient;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    @Override
    public List<UserDTO> addUsers(List<UserDTO> users, SqlMapClient client) {

        List<UserDTO> dtoList = new ArrayList<UserDTO>();

        try {
            for (UserDTO user : users) {
                Integer id = getNextUserId(client);
                user.setId(id);
                user.setStatus(1);
                client.insert("user.addUser", user);
                dtoList.add(getUserById(id, client));
            }

        } catch (Exception e) {
            System.out.println("DB işlemleri gerçekleşemedi! e:" + e);
        }
        return dtoList;
    }

    @Override
    public List<UserDTO> listUsers(SqlMapClient client) {

        try {
            return (List<UserDTO>) client.queryForList("user.getUsers");
        } catch (SQLException e) {
            System.out.println("DB işlemleri gerçekleşemedi! e:" + e);
        }

        return null;
    }

    @Override
    public UserDTO addUser(UserDTO user, SqlMapClient client) {
        try {
            Integer id = getNextUserId(client);
            user.setId(id);
            client.insert("user.addUser", user);
            return getUserById(id, client);
        } catch (Exception e) {
            System.out.println("DB işlemleri gerçekleşemedi! e:" + e);
        }
        return null;
    }

    @Override
    public UserDTO getUserById(Integer id, SqlMapClient client) {
        try {
            UserDTO user = (UserDTO) client.queryForObject("user.getUserById", id);
            return user;
        } catch (Exception e) {
            System.out.println("DB işlemleri gerçekleşemedi! e:" + e);
        }
        return null;
    }

    @Override
    public void deleteUserById(Integer id, SqlMapClient client) {
        try {
            client.delete("user.deleteUserById", id);
        } catch (Exception e) {
            System.out.println("DB işlemleri gerçekleşemedi! e:" + e);
        }
    }

    private Integer getNextUserId(SqlMapClient client) throws SQLException {
        Integer id = (Integer) client.queryForObject("user.getMaxId");
        id = id == null ? 1 : id + 1;
        return id;
    }
}
