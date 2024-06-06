package com.lab4.models;

import java.sql.*;

public class RegistrarFuncionario {
    public static String registrarFuncionario(String nome, String apelido, int cargaHoraria, Date dataContratacao) {
        String sql = "INSERT INTO funcionarios (nome, apelido, carga_horaria, data_contratacao) VALUES (?, ?, ?, ?)";
        String id = null;

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, nome);
            pstmt.setString(2, apelido);
            pstmt.setInt(3, cargaHoraria);
            pstmt.setDate(4, dataContratacao);

            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }
}
