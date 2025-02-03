package com.carload.vehiclejava21crud.controller;

import com.carload.vehiclejava21crud.models.Vehicle;
import com.carload.vehiclejava21crud.services.VehicleService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Cell;


import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping
    public ResponseEntity<List<Vehicle>> getAllVehicles() {
        List<Vehicle> vehicles = vehicleService.getAllVehicles();
        return ResponseEntity.ok(vehicles);
    }

    @PostMapping
    public ResponseEntity<Vehicle> createVehicle(@RequestBody Vehicle vehicle) {
        Vehicle savedVehicle = vehicleService.saveVehicle(vehicle);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedVehicle);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable UUID id) {
        return vehicleService.getVehicleById(id)
                .map(vehicle -> ResponseEntity.ok(vehicle))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable UUID id) {
        if (!vehicleService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        vehicleService.deleteVehicleById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vehicle> updateVehicle(@PathVariable UUID id, @RequestBody Vehicle vehicle) {
        if (!vehicleService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        vehicle.setId(id); // Garantir que o ID do veículo seja o mesmo da URL
        Vehicle updatedVehicle = vehicleService.updateVehicle(vehicle);
        return ResponseEntity.ok(updatedVehicle);
    }

    @GetMapping("/pdf")
    public ResponseEntity<byte[]> generatePdf() {
        List<Vehicle> vehicles = vehicleService.getAllVehicles();

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            PdfWriter writer = new PdfWriter(outputStream);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            document.add(new Paragraph("Lista de Veículos").setBold().setFontSize(16));

            Table table = new Table(new float[]{4, 3, 3, 2, 2, 2});
            table.addCell(new Cell().add(new Paragraph("ID")).setBold());
            table.addCell(new Cell().add(new Paragraph("Modelo")).setBold());
            table.addCell(new Cell().add(new Paragraph("Fabricante")).setBold());
            table.addCell(new Cell().add(new Paragraph("Ano")).setBold());
            table.addCell(new Cell().add(new Paragraph("Placa")).setBold());
            table.addCell(new Cell().add(new Paragraph("Preço Diária")).setBold());

            for (Vehicle vehicle : vehicles) {
                table.addCell(new Cell().add(new Paragraph(vehicle.getId().toString())));
                table.addCell(new Cell().add(new Paragraph(vehicle.getModel())));
                table.addCell(new Cell().add(new Paragraph(vehicle.getManufacturer())));
                table.addCell(new Cell().add(new Paragraph(vehicle.getManufacturedYear() != null ? vehicle.getManufacturedYear() : "N/A")));
                table.addCell(new Cell().add(new Paragraph(vehicle.getLicensePlate())));
                table.addCell(new Cell().add(new Paragraph(vehicle.getDailyRentalPrice().toString())));
            }

            document.add(table);
            document.close();

            byte[] pdfBytes = outputStream.toByteArray();

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=vehicles.pdf")
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(pdfBytes);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

}
