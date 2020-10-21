* ### Tanımlar
    **singleton**
    Spring içinde  her bean default olarak Singleton’dur. 
    singleton: sadece bir instance anlamına gelir.Spring her isteğe aynı bean'i döner.
    Sprinteki Singleton geleneksel singleton tasarından biraz farklıdır.
    Nesne ClassLoader yerine Spring container içinde oluşturulur.
    ```
    @Component
    public class SingletonScopeComponent {...}
    ```    

    **prototype**
    Bean'in inject edildiği her için yeni bir instance yaratılır. 
    ```
    @Component
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public class PrototypeScopeComponent {...}
    ```    
  
    **request**
    Her HTTP isteği geldiğinde instance oluşturulur.
    ```
    @Component
    @RequestScope
    public class RequestScopeComponent {...}
    ```    

    **session**
    Her HTTP session için yeni bir instance oluşturulur.
    ```
    @Component
    @SessionScope
    public class SessionScopeComponent {...}
    ```    

    **globalSession**
    Her yeni global HTTP session için yeni bir instance oluşturulur.
    Bu kapsam(globalSession) sadece Portlet container'ında çalışan portlet uygulamarı içindir.
    ~~Dolayısıyla bu çalışmada örneği yapılmamıştır.~~

* ### Açıklamalar
    Spring ile bean yaratırken üstteki kapsamlar kullanılır.
    Beanlerin hangi koşullarda yaratılacağı bu kapsamlar sayesinde Spring'e bildirilir. 
    Bir class'ın tepesine **@Component,@Service,@Repository,**..vs yazılarak spring ile beanler yaratılabilir.

    Bean lerden kaçtane instance yaratıldığını görmmek için **System.identityHashCode()** ve **hashCode()** methodları kullanılmıştır. 
    Ancak bu methodların hash algoritmaları bazı farklı değerler için aynı hash kodunu üretebileceğinden dolayı durumun daha iyi anlaşılabilmesi adına  örneklerin tamamında class'a ait olan static bir **instanceCount** değişkeni tanımlanmıştır. 
    Her bean'in default constructor'unda ise **instanceCount++** yapılarak kaçtane isntance oluşturulduğu gözlemlenmiştir.
    ```
    public PrototypeScopeComponent() {
        instanceCount++;
    }    
    ```
      
    **instanceCount()** ve **hashCode()** değerleri ise beanler içinde **printInstanceInfo()** methodunda ekrana basılmıştır.
    ```
    public void printInstanceInfo() {
        System.out.println("PrototypeScope instanceCount : " + instanceCount + " - hashCode : " + this.hashCode());
    }
    ```
  
 
* ### singleton
    Spring'in default scope'u singleton dur.  Çalışmada **SingletonScopeComponent** adında bir class oluşturulup üstüne @Component anotasyonu eklenmiştir. Scope belirtilmediği için singleton scope davranışı sergileyecektir.
    Bu bean autowired edildiği her yerde tek bir instance üzerinden işlem yapacaktır.
    
    Bean yaratma işlemi:
    ```
    @Component
    public class SingletonScopeComponent {
    
        private static int instanceCount;
    
        public SingletonScopeComponent() {
            instanceCount++;
        }
    
        public void printInstanceInfo() {
            System.out.println("SingletonScope instanceCount : " + instanceCount + " - hashCode : " + this.hashCode());
        }
    }
    ```     
    uygulama ayağa kaldırılıktan buradaki(http://localhost:8080/scopes/singleton) adrese gidilerek. consoleda aşağıdaki çıktının görülmesi beklenir.
    ```
    SingletonScope instanceCount : 1 - hashCode : 744180625
    ```    
    _**Burada kaç istek yapılırsa yapılsın instanceCount hep 1 olacaktır.**_

* ### prototype
    Her inject edildiği yer için yeni bir bean demektir.

    Prototype bean tanımı
    ```
    @Component
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public class PrototypeScopeComponent {
    
        private static int instanceCount;
    
        public PrototypeScopeComponent() {
            instanceCount++;
        }
    
        public void printInstanceInfo() {
            System.out.println("PrototypeScope instanceCount : " + instanceCount + " - hashCode : " + this.hashCode());
        }
    }
    ```
    
    Prototype bean injection
    ```
    @Autowired
    private PrototypeScopeComponent prototypeComponent;
    ```    
  
    #######NOTE: İkinci bir instance oluşturmak için farklı bir Controller da daha **Autowired** edilmelidir..
    
    Controller katmanında bir endpoint yaratma ve Prototype bean üzerinden method çağırma
    ```
    @GetMapping("/prototype")
    public long getPrototypeScopeData() {
        prototypeComponent.printInstanceInfo();
        return System.identityHashCode(prototypeComponent);
    }
    ```
    
    url:http://localhost:8080/scopes/prototype

* ### request
    her request'e karşılık bir bean yaratılır.
    
    Request Scope Bean tanımlama 
    ```
    @Component
    @RequestScope
    public class RequestScopeComponent {
    
        private static int instanceCount;
    
        public RequestScopeComponent() {
            instanceCount++;
        }
    
        public void printInstanceInfo() {
            System.out.println("RequestScope instanceCount : " + instanceCount + " - hashCode : " + this.hashCode());
        }
    }
    ```
    URL : http://localhost:8080/scopes/request
    
    Console çıktısı
    ```
    RequestScope instanceCount : 7 - hashCode : 1126154611
    ```

* ### session
    Her session için bean yaratma işlemi. Farklı iki browser üzerinden buradaki(http://localhost:8080/scopes/session) adrese gidilerek case oluşturulur.
    
    Session Scope Bean Yaratma
    ```
    @Component
    @SessionScope
    public class SessionScopeComponent {
    
        private static int instanceCount;
    
        public SessionScopeComponent() {
            instanceCount++;
        }
    
        public void printInstanceInfo() {
            System.out.println("SessionScope instanceCount : " + instanceCount + " - hashCode : " + this.hashCode());
        }
    }
    ```
    
    console çıktısı
    ```
    SessionScope instanceCount : 2 - hashCode : 2004746746
    ```


[index için tıklayın](../README.md)
