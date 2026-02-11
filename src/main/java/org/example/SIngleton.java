package org.example;

public class SIngleton {

    // A única impressora do escritório. 'volatile' garante que todos a vejam imediatamente.
    private static volatile SIngleton instance;

    // Construtor privado: Proíbe funcionários de comprarem impressoras por conta própria.
    private SIngleton() {
        // Instalação dos drivers...
    }

    // O Balcão de TI: O único lugar para pedir a impressora.
    public static SIngleton getInstance() {

        // 1. Olhada rápida: Se a impressora já existe, usa ela e economiza tempo.
        if (instance == null) {

            // 2. A Fila (Trava): Só um funcionário entra na sala de instalação por vez.
            synchronized (SIngleton.class) {

                // 3. A Confirmação (Double Check): "O colega da frente já instalou enquanto eu esperava?"
                // Se sim, não instala outra. Se não, instala a primeira.
                if (instance == null) {
                    instance = new SIngleton();
                }
            }
        }
        return instance;
    }
}