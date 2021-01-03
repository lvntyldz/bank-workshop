package com.ba.run;

import com.ba.dao.UserDao;
import com.ba.dao.UserDaoImpl;
import com.ba.dto.UserDTO;
import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class BulkInsert {

    public static void main(String[] args) throws Exception {
        UserDao dao = new UserDaoImpl();
        Reader reader = Resources.getResourceAsReader("batis-config.xml");
        SqlMapClient client = SqlMapClientBuilder.buildSqlMapClient(reader);

        List<UserDTO> users = new ArrayList<UserDTO>();
        users.add(new UserDTO(null, "Ali", "ali@ali.com", "a12345", 1));
        users.add(new UserDTO(null, "Veli", "veli@veli.com", "a12345", 1));
        users.add(new UserDTO(null, "Ahmet", "ahmet@ahmet.com", "a12345", 1));
        dao.addUsers(users, client);
    }
}
