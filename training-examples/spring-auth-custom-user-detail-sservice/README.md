* ### Referanslar
    Bu çalışmadaki örnekler **spring-auth-custom-user-detail-sservice** adlı projede  kodlanmıştır.

* ### Entitylerin oluşturulması
* ### Repositorylerin oluşturulması
* ### Password Encoder'un belirlenmesi
* ### DaoAuthenticationProvider'ın parametrelerinin verilmesi
* ### UserDetails'in implement edilmesi
* ### UserDetailsService'in implement edilmesi
* ### Tabloların oluşturulması
* ### Default user ve passwordün insert edilmesi
* ### Endpoint lere erişim
    - Authentication yaparak kullanıcı ekleme (u:admin p:123)
    ```
    curl --location --request GET 'http://localhost:8080/user/add' \
    --header 'Content-Type: application/json' \
    --header 'Authorization: Basic YWRtaW46MTIz' 
    ```    


* ### Kaynaklar
    - https://www.codejava.net/frameworks/spring-boot/spring-boot-security-role-based-authorization-tutorial 

[index için tıklayın](../README.md)
    
