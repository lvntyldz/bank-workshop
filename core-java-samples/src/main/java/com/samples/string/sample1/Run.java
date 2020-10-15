package com.samples.string.sample1;

public class Run {

    public static void main(String[] args) {
        immutableStringExample();
        stringPoolExample();
        stringNewReferanceExample();
        createStringWithCharArrayConstructor();

    }

    private static void createStringWithCharArrayConstructor() {
        char[] charArr = {'O', 'r', 'a', 'c', 'l', 'e'};

        //String'in charr array parametresiyle çağrılan constructoru ile String nesnesi yaratma
        String strData = new String(charArr);
        System.out.println(strData);
    }

    private static void stringNewReferanceExample() {
        String s1 = new String("Oracle");
        String s2 = new String("Oracle");

        if (s1 == s2) {//new ile yaratıldığı için referansları farklı
            System.out.println("s1 == s2");
        } else {
            System.out.println("s1 != s2");
        }

    }

    private static void stringPoolExample() {
        //string pool example
        String s1 = "Ahmet";
        String s2 = "Ahmet";
        String s3 = "Ahmet";

        //üstteki değişkenler new ile yaratılmadığı için pool da 1 adet String objesi vardır ve değeri "Ahmet" şeklindedir.
        //s1, s2 ve s3 değişkenleri aynı heap alanını işaret etmektedir.
        if (s1 == s2 && s2 == s3) {//üçünün referansı da aynı
            System.out.println("s1 == s2 == s3");
        } else {
            System.out.println("s1 != s2 || s2!= s3");
        }
    }

    private static void immutableStringExample() {
        //immutable(değişmezlik)
        String name = "Ahmet";
        name = "Can";//Yeni String objesi oluşturulur.
    }
}
