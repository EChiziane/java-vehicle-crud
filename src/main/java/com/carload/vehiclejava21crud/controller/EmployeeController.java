package com.carload.vehiclejava21crud.controller;

import com.carload.vehiclejava21crud.models.EmployeeEntity;
import com.carload.vehiclejava21crud.models.dtos.EmployeeInputDto;
import com.carload.vehiclejava21crud.models.dtos.EmployeeOutputDto;
import com.carload.vehiclejava21crud.services.EmailService;
import com.carload.vehiclejava21crud.services.EmployeeService;
import jakarta.mail.MessagingException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final EmailService emailService;

    public EmployeeController(EmployeeService employeeService, EmailService emailService) {
        this.employeeService = employeeService;
        this.emailService=emailService;
    }

    @GetMapping
    public List<EmployeeOutputDto> getAllEmployees() {
        return convertToDtoList(employeeService.getAllEmployees());
    }

    @GetMapping("/active")
    public List<EmployeeOutputDto> getActiveEmployees() {
        return convertToDtoList(employeeService.getActiveEmployees());
    }

    @GetMapping("/nonactive")
    public List<EmployeeOutputDto> getNonActiveEmployees() {
        return convertToDtoList(employeeService.getInactiveEmployees());
    }

    @PostMapping
    public EmployeeOutputDto addEmployee(@RequestBody EmployeeInputDto employeeRequest) throws MessagingException {
        EmployeeEntity employee = new EmployeeEntity();
        employee.setFirstName(employeeRequest.getFirstName());
        employee.setLastName(employeeRequest.getLastName());
        employee.setEmail(employeeRequest.getEmail());
        employee.setStatus(employeeRequest.getStatus());
        employee.setGender(employeeRequest.getGenero());
        employee.setPhone(employeeRequest.getPhone());


        emailService.sendEmail("eddybruno43@gmail.com",
                "ednilsonchiziane.dev@gmail.com",
                "Olá,\n" +
                        "\n" +
                        "Temos um novo trabalho registrado com os seguintes detalhes:\n" +
                        "\n" +
                        "Material: Areia grossa\n" +
                        "Quantidade: 7m³\n" +
                        "Destino: Matola Gare\n" +
                        "Telefone: 87123456789\n" +
                        "Cliente: Albano José\n" +
                        "Data de Entrega: 21/12\n" +
                        "Caso precise de mais informações ou ajustes, favor entrar em contato.\n" +
                        "\n" +
                        "Atenciosamente,\n" +
                        "[Seu Nome]\n" +
                        "Transportes Chiziane\n" +
                        "\uD83D\uDCDE 845098583\n" +
                        "✉\uFE0F contato@transporteschiziane.com",
                "Este Corpo",null);

        EmployeeEntity savedEmployee = employeeService.addEmployee(employee);
        return new EmployeeOutputDto(savedEmployee);
    }

    // Método auxiliar para converter listas
    private List<EmployeeOutputDto> convertToDtoList(List<EmployeeEntity> employees) {
        return employees.stream().map(EmployeeOutputDto::new).collect(Collectors.toList());
    }
}
