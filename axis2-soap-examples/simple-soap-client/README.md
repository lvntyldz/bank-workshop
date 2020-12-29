* ### Referanslar
    Bu çalışmadaki örnekler **simple-soap-client** adlı projedede kodlanmıştır.
    
* ### Java Örneği
```
OkHttpClient client = new OkHttpClient().newBuilder()
  .build();
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
