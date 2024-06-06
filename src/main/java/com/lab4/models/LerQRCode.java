package com.lab4.models;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LerQRCode {
    public static String lerQRCode(String filePath) throws IOException, NotFoundException {
        BufferedImage bufferedImage;
        try {
            // Tente ler a imagem do arquivo
            bufferedImage = ImageIO.read(new File(filePath));
            if (bufferedImage == null) {
                throw new IOException("Imagem não pode ser lida do arquivo: " + filePath);
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler a imagem do arquivo: " + filePath);
            throw e;
        }

        LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

        try {
            // Tente decodificar o QR Code
            Result result = new MultiFormatReader().decode(bitmap);
            return result.getText();
        } catch (NotFoundException e) {
            System.err.println("QR Code não encontrado na imagem: " + filePath);
            throw e;
        } catch (Exception e) {
            System.err.println("Erro ao decodificar o QR Code: " + filePath);
            throw new IOException("Erro ao decodificar o QR Code", e);
        }
    }
}