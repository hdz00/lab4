package com.lab4.controllers;

import com.lab4.models.Employee;
import com.lab4.repositories.EmployeeRepository;
import com.lab4.services.QRCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private QRCodeService qrCodeService;

    @GetMapping("/createEmployee")
    public String showCreateEmployeeForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "createEmployee";
    }

    @PostMapping("/createEmployee")
    public String createEmployee(Employee employee, Model model) {
        Employee savedEmployee = employeeRepository.save(employee);
        qrCodeService.generateQRCode(savedEmployee.getId().toString());
        model.addAttribute("message", "Employee created with ID: " + savedEmployee.getId());
        return "redirect:/createEmployee";
    }
}