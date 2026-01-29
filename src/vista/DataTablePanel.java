package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import model.Solicitud;

public class DataTablePanel extends JPanel {
    private JTable table;
    private JTextField searchField;
    
    public DataTablePanel(List<Solicitud> solicitudes) {
        setLayout(new BorderLayout());
        
        // Panel de búsqueda
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.add(new JLabel("Buscar:"));
        searchField = new JTextField(20);
        searchPanel.add(searchField);
        
        JButton searchButton = new JButton("Buscar");
        searchPanel.add(searchButton);
        
        add(searchPanel, BorderLayout.NORTH);
        
        // Tabla de datos
        String[] columnNames = {
            "ID Solicitud", "Sector", "Comunidad", "Ahorro Total (kWh)", 
            "Estado", "Año", "Actuaciones"
        };
        
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        for (Solicitud s : solicitudes) {
            Object[] row = {
                s.getIdSolicitud(),
                s.getSector(),
                s.getComunidadAutonoma(),
                String.format("%,.2f", s.getTotalAhorroSolicitado()),
                s.getEstado(),
                s.getAñoFinEjecucion(),
                s.getNumeroActuaciones()
            };
            model.addRow(row);
        }
        
        table = new JTable(model);
        table.setAutoCreateRowSorter(true);
        table.setFillsViewportHeight(true);
        
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
        
        // Panel de estadísticas
        JPanel statsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        statsPanel.add(new JLabel("Total registros: " + solicitudes.size()));
        add(statsPanel, BorderLayout.SOUTH);
    }
}