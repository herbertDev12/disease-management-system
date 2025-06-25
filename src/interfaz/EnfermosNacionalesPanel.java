package interfaz;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import logica.Enfermedad;
import logica.EnfermoNacional;
import logica.Minsap;

import java.awt.*;
import java.util.List;

public class EnfermosNacionalesPanel extends JPanel {
    private JTable tablaEnfermos;
    private DefaultTableModel modeloTabla;

    public EnfermosNacionalesPanel(Minsap minsap) {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        setBackground(new Color(240, 240, 240));

        // Configurar columnas de la tabla 
        String[] columnas = {
            "Código",
            "Nombre y Apellidos",
            "Edad",
            "Dirección",
            "Enfermedades"
        };
        
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
            
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 2) { 
                    return Integer.class;
                }
                return String.class;
            }
        };
        
        // Crear tabla con características visuales
        tablaEnfermos = new JTable(modeloTabla);
        tablaEnfermos.setAutoCreateRowSorter(true);
        tablaEnfermos.setFillsViewportHeight(true);
        tablaEnfermos.setRowHeight(30);
        tablaEnfermos.setIntercellSpacing(new Dimension(10, 5));
        tablaEnfermos.setShowGrid(true);
        tablaEnfermos.setGridColor(new Color(220, 220, 220));
        tablaEnfermos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        // Personalizar encabezados
        tablaEnfermos.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        tablaEnfermos.getTableHeader().setBackground(new Color(70, 130, 180));
        tablaEnfermos.getTableHeader().setForeground(Color.WHITE);
        tablaEnfermos.getTableHeader().setReorderingAllowed(false);
        
        // Personalizar contenido
        tablaEnfermos.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tablaEnfermos.setSelectionBackground(new Color(173, 216, 230));
        
        // Centrar contenido numérico 
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tablaEnfermos.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        
        configurarAnchosColumnas();
        
        // ScrollPane con bordes
        JScrollPane scrollPane = new JScrollPane(tablaEnfermos);
        scrollPane.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createEmptyBorder(5, 5, 5, 5),
            BorderFactory.createLineBorder(new Color(200, 200, 200))
        ));
        
        // Panel de título
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        titlePanel.setBackground(new Color(240, 240, 240));
        JLabel titleLabel = new JLabel("REGISTRO DE PACIENTES ENFERMADOS EN TERRITORIO NACIONAL");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titleLabel.setForeground(new Color(70, 130, 180));
        titlePanel.add(titleLabel);
        
        add(titlePanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        
        // Cargar datos
        cargarDatosEnfermos(minsap);
        
        // Resaltar pacientes con múltiples enfermedades
        resaltarFilas();
    }

    private void configurarAnchosColumnas() {

        int[] anchos = {100, 250, 60, 300, 250}; 
        for (int i = 0; i < anchos.length; i++) {
            TableColumn column = tablaEnfermos.getColumnModel().getColumn(i);
            column.setPreferredWidth(anchos[i]);
            column.setMinWidth(anchos[i] - 30);
            column.setMaxWidth(anchos[i] + 100);
        }
    }

    private void cargarDatosEnfermos(Minsap minsap) {
        List<EnfermoNacional> enfermos = minsap.getAllEnfermosNacionales();
        
        for (EnfermoNacional enfermo : enfermos) {
            
            Object[] fila = {
                enfermo.getCodigo(),
                enfermo.getNombre(), 
                enfermo.getEdad(),
                enfermo.getDireccion(),
                formatEnfermedades(enfermo.getEnfermedades())
            };
            modeloTabla.addRow(fila);
        }
    }

    private String formatEnfermedades(List<Enfermedad> enfermedades) {
        StringBuilder sb = new StringBuilder();
        for (Enfermedad enf : enfermedades) {
            if (sb.length() > 0) sb.append(", ");
            sb.append(enf.getNombreComun());
        }
        return sb.toString();
    }

    private void resaltarFilas() {
        tablaEnfermos.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                
                Component c = super.getTableCellRendererComponent(table, value, 
                        isSelected, hasFocus, row, column);
                
                String enfermedades = (String) table.getModel().getValueAt(row, 4);
                int count = enfermedades.isEmpty() ? 0 : enfermedades.split(",").length;
                
                if (!isSelected) {
                    if (count > 1) {
                        c.setBackground(new Color(255, 255, 200));
                    } else if (count == 0) {
                        c.setBackground(new Color(220, 220, 220));
                    } else {
                        c.setBackground(table.getBackground());
                    }
                }
                
                if (column == 4) {
                    setToolTipText(enfermedades);
                }
                
                return c;
            }
        });
    }
}

