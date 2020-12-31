* ### Referanslar
    Bu çalışmadaki örnekler **simple-soap-client** adlı projedede kodlanmıştır.

    
* ### SOAP serverin ayağa kaldırılması
- simple-soap-server adlı projede bir SOAP server uygulaması geliştirilmiştir.
- Bu proje ayağa kaldırılarak SOAP client buradaki serverı consume edecektir.
```
$ rm -rf ~/development/servers/tomcat7/webapps/axis2/WEB-INF/services/simple-soap-server*;  cd ~/development/git/sbank-workshop/axis2-soap-examples/simple-soap-server/; mvn clean package; cp target/simple-soap-server-1.0.aar ~/development/servers/tomcat7/webapps/axis2/WEB-INF/services/; ~/development/servers/tomcat7/bin/shutdown.sh; ~/development/servers/tomcat7/bin/startup.sh
```
- Ayağa kaldırma işleminden sonra aşağıdaki Http adreslerine erişim yapılarak sunucunun durumu gözlemlenebilir.
- http://localhost:8080/axis2/ 
- http://localhost:8080/axis2/services/listServices
- http://localhost:8080/axis2/services/PersonServiceImpl?wsdl


* ### Anotasyonlar
- @XmlRootElement(name = "addPersonResponse") : Class'ı XML deki ROOT element olarak işaretler.
- @XmlElement(name = "age") : Variable ile XML deki isimler farklı olabilir. int yas; olarak tanımlanmış değişkenin XML deki karşılığının age olduğu anlamına gelir.
- @XmlType(propOrder = {"id", "yas", "isim"}) XML içerisine elementlerin hangi sırada yerleşeceğini belirler.


* ### Marshalling ve Unmarshalling
- JAXB(Java Xml Binding)  : adlı paket içerisinde bulunurlar.
- Marshalling : Java class'ını XML'e dönüştürme.
- Unmarshalling : XML veriyi Java objesine dönüştürme


* ### Java Örneği
```
OkHttpClient client = new OkHttpClient().newBuilder().build();
MediaType mediaType = MediaType.parse("text/plain");
RequestBody body = RequestBody.create(mediaType, "<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:ser=\"http://service.ba.com\">\n   <soap:Header/>\n   <soap:Body>\n      <ser:getRegions/>\n   </soap:Body>\n</soap:Envelope>");
Request request = new Request.Builder()
  .url("http://localhost:8080/axis2/services/RegionService?wsdl")
  .method("POST", body)
  .addHeader("Content-Type", "text/plain")
  .build();
Response response = client.newCall(request).execute();
```

* ### CURL requesti
```
curl --location --request POST 'http://localhost:8080/axis2/services/RegionService?wsdl' \
--header 'Content-Type: text/plain' \
--data-raw '<soap:Envelope xmlns:soap="http://www.w3.org/2003/05/soap-envelope" xmlns:ser="http://service.ba.com">
   <soap:Header/>
   <soap:Body>
      <ser:getRegions/>
   </soap:Body>
</soap:Envelope>'
```

* ### Kaynaklar
- https://www.rukspot.com/blog/axis_2_sample_web_service_client_with_maven_and_eclipse

[index için tıklayın](../README.md)
