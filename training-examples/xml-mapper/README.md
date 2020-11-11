* ### Referanslar
    Bu çalışmadaki örnekler **xml-mapper** adlı projede kodlanmıştır.
    
* ### Açıklamalar
    Çalışmada  **com.mapper.Person** ve **com.mapper.Product** adında 2 class oluşturulmuştur. 
        
* ### XML ve Gson
    - Xml adında bir class oluşturulur. 
    - Gson classının içindeki **toJson()** ve **fromJson()** mtehodları gibi Xml class'ında **toXml()** ve **fromXml()** adında 2 method oluşturulmuştur.
    - Bu methodlar ile Objeler XML'e ve daha sonra da XML ler Objeye dönüştürlecektir.
     
* ### Convert işlemi
    Persondan instance oluşturma
    ```
    Person person = new Person();
    person.setFirstname("ali");
    person.setLastname("ALİOĞLU");
    ```    
    Product tan instance oluşturma
    ```
    Product product = new Product();
    product.setPrice(1000);
    product.setTitle("monitor");
    ```
    
    xml'e dönüştürme
    ```
    String personXmlString = xml.toXml(person);
    String productXmlString = xml.toXml(product);
    ```

[index için tıklayın](../README.md)
