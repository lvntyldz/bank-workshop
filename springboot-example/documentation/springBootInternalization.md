* ### Referanslar
    Bu çalışmadaki örnekler **com.ba.controller.LanguageController** adlı class ta kodlanmıştır.
    
* ### Açıklamalar
    - I18N içindeki 18,   internationalization kelimesi içindeki karakter sayısını temsil eder(I ve N hariç) 
    - I18N implementasyonu için LocaleResolver ve ResourceBundleMessageSource classları configure edilmelidir.

* ### Kofigürasyon
    - Kofigürasyon işlemleri için InternalizationConfiguration adında bir class oluşturulur.
    - @Configuration annotation'ı ile bu classın bir konfigürasyon bean'i olduğu springe bildirilir.
    - Bu class içinde dil dosyalarının adı,adresi ve varsayılan dil(locale) tanımlanır
    
    - Varsayılan dil ayarının belirlenmesi 
    ```
    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver resolver = new SessionLocaleResolver();
        resolver.setDefaultLocale(Locale.US);
        return resolver;
    }
    ```
    
    - Dil etiketlerinin(label) tutulacağı dosyanın seçilmesi
    ```
    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        messageSource.setUseCodeAsDefaultMessage(true);
        return messageSource;
    }
    ```
    
    - Dil etiketlerini barındıracak olan messages_en.properties ve messages_tr.properties dosyaları resources dizini altında oluştrulur.
    - messages_tr.properties
    ```
    app.welcome.message=hello
    ```
    
    - messages_tr.properties
    ```
    app.welcome.message=merhaba
    ```

* ### Controller katmanı
    - LanguageController adında bir class oluşturulur.
    - Client tan seçilen dile göre yanıt verecek bir endpoint oluşturulur.
    ```
    @GetMapping
    public String welcomeMessageByLocale(@RequestHeader("Accept-Language") String locale) {
        return messageSource.getMessage("app.welcome.message", null, new Locale(locale));
    }
    ```
    
    - Burada dil seçeneğinin client tan gelecek Http requestlerinde header da yeralacağı belirtilmiştir
    ```
    @RequestHeader("Accept-Language") String locale
    ```

* ### Endpointler
    - Türkçe karşılama mesajı('Accept-Language: tr')
    ```
    curl --location --request GET 'http://localhost:8080/language' --header 'Accept-Language: tr'
    ```
    - İngilizce karşılama mesajı('Accept-Language: en')
    ```
    curl --location --request GET 'http://localhost:8080/language' --header 'Accept-Language: en'
    ```

* ### Kaynaklar
    - https://www.tutorialspoint.com/spring_boot/spring_boot_internationalization.htm
    - https://www.javatpoint.com/restful-web-services-internationalization
    

[index için tıklayın](../README.md)
