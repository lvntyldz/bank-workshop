package com.ba.service;

import com.ba.domain.Game;
import com.ba.domain.Todo;
import com.itextpdf.barcodes.Barcode128;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.UnitValue;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PdfService {

    public void generateBarcodeAndAddToPdf(String destFilePath, String code) throws Exception {
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(destFilePath));
        Barcode128 barcode128 = prepareBarcode128(pdfDoc, code);
        Image barcodeAsImage = new Image(barcode128.createFormXObject(pdfDoc));

        Table table = new Table(UnitValue.createPercentArray(2)).useAllAvailableWidth();
        table.addCell("Barcode Verisi : ");

        Cell cell = new Cell().add(barcodeAsImage);
        table.addCell(cell);

        addPrdfToDocument(pdfDoc, table);
    }

    public void generateTodoList(String destFilePath, List<Todo> todos) throws Exception {
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(destFilePath));

        Table table = new Table(UnitValue.createPercentArray(2)).useAllAvailableWidth();

        todos.forEach(todo -> {
            table.addCell(todo.getTitle());
            table.addCell(todo.getDescription());
        });

        addPrdfToDocument(pdfDoc, table);
    }

    public void generateGameList(String destFilePath, List<Game> games) throws Exception {
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(destFilePath));

        Table table = new Table(UnitValue.createPercentArray(5)).useAllAvailableWidth();

        games.forEach(game -> {
            table.addCell(game.getName());
            table.addCell(game.getDescription());
            table.addCell(String.valueOf(game.getLeagueId()));
            table.addCell(String.valueOf(game.getRoundNo()));
            table.addCell(String.valueOf(game.getCreateDate()));
        });

        addPrdfToDocument(pdfDoc, table);
    }

    private void addPrdfToDocument(PdfDocument pdfDoc, Table table) {
        Document doc = new Document(pdfDoc);
        doc.add(table);
        doc.close();
    }

    private Barcode128 prepareBarcode128(PdfDocument pdfDoc, String code) {
        Barcode128 barcode128 = new Barcode128(pdfDoc);
        barcode128.setBaseline(-1);
        barcode128.setSize(12);
        barcode128.setCode(code);
        barcode128.setCodeType(Barcode128.CODE128);
        return barcode128;
    }
}
