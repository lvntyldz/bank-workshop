package com.ba.run;

import com.ba.dao.UserDao;
import com.ba.dao.UserDaoImpl;
import com.ba.dto.UserDTO;
import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import java.io.Reader;
import java.util.List;

public class ListUsers {

    public static void main(String[] args) throws Exception {
        UserDao dao = new UserDaoImpl();
        Reader reader = Resources.getResourceAsReader("sql-maps-config.xml");
        SqlMapClient client = SqlMapClientBuilder.buildSqlMapClient(reader);

        List<UserDTO> users = dao.listUsers(client);
        for (UserDTO user : users) {
            System.out.println("users : " + user);
        }

    }
}
