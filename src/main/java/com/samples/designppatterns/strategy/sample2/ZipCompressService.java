package com.samples.designppatterns.strategy.sample2;

import java.io.File;
import java.util.ArrayList;

public class ZipCompressService implements CompressService {

    @Override
    public void compressFiles(ArrayList<File> files) {
        files.forEach(f -> {
            printLog(f.getName());
        });
    }

    @Override
    public void compress(File file) {
        printLog(file.getName());
    }

    private void printLog(String filename) {
        System.out.println(filename + " file will be compressed as ZIP format...");
    }
}
