* ### FetchType[Lazy,Eager]
    Hibernate’de iki nesneyi birbirine bağlamak için, kullanılan yöntemlerden biri **EAGER** diğeri ise **LAZY** dir. <br/> 
    Eğer **EAGER** kullanırsak nesneyi veritabanından çekerken ilişkili olan  nesneyide  çekeriz.  <br/>
    Ancak **LAZY** kullanırsak, ihtiyaç duyduğumuz anda ilgili veriler çekilecektir. <br/>

* ### unidirectional(tek yönlü)  ilişkide FetchType
    Daha önceden unidirectional ilişkiyi örneklemek için <b>Book</b> ve <b>Page</b> adında birer entity tanımlanmıştı.
    Burada unidirectional ilişki kurulduğu için <b>Book</b> içinde <b>Page</b> enititysi <b>@OneToMany</b> şekilinde tanımlanmıştı. 
    ```
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "book_id")
    private Set<Page> pages;
    ```
    <b>Page</b> class'ınde ilişkiye dair bir değişiklik yapılmamıştı.

    ##### Endpoint'e erişerek Logların incelenmesi
    http://localhost:8080/book/add adresine gidip DB'ye örnek bir kayı ekledikten sonra http://localhost:8080/book/1 adresine gidildiğinde hibernate'in ilgili book'u(kitap) DB den getirmek için  SQL logları içerisinde sadece <b>BOOKS</b> tablosuna sorgu attığı görülür.
    ```
    Hibernate: 
        select
            book0_.id as id1_0_0_,
            book0_.author as author2_0_0_,
            book0_.isbn as isbn3_0_0_,
            book0_.title as title4_0_0_ 
        from
            books book0_ 
        where
            book0_.id=?
    ```
    çünkü <b>@OneToMany</b> annotationunun default **fetchType** değeri **LAZY** dir. Dolayısıyla  Page nesnesine erişmeden ilişkili sorgu çalıştırılmamaktadır. <br/>
    
    **Lazy** olarak yükelenen ilişkili nesnenin değerlerine erişmek istenildiğinde ise Hibernate loglarında aşağıdaki gibi bir ilişki sorgusu daha eklendiği görülür.
    ```
        select
            pages0_.book_id as book_id5_3_0_,
            pages0_.id as id1_3_0_,
            pages0_.id as id1_3_1_,
            pages0_.chapter as chapter2_3_1_,
            pages0_.content as content3_3_1_,
            pages0_.number as number4_3_1_ 
        from
            pages pages0_ 
        where
            pages0_.book_id=?
    ```    

     Controller katmanındaki http://localhost:8080/book/1 adresini karşılayan ve sonuç dönen methoda aşağıdaki satırlar eklenerek LAZY nesnesine erişim isteği gönderilebilir.
    ```
    book.getPages().forEach(p -> {
        System.out.println("page chapter : " + p.getChapter());
    });
    ```
    ##### İlişki EAGER olarak tanımlanırsa
    Eğer <b>Book</b> ve <b>Page</b> arasındaki ilişki <b>LAZY</b> yerine <b>EAGER</b> olarak tanımlansaydı Page'e eişimine bakılmaksızın DB den Book istenirken ilişkili Page lerde çekilecekti.    
    
    Book-Page ilişkisinin <b>EAGER</b> olarak tanımlanması
    ```
    @OneToMany(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    @JoinColumn(name = "book_id")
    private Set<Page> pages;
    ```    

    Üstteki şekilde tanımlama yapılarak loglar incelendiğinde ise ilk sorguda Book ve Page joinlenerek çekilecektir. Hibernate loglarında yazılan SQL sorgusu aşağıdaki gibi olacaktır.
    ```
    Hibernate: 
        select
            book0_.id as id1_0_0_,
            book0_.author as author2_0_0_,
            book0_.isbn as isbn3_0_0_,
            book0_.title as title4_0_0_,
            pages1_.book_id as book_id5_3_1_,
            pages1_.id as id1_3_1_,
            pages1_.id as id1_3_2_,
            pages1_.chapter as chapter2_3_2_,
            pages1_.content as content3_3_2_,
            pages1_.number as number4_3_2_ 
        from
            books book0_ 
        left outer join
            pages pages1_ 
                on book0_.id=pages1_.book_id 
        where
            book0_.id=?
    ```    


--- 
* ### bidirectional(çift yönlü)  ilişkide FetchType
    Çiftyönlü ilişki tanımında daha önceden <b>Customer</b> ve <b>Order</b> örneği yapılmıştı.
    Burada <b>Customer</b> üzerinde <b>@OneToMany</b> ilişki tanımı yapılmıştı. <b>Order</b> tarafında ise bunun tam tersi şeklinde <b>@ManyToOne</b> ilişkisi tanımlanmıştı.
    
    Order ilişki tanımı
    ```
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    ```

    Customer ilişki tanımı
    ```
    @JsonIgnore
    @OneToMany(mappedBy = "customer")
    private Set<Order> orders = new HashSet<>();
    ```    
    ##### Customer->Order Logların incelenmesi
    http://localhost:8080/customer/1 adresi üzerinden Customer'dan Order'a giderken(Müşterinin siparişlerine erişim) Customer <b>@OneToMany</b> olduğu için ilk sorguda sadece Customer bilgisi DB den alınır. Daha sonra Customer'on Orderlarına erişmek istediğimizde hibernate ikinci sorguyu atar DB'ye.
    ilk sorgu logu
    ```
    Hibernate: 
        select
            customer0_.id as id1_1_0_,
            customer0_.create_date as create_d2_1_0_,
            customer0_.name as name3_1_0_ 
        from
            customers customer0_ 
        where
            customer0_.id=?
    ```    
    ikinci sorgu logu
    ```
    Hibernate: 
        select
            orders0_.customer_id as customer4_2_0_,
            orders0_.id as id1_2_0_,
            orders0_.id as id1_2_1_,
            orders0_.customer_id as customer4_2_1_,
            orders0_.price as price2_2_1_,
            orders0_.product_name as product_3_2_1_ 
        from
            orders orders0_ 
        where
            orders0_.customer_id=?
    ```    
    ##### Order->Customer Logların incelenmesi
    http://localhost:8080/order/1 adresi üzerinden order dan customer bilgisi alınırken ise Order <b>@ManyToOne</b> olduğu için tek sorguda tüm ilişki çekilecektir.        
    çünkü <b>@ManyToOne</b> annotationunun default **fetchType** değeri **EAGER** dir.
    loglardaki hibernate sorgusu aşağıdaki gibi olacaktır.
    ```
    Hibernate: 
        select
            order0_.id as id1_2_0_,
            order0_.customer_id as customer4_2_0_,
            order0_.price as price2_2_0_,
            order0_.product_name as product_3_2_0_,
            customer1_.id as id1_1_1_,
            customer1_.create_date as create_d2_1_1_,
            customer1_.name as name3_1_1_ 
        from
            orders order0_ 
        left outer join
            customers customer1_ 
                on order0_.customer_id=customer1_.id 
        where
            order0_.id=?
    ```    

[index için tıklayın](../README.md)
