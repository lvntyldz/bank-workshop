package com.badam;

import com.badam.dto.PersonDTO;
import com.badam.dto.ResponseDTO;
import okhttp3.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.soap.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.nio.charset.Charset;

public class Run {

    public static ResponseDTO xmlToDTO(String xml) throws JAXBException, SOAPException, IOException {

        final InputStream inputStream = new ByteArrayInputStream(xml.getBytes(Charset.forName("UTF-8")));
        final SOAPMessage message = MessageFactory.newInstance().createMessage(null, inputStream);
        final SOAPPart sp = message.getSOAPPart();
        final SOAPEnvelope env = sp.getEnvelope();
        final SOAPBody body = env.getBody();
        final JAXBContext jaxbContext = JAXBContext.newInstance(ResponseDTO.class);
        final Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        return (ResponseDTO) jaxbUnmarshaller.unmarshal(body.extractContentAsDocument());
    }

    public static String dtoToXML(PersonDTO personDto) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(PersonDTO.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty("jaxb.fragment", Boolean.TRUE);
        StringWriter stringWriter = new StringWriter();
        marshaller.marshal(personDto, stringWriter);

        String soapEnvelope = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://service.ba.com\" xmlns:xsd=\"http://dto.ba.com/xsd\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <ser:addPerson>\n" +
                stringWriter.toString() +
                "      </ser:addPerson>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";

        return soapEnvelope;
    }

    public static void main(String[] args) throws IOException, JAXBException, SOAPException {

        PersonDTO dto = new PersonDTO();
        dto.setYas(12);
        dto.setId(5);
        dto.setIsim("ali");

        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("text/plain");

        RequestBody body = RequestBody.create(mediaType, dtoToXML(dto));
        Request request = new Request.Builder()
                .url("http://localhost:8080/axis2/services/PersonServiceImpl?wsdl")
                .method("POST", body)
                .addHeader("Content-Type", "text/plain")
                .build();

        Response response = client.newCall(request).execute();

        String responseBody = response.body().string();
        System.out.println("response : " + responseBody);

        ResponseDTO responseDTO = xmlToDTO(responseBody);
        System.out.println(responseDTO);

    }
}
