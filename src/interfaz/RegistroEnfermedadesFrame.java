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
    private JTextField txtViasTransmision;
    private JTextField txtPeriodoIncubacion;
    private JTextField txtPacientesHombres;
    private JTextField txtPacientesMujeres;
    private JTextField txtRangoEdades;
    private JTextField txtCurados;
    private JTextField txtMuertos;
    private JTextField txtActivos;
    private static final String ARCHIVO_DATOS = "C:\\herbert\\disease-management-systemTEST\\src\\data\\enfermedades.txt";
    private static final String DELIMITADOR = "|";
    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public RegistroEnfermedadesFrame() {
        setTitle("Registro de Enfermedades");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JPanel mainPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Crear componentes
        txtNombreComun = new JTextField();
        txtNombreCientifico = new JTextField();
        txtViasTransmision = new JTextField();
        txtPeriodoIncubacion = new JTextField();
        txtPacientesHombres = new JTextField();
        txtPacientesMujeres = new JTextField();
        txtRangoEdades = new JTextField();
        txtCurados = new JTextField();
        txtMuertos = new JTextField();
        txtActivos = new JTextField();
        
        JButton btnGuardar = new JButton("Guardar Enfermedad");
        btnGuardar.addActionListener(new GuardarListener());
        
        // Agregar componentes al panel
        mainPanel.add(new JLabel("Nombre Común:"));
        mainPanel.add(txtNombreComun);
        
        mainPanel.add(new JLabel("Nombre Científico:"));
        mainPanel.add(txtNombreCientifico);
        
        mainPanel.add(new JLabel("Vías de Transmisión:"));
        mainPanel.add(txtViasTransmision);
        
        mainPanel.add(new JLabel("Período de Incubación (días):"));
        mainPanel.add(txtPeriodoIncubacion);
        
        mainPanel.add(new JLabel("Pacientes Hombres:"));
        mainPanel.add(txtPacientesHombres);
        
        mainPanel.add(new JLabel("Pacientes Mujeres:"));
        mainPanel.add(txtPacientesMujeres);
        
        mainPanel.add(new JLabel("Rango de Edades:"));
        mainPanel.add(txtRangoEdades);
        
        mainPanel.add(new JLabel("Pacientes Curados:"));
        mainPanel.add(txtCurados);
        
        mainPanel.add(new JLabel("Pacientes Muertos:"));
        mainPanel.add(txtMuertos);
        
        mainPanel.add(new JLabel("Casos Activos:"));
        mainPanel.add(txtActivos);
        
        mainPanel.add(new JLabel()); // Espacio vacío
        mainPanel.add(btnGuardar);
        
        add(mainPanel);
    }
    
    private class GuardarListener implements ActionListener {
     
        public void actionPerformed(ActionEvent e) {
            try {
                // Obtener valores de los campos
                String nombreComun = txtNombreComun.getText();
                String nombreCientifico = txtNombreCientifico.getText();
                String viasTransmision = txtViasTransmision.getText();
                int periodoIncubacion = Integer.parseInt(txtPeriodoIncubacion.getText());
                int pacientesHombres = Integer.parseInt(txtPacientesHombres.getText());
                int pacientesMujeres = Integer.parseInt(txtPacientesMujeres.getText());
                String rangoEdades = txtRangoEdades.getText();
                int curados = Integer.parseInt(txtCurados.getText());
                int muertos = Integer.parseInt(txtMuertos.getText());
                int activos = Integer.parseInt(txtActivos.getText());
                
                // Crear línea para guardar en el archivo
                String registro = String.join(DELIMITADOR,
                        LocalDateTime.now().format(FORMATO_FECHA),
                        nombreComun,
                        nombreCientifico,
                        viasTransmision,
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
                
                // Limpiar campos después de guardar
                limpiarCampos();
                
            } catch (NumberFormatException ex) {
                mostrarError("Error en campos numéricos: " + ex.getMessage());
            } catch (IOException ex) {
                mostrarError("Error al guardar en archivo: " + ex.getMessage());
            }
        }
        
        private void limpiarCampos() {
            txtNombreComun.setText("");
            txtNombreCientifico.setText("");
            txtViasTransmision.setText("");
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
