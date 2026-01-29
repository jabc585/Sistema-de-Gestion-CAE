package vista;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import model.Solicitud;
import model.AnalisisResultado;

public class DashboardFrame extends JFrame {
    private JTabbedPane tabbedPane;
    private ChartsPanel chartsPanel;
    private DataTablePanel dataTablePanel;
    
    public DashboardFrame(List<Solicitud> solicitudes, AnalisisResultado analisis) {
        setTitle("Dashboard de Ahorro Energético");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);
        
        // Crear barra de menú
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("Archivo");
        JMenuItem exitItem = new JMenuItem("Salir");
        exitItem.addActionListener(e -> System.exit(0));
        fileMenu.add(exitItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);
        
        // Panel principal con pestañas
        tabbedPane = new JTabbedPane();
        
        // Panel de gráficos
        chartsPanel = new ChartsPanel(analisis);
        tabbedPane.addTab("Gráficos", chartsPanel);
        
        // Panel de datos
        dataTablePanel = new DataTablePanel(solicitudes);
        tabbedPane.addTab("Datos", dataTablePanel);
        
        // Panel de predicciones
        JPanel predictionPanel = createPredictionPanel();
        tabbedPane.addTab("Predicciones", predictionPanel);
        
        // Panel de resumen
        JPanel summaryPanel = createSummaryPanel(analisis);
        tabbedPane.addTab("Resumen", summaryPanel);
        
        add(tabbedPane);
    }
    
    private JPanel createPredictionPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        
        // Título
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        JLabel title = new JLabel("Predicción de Ahorro Energético");
        title.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(title, gbc);
        
        // Campos de entrada
        gbc.gridwidth = 1;
        gbc.gridy = 1;
        gbc.gridx = 0;
        panel.add(new JLabel("Sector:"), gbc);
        
        gbc.gridx = 1;
        JComboBox<String> sectorCombo = new JComboBox<>(new String[]{"Industrial", "Terciario", "Residencial", "Agropecuario", "Transporte"});
        panel.add(sectorCombo, gbc);
        
        gbc.gridy = 2;
        gbc.gridx = 0;
        panel.add(new JLabel("Estado:"), gbc);
        
        gbc.gridx = 1;
        JComboBox<String> estadoCombo = new JComboBox<>(new String[]{"Favorable", "Desfavorable", "Desistimiento"});
        panel.add(estadoCombo, gbc);
        
        gbc.gridy = 3;
        gbc.gridx = 0;
        panel.add(new JLabel("Año:"), gbc);
        
        gbc.gridx = 1;
        JSpinner añoSpinner = new JSpinner(new SpinnerNumberModel(2024, 2020, 2030, 1));
        panel.add(añoSpinner, gbc);
        
        gbc.gridy = 4;
        gbc.gridx = 0;
        panel.add(new JLabel("Número Actuaciones:"), gbc);
        
        gbc.gridx = 1;
        JSpinner actuacionesSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 50, 1));
        panel.add(actuacionesSpinner, gbc);
        
        // Botón de predicción
        gbc.gridy = 5;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        JButton predictButton = new JButton("Predecir Ahorro");
        JLabel resultLabel = new JLabel("Resultado: ");
        resultLabel.setFont(new Font("Arial", Font.BOLD, 14));
        
        predictButton.addActionListener(e -> {
            String sector = (String) sectorCombo.getSelectedItem();
            String estado = (String) estadoCombo.getSelectedItem();
            int año = (int) añoSpinner.getValue();
            int actuaciones = (int) actuacionesSpinner.getValue();
            
            // Aquí llamarías al modelo de predicción
            double predicted = 100000 + (Math.random() * 500000);
            resultLabel.setText(String.format("Resultado: %.2f kWh predichos", predicted));
        });
        
        panel.add(predictButton, gbc);
        
        gbc.gridy = 6;
        panel.add(resultLabel, gbc);
        
        return panel;
    }
    
    private JPanel createSummaryPanel(AnalisisResultado analisis) {
        JPanel panel = new JPanel(new GridLayout(4, 2, 20, 20));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Total ahorro
        JPanel totalPanel = createSummaryCard("Ahorro Total", 
            String.format("%,.2f kWh", analisis.getAhorroTotal()), Color.BLUE);
        
        // Ahorro promedio
        JPanel avgPanel = createSummaryCard("Ahorro Promedio", 
            String.format("%,.2f kWh", analisis.getAhorroPromedio()), Color.GREEN);
        
        // Top sector
        String topSector = analisis.getAhorroPorSector().entrySet().stream()
            .max(Map.Entry.comparingByValue())
            .map(Map.Entry::getKey)
            .orElse("N/A");
        JPanel sectorPanel = createSummaryCard("Sector Líder", topSector, Color.ORANGE);
        
        // Total solicitudes
        long totalSolicitudes = analisis.getConteoPorEstado().values().stream().mapToLong(Long::longValue).sum();
        JPanel countPanel = createSummaryCard("Total Solicitudes", String.valueOf(totalSolicitudes), Color.RED);
        
        panel.add(totalPanel);
        panel.add(avgPanel);
        panel.add(sectorPanel);
        panel.add(countPanel);
        
        return panel;
    }
    
    private JPanel createSummaryCard(String title, String value, Color color) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBorder(BorderFactory.createLineBorder(color, 2));
        card.setBackground(Color.WHITE);
        
        JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        titleLabel.setForeground(color.darker());
        
        JLabel valueLabel = new JLabel(value, SwingConstants.CENTER);
        valueLabel.setFont(new Font("Arial", Font.BOLD, 18));
        
        card.add(titleLabel, BorderLayout.NORTH);
        card.add(valueLabel, BorderLayout.CENTER);
        
        return card;
    }
}