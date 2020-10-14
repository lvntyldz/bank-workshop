<b>unidirectional(tek yönlü) -> </b> source entity den target entity'e doğru erişim olur ancak target tan source'a doğru erişim olmaz. <br/>
<b>bidirectional(çift yönlü) -> </b> soruce entity den target entity'e doğru gidilebileceği gibi target entity den de source entity'e doğru erişim de mümkündür.

--- 

manyToMany dışındaki entity ilişkileri <b>JoinColumn</b> yada <b>JoinTable</b> ile tutulabilir.
manyToMany ilişkisini ise <b>JoinColumn</b> ile tutmamız mümkün değildir. Burada <b>JoinTable</b> kullanmamız gerekir.

--- 

**@Transactional** olarak işaretlenen bir method içerisinde obje üzerirnde değişiklik yapıldığında objectRepository.save(obje) şeklinde repository üzerinden bir save işlemi yapılmasa bile hibernate değişiklikleri DB ye yansıtır.
Hibernate transaction aşamasında objeler üzerindeki değişikliklieri tespit ederek DB ye vurur.

---

Hibernate'in objeler arasındaki ilişkilerinde default **fetchType** tanımı aşağıdaki gibidir.
- **OneToMany:** LAZY
- **ManyToOne:** EAGER
- **ManyToMany:** LAZY
- **OneToOne:** EAGER
---
Hibernate te entityler arasında kalıtım ilişkisi kurmak için @Inheritance anotasyonu kullanılır.
**@Inheritance**'in aldığı **strategy** değerleri aşağıdaki gibidir.
- **InheritanceType.JOINED :** super class hemde alt classlar(entity) için birer tablo oluşturur.
- **InheritanceType.SINGLE_TABLE :** Super class adıyla bir tablo oluşturulur. Tüm alt entityler burada tutulur. 
- **InheritanceType.TABLE_PER_CLASS :**  
---

Aynı primary key'e sahip entityleri yüklerken ilk erişim isteğinde DB'ye sorgu atılır. ikinci erişim isteğinde ise DB ye sorgu atılmadan hibernate cacheten cevap veriri.
Bu hibernate'in first level cache kabiliyetinden gelmektedir. first level cache devre dışı bırakılamaz.
```
session.get(Employee.class,1L);//ilk istek(DB)
session.get(Employee.class,1L);//ikinc istek(Cache)
```
---

[index için tıklayın](../README.md)
