* ### Referanslar
    Bu çalışmadaki örnekler **com.samples.reflection** adlı pakette  kodlanmıştır.

* ### Reflection 
    Runtime da isimlerini bilmemize gerek kalmadan class, interface, field ve methodları inspect etmek için kullanılır.
    Ayrıca reflection kullanarak instance yaratmak, method çağırmak, fieldlara get/set yapmak ta mümkündür.

* ###  constructor erişimi
    tanımlı constructorları görüntüle
    ```
    cls.getDeclaredConstructors()
    ```

* ###  method erişimi
    tanımlı methodları görüntüler
    ```
    cls.getMethods()
    ```

* ###  field erişimi
    tanımlı fieldları görüntüler
    ```
    cls.getDeclaredFields()
    ```

* ### private methodun erişimi
    method ismiyle birlikte "getDeclaredMethod(...)" method çağrıldığında aradığımız methodun sonucunu döner
    ```
    Product.class.getDeclaredMethod("calculateDiscount", null);
    ```
    bulunan sonuç içinde accessible=true yaptığımızda reflection ile private method erişilebilir hale gelir.
    
    ```
    privateMethod.setAccessible(true); 
    ```
    
    üstteki değişikliklerin son hali
    ```
    Method privateMethod = Product.class.getDeclaredMethod("calculateDiscount", null);
    privateMethod.setAccessible(true);
    ```


* ### private ve final değişkene erişimi
    private final değişken erişilebilir yapılır
    ```
    field.setAccessible(true);
    ``` 
    
    private field'a(kategori) yeni değeri set edilir
    ```
    field.set(product, "Ayakkabı");
    ```
    
    üstteki değişikliklerin son hali
    ```
    Field field = product.getClass().getDeclaredField("PRODUCT_CATEGORY_NAME");
    field.setAccessible(true);
    field.set(product, "Ayakkabı");
    
    ```

[index için tıklayın](../README.md)
