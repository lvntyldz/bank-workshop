* ### Referanslar
    Bu çalışmadaki örnekler **com.ba.controller.MapperController** adlı class ta kodlanmıştır.
    
* ### Açıklamalar
    - Mapstruct Variable type ve isimleri eşleşiyorsa direk convert eder.
    - Mapstruct Interface yada Abstract class olarak yazılabilir.
    - Mapstruct mapper interface veya abstract sınıflarım implementasyonlarını otomatik olarak "target/generated-sources/annotations/com/ba/converter" dizininde oluşturur oluşturmaktadır.
    - Özetle Mapstruct **derleme zamanında** implementasyon dosyalarını oluşturur. Derleme zamanında kaynak  kod oluşturması avantajlarından biridir.
    - Mapstruct convert işlemini **typesafe** olarak yapar. Yani String'i integer olarak convert etmez.

* ### Bağımlılıklar
    - mapstruct'ın pom.xml'e eklenmesi
    ```
    <dependency>
        <groupId>org.mapstruct</groupId>
        <artifactId>mapstruct</artifactId>
        <version>1.3.1.Final</version>
    </dependency>
    ```

* ### Build pluginleri
    - projede lombok ve mapstruct bir arada kullanılıyorsa Annotation Processor Paths'e her iki library de eklenmelidir.
    ```
    <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-compiler-plugin</artifactId>
        <version>${maven.compiler.plugin.version}</version>
        <configuration>
            <source>11</source>
            <target>11</target>
            <compilerArgs>--enable-preview</compilerArgs>
            <annotationProcessorPaths>
                <path>
                    <groupId>org.projectlombok</groupId>
                    <artifactId>lombok</artifactId>
                    <version>${lombok.version}</version>
                </path>
                <path>
                    <groupId>org.mapstruct</groupId>
                    <artifactId>mapstruct-processor</artifactId>
                    <version>${mapstruct.version}</version>
                </path>
            </annotationProcessorPaths>
        </configuration>
    </plugin>
    ```

* ### Mapping işlemi ve @Mapper annotasyonu
    - Mapstruct ile Mapping işlemi **Interfaceler ve Abstract classlar** aracılığıyla yapılabilir.
    - Bu çalışmada Interfaceler üzerinden örnekler hazırlanmıştır.
    - İlk olarak map işlemi için bir interface yaratılıp **@Mapper** annotasyonu ile işaretlenir.
    ```
    @Mapper
    public interface SystemUserMapper {...}
    ```
    - Daha sonra  **Mappers.getMapper()** methoduna oluşturulan interface classı parametre verilerek çağrılır. Bu sayede bir Mapper Instance ı oluşturulur.
    ```
    SystemUserMapper INSTANCE = Mappers.getMapper(SystemUserMapper.class);
    ```
    - Oluşturulan Interface içerisine map işlemini yapacak methodlar parametre ve return type'ı ile birlikte tanımlanır.
    ```
    SystemUserDTO toDTO(SystemUser systemUser);
    ```
    - Kaynak ve hedef classlar içinde tanımlana değişken isimleri birbirinden farklıysa **@Mapping** anotasyonuna **source ve target** parametreleri String olarak geçilir.
    ```
    @Mapping(source = "telefon", target = "phone")
    ```
    
    - **SystemUser -> SystemUserDTO ve SystemUserDTOTr -> SystemUser**  dönüşümünü yapan tüm içerik aşağıdaki gibi olacaktır
    ```
    @Mapper
    public interface SystemUserMapper {
    
        SystemUserMapper INSTANCE = Mappers.getMapper(SystemUserMapper.class);
    
        SystemUserDTO toDTO(SystemUser systemUser);
    
        @Mappings({
                @Mapping(source = "telefon", target = "phone"),
                @Mapping(source = "sifre", target = "password")
        })
        SystemUser toEntity(SystemUserDTOTr dto);
    
    }
    ```

