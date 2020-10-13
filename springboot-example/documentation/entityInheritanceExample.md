* ### Tanımlar
    OOP dünyasında adı sık duyulan kavramlardan biri de inheritance tır. 
    Hibernate ile entityler oluşturulurken de tekrardan kaçmak amacıyla inheritance tan faydalanmak gerekir.
    @Inheritance anotasyonu ile hibernate entityleri arasındaki kalıtım ilişkisi kurulmuş olur.

    @Inheritance'ın aldığı strategy değerleri aşağıdaki gibidir.
    - SINGLE_TABLE     
    - TABLE_PER_CLASS     
    - JOINED               
    ```
    @Entity
    @Table(name = "notification")
    @Inheritance(
            strategy = InheritanceType.JOINED
    )
    public class Notification extends BaseEntity {...}
    ```

* ### Diagram
    Uygulamada kullanılan Entitylerin birbirleriyle ilişkisini gösteren Class diagramı aşağıdaki gibidir.
    Bu çalışmada **SmsNotification** ve **EmailNotification** entitylerinin ortak özellikleri Notification kısmında toplanarak kod tekrarından kaçılmıştır.
    ![](../screenshots/inheritanceNotificationDiagram.png)
    
* ### BaseEntity
```
@MappedSuperclass
public abstract class BaseEntity implements IdBasedDomain,Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
}
```    

```
@MappedSuperclass
```

* ### IdBasedDomain
```
public interface IdBasedDomain {
    Long getId();

    void setId(Long id);
}
```

* ### Notification
```
@Entity
@Table(name = "notification")
@Inheritance(
        strategy = InheritanceType.JOINED
)
public class Notification extends BaseEntity {

    private String firstName;

    private String lastName;

    private String content;
```

* ### SmsNotification
```
@Entity
public class SmsNotification extends Notification {

    private String phoneNumber;

    public SmsNotification() {
    }

    public SmsNotification(String firstName, String lastName, String content, String phoneNumber) {
        super(firstName, lastName, content);
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
```
* ### PrimaryKey lerle ilişki kurulması
    
* ### End-point ler ile CRUD işlemleri
    Sms notification'ı ekleme
    http://localhost:8080/notification/sms/add
    
    Email notification'ı ekleme
    http://localhost:8080/notification/email/add
    
    Db deki notification'u Id değeriyle alma
    http://localhost:8080/notification/5
    
    DB deki tüm notificationları listeleme
    http://localhost:8080/notification/list

* ### Hibernate SQL logları


* ### Kaynaklar
    - https://vladmihalcea.com/the-best-way-to-use-entity-inheritance-with-jpa-and-hibernate/
    - https://gist.github.com/dungdm93/22eb53b95030b641e2cb/0559be2082b7786212942d3dd264a18498944fa1


[index için tıklayın](../README.md)
