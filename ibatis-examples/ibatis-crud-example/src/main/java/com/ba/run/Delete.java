package com.ba.run;

import com.ba.dao.UserDao;
import com.ba.dao.UserDaoImpl;
import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import java.io.Reader;

public class Delete {

    public static void main(String[] args) throws Exception {
        UserDao dao = new UserDaoImpl();
        Reader reader = Resources.getResourceAsReader("sql-maps-config.xml");
        SqlMapClient client = SqlMapClientBuilder.buildSqlMapClient(reader);

        dao.deleteUserById(1, client);
    }
}
