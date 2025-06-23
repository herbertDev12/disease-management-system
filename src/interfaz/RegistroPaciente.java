package interfaz;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;

import logica.Minsap;
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
	private static final String ARCHIVO_ENFERMO_EN_EXTRANJERO = "C:\\herbert\\disease-management-system\\src\\data\\pacientesEnfermosExtranjero.txt";
	private static final String ARCHIVO_ENFERMO_NACIONAL = "C:\\herbert\\disease-management-system\\src\\data\\pacientesEnfermosNacional.txt";
	private static final String ARCHIVO_PACIENTE_NOENFERMO = "C:\\herbert\\disease-management-system\\src\\data\\pacientesNoEnfermos.txt";
	private static final String ARCHIVO_ENFERMEDADES = "C:\\herbert\\disease-management-system\\src\\data\\enfermedades.txt";
	private static final String DELIMITADOR = "|";
    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private boolean resultadoAnalisis = false;
    private int hizoDiagnostico;
    private boolean analisisRealizado = false;
    private JButton btnAnalizar; 
    private boolean isEnfermoEnExterior = false;
    
 // Paneles para pesta�as
    private JPanel panelContactos;
    private JPanel panelEnfermedades;
    private JPanel panelTratamientos;
    private JPanel panelPaises;
    private JPanel panelAnalisis;
    private JTabbedPane tabbedPane;
    
    // Componentes para datos b�sicos
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
    private JComboBox<String> comboEnfermoExterior;
    private JButton btnAgregarPais;
    private JButton btnEliminarPais;
    
    public RegistroPaciente() {
        setTitle("Registro de Paciente Enfermo en Extranjero");
        setSize(800, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Usar un panel principal con pesta�as
        tabbedPane = new JTabbedPane();
        
        // Pesta�a 1: Datos b�sicos (siempre visible)
        tabbedPane.addTab("Datos B�sicos", crearPanelDatosBasicos());

        // Crear los paneles adicionales pero no agregarlos todav�a
        panelContactos = crearPanelContactos();
        panelEnfermedades = crearPanelEnfermedades();
        panelTratamientos = crearPanelTratamientos();
        panelPaises = crearPanelPaisesVisitados();
        panelAnalisis = crearPanelAnalisis();
        
        // Listener para cambios en el campo de diagn�stico
        txtDiagnostico.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                actualizarPesta�as();
            }

            public void removeUpdate(DocumentEvent e) {
                actualizarPesta�as();
            }

            public void changedUpdate(DocumentEvent e) {
                actualizarPesta�as();
            }
        });
        
        // Bot�n Guardar
        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(new GuardarListener());
        
        // Panel para el bot�n
        JPanel panelBoton = new JPanel();
        panelBoton.add(btnGuardar);
        
        // Organizar en el contenedor principal
        setLayout(new BorderLayout());
        add(tabbedPane, BorderLayout.CENTER);
        add(panelBoton, BorderLayout.SOUTH);
        
        // Actualizar pesta�as inicialmente
        actualizarPesta�as();
    }
    
    private void actualizarPesta�as() {
        // Eliminar todas las pesta�as adicionales
        while (tabbedPane.getTabCount() > 1) {
            tabbedPane.removeTabAt(1);
        }
        
        // Verificar si se hizo diagn�stico positivo
        hizoDiagnostico = Validacion.volverIntADiagnostico(txtDiagnostico, "Diagnostico");
        
        // Agregar pesta�as adicionales solo si hay diagn�stico positivo
        if (hizoDiagnostico == 1) {
            agregarPesta�asAdicionales();
        } else if (hizoDiagnostico == 2 && !analisisRealizado) {
            // Solo mostrar an�lisis si no se ha realizado
            tabbedPane.addTab("Realizar Analisis", panelAnalisis);
        } else if (analisisRealizado) {
            // Despu�s del an�lisis, mostrar pesta�as seg�n resultado
            if (resultadoAnalisis) {
                agregarPesta�asAdicionales();
            }
        }
        
        // Actualizar la interfaz
        revalidate();
        repaint();
    }


    // M�todo para agregar todas las pesta�as adicionales
    private void agregarPesta�asAdicionales() {
        tabbedPane.addTab("Personas de Contacto", panelContactos);
        tabbedPane.addTab("Enfermedades", panelEnfermedades);
        tabbedPane.addTab("Tratamientos", panelTratamientos);
        tabbedPane.addTab("Paises visitados", panelPaises);
    }
    private JPanel crearPanelDatosBasicos() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Configuraci�n de los campos
        txtNombre = new JTextField(20);
        txtId = new JTextField(20);
        txtEdad = new JTextField(20);
        txtSexo = new JTextField(20);
        txtDireccion = new JTextField(20);
        txtDiagnostico = new JTextField(20);
        
        // A�adir componentes al panel
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
        panel.add(new JLabel("Direcci�n:"), gbc);
        
        gbc.gridx = 1;
        panel.add(txtDireccion, gbc);
        
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Diagn�stico:"), gbc);
        
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
        
        // Panel para a�adir nuevos contactos
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
        
        panel.add(new JLabel("A�adir nueva persona de contacto:"), BorderLayout.NORTH);
        panel.add(panelEntrada, BorderLayout.CENTER);
        panel.add(panelBotones, BorderLayout.SOUTH);
        panel.add(scrollContactos, BorderLayout.EAST);
        
        return panel;
    }
    
    private JPanel crearPanelEnfermedades() {
        final JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        // Modelo y lista para enfermedades
        modelEnfermedades = new DefaultListModel<>();
        listEnfermedades = new JList<>(modelEnfermedades);
        JScrollPane scrollEnfermedades = new JScrollPane(listEnfermedades);
        scrollEnfermedades.setBorder(new TitledBorder("Enfermedades Registradas"));
        scrollEnfermedades.setPreferredSize(new Dimension(300, 400));
        
        // Panel para el formulario de enfermedades
        JPanel formularioPanel = new JPanel(new GridBagLayout());
        formularioPanel.setBorder(BorderFactory.createTitledBorder("Agregar Nueva Enfermedad"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Campos del formulario
        final JTextField txtNombreComun = new JTextField(20);
        final JTextField txtNombreCientifico = new JTextField(20);
        
        String[] viasTransmision = {
            "Vectorial", "Sanguinea", "Sexual", "Aerea", 
            "Oral", "Materna", "Zoonotica", "Fecal"
        };
        final JComboBox<String> comboViasTransmision = new JComboBox<>(viasTransmision);
        comboViasTransmision.setSelectedIndex(-1);
        
        final JTextField txtPeriodoIncubacion = new JTextField(20);
        
        String[] estadosPaciente = {"curado", "muerto", "activo"};
        final JComboBox<String> comboEstadoPaciente = new JComboBox<>(estadosPaciente);
        comboEstadoPaciente.setSelectedIndex(-1); // Seleccionar nada por defecto
        
        // Agregar campos al formulario
        int row = 0;
        gbc.gridx = 0; gbc.gridy = row++;
        formularioPanel.add(new JLabel("Nombre Com�n:"), gbc);
        gbc.gridx = 1;
        formularioPanel.add(txtNombreComun, gbc);
        
        gbc.gridx = 0; gbc.gridy = row++;
        formularioPanel.add(new JLabel("Nombre Cient�fico:"), gbc);
        gbc.gridx = 1;
        formularioPanel.add(txtNombreCientifico, gbc);
        
        gbc.gridx = 0; gbc.gridy = row++;
        formularioPanel.add(new JLabel("V�as de Transmisi�n:"), gbc);
        gbc.gridx = 1;
        formularioPanel.add(comboViasTransmision, gbc);
        
        gbc.gridx = 0; gbc.gridy = row++;
        formularioPanel.add(new JLabel("Per�odo Incubaci�n (d�as):"), gbc);
        gbc.gridx = 1;
        formularioPanel.add(txtPeriodoIncubacion, gbc);
        
        gbc.gridx = 0; gbc.gridy = row++;
        formularioPanel.add(new JLabel("Estado paciente:"), gbc);
        gbc.gridx = 1;
        formularioPanel.add(comboEstadoPaciente, gbc);
        
        // Botones
        JButton btnAgregarEnfermedad = new JButton("Agregar Enfermedad");
        JButton btnEliminarEnfermedad = new JButton("Eliminar Seleccionada");
        
        gbc.gridx = 0; gbc.gridy = row++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        formularioPanel.add(btnAgregarEnfermedad, gbc);
        
        // Panel para el formulario con desplazamiento
        JScrollPane formularioScroll = new JScrollPane(formularioPanel);
        formularioScroll.setPreferredSize(new Dimension(400, 300)); // Ajustado para el nuevo campo
        
        // Panel de botones
        JPanel panelBotones = new JPanel();
        panelBotones.add(btnEliminarEnfermedad);
        
        // Organizar componentes principales
        panel.add(formularioScroll, BorderLayout.CENTER);
        panel.add(scrollEnfermedades, BorderLayout.EAST);
        panel.add(panelBotones, BorderLayout.SOUTH);
        
        // Acci�n para agregar enfermedad
        btnAgregarEnfermedad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Validar campos obligatorios
                    String nombreComun = txtNombreComun.getText().trim();
                    if (nombreComun.isEmpty()) {
                        throw new IllegalArgumentException("El nombre com�n es obligatorio");
                    }
                    
                    String viaTransmision = (String) comboViasTransmision.getSelectedItem();
                    if (viaTransmision == null) {
                        throw new IllegalArgumentException("Debe seleccionar una v�a de transmisi�n");
                    }
                    
                    // Obtener el estado del paciente
                    String estadoPaciente = (String) comboEstadoPaciente.getSelectedItem();
                    
                    // Validar campo num�rico
                    int periodoIncubacion = validarNumero(txtPeriodoIncubacion, "Per�odo de incubaci�n", true);
                    
                    // Verificar si ya existe una enfermedad con estado "muerto"
                    boolean existeMuerto = false;
                    for (int i = 0; i < modelEnfermedades.size() && !existeMuerto; i++) {
                        String enfermedad = modelEnfermedades.getElementAt(i);
                        if (enfermedad.contains("Estado: muerto")) {
                            existeMuerto = true;
                        }
                    }
                    
                    // Si existe una enfermedad con estado "muerto", solo permitir agregar otra si tambi�n es "muerto"
                    if (existeMuerto && !"muerto".equals(estadoPaciente)) {
                        throw new IllegalArgumentException("No se puede agregar una enfermedad con estado '" + estadoPaciente + 
                                                           "' porque ya existe una enfermedad con estado 'muerto'");
                    }
                    
                    // Si se intenta agregar una enfermedad no "muerto" cuando ya existe una "muerto"
                    if (existeMuerto && !"muerto".equals(estadoPaciente)) {
                        throw new IllegalArgumentException("Solo se pueden agregar enfermedades con estado 'muerto' cuando ya existe una enfermedad con ese estado");
                    }
                    
                    // Crear representaci�n de la enfermedad
                    String enfermedad = String.format(
                        "%s|%s|%s|%d|%s",
                        nombreComun,
                        txtNombreCientifico.getText().trim(),
                        viaTransmision,
                        periodoIncubacion,
                        estadoPaciente
                    );
                    
                    // Agregar al modelo
                    modelEnfermedades.addElement(enfermedad);
                    
                    // Limpiar campos
                    txtNombreComun.setText("");
                    txtNombreCientifico.setText("");
                    comboViasTransmision.setSelectedIndex(-1);
                    txtPeriodoIncubacion.setText("");
                    comboEstadoPaciente.setSelectedIndex(-1); // Volver a estado predeterminado
                    
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(panel, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            
            private int validarNumero(JTextField campo, String nombreCampo, boolean permitirCero) {
                String texto = campo.getText().trim();
                if (texto.isEmpty()) {
                    return 0; // Valor por defecto si est� vac�o
                }
                try {
                    int valor = Integer.parseInt(texto);
                    if (valor < 0) {
                        throw new IllegalArgumentException("El campo '" + nombreCampo + "' debe ser un n�mero positivo");
                    }
                    if (!permitirCero && valor == 0) {
                        throw new IllegalArgumentException("El campo '" + nombreCampo + "' debe ser mayor que cero");
                    }
                    return valor;
                } catch (NumberFormatException ex) {
                    throw new IllegalArgumentException("El campo '" + nombreCampo + "' debe ser un n�mero v�lido");
                }
            }
        });
        
        // Acci�n para eliminar enfermedad
        btnEliminarEnfermedad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = listEnfermedades.getSelectedIndex();
                if (selectedIndex != -1) {
                    modelEnfermedades.remove(selectedIndex);
                }
            }
        });
        
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
        
        // Panel para a�adir nuevos tratamientos
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
        
        panel.add(new JLabel("A�adir nuevo tratamiento:"), BorderLayout.NORTH);
        panel.add(panelEntrada, BorderLayout.CENTER);
        panel.add(panelBotones, BorderLayout.SOUTH);
        panel.add(scrollTratamientos, BorderLayout.EAST);
        
        return panel;
    }
    
    private JPanel crearPanelPaisesVisitados() {
        final JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        // Panel para la pregunta
        JPanel panelPregunta = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lblPregunta = new JLabel("�Se enferm� en el exterior?");
        String[] opciones = {"Seleccione", "S�", "No"};
        comboEnfermoExterior = new JComboBox<>(opciones);
        comboEnfermoExterior.setSelectedIndex(0); // Inicialmente en "Seleccione"
        
        panelPregunta.add(lblPregunta);
        panelPregunta.add(comboEnfermoExterior);
        
        mainPanel.add(panelPregunta, BorderLayout.NORTH);
        
        // Panel para el contenido de pa�ses (inicialmente deshabilitado)
        final JPanel panelPaisesContent = new JPanel(new BorderLayout(10, 10));
        panelPaisesContent.setEnabled(false);
        
        // Modelo y lista para pa�ses
        modelPaises = new DefaultListModel<>();
        listPaises = new JList<>(modelPaises);
        final JScrollPane scrollPaises = new JScrollPane(listPaises);
        scrollPaises.setBorder(new TitledBorder("Pa�ses registrados"));
        scrollPaises.setEnabled(false);
        
        // Panel para a�adir nuevos pa�ses
        JPanel panelEntrada = new JPanel(new BorderLayout(5, 5));
        txtPais = new JTextField();
        txtPais.setEnabled(false);
        
        btnAgregarPais = new JButton("Agregar pa�s");
        btnAgregarPais.setEnabled(false);
        
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
        
        btnEliminarPais = new JButton("Eliminar Seleccionado");
        btnEliminarPais.setEnabled(false);
        
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
        
        panelPaisesContent.add(new JLabel("A�adir nuevo pa�s visitado:"), BorderLayout.NORTH);
        panelPaisesContent.add(panelEntrada, BorderLayout.CENTER);
        panelPaisesContent.add(panelBotones, BorderLayout.SOUTH);
        panelPaisesContent.add(scrollPaises, BorderLayout.EAST);
        
        mainPanel.add(panelPaisesContent, BorderLayout.CENTER);
        
        // Listener para la selecci�n de la pregunta
        comboEnfermoExterior.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String seleccion = (String) comboEnfermoExterior.getSelectedItem();
                if (seleccion != null) {
                    if (seleccion.equals("S�")) {
                    	isEnfermoEnExterior = true;
                        // Habilitar componentes para agregar pa�ses
                        panelPaisesContent.setEnabled(true);
                        scrollPaises.setEnabled(true);
                        txtPais.setEnabled(true);
                        btnAgregarPais.setEnabled(true);
                        btnEliminarPais.setEnabled(true);
                    } else if (seleccion.equals("No")) {
                    	isEnfermoEnExterior = false;
                        // Eliminar la pesta�a de pa�ses visitados
                        int index = tabbedPane.indexOfComponent(mainPanel);
                        if (index != -1) {
                            tabbedPane.remove(index);
                        }
                    }
                }
            }
        });
        
        return mainPanel;
    }
    private JPanel crearPanelAnalisis() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        panel.setPreferredSize(new Dimension(300, 300)); 
        
        Font fuenteGrande = new Font("Arial", Font.BOLD, 16);
        Font fuenteResultado = new Font("Arial", Font.BOLD, 14);
        
        JLabel lblPresioneElBoton = new JLabel("Presione el bot�n para realizar el an�lisis y espere 8 segundos...");
        lblPresioneElBoton.setFont(fuenteGrande);
        lblPresioneElBoton.setBounds(50, 30, 400, 40); 
        panel.add(lblPresioneElBoton);
        
        btnAnalizar = new JButton("REALIZAR AN�LISIS");  // Asignar a campo de clase
        btnAnalizar.setFont(new Font("Arial", Font.BOLD, 18)); 
        btnAnalizar.setBounds(100, 80, 300, 60);
        panel.add(btnAnalizar);
        
        final JLabel lblResultado = new JLabel("");
        lblResultado.setFont(fuenteResultado);
        lblResultado.setBounds(50, 180, 400, 40); 
        lblResultado.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(lblResultado);
        
        btnAnalizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                resultadoAnalisis = Minsap.realizarAnalisis(); 
                String resultadoToString = resultadoAnalisis ? "POSITIVO" : "NEGATIVO";
                lblResultado.setText("El resultado del an�lisis fue: " + resultadoToString);
                lblResultado.setForeground(resultadoAnalisis ? Color.GREEN : Color.RED);
                
                // Actualizar el campo de diagn�stico
                if (resultadoAnalisis) {
                    txtDiagnostico.setText("S�");
                    txtDiagnostico.setEditable(false);
                } else {
                    txtDiagnostico.setText("No");
                    txtDiagnostico.setEditable(false);
                }
                
                // Marcar an�lisis como realizado
                analisisRealizado = true;
                
                // Deshabilitar bot�n para evitar m�ltiples an�lisis
                btnAnalizar.setEnabled(false);
                
                // Actualizar pesta�as (quitar� la pesta�a de an�lisis)
                actualizarPesta�as();
            }
        });
        
        return panel;
    }
    private class GuardarListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                // Obtener valores b�sicos comunes a todos los tipos de pacientes
                String nombre = Validacion.validarNombre(txtNombre, "Nombre");
                String id = Validacion.validarIdPersona(txtId, "Id");
                int edad = Validacion.validarEdad(txtEdad, "Edad");
                String sexo = Validacion.validarSexoPersona(txtSexo, "Sexo");
                String direccion = txtDireccion.getText().trim();
                
                // Validar campos obligatorios
                if (nombre.isEmpty() || id.isEmpty() || sexo.isEmpty() || direccion.isEmpty()) {
                    throw new IllegalArgumentException("Todos los campos b�sicos son obligatorios.");
                }
                
                // Obtener listas de elementos m�ltiples
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
                
                // Guardar enfermedades en archivo de enfermedades
                guardarEnfermedadesEnArchivo(enfermedades);
                
                // Determinar el tipo de paciente y guardar en el archivo correspondiente
                if (!resultadoAnalisis && (hizoDiagnostico == 0 || txtDiagnostico.getText().equals("No"))) {
                    // Paciente sin enfermedad
                    guardarPacienteNoEnfermo(nombre, id, edad, sexo, direccion);
                } else if (resultadoAnalisis || hizoDiagnostico == 1) {
                    // Paciente enfermo
                    if (isEnfermoEnExterior) {
                        // Paciente enfermo en extranjero
                        guardarPacienteEnfermoExtranjero(
                            nombre, id, edad, sexo, direccion, 
                            contactos, enfermedades, tratamientos
                        );
                    } else {
                        // Paciente enfermo nacional
                        guardarPacienteEnfermoNacional(
                            nombre, id, edad, sexo, direccion, 
                            contactos, enfermedades, tratamientos
                        );
                    }
                } else {
                    throw new IllegalArgumentException("Estado de diagn�stico no v�lido");
                }
                
                // Mensaje de �xito
                JOptionPane.showMessageDialog(RegistroPaciente.this,
                    "Paciente registrado exitosamente",
                    "Registro Exitoso",
                    JOptionPane.INFORMATION_MESSAGE);
                
                limpiarCampos();
                
            } catch (NumberFormatException ex) {
                mostrarError("Error en campos num�ricos: " + ex.getMessage());
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
            txtDiagnostico.setEditable(true);  // Restaurar edici�n
            modelContactos.clear();
            modelEnfermedades.clear();
            modelTratamientos.clear();
            
            // Resetear estado de an�lisis
            analisisRealizado = false;
            resultadoAnalisis = false;
            
            // Restaurar bot�n de an�lisis
            if (btnAnalizar != null) {
                btnAnalizar.setEnabled(true);
            }
        }
        
        private void guardarPacienteNoEnfermo(String nombre, String id, int edad, String sexo, String direccion) throws IOException {
        	String registro = String.join(DELIMITADOR,
        			LocalDateTime.now().format(FORMATO_FECHA),
        			nombre,
        			id,
        			String.valueOf(edad),
        			sexo,
        			direccion
        			);
        	guardarEnArchivo(ARCHIVO_PACIENTE_NOENFERMO, registro);
        }
        
        private void guardarPacienteEnfermoNacional(String nombre, String id, int edad, String sexo, String direccion,List<String> contactos, List<String> enfermedades,List<String> tratamientos) throws IOException {
        	// Validar al menos una enfermedad
        	if (enfermedades.isEmpty()) {
        		throw new IllegalArgumentException("Debe registrar al menos una enfermedad.");
        	}

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
        	guardarEnArchivo(ARCHIVO_ENFERMO_NACIONAL, registro);
        }
        
        private void guardarPacienteEnfermoExtranjero(String nombre, String id, int edad, String sexo, String direccion,List<String> contactos, List<String> enfermedades,List<String> tratamientos) throws IOException {
        	// Validar al menos una enfermedad
        	if (enfermedades.isEmpty()) {
        		throw new IllegalArgumentException("Debe registrar al menos una enfermedad.");
        	}

        	// Obtener lista de pa�ses
        	List<String> paises = new ArrayList<>();
        	for (int i = 0; i < modelPaises.size(); i++) {
        		paises.add(modelPaises.getElementAt(i));
        	}

        	String registro = String.join(DELIMITADOR,
        			LocalDateTime.now().format(FORMATO_FECHA),
        			nombre,
        			id,
        			String.valueOf(edad),
        			sexo,
        			direccion,
        			String.join(",", contactos),
        			String.join(",", enfermedades),
        			String.join(",", tratamientos),
        			String.join(",", paises)
        			);
        	guardarEnArchivo(ARCHIVO_ENFERMO_EN_EXTRANJERO, registro);
        }
        
        private void guardarEnfermedadesEnArchivo(List<String> enfermedades) throws IOException {
            for (String enfermedad : enfermedades) {
                try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(ARCHIVO_ENFERMEDADES, true)))) {
                    out.println(enfermedad);
                }
            }
        }
        
        private void guardarEnArchivo(String archivo, String registro) throws IOException {
            try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(archivo, true)))) {
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