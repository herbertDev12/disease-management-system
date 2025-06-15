package interfaz;

import javax.swing.*;
import java.io.File;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;       
import java.time.format.DateTimeFormatter; 

public class RegistroEnfermedadesFrame extends JFrame {
    private JTextField txtNombreComun;
    private JTextField txtNombreCientifico;
    private JTextField txtPeriodoIncubacion;
    private JTextField txtPacientesHombres;
    private JTextField txtPacientesMujeres;
    private JTextField txtRangoEdades;
    private JTextField txtCurados;
    private JTextField txtMuertos;
    private JTextField txtActivos;
    private JComboBox<String> comboViasTransmision;
    private String viaTransmisionSeleccionada;
    private static final String ARCHIVO_DATOS = "C:\\herbert\\disease-management-systemTEST\\src\\data\\enfermedades.txt";
    private static final String DELIMITADOR = "|";
    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public RegistroEnfermedadesFrame() {
        setTitle("Registro de Enfermedades");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        String[] viasTransmision = {
                "Vectorial",
                "Sanguinea",
                "Sexual",
                "Aerea",
                "Oral",
                "Materna",
                "Zoonotica",
                "Fecal"
            };
        
        // Crear componentes
        txtNombreComun = new JTextField();
        txtNombreComun.setBounds(297, 21, 267, 29);
        txtNombreCientifico = new JTextField();
        txtNombreCientifico.setBounds(297, 60, 267, 29);
        txtPeriodoIncubacion = new JTextField();
        txtPeriodoIncubacion.setBounds(297, 138, 267, 29);
        txtPacientesHombres = new JTextField();
        txtPacientesHombres.setBounds(297, 178, 267, 29);
        txtPacientesMujeres = new JTextField();
        txtPacientesMujeres.setBounds(297, 216, 267, 29);
        txtRangoEdades = new JTextField();
        txtRangoEdades.setBounds(297, 256, 267, 29);
        txtCurados = new JTextField();
        txtCurados.setBounds(297, 295, 267, 29);
        txtMuertos = new JTextField();
        txtMuertos.setBounds(297, 334, 267, 29);
        
        JButton btnGuardar = new JButton("Guardar Enfermedad");
        btnGuardar.setBounds(20, 411, 267, 29);
        btnGuardar.addActionListener(new GuardarListener());
        mainPanel.setLayout(null);
        
        // Agregar componentes al panel
        JLabel label = new JLabel("Nombre Común:");
        label.setBounds(20, 21, 267, 29);
        mainPanel.add(label);
        mainPanel.add(txtNombreComun);
        
        JLabel label_1 = new JLabel("Nombre Científico:");
        label_1.setBounds(20, 60, 267, 29);
        mainPanel.add(label_1);
        mainPanel.add(txtNombreCientifico);
        
        JLabel label_2 = new JLabel("Vías de Transmisión:");
        label_2.setBounds(20, 99, 267, 29);
        mainPanel.add(label_2);
        
        JLabel label_3 = new JLabel("Período de Incubación (días):");
        label_3.setBounds(20, 139, 267, 29);
        mainPanel.add(label_3);
        mainPanel.add(txtPeriodoIncubacion);
        
        JLabel label_4 = new JLabel("Pacientes Hombres:");
        label_4.setBounds(20, 178, 267, 29);
        mainPanel.add(label_4);
        mainPanel.add(txtPacientesHombres);
        
        JLabel label_5 = new JLabel("Pacientes Mujeres:");
        label_5.setBounds(20, 217, 267, 29);
        mainPanel.add(label_5);
        mainPanel.add(txtPacientesMujeres);
        
        JLabel label_6 = new JLabel("Rango de Edades:");
        label_6.setBounds(20, 256, 267, 29);
        mainPanel.add(label_6);
        mainPanel.add(txtRangoEdades);
        
        JLabel label_7 = new JLabel("Pacientes Curados:");
        label_7.setBounds(20, 295, 267, 29);
        mainPanel.add(label_7);
        mainPanel.add(txtCurados);
        
        JLabel label_8 = new JLabel("Pacientes Muertos:");
        label_8.setBounds(20, 334, 267, 29);
        mainPanel.add(label_8);
        mainPanel.add(txtMuertos);
        
        JLabel label_9 = new JLabel("Casos Activos:");
        label_9.setBounds(20, 373, 267, 29);
        mainPanel.add(label_9);
        
        JLabel label_10 = new JLabel();
        label_10.setBounds(20, 372, 267, 29);
        mainPanel.add(label_10); // Espacio vacío
        txtActivos = new JTextField();
        txtActivos.setBounds(297, 372, 267, 29);
        mainPanel.add(txtActivos);
        mainPanel.add(btnGuardar);
        
        getContentPane().add(mainPanel);
        
        comboViasTransmision = new JComboBox<>(viasTransmision);
        comboViasTransmision.setSelectedIndex(-1); // Ninguna opción seleccionada inicialmente
        comboViasTransmision.setBounds(307, 100, 245, 28);
        mainPanel.add(comboViasTransmision);
        
        comboViasTransmision.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	viaTransmisionSeleccionada = (String) comboViasTransmision.getSelectedItem();
                //guardarCapital();
            }
        });
    }
    
    private class GuardarListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                // Obtener valores de los campos
                String nombreComun = txtNombreComun.getText();
                String nombreCientifico = txtNombreCientifico.getText();
                String viaTransmision = viaTransmisionSeleccionada;
                
                // Validar campos numéricos
                int periodoIncubacion = validarNumeroPositivo(txtPeriodoIncubacion, "Período de incubación");
                int pacientesHombres = validarNumeroPositivo(txtPacientesHombres, "Pacientes hombres");
                int pacientesMujeres = validarNumeroPositivo(txtPacientesMujeres, "Pacientes mujeres");
                String rangoEdades = txtRangoEdades.getText();
                int curados = validarNumeroPositivo(txtCurados, "Pacientes curados");
                int muertos = validarNumeroPositivo(txtMuertos, "Pacientes muertos");
                int activos = validarNumeroPositivo(txtActivos, "Casos activos");
                
                // Crear línea para guardar en el archivo
                String registro = String.join(DELIMITADOR,
                        LocalDateTime.now().format(FORMATO_FECHA),
                        nombreComun,
                        nombreCientifico,
                        viaTransmision,
                        String.valueOf(periodoIncubacion),
                        String.valueOf(pacientesHombres),
                        String.valueOf(pacientesMujeres),
                        rangoEdades,
                        String.valueOf(curados),
                        String.valueOf(muertos),
                        String.valueOf(activos)
                );
               
                guardarEnArchivo(registro);
               
                JOptionPane.showMessageDialog(RegistroEnfermedadesFrame.this,
                    "Enfermedad registrada exitosamente en " + ARCHIVO_DATOS,
                    "Registro Exitoso",
                    JOptionPane.INFORMATION_MESSAGE);
                
                limpiarCampos();
                
            } catch (NumberFormatException ex) {
                mostrarError("Error en campos numéricos: " + ex.getMessage());
            } catch (IllegalArgumentException ex) {
                mostrarError(ex.getMessage());
            } catch (IOException ex) {
                mostrarError("Error al guardar en archivo: " + ex.getMessage());
            }
        }
        
        // Método para validar números positivos
        private int validarNumeroPositivo(JTextField campo, String nombreCampo) {
            String texto = campo.getText().trim();
            
            // Validar que no esté vacío
            if (texto.isEmpty()) {
                throw new IllegalArgumentException("El campo '" + nombreCampo + "' no puede estar vacío");
            }
            
            try {
                int valor = Integer.parseInt(texto);
                
                // Validar que sea mayor que cero
                if (valor <= 0) {
                    throw new IllegalArgumentException("El campo '" + nombreCampo + "' debe ser mayor que cero");
                }
                
                return valor;
            } catch (NumberFormatException e) {
                throw new NumberFormatException("El campo '" + nombreCampo + "' debe ser un número entero válido");
            }
        }
        
        private void limpiarCampos() {
            txtNombreComun.setText("");
            txtNombreCientifico.setText("");
            comboViasTransmision.setSelectedIndex(-1); // Ninguna opción seleccionada inicialmente
            txtPeriodoIncubacion.setText("");
            txtPacientesHombres.setText("");
            txtPacientesMujeres.setText("");
            txtRangoEdades.setText("");
            txtCurados.setText("");
            txtMuertos.setText("");
            txtActivos.setText("");
        }
        
        private void guardarEnArchivo(String registro) throws IOException {
            try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(ARCHIVO_DATOS, true)))) {
                out.println(registro);
            }
        }
        
        private void mostrarError(String mensaje) {
            JOptionPane.showMessageDialog(RegistroEnfermedadesFrame.this,
                mensaje,
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }
}
