<b>unidirectional(tek yönlü) -> </b> source entity den target entity'e doğru erişim olur ancak target tan source'a doğru erişim olmaz. <br/>
<b>bidirectional(çift yönlü) -> </b> soruce entity den target entity'e doğru gidilebileceği gibi target entity den de source entity'e doğru erişim de mümkündür.

--- 

manyToMany dışındaki entity ilişkileri <b>JoinColumn</b> yada <b>JoinTable</b> ile tutulabilir.
manyToMany ilişkisini ise <b>JoinColumn</b> ile tutmamız mümkün değildir. Burada <b>JoinTable</b> kullanmamız gerekir.

--- 

@Transactional olarak işaretlenen bir method içerisinde obje üzerirnde değişiklik yapıldığında objectRepository.save(obje) şeklinde repository üzerinden bir save işlemi yapılmasa bile hibernate değişiklikleri DB ye yansıtır.
Hibernate transaction aşamasında objeler üzerindeki değişikliklieri tespit ederek DB ye vurur.

---

Hibernate'in objeler arasındaki ilişkilerinde default fetchType tanımı aşağıdaki gibidir.
- OneToMany: LAZY
- ManyToOne: EAGER
- ManyToMany: LAZY
- OneToOne: EAGER

---

[index için tıklayın](../README.md)
