package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SingletonConnection {

    // 1. Variável estática que guarda a INSTÂNCIA DA CLASSE (o Singleton em si)
    private static volatile SingletonConnection instance;

    // 2. Variável de instância que guarda a CONEXÃO com o banco
    private Connection connection;

    // 3. Construtor Privado: Aqui fazemos a conexão (acontece apenas 1 vez)
    private SingletonConnection() {
        try {
            // Sua string de conexão (Nota: O user/senha já estão na URL que você passou)
            String url = "jdbc:mysql://avnadmin:AVNS_TSkByQvZMLFQD6e4eps@mysql-singleton-estudante-b430.h.aivencloud.com:25747/Singleton?ssl-mode=REQUIRED";

            // O DriverManager cria a conexão e guardamos na variável 'this.connection'
            this.connection = DriverManager.getConnection(url);

            System.out.println("Conexão estabelecida com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco: " + e.getMessage());
        }
    }

    // 4. Método estático para pegar a instância do Singleton
    public static SingletonConnection getInstance() {
        if (instance == null) {
            synchronized (SingletonConnection.class) {
                if (instance == null) {
                    instance = new SingletonConnection();
                }
            }
        }
        return instance;
    }

    // 5. Método público para entregar a conexão para quem pedir
    public Connection getConnection() {
        return this.connection;
    }

}