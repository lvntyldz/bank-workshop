package com.samples.designppatterns.strategy.sample2;

import java.io.File;
import java.util.ArrayList;

public class CompressContext {
    private CompressService service;

    public void setCompressionStrategy(CompressService strategy) {
        this.service = strategy;
    }

    public void createArchive(ArrayList<File> files) {
        service.compressFiles(files);
    }

    public void createSingleArchive(File file) {
        service.compress(file);
    }
}
