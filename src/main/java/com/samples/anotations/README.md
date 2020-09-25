## önemli notlar
- C# taki karşılığı Attribute, java da ise Annotation olarak geçer.
- Java5 ile birlikte java dünyasına girmiştir.
- Annotation lar aracılığıyla Sınıf, metod veya değişkene özellik katılabilir.
- Java da Annotation lar @ işaretiyle başlarlar.
- Javada gelenllikle configuration amacıyla kullanılırlar.
- Framework ve library ler tarafından çokça kullanılırlar (Lombo,Spring,Hibernate)


## javadaki build-in annotation lardan bazıları 

- ### @Override
    super class ta tanımlanan bir methodun ezildiğini derleyiciye bildirmek için kullanılır.

- ### @Deprecated
    Bu metodun yeni bir versiyonunun olduğunu derleyiciye bildirir ve kullanımda olmadığı uyarısını verir. 

- ### @SuppressWarnings
    derleyicinin uyarı vereceği durumlarda uyarı istemediğimizi derleyiciye bildiririz.
    
### Javadaki Meta Annotation lar
Bu Annotation lar javada yeni bir(custom) anotation tanımlamak için kullanırlırlar.

- ### @Target
    - Yazılam Annotation un  nerede kullanılacağını bildirmek için kullanılır.
    - Target; Method, değişken veya class olarak tanımlanabileceği gibi bunlardan birkaçı seçilerek te tanımlanabilir. 
    ```
    @Target({ElementType.METHOD,ElementType.TYPE})
    ```
  
- ### @Retention  
    - Yazılan Annotation'ın hangi seviyede kullanılacağını bildirmek için kullanılır.
    - SOURCE, CLASS ve RUNTIME şeklinde tanımlanabilir.
    - Varsayılan tanım şekli CLASS’tır. 
    - CLASS: Annotationın compile time da sınıfa eklenmesini sağlar.Bu kullanımda runtimeda erişilmesine gerek olmadığı belirtilmiş olur. 
    - SOURCE: annotationın compile time da yok sayılmasını sağlar. 
    - RUNTIME: annotationın runtime da erişilebilirliğini sağlar.
        ```
        $ @Retention(RetentionPolicy.RUNTIME) 
        ```
        ```
        $ @Retention(RetentionPolicy.SOURCE) 
        ```
        ```
        $ @Retention(RetentionPolicy.CLASS) 
        ```
- ### @Documented
    - Yazılan özel(custom) Annotationlarının etkilediği elemanı javadoc'a dahil etmek için kullanılır.
    
## javadaki özel(custom) annotation yazma işlemleri

-  Annotations, @interface ve ardından Annotations adı kullanılarak oluşturulur.
-  Tüm annotation lar  java.lang.annotation.Annotation interface'inden extends edilirler. 
-  Annotation lar  extends edilemezler.    

### @interface 
"@interface" tanımı ile javaya custom anotation tanımı yapıldığı bildirilir

### komutlar 
maven ile Javadoc oluşturma komutu (output: target/site/index.html)
```
$ mvn javadoc:javadoc
```

### Annotation'ın Class olarak ve Runtime da çalışacak şekilde tanımlanması
```
@Retention(RetentionPolicy.RUNTIME)//runtime da devrede olacak
@Target(ElementType.TYPE) //class larda kullanılacak
public @interface TestConfig {
```

### Annotationdaki değişkenlere default değer atanması
```
Platform platform() default Platform.DEVELOPMENT;
```

### farklı parametrelerle testin koşulması 
```
@TestConfig(platform = TestConfig.Platform.PRODUCTION)
//@TestConfig(platform = TestConfig.Platform.DEVELOPMENT)
//@TestConfig(platform = TestConfig.Platform.TEST)
```

### testing ignore edilmesi
```
@Test(enabled = false)
void testB() {
    ...
}
```

 ### kaynaklar
 - https://docs.oracle.com/javase/specs/jls/se11/html/jls-9.html#jls-9.7
 - https://mkyong.com/java/java-custom-annotations-example/
