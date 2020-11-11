package com.mapper;

import com.google.gson.Gson;

public class Run {

    public static void main(String[] args) throws Exception {

        Person person = new Person();
        person.setFirstname("ali");
        person.setLastname("ALİOĞLU");

        Product product = new Product();
        product.setPrice(1000);
        product.setTitle("monitor");

        System.out.println(person);
        System.out.println(product);

        Gson gson = new Gson();
        Xml xml = new Xml();

        String personXmlString = xml.toXml(person);
        String productXmlString = xml.toXml(product);

        System.out.println("personXmlString : " + personXmlString);
        System.out.println("productXmlString : " + productXmlString);

        String jsonInString = gson.toJson(person);
        System.out.println(jsonInString);

        Person personObj1 = gson.fromJson(jsonInString, Person.class);
        Person personObj2 = gson.fromJson(jsonInString, Person.class);

        System.out.println(personObj1);
        System.out.println(personObj2);

        Person person1 = xml.fromXml(personXmlString, Person.class);
        Product product1 = xml.fromXml(productXmlString, Product.class);

        System.out.println(person1);
        System.out.println(product1);

    }

}
