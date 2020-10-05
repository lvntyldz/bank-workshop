##### properties dosyasındaki değerlere erişim
* @Value annotation'u ile properties dosyasındaki değerlere erişilip bir variable ile kullanılabilir
    ```
    @Value("${server.port}")
    private String serverPort;
    ```
    
    InfoController içinde tanımlanan getServerPort methoduyla properties den okunan port numarası bir endpoint ile public erişime açılmış olur.
    ```
    @GetMapping("info-server")
    public String getServerPort() {
        return serverPort;
    }
    ```
    URL : http://localhost:8080/info-server

* Profil bilgilerine programatically olarak erişmek için Environment bean'i kullanılabilir.
    ```
    @Autowired
    private Environment env;
    ```
    
    InfoController içerisine eklenen getProfile() methodu ile profil bilgisi ppublic erişime açılmış olur 
    ```
    @GetMapping("info-profile")
    public String getProfile() {
        List<String> profiles = Arrays.asList(env.getActiveProfiles());
        if (CollectionUtils.isEmpty(profiles)) {
            return null;
        }
    
        return profiles.get(0);
    }
    ```
    
    URL :http://localhost:8080/info-profile


[index için tıklayın](../README.md)
