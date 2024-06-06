package com.lab4.controllers;

import com.lab4.models.FrequencyRecord;
import com.lab4.repositories.FrequencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ReportController {

    @Autowired
    private FrequencyRepository frequencyRepository;

    @GetMapping("/managementReport")
    public String showManagementReport(Model model) {
        List<FrequencyRecord> records = frequencyRepository.findAll();
        model.addAttribute("records", records);
        return "managementReport";
    }
}