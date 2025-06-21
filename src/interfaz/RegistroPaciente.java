package interfaz;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;

import utils.Validacion;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class RegistroPaciente extends JFrame {
    private static final String ARCHIVO_DATOS = "C:\\herbert\\disease-management-system\\src\\data\\pacientesEnfermosExtranjero.txt";
    private static final String DELIMITADOR = "|";
    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private boolean resultadoAnalisis = false;
    private boolean hizoDiagnostico = false;
    
 // Paneles para pestañas
    private JPanel panelContactos;
    private JPanel panelEnfermedades;
    private JPanel panelTratamientos;
    private JPanel panelPaises;
    private JPanel panelAnalisis;
    private JTabbedPane tabbedPane;
    
    // Componentes para datos básicos
    private JTextField txtNombre;
    private JTextField txtId;
    private JTextField txtEdad;
    private JTextField txtSexo;
    private JTextField txtDireccion;
    private JTextField txtDiagnostico;
    
    // Componentes para contactos
    private JTextField txtContacto;
    private JList<String> listContactos;
    private DefaultListModel<String> modelContactos;
    
    // Componentes para enfermedades
    private JTextField txtEnfermedad;
    private JList<String> listEnfermedades;
    private DefaultListModel<String> modelEnfermedades;
    
    // Componentes para tratamientos
    private JTextField txtTratamiento;
    private JList<String> listTratamientos;
    private DefaultListModel<String> modelTratamientos;
    
    // Componentes para paises visitados
    private JTextField txtPais;
    private JList<String> listPaises;
    private DefaultListModel<String> modelPaises;
    
    
    public RegistroPaciente() {
        setTitle("Registro de Paciente Enfermo en Extranjero");
        setSize(800, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Usar un panel principal con pestañas
        tabbedPane = new JTabbedPane();
        
        // Pestaña 1: Datos básicos (siempre visible)
        tabbedPane.addTab("Datos Básicos", crearPanelDatosBasicos());

        // Crear los paneles adicionales pero no agregarlos todavía
        panelContactos = crearPanelContactos();
        panelEnfermedades = crearPanelEnfermedades();
        panelTratamientos = crearPanelTratamientos();
        panelPaises = crearPanelPaisesVisitados();
        panelAnalisis = crearPanelAnalisis();
        
        // Listener para cambios en el campo de diagnóstico
        txtDiagnostico.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                actualizarPestañas();
            }

            public void removeUpdate(DocumentEvent e) {
                actualizarPestañas();
            }

            public void changedUpdate(DocumentEvent e) {
                actualizarPestañas();
            }
        });
        
        // Botón Guardar
        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(new GuardarListener());
        
        // Panel para el botón
        JPanel panelBoton = new JPanel();
        panelBoton.add(btnGuardar);
        
        // Organizar en el contenedor principal
        setLayout(new BorderLayout());
        add(tabbedPane, BorderLayout.CENTER);
        add(panelBoton, BorderLayout.SOUTH);
        
        // Actualizar pestañas inicialmente
        actualizarPestañas();
    }
    
    private void actualizarPestañas() {
        // Eliminar todas las pestañas adicionales
        while (tabbedPane.getTabCount() > 1) {
            tabbedPane.removeTabAt(1);
        }
        
        // Verificar si se hizo diagnóstico positivo
        int hizoDiagnostico = Validacion.volverIntADiagnostico(txtDiagnostico, "Diagnostico");
        
        // Agregar pestañas adicionales solo si hay diagnóstico positivo
        if (hizoDiagnostico == 1) {
            tabbedPane.addTab("Personas de Contacto", panelContactos);
            tabbedPane.addTab("Enfermedades", panelEnfermedades);
            tabbedPane.addTab("Tratamientos", panelTratamientos);
            tabbedPane.addTab("Paises visitados", panelPaises);
        }else if(hizoDiagnostico == 2){
        	tabbedPane.addTab("Realizar Analisis", panelAnalisis);
        }
        
        // Actualizar la interfaz
        revalidate();
        repaint();
    }
    private JPanel crearPanelDatosBasicos() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Configuración de los campos
        txtNombre = new JTextField(20);
        txtId = new JTextField(20);
        txtEdad = new JTextField(20);
        txtSexo = new JTextField(20);
        txtDireccion = new JTextField(20);
        txtDiagnostico = new JTextField(20);
        
        // Añadir componentes al panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Nombre:"), gbc);
        
        gbc.gridx = 1;
        panel.add(txtNombre, gbc);
        
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("ID:"), gbc);
        
        gbc.gridx = 1;
        panel.add(txtId, gbc);
        
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Edad:"), gbc);
        
        gbc.gridx = 1;
        panel.add(txtEdad, gbc);
        
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Sexo:"), gbc);
        
        gbc.gridx = 1;
        panel.add(txtSexo, gbc);
        
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Dirección:"), gbc);
        
        gbc.gridx = 1;
        panel.add(txtDireccion, gbc);
        
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Diagnóstico:"), gbc);
        
        gbc.gridx = 1;
        panel.add(txtDiagnostico, gbc);
        
        return panel;
    }
    
    private JPanel crearPanelContactos() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        // Modelo y lista para contactos
        modelContactos = new DefaultListModel<>();
        listContactos = new JList<>(modelContactos);
        JScrollPane scrollContactos = new JScrollPane(listContactos);
        scrollContactos.setBorder(new TitledBorder("Personas de Contacto Registradas"));
        
        // Panel para añadir nuevos contactos
        JPanel panelEntrada = new JPanel(new BorderLayout(5, 5));
        txtContacto = new JTextField();
        JButton btnAgregarContacto = new JButton("Agregar Contacto");
        btnAgregarContacto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String contacto = txtContacto.getText().trim();
                if (!contacto.isEmpty()) {
                    modelContactos.addElement(contacto);
                    txtContacto.setText("");
                }
            }
        });
        
        JButton btnEliminarContacto = new JButton("Eliminar Seleccionado");
        btnEliminarContacto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = listContactos.getSelectedIndex();
                if (selectedIndex != -1) {
                    modelContactos.remove(selectedIndex);
                }
            }
        });
        
        panelEntrada.add(txtContacto, BorderLayout.CENTER);
        panelEntrada.add(btnAgregarContacto, BorderLayout.EAST);
        
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBotones.add(btnEliminarContacto);
        
        panel.add(new JLabel("Añadir nueva persona de contacto:"), BorderLayout.NORTH);
        panel.add(panelEntrada, BorderLayout.CENTER);
        panel.add(panelBotones, BorderLayout.SOUTH);
        panel.add(scrollContactos, BorderLayout.EAST);
        
        return panel;
    }
    
    private JPanel crearPanelEnfermedades() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        // Modelo y lista para enfermedades
        modelEnfermedades = new DefaultListModel<>();
        listEnfermedades = new JList<>(modelEnfermedades);
        JScrollPane scrollEnfermedades = new JScrollPane(listEnfermedades);
        scrollEnfermedades.setBorder(new TitledBorder("Enfermedades Registradas"));
        
        // Panel para añadir nuevas enfermedades
        JPanel panelEntrada = new JPanel(new BorderLayout(5, 5));
        txtEnfermedad = new JTextField();
        JButton btnAgregarEnfermedad = new JButton("Agregar Enfermedad");
        btnAgregarEnfermedad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String enfermedad = txtEnfermedad.getText().trim();
                if (!enfermedad.isEmpty()) {
                    modelEnfermedades.addElement(enfermedad);
                    txtEnfermedad.setText("");
                }
            }
        });
        
        JButton btnEliminarEnfermedad = new JButton("Eliminar Seleccionada");
        btnEliminarEnfermedad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = listEnfermedades.getSelectedIndex();
                if (selectedIndex != -1) {
                    modelEnfermedades.remove(selectedIndex);
                }
            }
        });
        
        panelEntrada.add(txtEnfermedad, BorderLayout.CENTER);
        panelEntrada.add(btnAgregarEnfermedad, BorderLayout.EAST);
        
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBotones.add(btnEliminarEnfermedad);
        
        panel.add(new JLabel("Añadir nueva enfermedad:"), BorderLayout.NORTH);
        panel.add(panelEntrada, BorderLayout.CENTER);
        panel.add(panelBotones, BorderLayout.SOUTH);
        panel.add(scrollEnfermedades, BorderLayout.EAST);
        
        return panel;
    }
    
    private JPanel crearPanelTratamientos() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        // Modelo y lista para tratamientos
        modelTratamientos = new DefaultListModel<>();
        listTratamientos = new JList<>(modelTratamientos);
        JScrollPane scrollTratamientos = new JScrollPane(listTratamientos);
        scrollTratamientos.setBorder(new TitledBorder("Tratamientos Registrados"));
        
        // Panel para añadir nuevos tratamientos
        JPanel panelEntrada = new JPanel(new BorderLayout(5, 5));
        txtTratamiento = new JTextField();
        JButton btnAgregarTratamiento = new JButton("Agregar Tratamiento");
        btnAgregarTratamiento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tratamiento = txtTratamiento.getText().trim();
                if (!tratamiento.isEmpty()) {
                    modelTratamientos.addElement(tratamiento);
                    txtTratamiento.setText("");
                }
            }
        });
        
        JButton btnEliminarTratamiento = new JButton("Eliminar Seleccionado");
        btnEliminarTratamiento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = listTratamientos.getSelectedIndex();
                if (selectedIndex != -1) {
                    modelTratamientos.remove(selectedIndex);
                }
            }
        });
        
        panelEntrada.add(txtTratamiento, BorderLayout.CENTER);
        panelEntrada.add(btnAgregarTratamiento, BorderLayout.EAST);
        
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBotones.add(btnEliminarTratamiento);
        
        panel.add(new JLabel("Añadir nuevo tratamiento:"), BorderLayout.NORTH);
        panel.add(panelEntrada, BorderLayout.CENTER);
        panel.add(panelBotones, BorderLayout.SOUTH);
        panel.add(scrollTratamientos, BorderLayout.EAST);
        
        return panel;
    }
    
    private JPanel crearPanelPaisesVisitados(){
    	JPanel panel = new JPanel(new BorderLayout(10, 10));
    	panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
    	
    	// Modelo y lista para enfermedades
    	modelPaises = new DefaultListModel<>();
        listPaises = new JList<>(modelPaises);
        JScrollPane scrollPaises = new JScrollPane(listPaises);
        scrollPaises.setBorder(new TitledBorder("Países registrados"));
        
        // Panel para añadir nuevas enfermedades
        JPanel panelEntrada = new JPanel(new BorderLayout(5, 5));
        txtPais = new JTextField();
        JButton btnAgregarPais = new JButton("Agregar país");
        btnAgregarPais.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pais = txtPais.getText().trim();
                if (!pais.isEmpty()) {
                    modelPaises.addElement(pais);
                    txtPais.setText("");
                }
            }
        });
        
        JButton btnEliminarPais = new JButton("Eliminar Seleccionada");
        btnEliminarPais.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = listPaises.getSelectedIndex();
                if (selectedIndex != -1) {
                    modelPaises.remove(selectedIndex);
                }
            }
        });
        
        panelEntrada.add(txtPais, BorderLayout.CENTER);
        panelEntrada.add(btnAgregarPais, BorderLayout.EAST);
        
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBotones.add(btnEliminarPais);
        
        panel.add(new JLabel("Añadir nuevo país visitado:"), BorderLayout.NORTH);
        panel.add(panelEntrada, BorderLayout.CENTER);
        panel.add(panelBotones, BorderLayout.SOUTH);
        panel.add(scrollPaises, BorderLayout.EAST);
        
        return panel;
    }
    
    private JPanel crearPanelAnalisis() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        return panel;
    }
    
    private class GuardarListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                // Obtener valores de los campos básicos
                String nombre = Validacion.validarNombre(txtNombre, "Nombre");
                String id = Validacion.validarIdPersona(txtId, "Id");
                int edad = Validacion.validarEdad(txtEdad, "Edad");
                String sexo = Validacion.validarSexoPersona(txtSexo, "Sexo");
                String direccion = txtDireccion.getText().trim();
                
                // Validar campos obligatorios
                if (nombre.isEmpty() || id.isEmpty() || sexo.isEmpty() || direccion.isEmpty()) {
                    throw new IllegalArgumentException("Todos los campos básicos son obligatorios.");
                }
                
                // Obtener listas de elementos múltiples
                List<String> contactos = new ArrayList<>();
                for (int i = 0; i < modelContactos.size(); i++) {
                    contactos.add(modelContactos.getElementAt(i));
                }
                
                List<String> enfermedades = new ArrayList<>();
                for (int i = 0; i < modelEnfermedades.size(); i++) {
                    enfermedades.add(modelEnfermedades.getElementAt(i));
                }
                
                List<String> tratamientos = new ArrayList<>();
                for (int i = 0; i < modelTratamientos.size(); i++) {
                    tratamientos.add(modelTratamientos.getElementAt(i));
                }
                
                // Validar al menos una enfermedad
                if (enfermedades.isEmpty()) {
                    throw new IllegalArgumentException("Debe registrar al menos una enfermedad.");
                }
                
                // Crear línea para guardar en el archivo
                String registro = String.join(DELIMITADOR,
                        LocalDateTime.now().format(FORMATO_FECHA),
                        nombre,
                        id,
                        String.valueOf(edad),
                        sexo,
                        direccion,
                        String.join(",", contactos),
                        String.join(",", enfermedades),
                        String.join(",", tratamientos)
                );
               
                guardarEnArchivo(registro);
               
                JOptionPane.showMessageDialog(RegistroPaciente.this,
                    "Paciente registrado exitosamente en " + ARCHIVO_DATOS,
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
        
        private void limpiarCampos() {
            txtNombre.setText("");
            txtId.setText("");
            txtEdad.setText("");
            txtSexo.setText("");
            txtDireccion.setText("");
            modelContactos.clear();
            modelEnfermedades.clear();
            modelTratamientos.clear();
        }
        
        private void guardarEnArchivo(String registro) throws IOException {
            try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(ARCHIVO_DATOS, true)))) {
                out.println(registro);
            }
        }
        
        private void mostrarError(String mensaje) {
            JOptionPane.showMessageDialog(RegistroPaciente.this,
                mensaje,
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new RegistroPaciente().setVisible(true);
            }
        });
    }
}
