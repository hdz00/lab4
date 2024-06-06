package com.lab4.services;

import com.google.zxing.NotFoundException;
import com.lab4.models.LerQRCode;
import com.lab4.models.RegistroFrequencia;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import com.lab4.models.GerarQRCode;

@Service
public class QRCodeService {

    public void generateQRCode(String id) {
        try {
            GerarQRCode.gerarQRCode(id, "QRCode_" + id + ".png");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String lerQRCode(MultipartFile file) throws IOException, NotFoundException {
        Path tempDir = Files.createTempDirectory("");
        Path filePath = tempDir.resolve(file.getOriginalFilename());
        file.transferTo(filePath.toFile());

        String idLido = LerQRCode.lerQRCode(filePath.toString());

        Files.delete(filePath);
        Files.delete(tempDir);

        return idLido;
    }

    public void registerPresence(String idLido) {
        RegistroFrequencia.registrarFrequencia(idLido);
    }
}