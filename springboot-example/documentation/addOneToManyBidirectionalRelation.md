* ### bidirectional(çift yönlü)  ilişki 
    bidirectional ilişkide kaynak(source) entity den hedef(target) entity'e doğru erişim olduğu gibi.target tan source'a doğruda erişim olur.

* ### Create scriptlerinin liquibase'e eklenmesi
    master.xml'e aşağıdaki satır eklenerek değişiklikler liquibase ile ilişkilendirilir.
    ```
    <include file="/db-changelog/changes/create-scripts.xml"/>
    ```
    
  create-scripts.xml de çağrılan create.sql ve insert.sql dosyasındaki sql cümleleri CUSTOMERS ve ORDERS tablolarını oluşturarak default değerler insert edecektir.

* ### Repository tanımının yapılması
    Customer ve Order DB işlemlerinin yapılabilmesi için JpaRepository den extends edilecek şekilde BookRepository adında bir interface oluşturulur
    ```
    @Repository
    public interface CustomerRepository extends JpaRepository<Customer, Long> {}
    ```

    ```  
    @Repository
    public interface OrderRepository extends JpaRepository<Order, Long> {}
    ```

* ### Entity lerin eklenmesi
    Customer  ve Order bilgilerini turacak şekilde  birer enity oluşturulur. 
    ```
    @Entity
    @Table(name = "customers")
    public class Customer {
        ...
        @JsonIgnore
        @OneToMany(mappedBy = "customer")
        private Set<Order> orders = new HashSet<>();
        ...
    }
    ```

    ```
    @Entity
    @Table(name = "orders")
    public class Order {
        ...
        @ManyToOne
        @JoinColumn(name = "customer_id")
        private Customer customer;
        ...
    }
    ```



* ### Endpointlere erişim
    <b>Order eklemek için  : </b> http://localhost:8080/order/add

     <b>Tüm sipariş listesi için : </b> http://localhost:8080/order/list

     <b>ID'ye göre sipariş listelemek için : </b> http://localhost:8080/order/2

     <b>sipariş ve Sayfalarını silme işlemi </b>
    ```
    curl --location --request DELETE 'http://localhost:8080/order/delete/1'
    ```

[index için tıklayın](../README.md)
