* ### Referanslar
    Bu çalışmadaki örnekler **com.ba.controller.CountriesController** adlı class ta kodlanmıştır.
    
* ### Verilerin oluşturulması
- Sayfalama senaryosu için TBL_COUNTRIES adında bir tablo oluşturularak içinde ülke isimleri insert edilir.
```
CREATE TABLE TBL_COUNTRIES (
ID INT(11) NOT NULL AUTO_INCREMENT,
CODE VARCHAR(2) NOT NULL ,
NAME VARCHAR(100) NOT NULL,
PRIMARY KEY (ID)
);

INSERT INTO TBL_COUNTRIES VALUES (NULL, 'AF', 'Afghanistan');
INSERT INTO TBL_COUNTRIES VALUES (NULL, 'AL', 'Albania');
...
```

* ### Entity
- Country adında bir entity oluşturulur
```
@Entity
@Table(name = "TBL_COUNTRIES")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    private String name;

}
```

* ### Repository
- CountryRepository classının oluşturulması
```
@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {...}
```

- Spring data JPA ile @Query annotation u yerine method ismiyle query oluşturma
```
@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
    Page<Country> findAllByNameContainsIgnoreCase(String name, Pageable pageable);
}
```

desen | anlamı | 
--- |--- |
findAllByName | isme göre tüm sonuçlar |
Contains | Like ile ara |    
IgnoreCase | Büyük/küçük harfe duyarsız|

* ### CountriesController'un incelenmesi
- search işlemi için URL den  countryName,page ve size parametreleri alınır
```
public Page<Country> searchCountries(@RequestParam String countryName, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "2") int size) {...}
```
- page ve size parametreleriyle Pagable bir obje oluşturulur.
```
Pageable pageable = PageRequest.of(page, size);
```

- searchCountries methodunun içeriği aşağıdaki gibi olacaktır.
```
@GetMapping("/search")
public Page<Country> searchCountries(@RequestParam String countryName, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "2") int size) {
    Pageable pageable = PageRequest.of(page, size);
    return countryRepository.findAllByNameContainsIgnoreCase(countryName, pageable);
}
```

- V2 search işleminde ise method request parametresi olarak countryName ve pageable vardır sadece. Burada yeniden bir pageable oluşturmaya gerek yoktur.
```
public Slice<Country> searchCountriesV2(@RequestParam String countryName, Pageable pageable) {...}
```

- searchCountriesV2 methodunun son hali aşağıdaki gibi olacaktır
```
@GetMapping("/v2/search")
public Slice<Country> searchCountriesV2(@RequestParam String countryName, Pageable pageable) {
    return countryRepository.findAllByNameContainsIgnoreCase(countryName, pageable);
}
```

* ### Notlar
- Yukarıdaki örneklerde V1 de **Page< Country>** tipinde bir değer dönülmektedir. V2 de ise **Slice< Country>** şeklinde bir değer dönülmektedir.
- Bu ikisi arasındaki fark total cont sorgusudur. 
- İlk kullanımda ilgili search'e ait kaç kaydın olduğu bilgisi de dönülür.
- ikinci kullanımda ise sadece birsonraki sayfada kayıt var mı yok mu bilgisi dönülür. 
- ilk kullanımda  count bilgisi için de ayrı bir SQL sorgusu çalışacağı için ikinci kullanım daha performansldır.
- Ayrıca ikinci kullanım "load more" özelliği olan ekran ve uygulamalarda kullanılmaktadır 
- NOT: Slice'in bu örnekte aktif olması için repository nin de **Slice< Country>** dönmesi gerekmektedir  
 
* ### Endpointler
- Paging siz tüm liste : http://localhost:8080/countries/list
- V1: http://localhost:8080/countries/search?countryName=republic&page=0&size=5
- V2: http://localhost:8080/countries/v2/search?countryName=republic&page=0&size=5

* ### Hibernate loglarının incelenmesi
- **Page< Country>** hibernate sorgusu 
```
Hibernate: 
    select
        country0_.id as id1_20_,
        country0_.code as code2_20_,
        country0_.name as name3_20_ 
    from
        tbl_countries country0_ 
    where
        upper(country0_.name) like upper(?) escape ? limit ?
```

- **Page< Country>** hibernate total count sorgusu
```
Hibernate: 
    select
        count(country0_.id) as col_0_0_ 
    from
        tbl_countries country0_ 
    where
        upper(country0_.name) like upper(?) escape ?
```

- **Slice< Country>** sorgusu 
```
Hibernate: 
    select
        country0_.id as id1_20_,
        country0_.code as code2_20_,
        country0_.name as name3_20_ 
    from
        tbl_countries country0_ 
    where
        upper(country0_.name) like upper(?) escape ? limit ?
```

* ### Kaynaklar
- https://www.baeldung.com/spring-data-jpa-pagination-sorting


[index için tıklayın](../README.md)
