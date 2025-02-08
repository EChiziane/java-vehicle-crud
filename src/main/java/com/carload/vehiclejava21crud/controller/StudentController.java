package com.carload.vehiclejava21crud.controller;

import com.carload.vehiclejava21crud.models.StudentEntity;
import com.carload.vehiclejava21crud.services.StudentService;


import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Cell;


import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.io.ByteArrayOutputStream;
import java.util.List;
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<StudentEntity> showAllStudents() {
        List<StudentEntity> studentEntities = studentService.findAll();
        return studentEntities;

    }
    @PostMapping
    public StudentEntity createStudent(@RequestBody StudentEntity studentEntity) {
        studentService.save(studentEntity);
        return studentEntity;
    }

    @GetMapping("/pdf")
    public ResponseEntity<byte[]> generatePdf() {
        List<StudentEntity> studentEntities = studentService.findAll();

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            PdfWriter writer = new PdfWriter(outputStream);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            document.add(new Paragraph("Lista de Estudantes").setBold().setFontSize(16));

            // Criando a tabela com colunas correspondentes
            Table table = new Table(new float[]{4, 3, 3, 4, 3});
            table.addCell(new Cell().add(new Paragraph("ID")).setBold());
            table.addCell(new Cell().add(new Paragraph("Nome")).setBold());
            table.addCell(new Cell().add(new Paragraph("Sobrenome")).setBold());
            table.addCell(new Cell().add(new Paragraph("E-mail")).setBold());
            table.addCell(new Cell().add(new Paragraph("Data de Nascimento")).setBold());

            // Preenchendo a tabela com os dados dos estudantes
            for (StudentEntity studentEntity : studentEntities) {
                table.addCell(new Cell().add(new Paragraph(studentEntity.getId().toString())));
                table.addCell(new Cell().add(new Paragraph(studentEntity.getFirstName())));
                table.addCell(new Cell().add(new Paragraph(studentEntity.getLastName())));
                table.addCell(new Cell().add(new Paragraph(studentEntity.getEmail())));
                table.addCell(new Cell().add(new Paragraph(studentEntity.getBirthday())));
            }

            document.add(table);
            document.close();

            byte[] pdfBytes = outputStream.toByteArray();

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=students.pdf")
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(pdfBytes);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }



}
