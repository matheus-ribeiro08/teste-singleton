package org.example;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        // Passo 1: Pegar a conexão através do Singleton
        // Note que não usamos 'new', usamos getInstance()
        SingletonConnection singleton = SingletonConnection.getInstance();

        // Passo 2: Usar a conexão para inserir os nomes (Miguel, Matheus, etc.)
        String sql = "INSERT INTO Usuarios (id, nome, email) VALUES (?, ?, ?)";

        try {
            // Pega o objeto Connection de dentro do Singleton
            PreparedStatement stmt = singleton.getConnection().prepareStatement(sql);

            // Exemplo inserindo o Miguel
            stmt.setInt(1, 8);
            stmt.setString(2, "Gabriela");
            stmt.setString(3, "gab@exemplo.com");
            stmt.execute();

            System.out.println("Gab inserido com sucesso!");
            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}