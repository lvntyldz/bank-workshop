* ### Referanslar
    Bu çalışmadaki örnekler **com.samples.imdbparser.Run** adlı class ta kodlanmıştır.

* ### Açıklamalar
    String bir referans tipi(Class) olduğu için içinde çokça hazır fonksiyon bulundurur.
    String fonksitonlarından bazıları ve açıklamaları aşağıdaki gibidir.
    
    - **charAt(int index) :** Girilen indexdeki karakteri(char) verir.
    - **concat(String str) :** Mevcut string ile girilen string'i birleştirir.
    - **equals(Object anObject) :** iki string'in değerlerini karşılaştırır.
    - **hashCode() :** Stringin hash kodunu döner
    - **indexOf(int ch) :** Girilen karaktering geçtiği index numarasını döner.
    - **isEmpty() :** String'in length'ine bakarak true yada false döner
    - **lastIndexOf(int ch)  :** Karakterin en son bulunduğu indexi döner
    - **length()  :** Stringin genişliğini döner.
    - **replace(char oldChar, char newChar)  :** Girilen eski karakteri yenisiyle değiştirir
    - **startsWith(String prefix)  :** String'in belirtilen pattern ile başlayıp başlamadığına bakar.
    - **substring(int beginIndex, int endIndex)  :** Girilen aralıktaki değeri döner
    - **toLowerCase()  :** küçük harfli hale dönüştürür
    - **toUpperCase()  :** Tüm karakterleri büyük harfe dönüştürür.
    - **trim()()  :** Boşlukları temizler
 
* ### inheritance özellikleri
    String class'ı Object classından extends edecek şekilde tanımlanmıştır.
    Ayrıca Serializable, Comparable ve CharSequence interfacelerini implements eder.
    Dolayısıyla bu classlarda tanımlanan methodları da override ederek geliştirmesi yapılmıştır.
    
    **CharSequence interfaceinden gelen methdolar**
    - int length();
    - char charAt(int index);
    
    **Comparable interfaceinden gelen methdolar**   
    - int compareTo(T o);
    
    **Object classından gelen methdolar** 
    - int hashCode();

* ### Kodlama Örnekleri
    Uygulamda aşağıdaki string fonksiyonlarının tamamı kullanılmıştır.
    - string.indexOf(...)
    - string.lastIndexOf(...)
    - string.contains(...)
    - string.substring(...)
    - string.split(...)
    - string.replace(...)
    - string.startsWith(...)
    - string.endsWith(...)
    - string.length(...)
    - string.charAt(...)
    - String.format(...) -> static

* ### Kaynaklar
    - https://docs.oracle.com/javase/8/docs/api/java/lang/String.html
    

[index için tıklayın](../README.md)
