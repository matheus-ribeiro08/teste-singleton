package org.example;

public class SIngleton {
    // 'volatile' garante que alterações sejam visíveis para todos os threads imediatamente
    private static volatile SIngleton instance;

    // Construtor privado evita instanciação externa
    private SIngleton() {
        // Inicialização de recursos (ex: conexão com banco)
    }

    public static SIngleton getInstance() {
        // Primeiro cheque (sem bloqueio) para performance
        if (instance == null) {
            // Bloco sincronizado apenas se a instância não existir
            synchronized (SIngleton.class) {
                // Segundo cheque (dentro do bloqueio) para segurança
                if (instance == null) {
                    instance = new SIngleton();
                }
            }
        }
        return instance;
    }
}