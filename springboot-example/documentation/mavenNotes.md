Maven'a dependency eklerken bazen optional=true parametresiyle ekleme yapılır. 
Bunun anlamı geliştirilen uygulama eğer bir library olarak paketenecekse bu bağımlılığı pakete eklemeye ihtiyaç yok paket alırken bunu dahil etme anlamındadır.
Optional true olan library sadece geliştirme ortamının classpathinde yer alır. 
```
<dependency>
    <groupId>...</groupId>
    <artifactId>...</artifactId>
    <optional>true</optional>
</dependency>
```

--- 

[index için tıklayın](../README.md)
