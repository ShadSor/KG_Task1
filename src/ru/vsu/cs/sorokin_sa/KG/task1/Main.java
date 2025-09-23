package ru.vsu.cs.sorokin_sa.KG.task1;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String [] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Sun and Moon Animation");

            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            frame.setSize(screenSize.width, screenSize.height);

            MainWindow mainWindow = new MainWindow();

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(mainWindow);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setResizable(true);
            frame.setVisible(true);
        });
    }
}