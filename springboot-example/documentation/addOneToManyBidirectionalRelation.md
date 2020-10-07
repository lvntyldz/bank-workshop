* ### bidirectional(tek yönlü)  ilişki 
    bidirectional ilişkide kaynak(source) entity den hedef(target) entity'e doğru erişim olduğu gibi.target tan source'a doğruda erişim olur.

* ### Create scriptlerinin liquibase'e eklenmesi
    create-book-table.xml ve create-page-table.xml dosyaları daha önce gerekli configurasyonları yapılan master.xml dosyasına eklenir.
    ```
    ```

* ### Repository tanımının yapılması
    Book DB işlemlerinin yapılabilmesi için JpaRepository den extends edilecek şekilde BookRepository adında bir interface oluşturulur
    ```
    ```

* ### Entity lerin eklenmesi
    Kitap ve sayflarını turacak şekilde Page ve Book adında birer enity oluşturulur. 
    ```
    ```

    ```
    ```


* ### OneToMany ilişkinin Book'a eklenmesi 
    Oluşturulan Book entitysi ile Page entitysi arasında one to many ilişki kurmak için  <b>@OneToMany</b> annotation'ı kullanılır.
    ```
    ```
  - <b> cascade = CascadeType.ALL : </b> üst entity eklendiği,silindiği,refresh edildiği zaman alt entity de buradaki aksiyondan birebir etkilenir.
  - <b> @JoinColumn(name="book_id") : </b> Burada Book ve Page ilişkisinin book_id ile tutulacağı belirtilmiş olur. PAGES tablosunda BOOK_ID adında bir sütun oluşturulmalıdır.

###### NOTE: Eğer @JoinColumn kullanılmadan aşağıdaki gibi sadece @OneToMany satırındaki tanım yapılsaydı bu durumda hibernate varsayılan olarak PAGES ve BOOKS tablolarını ilişkilendirmek için BOOK_PAGES adında bir ilişki tablosu(ara tablo) arayacaktı. Ve ilişkiler ara tablo üzerinden tanımlanacaktı.   
@JoinColumn kullanılmasıyla birlikte yeni bir tabloya ihtiyaç duymadan mevcut entity'e bir ilişki sütunu eklenerek ilişkiler yönetilir.

```
```

* ### Endpointlere erişim
    <b>Kitap eklemek için  : </b> http://localhost:8080/book/add

     <b>Tüm kitap listesi için : </b> http://localhost:8080/book/list

     <b>ID'ye göre kitap listelemek için : </b> http://localhost:8080/book/2

     <b>Kitap ve Sayfalarını silme işlemi </b>
    ```
    curl --location --request DELETE 'http://localhost:8080/book/delete/1'
    ```

[index için tıklayın](../README.md)
