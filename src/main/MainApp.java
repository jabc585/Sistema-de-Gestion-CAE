package main;

import vista.DashboardFrame;
import model.Solicitud;
import model.AnalisisResultado;
import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import javax.swing.SwingUtilities;
import javax.swing.JOptionPane;

public class MainApp {
    private List<Solicitud> solicitudes;
    private AnalisisResultado analisis;
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainApp app = new MainApp();
            app.loadData();
            app.analyzeData();
            app.showDashboard();
        });
    }
    
    private void loadData() {
        solicitudes = new ArrayList<>();
        String csvFile = Paths.get("data", "SGC.csv").toString();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            boolean firstLine = true;
            
            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue;
                }
                
                String[] values = line.split(";");
                if (values.length >= 15) {
                    try {
                        Solicitud solicitud = new Solicitud(
                            values[0].trim(),
                            values[1].trim(),
                            values[2].trim(),
                            Integer.parseInt(values[3].trim()),
                            Double.parseDouble(values[4].trim().replace(".", "").replace(",", ".")),
                            values[5].trim(),
                            Integer.parseInt(values[6].trim()),
                            Double.parseDouble(values[7].trim().replace(".", "").replace(",", ".")),
                            values[8].trim(),
                            values[9].trim(),
                            values[10].trim(),
                            LocalDate.parse(values[11].trim(), dateFormatter),
                            values[13].trim(),
                            values[14].trim()
                        );
                        solicitudes.add(solicitud);
                    } catch (Exception e) {
                        System.err.println("Error parsing line: " + line);
                        e.printStackTrace();
                    }
                }
            }
            
            System.out.println("Datos cargados: " + solicitudes.size() + " registros");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, 
                "Error cargando datos: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void analyzeData() {
        // Ahorro por sector
        Map<String, Double> ahorroPorSector = solicitudes.stream()
            .collect(Collectors.groupingBy(
                Solicitud::getSector,
                Collectors.summingDouble(Solicitud::getTotalAhorroSolicitado)
            ));
        
        // Ahorro por comunidad
        Map<String, Double> ahorroPorComunidad = solicitudes.stream()
            .collect(Collectors.groupingBy(
                Solicitud::getComunidadAutonoma,
                Collectors.summingDouble(Solicitud::getTotalAhorroSolicitado)
            ));
        
        // Conteo por estado
        Map<String, Long> conteoPorEstado = solicitudes.stream()
            .collect(Collectors.groupingBy(
                Solicitud::getEstado,
                Collectors.counting()
            ));
        
        // Cálculos totales
        double ahorroTotal = solicitudes.stream()
            .mapToDouble(Solicitud::getTotalAhorroSolicitado)
            .sum();
        
        double ahorroPromedio = ahorroTotal / solicitudes.size();
        
        // Top 5 comunidades
        List<String> topComunidades = ahorroPorComunidad.entrySet().stream()
            .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
            .limit(5)
            .map(Map.Entry::getKey)
            .collect(Collectors.toList());
        
        analisis = new AnalisisResultado(
            ahorroPorSector,
            ahorroPorComunidad,
            conteoPorEstado,
            ahorroTotal,
            ahorroPromedio,
            topComunidades
        );
    }
    
    private void showDashboard() {
        DashboardFrame frame = new DashboardFrame(solicitudes, analisis);
        frame.setVisible(true);
    }
}