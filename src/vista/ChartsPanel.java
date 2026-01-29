package vista;

import javax.swing.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import java.awt.*;
import model.AnalisisResultado;

public class ChartsPanel extends JPanel {
    private AnalisisResultado analisis;
    
    public ChartsPanel(AnalisisResultado analisis) {
        this.analisis = analisis;
        setLayout(new GridLayout(2, 2, 10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Gráfico de sectores
        add(createPieChart("Distribución por Sector", analisis.getAhorroPorSector()));
        
        // Gráfico de barras por comunidad
        add(createBarChart("Ahorro por Comunidad", analisis.getAhorroPorComunidad()));
        
        // Gráfico de estados
        add(createPieChart("Distribución por Estado", 
            analisis.getConteoPorEstado().entrySet().stream()
                .collect(java.util.stream.Collectors.toMap(
                    Map.Entry::getKey,
                    e -> e.getValue().doubleValue()
                ))));
        
        // Gráfico de líneas para top comunidades
        add(createLineChart("Top Comunidades", analisis.getAhorroPorComunidad()));
    }
    
    private ChartPanel createPieChart(String title, Map<String, Double> data) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        data.forEach(dataset::setValue);
        
        JFreeChart chart = ChartFactory.createPieChart(
            title,
            dataset,
            true,
            true,
            false
        );
        
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setSectionOutlinesVisible(false);
        plot.setLabelGenerator(null);
        
        return new ChartPanel(chart);
    }
    
    private ChartPanel createBarChart(String title, Map<String, Double> data) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        // Tomar solo los top 10
        data.entrySet().stream()
            .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
            .limit(10)
            .forEach(entry -> dataset.addValue(entry.getValue(), "Ahorro", entry.getKey()));
        
        JFreeChart chart = ChartFactory.createBarChart(
            title,
            "Comunidad",
            "Ahorro (kWh)",
            dataset
        );
        
        return new ChartPanel(chart);
    }
    
    private ChartPanel createLineChart(String title, Map<String, Double> data) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        // Tomar solo los top 5
        data.entrySet().stream()
            .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
            .limit(5)
            .forEach(entry -> dataset.addValue(entry.getValue(), "Ahorro", entry.getKey()));
        
        JFreeChart chart = ChartFactory.createLineChart(
            title,
            "Comunidad",
            "Ahorro (kWh)",
            dataset
        );
        
        return new ChartPanel(chart);
    }
}