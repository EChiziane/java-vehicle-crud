package com.carload.vehiclejava21crud.controller;

import com.carload.vehiclejava21crud.models.VehicleEntity;
import com.carload.vehiclejava21crud.services.VehicleService;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Cell;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/vehicles")
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleService vehicleService;

    @GetMapping
    public ResponseEntity<List<VehicleEntity>> getAllVehicles() {
        List<VehicleEntity> vehicles = vehicleService.getAllVehicles();
        return ResponseEntity.ok(vehicles);
    }

    @PostMapping
    public ResponseEntity<VehicleEntity> createVehicle(@RequestBody VehicleEntity vehicleEntity) {
        VehicleEntity savedVehicle = vehicleService.saveVehicle(vehicleEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedVehicle);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleEntity> getVehicleById(@PathVariable UUID id) {
        return vehicleService.getVehicleById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable UUID id) {
        if (!vehicleService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        vehicleService.deleteVehicleById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<VehicleEntity> updateVehicle(@PathVariable UUID id, @RequestBody VehicleEntity vehicleEntity) {
        if (!vehicleService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        vehicleEntity.setId(id);
        VehicleEntity updatedVehicle = vehicleService.updateVehicle(vehicleEntity);
        return ResponseEntity.ok(updatedVehicle);
    }

    @GetMapping("/pdf")
    public ResponseEntity<byte[]> generatePdf() {
        List<VehicleEntity> vehicles = vehicleService.getAllVehicles();

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
             PdfWriter writer = new PdfWriter(outputStream);
             PdfDocument pdf = new PdfDocument(writer);
             Document document = new Document(pdf)) {

            document.add(new Paragraph("Lista de Veículos").setBold().setFontSize(16));

            Table table = new Table(new float[]{4, 3, 3, 2, 2, 2});
            table.addCell(new Cell().add(new Paragraph("ID")).setBold());
            table.addCell(new Cell().add(new Paragraph("Modelo")).setBold());
            table.addCell(new Cell().add(new Paragraph("Fabricante")).setBold());
            table.addCell(new Cell().add(new Paragraph("Ano")).setBold());
            table.addCell(new Cell().add(new Paragraph("Placa")).setBold());
            table.addCell(new Cell().add(new Paragraph("Preço Diária")).setBold());

            for (VehicleEntity vehicle : vehicles) {
                table.addCell(new Cell().add(new Paragraph(vehicle.getId().toString())));
                table.addCell(new Cell().add(new Paragraph(vehicle.getModel() != null ? vehicle.getModel() : "N/A")));
                table.addCell(new Cell().add(new Paragraph(vehicle.getManufacturer() != null ? vehicle.getManufacturer() : "N/A")));
                table.addCell(new Cell().add(new Paragraph(vehicle.getManufacturedYear() != null ? String.valueOf(vehicle.getManufacturedYear()) : "N/A")));
                table.addCell(new Cell().add(new Paragraph(vehicle.getLicensePlate() != null ? vehicle.getLicensePlate() : "N/A")));
                table.addCell(new Cell().add(new Paragraph(vehicle.getDailyRentalPrice() != null ? vehicle.getDailyRentalPrice().toString() : "N/A")));
            }

            document.add(table);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=vehicles.pdf")
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(outputStream.toByteArray());

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
