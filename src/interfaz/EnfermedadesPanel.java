package interfaz;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import logica.Enfermedad;
import logica.Minsap;

public class EnfermedadesPanel extends JPanel {
    private JTable tablaEnfermedades;
    private DefaultTableModel modeloTabla;

    public EnfermedadesPanel(Minsap minsap) {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        setBackground(new Color(240, 240, 240));

        // Configurar modelo de tabla
        String[] columnas = {
            "Nombre Común", 
            "Nombre Científico", 
            "Vías de Transmisión",
            "Incubación (días)",
            "Hombres",
            "Mujeres",
            "Rangos de Edad",
            "Curados",
            "Muertos",
            "Activos"
        };
        
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Tabla no editable
            }
            
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex >= 4 && columnIndex <= 9) {
                    return Integer.class; // Columnas numéricas
                }
                return String.class; // Columnas de texto
            }
        };
        
        // Crear tabla con características mejoradas
        tablaEnfermedades = new JTable(modeloTabla);
        tablaEnfermedades.setAutoCreateRowSorter(true);
        tablaEnfermedades.setFillsViewportHeight(true);
        tablaEnfermedades.setRowHeight(30);
        tablaEnfermedades.setIntercellSpacing(new Dimension(10, 5));
        tablaEnfermedades.setShowGrid(true);
        tablaEnfermedades.setGridColor(new Color(220, 220, 220));
        tablaEnfermedades.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        // Personalizar encabezados
        tablaEnfermedades.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        tablaEnfermedades.getTableHeader().setBackground(new Color(70, 130, 180));
        tablaEnfermedades.getTableHeader().setForeground(Color.WHITE);
        tablaEnfermedades.getTableHeader().setReorderingAllowed(false);
        
        // Personalizar contenido
        tablaEnfermedades.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tablaEnfermedades.setSelectionBackground(new Color(173, 216, 230));
        
        // Centrar contenido numérico
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 3; i < modeloTabla.getColumnCount(); i++) {
            tablaEnfermedades.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        
        // Ajustar anchos de columnas
        configurarAnchosColumnas();
        
        // Agregar tabla a un JScrollPane con bordes mejorados
        JScrollPane scrollPane = new JScrollPane(tablaEnfermedades);
        scrollPane.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createEmptyBorder(5, 5, 5, 5),
            BorderFactory.createLineBorder(new Color(200, 200, 200))
        ));
        
        // Panel de título
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        titlePanel.setBackground(new Color(240, 240, 240));
        JLabel titleLabel = new JLabel("REGISTRO DE ENFERMEDADES");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titleLabel.setForeground(new Color(70, 130, 180));
        titlePanel.add(titleLabel);
        
        add(titlePanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        
        // Cargar datos
        cargarDatosEnfermedades(minsap);
        
        // Resaltar filas con alta mortalidad
        resaltarFilasPeligrosas();
    }

    private void configurarAnchosColumnas() {
        int[] anchos = {150, 150, 150, 80, 70, 70, 200, 70, 70, 70};
        for (int i = 0; i < anchos.length; i++) {
            TableColumn column = tablaEnfermedades.getColumnModel().getColumn(i);
            column.setPreferredWidth(anchos[i]);
            column.setMinWidth(anchos[i] - 30);
            column.setMaxWidth(anchos[i] + 100);
        }
    }

    private void cargarDatosEnfermedades(Minsap minsap) {
        ArrayList<Enfermedad> enfermedades = minsap.getAllEnfermedades();
        
        for (Enfermedad enfermedad : enfermedades) {
            // Asegurar que los mapas estén inicializados
            if (enfermedad.getRangoEdades() == null) {
                enfermedad.setRangoEdades(new HashMap<String,Integer>());
            }
            
            Object[] fila = {
                enfermedad.getNombreComun(),
                enfermedad.getNombreCientifico(),
                formatViasTransmision(enfermedad.getViasTransmision()),
                enfermedad.getPeriodoIncubacion(),
                enfermedad.getCantidadPacientesHombres(),
                enfermedad.getCantidadPacientesMujeres(),
                formatRangoEdades(enfermedad.getRangoEdades()),
                enfermedad.getCurados(),
                enfermedad.getMuertos(),
                enfermedad.getActivos()
            };
            modeloTabla.addRow(fila);
        }
    }

    private String formatViasTransmision(ArrayList<String> vias) {
        return String.join(", ", vias);
    }

    private String formatRangoEdades(HashMap<String, Integer> rangoEdades) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Integer> entry : rangoEdades.entrySet()) {
            if (sb.length() > 0) sb.append("; ");
            sb.append(entry.getKey())
              .append(": ")
              .append(entry.getValue());
        }
        return sb.toString();
    }

    private void resaltarFilasPeligrosas() {
        tablaEnfermedades.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                
                Component c = super.getTableCellRendererComponent(table, value, 
                        isSelected, hasFocus, row, column);
                
                int muertos = (int) table.getModel().getValueAt(row, 8);
                int activos = (int) table.getModel().getValueAt(row, 9);
                
                if (!isSelected) {
                    if (muertos > 50) {
                        c.setBackground(new Color(255, 200, 200)); // Rojo claro para alta mortalidad
                    } else if (activos > 100) {
                        c.setBackground(new Color(255, 255, 200)); // Amarillo para muchos casos activos
                    } else {
                        c.setBackground(table.getBackground());
                    }
                }
                
                // Tooltip para mostrar información completa
                if (column == 2 || column == 6) {
                    setToolTipText(value.toString());
                }
                
                return c;
            }
        });
    }
}
