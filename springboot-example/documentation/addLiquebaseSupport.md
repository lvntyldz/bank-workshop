* ### SQL Script Fileların oluşturulması
- scripts dizininin altında create.sql ve insert.sql adında iki dosya oluşturulur. create scriptlerinin create.sql de insert scriptlerinin ise insert.sql dosyasında tutulması amaçlanır.
- create-scripts.xml adında bir xml dosyası oluşturulur.
bu dosyaların içine ağaşıdaki gibi create ve insert scriptleri eklenir.
```
CREATE TABLE CUSTOMERS (
    ID BIGINT NOT NULL AUTO_INCREMENT,
    NAME VARCHAR(252),
    CREATE_DATE TIMESTAMP,
    PRIMARY KEY (ID)
);
```

```
INSERT INTO CUSTOMERS (ID, NAME, CREATE_DATE) VALUES (3, 'Ali',  CURRENT_TIMESTAMP);
INSERT INTO CUSTOMERS (ID, NAME, CREATE_DATE) VALUES (4, 'Can',  CURRENT_TIMESTAMP);
```

* ### SQL Script Fileların changeset'e eklenmesi
scripts.xml dısyasına aşağıdaki satırlar eklenerek SQL dosyaları üzerindeki değişiklikler takip edilir.
```
<changeSet author="levent.yildiz" id="07-10-2020_18:37" runOnChange="true">
    <sqlFile path="/db-changelog/changes/scripts/create.sql" stripComments="true"/>
</changeSet>

<changeSet author="levent.yildiz" id="07-10-2020_18:38" runOnChange="true">
    <sqlFile path="/db-changelog/changes/scripts/insert.sql" stripComments="true"/>
</changeSet>
```

master.xml'e aşağıdaki satır eklenerek değişiklikler liquibase ile ilişkilendirilir.
```
<include file="/db-changelog/changes/create-scripts.xml"/>
```

* ### runOnChange parametresi
changeset'e eklenen runOnChange=true parametresiyle dosyalardaki bir değişiklik olması durumunda dosya içindeki SQL cümleleri yeniden koşulur
```
<changeSet author="levent.yildiz" id="07-10-2020_18:37" runOnChange="true">
...
</changeSet>
```
* ### H2-console üzerinden sonuçların gözlemlenmesi
http://localhost:8080/h2-console adresine login olunarak aşağıdaki sorgular ile sonuçlar gözlemlenebilir.
```
SELECT * FROM CUSTOMERS 
```
```
SELECT * FROM ORDERS 
```  
[index için tıklayın](../README.md)
