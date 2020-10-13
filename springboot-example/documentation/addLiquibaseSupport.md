* ### Bağımlılıkların(dependency) eklenmesi
    ```
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-jdbc</artifactId>
    </dependency>
    <dependency>
        <groupId>org.liquibase</groupId>
        <artifactId>liquibase-core</artifactId>
    </dependency>
    ```


* ### application.properties dosyasının güncellenmesi
    Tabloları otomotik oluşturan hibernate.ddl-auto parametresi application.properties dosyasından kaldırılır. 
    ```
    spring.jpa.hibernate.ddl-auto=create-drop
    ```
  
    liquibase'in değişiklikleri takip edebilmesi için appliation properties'e aşağıdaki parametreler eklenir.
    ```
    spring.liquibase.change-log=classpath:/db-changelog/master.xml
    logging.level.liquibase = INFO
    ```

* ### liquibase changelog dosyalarının eklenmesi
    üstte application.properties dosyasında tanımlanan yola(spring.liquibase.change-log=classpath:/db-changelog/master.xml) changelog dosyaları eklenir.
    master.xml dosyası aşağıdaki gibi olmalıdır.
    ```
    <?xml version="1.0" encoding="UTF-8"?>
    <databaseChangeLog
          xmlns="http://www.liquibase.org/xml/ns/dbchangelog"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://www.liquibase.org/xml/ns/dbchangelog http://www.liquibase.org/xml/ns/dbchangelog/dbchangelog-3.1.xsd">
    
      <include file="/db-changelog/changes/create-student-table.xml"/>
      <include file="/db-changelog/changes/insert-student-table.xml"/>
    
    </databaseChangeLog>
    ```
    create-student-table.xml dosyasında student tablosu oluşturulur.
    ```
    <changeSet author="levent.yildiz" id="1">
    
        <createTable tableName="student">
            <column autoIncrement="true" name="id" type="int(10)">
                <constraints primaryKey="true"/>
            </column>
            <column name="name" type="VARCHAR(255)"/>
            <column name="passportNumber" type="VARCHAR(255)"/>
        </createTable>
    
        <rollback>
            <dropTable tableName="student"/>
        </rollback>
    
    </changeSet>
    ```

    insert-student-table.xml dosyasında ise oluşturulan student tablosuna data insert edilir.
    ```
    <changeSet author="levent.yildiz" id="06-10-2020_17:37">
    
        <insert tableName="student">
            <column name="id" valueNumeric="1"/>
            <column name="name" value="ahmet"/>
            <column name="passportNumber" value="TC123"/>
        </insert>
    
        <insert tableName="student">
            <column name="id" valueNumeric="2"/>
            <column name="name" value="mehmet"/>
            <column name="passportNumber" value="TC345"/>
        </insert>
    
        <insert tableName="student">
            <column name="id" valueNumeric="3"/>
            <column name="name" value="ali"/>
            <column name="passportNumber" value="TC456"/>
        </insert>
    
    </changeSet>
    ```
  
    uygulama ayağa kaldırıldıktan sonra h2-console'a(http://localhost:8080/h2-console) bağlanılarak aşağıdaki sorgular çalıştırılıp son durum gözlemlenebilir.
    ```
    SELECT * FROM STUDENT 
    ```
  
    ```
    SELECT * FROM DATABASECHANGELOG  
    ```
  
[index için tıklayın](../README.md)
