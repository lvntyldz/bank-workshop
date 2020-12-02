* ### Referanslar
    Bu çalışmadaki örnekler **com.ba.service.EmployeeServiceTest** adlı class ta kodlanmıştır.
    
* ### Test Root dizininin belirlenmesi
    ilk olarak **src/main** dizininin altında **test** adında bir directory oluşturulur.
    Idea üzerinden **test dizinine sağ tıklanır -> Mark Directory As -> Test Root** linkine tıklanır.
    Bu sayede uygulamanın test kodlarının buradaki dizinde olacağı idea'ya bildirilmiş olur.


* ### Bağımlılıklar
    Junit bağımlılığının pom.xml dosyasına eklenmesi
    ```
    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <scope>test</scope>
    </dependency>
    ```
    
    mockito nun pom.xml dosyasına eklenmesi
    ```
    <dependency>
        <groupId>org.mockito</groupId>
        <artifactId>mockito-all</artifactId>
        <version>1.10.19</version>
        <scope>test</scope>
    </dependency>
    ```

* ### açıklamalar
    Testi yazılacak class tan test dizini altında aynı paket ve isimde bir test class'ı oluşturulur.
    ```    
    @RunWith(MockitoJUnitRunner.class)
    public class EmployeeServiceTest {...}
    ```
    
    Test classının içinde ana class @InjectMocks olarak işaretlenir. Bu şekilde yapılan tanımla EmployeeService classından mock(@mock,@spy,...gibi) özelliklerini de barındıran bir instance oluşturulur. 
    ```
    @InjectMocks
    private EmployeeService service;
    ```
    
    @Before anotasyonu ile her methoddan önce çalışması istenilen block tanımlanır.
    ```
    @Before
    public void setup() {
        employee = new PartTimeEmployee();
        employee.setHourlyRate(100);
        employee.setFirstName("Ali");
        employee.setLastName("ALİOĞLU");
        employee.setId(123L);
    }
    ```
    
    @Test anotasyonu ile methodun bir test için eklendiği belirtilir. üstünde @Test olan tüm methodlar test koşulduğu anda çalışacaktır.
    ```
    @Test
    public void shouldAddNewEmployee() {...}
    ```

* ### açıklamalar
    @Mock anotasyonu ile EmployeeRepository'nin bir mock instance'ı yaratılmış olur.  
    ```
    @Mock
    private EmployeeRepository employeeRepository;
    ```
    
    When-thenReturn ile mocklanmış class'ın methodundan test işlemi esnasında istenen değer döndürülür.
    ```
    when(employeeRepository.save(any())).thenReturn(employee);
    ```
    
    verify ile class içerisinde istenilen methodun çağrılıp çağrılmadığı kontrol edilir.
    ```
    verify(employeeRepository, times(1)).save(any(PartTimeEmployee.class));
    ```
    
    expected = Exception.class tanımı ile test methodunun çalıştırıldığı zaman Exception atılacağı belirtilir.
    ```
    @Test(expected = Exception.class)
    ```

[index için tıklayın](../README.md)
