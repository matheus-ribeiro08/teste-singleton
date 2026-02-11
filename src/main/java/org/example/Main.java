package org.example;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        // --- [MOMENTO 1: O PEDIDO] ---
        // AQUI ESTÁ A MÁGICA DO SINGLETON:
        // Em vez de darmos 'new' (comprar uma impressora nova),
        // nós chamamos 'getInstance' (perguntamos: "Cadê a impressora do escritório?").
        // Se já existir uma, ele devolve a mesma. Se não, ele cria a primeira.
        SingletonConnection singleton = SingletonConnection.getInstance();

        // Preparando o comando SQL (O documento a ser impresso)
        String sql = "INSERT INTO Usuarios (id, nome, email) VALUES (?, ?, ?)";

        try {
            // --- [MOMENTO 2: O USO DO RECURSO] ---
            // Perceba que não configuramos senha ou usuário aqui.
            // Apenas pedimos: "Singleton, me empresta a sua conexão aberta?"
            // Isso centraliza a configuração em um único lugar.
            PreparedStatement stmt = singleton.getConnection().prepareStatement(sql);

            // Configurando os dados (Preenchendo o formulário)
            stmt.setInt(1, 8);
            stmt.setString(2, "Gabriela");
            stmt.setString(3, "gab@exemplo.com");

            // Enviando para o banco
            stmt.execute();

            System.out.println("Gab inserido com sucesso!");

            // Fechamos o 'statement' (o comando), mas a CONEXÃO (a impressora)
            // continua viva dentro do Singleton para ser usada por outras partes do código.
            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}