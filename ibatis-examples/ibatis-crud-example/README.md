* ### Referanslar
    Bu çalışmadaki örnekler **ibatis-crud-example** adlı projede kodlanmıştır.
    
* ### Açıklamalar
    - çalışmada Ibatis ile MySQL DB üzerinde CRUD işlemi yapımıştır.

* ### Bağımlılıklar
    - pom.xml doyasına ibatis,mysql-connector eklenmelidir. 
    - Lombok ve log4j opsiyoneldir.
    ```
    <dependency>
        <groupId>org.apache.ibatis</groupId>
        <artifactId>ibatis-sqlmap</artifactId>
        <version>2.3.4.726</version>
    </dependency>
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>5.1.9</version>
    </dependency>
    <dependency>
        <groupId>log4j</groupId>
        <artifactId>log4j</artifactId>
        <version>1.2.17</version>
    </dependency>
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <version>1.16.10</version>
        <scope>provided</scope>
    </dependency>
    ```
* ### Database'in ve tabloların oluşturulması
    ```
    CREATE DATABASE DEMODB;
    
    CREATE TABLE DEMODB.USERINFO(
        ID       INT,
        ISIM     VARCHAR(100),
        EMAIL    VARCHAR(50),
        PASSWORD VARCHAR(16),
        STATUS   INT
    );
    ```

* ### batis-config.xml
    - DB bağlantı bilgileri config dosyasından yapılır.
    ```
    <transactionManager type="JDBC">
        <dataSource type="SIMPLE">
            <property name="JDBC.Driver" value="com.mysql.jdbc.Driver"/>
            <property name="JDBC.ConnectionURL" value="jdbc:mysql://localhost:3306/DEMODB"/>
            <property name="JDBC.Username" value="root"/>
            <property name="JDBC.Password" value="1234567890"/>
        </dataSource>
    </transactionManager>
    ```

    - Map edilecek classların tanımlandığı xml ler import edilir.
    ```
    <sqlMap resource="user.xml"/>
    ``` 
    
    - batis-config.xml dosyasının son hali aşağıdaki gibi olacaktır
    ```
    <?xml version="1.0" encoding="UTF-8" ?>
    <!DOCTYPE sqlMapConfig
            PUBLIC "-//ibatis.apache.org//DTD SQL Map Config 2.0//EN"
            "http://ibatis.apache.org/dtd/sql-map-config-2.dtd">
    <sqlMapConfig>
    
        <settings useStatementNamespaces="true"/>
    
        <transactionManager type="JDBC">
            <dataSource type="SIMPLE">
                <property name="JDBC.Driver" value="com.mysql.jdbc.Driver"/>
                <property name="JDBC.ConnectionURL" value="jdbc:mysql://localhost:3306/DEMODB"/>
                <property name="JDBC.Username" value="root"/>
                <property name="JDBC.Password" value="1234567890"/>
            </dataSource>
        </transactionManager>
    
        <sqlMap resource="user.xml"/>
    
    </sqlMapConfig>
    ```

* ### user.xml
    - SQL cümleleri ve MAP edilecke classlar bu xml dosyalarında tanımlanır.
    - Buradaki örnekte UserDTO classı ve bu class üzerindne CRUD yapacak SQL cümleleri tanımlanmıştır.
    - Java katmanından erişim namespace ler ile başlar
    ```
    client.insert("user.addUser", user);
    ```
    - xml tarafındaki karşılığı ise aşağıdaki gibidir
    ```
    <sqlMap namespace="user">
        ...
        <insert id="addUser" parameterClass="IBatisUser">
            INSERT INTO USERINFO (ID,ISIM,EMAIL,PASSWORD,STATUS)
             VALUES(#id#,#name#,#email#,#password#,#status#);
        </insert>
        ...
    </sqlMap>
    ```
    
    - XML içerisinde MAP edilen class'a tek tek erişim yapılabileceği gibi bir alias oluşturarak bu alias üzeirnden de erişim sağlanabilir.
    ```
    <typeAlias alias="IBatisUser" type="com.ba.dto.UserDTO"/>
    ```
    
    - DTO ve DB tablosunun eşleştirilmesi aşağıdaki gibi olacaktır.
    ```
    <resultMap id="userResultMap" class="IBatisUser">
        <result property="id" column="ID"/>
        <result property="name" column="ISIM"/>
        <result property="email" column="EMAIL"/>
        <result property="password" column="PASSWORD"/>
        <result property="status" column="STATUS"/>
    </resultMap>
    ```
    
    - user.xml dosyasının son hali aşağıdaki gibi olacaktır
    ```
    <?xml version="1.0" encoding="UTF-8"?>
    <!DOCTYPE sqlMap  PUBLIC "-//ibatis.apache.org//DTD SQL Map 2.0//EN" "http://ibatis.apache.org/dtd/sql-map-2.dtd">
    <sqlMap namespace="user">
    
        <typeAlias alias="IBatisUser" type="com.ba.dto.UserDTO"/>
    
        <resultMap id="userResultMap" class="IBatisUser">
            <result property="id" column="ID"/>
            <result property="name" column="ISIM"/>
            <result property="email" column="EMAIL"/>
            <result property="password" column="PASSWORD"/>
            <result property="status" column="STATUS"/>
        </resultMap>
    
        <select id="getUsers"  resultMap="userResultMap">
              SELECT * FROM USERINFO
        </select>
    
        <select id="getUserById" parameterClass="java.lang.Integer" resultMap="userResultMap">
              SELECT * FROM USERINFO WHERE ID = #value#
        </select>
    
        <select id="getMaxId" resultClass="java.lang.Integer">
              SELECT MAX(ID) FROM USERINFO
        </select>
    
        <insert id="addUser" parameterClass="IBatisUser">
            INSERT INTO USERINFO (ID,ISIM,EMAIL,PASSWORD,STATUS)
             VALUES(#id#,#name#,#email#,#password#,#status#);
        </insert>
    
        <delete id="deleteUserById" parameterClass="java.lang.Integer">
              DELETE FROM USERINFO WHERE ID = #value#
        </delete>
    
    </sqlMap>
    ```

* ### Run classları
    - Toplu insert : com.ba.run.BulkInsert.main
    - Silme işlemi : com.ba.run.Delete.main
    - Ekleme işlemi: com.ba.run.Insert.main
    - Listeleme işlemi : com.ba.run.ListUsers.main

* ### Kaynaklar
    - https://howtodoinjava.com/ibatis/ibatis-hello-world-example/

[index için tıklayın](../README.md)