* ### Nested  Mapping işlemi
    - Bu örnek ürün kargolama senaryosu üzerinden oluşturulmuştur. 
    - Senaryoda bir teslimat makbuzu ile  ürün sevkiyatı  yapılmaktadır.
    - Teslimat makbuzu bilgileri **DeliveryReceipt** class'ında, ürün bilgileri ise **ProductShipment** adlı class ta tutulmaktadır.  
    - Tüm işlemler için Java tarafında **DeliveryReceiptDTO, ProductShipmentDTO, DeliveryReceipt ve ProductShipment** classları kullanılmıştır.
    
    **DeliveryReceipt** classında **ProductShipment**'ın tanımı
    ```
    public class DeliveryReceipt {
        ...
        private ProductShipment productShipment;
        ...
    }
    ```
    
    **DeliveryReceiptDTO** classında **ProductShipmentDTO**'ın tanımı
    ```
    public class DeliveryReceiptDTO {
        ...
        private ProductShipmentDTO productShipmentDTO;
        ...
    }
    ```
    
    **ProductShipment** classında ödemenin kime ait olduğu **sellerFee** olarak tanımlanmıştır
    ```
    public class ProductShipment {
        ...
        private Boolean sellerFee;
        ...
    }
    ```
    
    **ProductShipmentDTO**  classında ödemenin kime ait olduğu **saticiOder** olarak tanımlanmıştır
    ```
    public class ProductShipmentDTO {
        ...
        private Boolean saticiOder;
        ...
    }
    ```
    
    - üstteki kodlar incelendiğinde **ProductShipmentDTO ve ProductShipment** classlarındaki değişken isimlerinde farklılık olduğu görülmektedir.
    - **ProductShipmentDTO.saticiOder** diğer tarafta **ProductShipment.sellerFee** olarak tanımlıdır.
    - nested mapping işlemlerinde bu tür farklılıklar yine **@Mapping** annotationu ile çözülebilir.
    ```
    @Mapping(source = "productShipment.sellerFee", target = "productShipmentDTO.saticiOder")
    ``` 
    
    **DeliveryReceiptMapper** içeriği aşağıdaki gibi olacaktır
    ```
    @Mapper
    public interface DeliveryReceiptMapper {
    
        DeliveryReceiptMapper INSTANCE = Mappers.getMapper(DeliveryReceiptMapper.class);
    
        @Mapping(source = "productShipment.sellerFee", target = "productShipmentDTO.saticiOder")
        DeliveryReceiptDTO toDTO(DeliveryReceipt entity);
    
        @Mapping(source = "productShipmentDTO.saticiOder", target = "productShipment.sellerFee")
        DeliveryReceipt toEntity(DeliveryReceiptDTO dto);
    }
    ```
    
    - **NOTE:** Üstteki örnekteki nested mapping işleminde sadece **productShipmentDTO.saticiOder -> productShipment.sellerFee** şeklinde tanımlanmıştır. 
    Ancak **Mapstruct** bu tanımdan isimleri farklı olan **DeliveryReceiptDTO.productShipmentDTO -> DeliveryReceipt.productShipment** şeklinde dönüştürüleceğini de anlamış olur.
    Dolayısıyla tanımlama aşağıdaki şekilde de yapılabilirdi ancak gereksiz satır eklenmemesi adına üsteki halinde bırakılmıştır.
    ```
        @Mappings({
                @Mapping(source = "productShipmentDTO", target = "productShipment"),
                @Mapping(source = "productShipmentDTO.saticiOder", target = "productShipment.sellerFee")
        })
        DeliveryReceipt toEntity(DeliveryReceiptDTO dto);
    ```
    
* ### Collectionların farklı isimlerde olması
    Buradaki örnekte **Metting** adlı bir entity ve **MeetingDTO** adlı bir dto class'ı kullanılmıştır.
    **Meeting** içerisindeki **meetings** değişkeni **MeetingDTO** içinde **toplantilar** olarak adlandırılmıştır.
    
    Meeting classı
    ```
    public class Meeting {
        ...
        private Set<Member> meetings = new HashSet<>();
        ...
    }
    ```
    
    MeetingDTO classı
    ```
    public class MeetingDTO {
        ...
        private Set<MemberDTO> toplantilar = new HashSet<>();
        ...
    }
    ```
    
    Yine mapper classta farklı isimlendirmeleri Mapstruct'a bilgirmek için **@Mapping** annotationundan yararlanılmıştır.
    ```
    @Mapping(source = "toplantilar", target = "meetings")
    ``` 
    
    MeetingMapper class'ının içeriği aşağıdaki gibidir
    ```
    @Mapper
    public interface MeetingMapper {
    
        MeetingMapper INSTANCE = Mappers.getMapper(MeetingMapper.class);
    
        @Mapping(source = "toplantilar", target = "meetings")
        Meeting toEntity(MeetingDTO dto);
    
        @Mapping(source = "meetings", target = "toplantilar")
        MeetingDTO toDTO(Meeting entity);
    
    }
    ```

