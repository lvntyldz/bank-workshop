package com.samples.designppatterns.strategy.sample2;

import java.io.File;
import java.util.ArrayList;

public interface CompressService {
    public void compressFiles(ArrayList<File> files);
    public void compress(File file);
}
