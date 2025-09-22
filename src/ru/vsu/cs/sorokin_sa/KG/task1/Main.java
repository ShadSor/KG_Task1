package ru.vsu.cs.sorokin_sa.KG.task1;

import javax.swing.*;

public class Main {
    public static void main(String [] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Sun and Moon Animation");

            // Создаем главную панель
            MainWindow mainWindow = new MainWindow();

            // Настраиваем JFrame
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(mainWindow);
            frame.pack();
            frame.setLocationRelativeTo(null); // Центрируем окно
            frame.setResizable(false); // Запрещаем изменение размера (опционально)
            frame.setVisible(true);
        });
    }
}