package com.lab4.controllers;

import com.google.zxing.NotFoundException;
import com.lab4.services.QRCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class QRCodeController {

    @Autowired
    private QRCodeService qrCodeService; // Ensure this service name matches your actual service class name.

    @GetMapping("/uploadQRCode")
    public String showUploadQRCodeForm() {
        // This method returns the name of the HTML file that contains the form for
        // uploading QR codes
        return "uploadQRCode"; // Make sure you have a Thymeleaf template named 'uploadQRCode.html'
    }

    @PostMapping("/uploadQRCode")
    public String uploadQRCode(@RequestParam("file") MultipartFile file, Model model) {
        try {
            // Process the QR code
            String idLido = qrCodeService.lerQRCode(file);
            qrCodeService.registerPresence(idLido);
            model.addAttribute("message", "Presence registered for ID: " + idLido);
        } catch (IOException | NotFoundException e) {
            e.printStackTrace();
            model.addAttribute("message", "Error processing QR Code: " + e.getMessage());
        }
        return "uploadQRCode"; // Redirects back to the same page after submitting the form
    }
}