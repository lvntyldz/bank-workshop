* ### Referanslar
    Bu çalışmadaki örnekler **com.samples.string.sample2.Run** adlı class ta kodlanmıştır.

* ### == kullanımı 
    - == ile primitive tiplerde actual value'ya(değer) bakılır.
    - Referans tiplerde ise Objenin referansına bakılır.
    
* ### equals()
    - equals() object sınıfından gelen bir methoddur.
    - String class'ı içinde bu method override edilerek String nesnelerinin değerleri kontrol edilmiştir.

* ### Örnekler
    Aşağıdaki gibi tanımlamada değerleri aynı olan string tanımlarında  String pool da 1 ader String objesi oluşturularak tüm değişkenler aynı alanı refere eder.
    s1 ve s2 aynı referansa sahip olduğu için if bloğu çalışacaktır.
    ```
    String s1 = "Hello World";
    String s2 = "Hello World";
    
    if (s1 == s2) {//Referansları aynı
        System.out.println("s1 == s2");
    } else {
        System.out.println("s1 != s2");
    }
    ```
    
    Java da new=yeni referans anlamına gelir. s3 ve s4 new ile yaratıldığı için heap te  farklı iki String nesnesi oluşturulacaktır.
    dolayısıyla s3 ve s4 farklı referanslara sahip olacağı için else bloğu çalışır.
    ```
    String s3 = new String("Hello World");
    String s4 = new String("Hello World");
    
    if (s3 == s4) {//referansları farklı
        System.out.println("s3 == s4");
    } else {
        System.out.println("s3 != s4");
    }
    ```
    
    equals ile string lerin değerleri kontrol edileceği için değerleri birbirine eşit olan aşağıdaki ifade de if bloğu çalışacaktır.
    ```
    String s1 = "Hello World";
    String s2 = "Hello World";
    
    if (s1.equals(s2)) {
        System.out.println("s1.equals(s2)");
    } else {
        System.out.println("!s1.equals(s2)");
    }
    ```
    
    Aşağıdaki ifadede farklı iki refans olması önemli değildir. equals ile değerlere bakıldığı için if bloğu çalışacaktır.
    ```
    String s3 = new String("Hello World");
    String s4 = new String("Hello World");
    
    if (s3.equals(s4)) {//referansları farklı
        System.out.println("s3.equals(s4)");
    } else {
        System.out.println("s3.equals(s4)");
    }
    ```
[index için tıklayın](../README.md)
