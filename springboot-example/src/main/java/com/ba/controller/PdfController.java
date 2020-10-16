package com.ba.controller;

import com.ba.service.PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("pdf")
public class PdfController {

    public static final String PDF_FILE_PATH = "./target/barcode.pdf";

    @Autowired
    private PdfService service;

    @RequestMapping(value = {"/add", "/add/{barcodeTxt}"}, method = RequestMethod.GET)
    public String addBarcode(@PathVariable Map<String, String> pathVariables) throws Exception {
        String barcodeTxt = null;

        if (pathVariables.containsKey("barcodeTxt")) {
            barcodeTxt = pathVariables.get("barcodeTxt");
        }

        if (barcodeTxt == null) {
            barcodeTxt = generateBarcodeData();
        }

        service.generateBarcodeAndAddToPdf(PDF_FILE_PATH, barcodeTxt);
        return barcodeTxt + " : " + PDF_FILE_PATH;
    }

    private String generateBarcodeData() {
        return generateRandomVal() + "-" + generateRandomVal() + "-" + generateRandomVal();
    }

    private int generateRandomVal() {
        int min = 100;
        int max = 999;
        return (int) (Math.random() * (max - min + 1) + min);
    }
}
