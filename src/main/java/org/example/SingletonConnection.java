package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingletonConnection {

    // 1. O GERENTE (Singleton): A única instância que gerencia tudo.
    // O 'volatile' garante que todos vejam o gerente imediatamente.
    private static volatile SingletonConnection instance;

    // 2. A IMPRESSORA (O Recurso): A conexão real com o banco que vamos compartilhar.
    private Connection connection;

    // 3. A INSTALAÇÃO (Construtor Privado):
    // Acontece APENAS UMA VEZ na vida do programa. É a parte "cara" e demorada.
    private SingletonConnection() {
        try {
            // Configuração da "impressora" (URL, Usuário, Senha)
            String url = "jdbc:mysql://avnadmin:AVNS_TSkByQvZMLFQD6e4eps@mysql-singleton-estudante-b430.h.aivencloud.com:25747/Singleton?ssl-mode=REQUIRED";

            // Aqui ligamos o cabo na tomada (Conectamos ao banco)
            this.connection = DriverManager.getConnection(url);

            System.out.println("Conexão estabelecida com sucesso! (Impressora pronta)");

        } catch (SQLException e) {
            System.err.println("Erro ao conectar: " + e.getMessage());
        }
    }

    // 4. O BALCÃO DE ATENDIMENTO (getInstance):
    // Garante que só existe UM gerente cuidando da impressora (Thread Safety).
    public static SingletonConnection getInstance() {
        if (instance == null) {
            synchronized (SingletonConnection.class) { // Organiza a fila
                if (instance == null) { // Confere se ninguém furou a fila
                    instance = new SingletonConnection();
                }
            }
        }
        return instance;
    }

    // 5. USAR A IMPRESSORA (Getter):
    // O método que o 'Main' chama para pegar a conexão aberta e trabalhar.
    public Connection getConnection() {
        return this.connection;
    }
}