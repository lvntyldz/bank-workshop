package com.samples.designppatterns.strategy.sample2;

import java.io.File;
import java.util.ArrayList;

public class Run {


    public static void main(String[] args) {
        ArrayList<File> zipFileList = new ArrayList<>();
        zipFileList.add(new File("aabbcc.log"));
        zipFileList.add(new File("ddeeff.log"));
        zipFileList.add(new File("gghhii.log"));

        //zip file
        CompressContext context1 = new CompressContext();
        context1.setCompressionStrategy(new ZipCompressService());
        context1.createArchive(zipFileList);
        context1.createSingleArchive(new File("abcdefgh.log"));

        System.out.println("\n ********** ********** ********** \n");

        ArrayList<File> rarFileList = new ArrayList<>();
        rarFileList.add(new File("112233.log"));
        rarFileList.add(new File("445566.log"));
        rarFileList.add(new File("778899.log"));

        //rar file
        CompressContext context2 = new CompressContext();
        context2.setCompressionStrategy(new RarCompressService());
        context2.createArchive(rarFileList);
        context2.createSingleArchive(new File("1234567890.log"));

    }
}