* ### Hem Collectionların hem de kaynak-hedef classların farklı isimlerde olması
    - Buraki başlığı açıklamak için kampüs ve sınıf senaryosu üzerinden çalışma örneklenmiştir.
    - Campus içinde Classroom lar hibernate ile OneToMany releationlar olarak tanımlanmıştır.
    - Senaryoda hem convert edilmek istenen kaynak ve hedefteki collectionlar farklı isimlerde tanımlanmıştır. Hemde collection tipinin kaynak ve hedefteki isimleri örtüşmemektedir.
    
    Dönüşümler |
    --- |
    CampusDTO.siniflar -> Campus.classrooms |
    CampusDTO.siniflar.isim -> Campus.classrooms.name |
    CampusDTO.siniflar.roomNo -> Campus.classrooms.no |
    
    - Buradaki karmaşayı çözmek için çalışmada içerden dışarıya doğru dönüştürme yapılmıştır.
    - ilk olarak **Classroom** dönüşümünü yapacak bir Mapper class oluşturulur ve ilgili alanların nasıl dönüşeceği **@Mapping** annotationu ile belirtilir.
    ```
    @Mapper
    public interface ClassroomMapper {
    
        @Mapping(source = "name", target = "isim")
        @Mapping(source = "no", target = "roomNo")
        ClassroomDTO toDto(Classroom entity);
    
        @Mapping(source = "isim", target = "name")
        @Mapping(source = "roomNo", target = "no")
        Classroom toEntity(ClassroomDTO dto);
    }
    ```
    
    - ikinci aşamada ise **Classroom'u** içinde barındıran **Campus** dönüşümünü yapacak bir mapper class tanımlanır ve yine ilgili alanların nasıl dönüşeceği **@Mapping** annotationu ile belirtilir.
    ```
    @Mappings({
            @Mapping(source = "isim", target = "name"),
            @Mapping(source = "telefon", target = "phone"),
            @Mapping(source = "eposta", target = "email"),
            @Mapping(source = "siniflar", target = "classrooms")
    })
    Campus toEntity(CampusDTO dto);
    ```
    
    - Son aşamada ise Campus Mapper classına **uses** parametresi verilerek dönüştürme işlemlerinde ikinci bir mapstruct mapper kullanılacağı bildirilir.
    ```
    @Mapper(uses = {ClassroomMapper.class})
    ```
  
    - **CampusMapper** classının son hali aşağıdaki gibi olacaktır.
    ```
    @Mapper(uses = {ClassroomMapper.class})
    public interface CampusMapper {
    
        CampusMapper INSTANCE = Mappers.getMapper(CampusMapper.class);
    
        @Mapping(source = "name", target = "isim")
        @Mapping(source = "phone", target = "telefon")
        @Mapping(source = "email", target = "eposta")
        @Mapping(source = "classrooms", target = "siniflar")
        CampusDTO toDto(Campus entity);
    
        @Mappings({
                @Mapping(source = "isim", target = "name"),
                @Mapping(source = "telefon", target = "phone"),
                @Mapping(source = "eposta", target = "email"),
                @Mapping(source = "siniflar", target = "classrooms")
        })
        Campus toEntity(CampusDTO dto);
    }
    ```

* ### Endpointler
    - mapstruct'ı anlamak için DTO->Entity ve Entity->DTO örnekleri üzerinde çalışılmıştır.
    - bur örnekleri görmek için **com.ba.controller.MapperController** debug edilerek incelenebilir.
    - ilk mapper örneği : http://localhost:8080/mapper/simple-example
    Bu örnekte kaynek ve hedef class taki değişken isim ve tipleri birebir aynıdır.
    
    - eşleşmeyen varible isimleriyle mapper örneği : http://localhost:8080/mapper/simple-example-mismatched
    Buradaki örnekte DTO ve Entityde farklı isimlerle tanımlanmış değişkenler kullanılmıştır. DTO da password ve phone alanları da türkçe olarak tanımlanmıştır.
    Ayrıca Dto dan Id kısmı kaldırılmıştır. 
    
    - OneToOne ilişkili enetity ile mapper örneği : http://localhost:8080/mapper/one-to-one
    Nested class convert işlemini anlamak için bu örnek yapılmıştır. DeliveryReceipt class'ı içinde ProductShipment tanımlaıdır.
    
    - OneToOne ilişkili dto ile mapper örneği : http://localhost:8080/mapper/one-to-one-dto
    - OneToMany ilişkili enetity ile mapper örneği : http://localhost:8080/mapper/one-to-many
    Buradaki örnekte ise Collection ların nasıl Map edildiği gösterilmiştir.
    Campus içinde Set<Classroom> şekilnde classrooms tanımlanmıştır.
    
    - OneToMany ilişkili dto ile mapper örneği : http://localhost:8080/mapper/one-to-many-dto
    - ManyToMany ilişkili enetity ile mapper örneği : http://localhost:8080/mapper/many-to-many
    Buradaki örnekte yine Meeting içinde Set<Member> şekilnde members tanımlanmıştır.
    
    - ManyToMany ilişkili dto ile mapper örneği : http://localhost:8080/mapper/many-to-many-dto
    

[index için tıklayın](../README.md)
