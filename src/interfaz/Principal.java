package interfaz;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Frame;

import javax.swing.JMenuBar;
import javax.swing.JMenu;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JMenuItem;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import logica.Minsap;

public class Principal extends JFrame {

	private JPanel fotoFondo;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public Principal() {
		setTitle("MINSAP");
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnGestion = new JMenu("Gesti\u00F3n");
		mnGestion.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
		mnGestion.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		menuBar.add(mnGestion);
		
		JMenu mnGestionarPacientes = new JMenu("Gestionar Pacientes ");
		mnGestionarPacientes.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
		mnGestion.add(mnGestionarPacientes);
		
		JMenuItem mntmAadirPaciente = new JMenuItem("A\u00F1adir paciente");
		mntmAadirPaciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RegistroPaciente addPacienteFrame = new RegistroPaciente();
				addPacienteFrame.setVisible(true);
			}
		});
		mntmAadirPaciente.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
		mnGestionarPacientes.add(mntmAadirPaciente);
		
		JMenuItem mntmListaDePacientes = new JMenuItem("Lista de pacientes enfermados en el extranjero");
		mntmListaDePacientes.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
		        Minsap minsap = new Minsap();
		        minsap.cargarPacientes("C:\\herbert\\disease-management-system\\src\\data\\pacientesEnfermosExtranjero.txt");
		        
		        // Crear ventana para mostrar el panel
		        JFrame framePacientesExtranjeros = new JFrame("Listado de Pacientes enfermos en el exterior");
		        framePacientesExtranjeros.setSize(1400, 600);
		        framePacientesExtranjeros.setLocationRelativeTo(null);
		        framePacientesExtranjeros.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		        
		        // Crear y agregar el panel
		        EnfermosEnExtranjeroPanel enfermosEnExtranjeroPanel = new EnfermosEnExtranjeroPanel(minsap);
		        framePacientesExtranjeros.add(enfermosEnExtranjeroPanel);
		        
		        // Mostrar la ventana
		        framePacientesExtranjeros.setVisible(true);
		    }
		});
		mntmListaDePacientes.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
		mnGestionarPacientes.add(mntmListaDePacientes);
		
		JMenuItem mntmListaDePacientes_1 = new JMenuItem("Lista de pacientes nacionales ");
		mntmListaDePacientes_1.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
		        Minsap minsap = new Minsap();
		        minsap.cargarPacientes("C:\\herbert\\disease-management-system\\src\\data\\pacientesEnfermosNacional.txt");
		        
		        // Crear ventana para mostrar el panel
		        JFrame framePacientesNacionales = new JFrame("Listado de Pacientes Nacionales");
		        framePacientesNacionales.setSize(1400, 600);
		        framePacientesNacionales.setLocationRelativeTo(null);
		        framePacientesNacionales.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		        
		        // Crear y agregar el panel
		        EnfermosNacionalesPanel enfermosNacionalesPanel = new EnfermosNacionalesPanel(minsap);
		        framePacientesNacionales.add(enfermosNacionalesPanel);
		        
		        // Mostrar la ventana
		        framePacientesNacionales.setVisible(true);
		    }
		});
		mntmListaDePacientes_1.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
		mnGestionarPacientes.add(mntmListaDePacientes_1);
		
		JMenu mnGestionarEnfermedades = new JMenu("Gestionar Enfermedades");
		mnGestionarEnfermedades.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
		mnGestion.add(mnGestionarEnfermedades);
		
		JMenuItem mntmListaDeEnfermedades = new JMenuItem("Lista de enfermedades");
		mntmListaDeEnfermedades.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
		        Minsap minsap = new Minsap();
		        minsap.cargarPacientes("C:\\herbert\\disease-management-system\\src\\data\\pacientesEnfermosNacional.txt");
		        minsap.cargarPacientes("C:\\herbert\\disease-management-system\\src\\data\\pacientesEnfermosExtranjero.txt");
		        
		        // Crear ventana para mostrar el panel
		        JFrame frameEnfermedades = new JFrame("Listado de Enfermedades");
		        frameEnfermedades.setSize(1400, 600);
		        frameEnfermedades.setLocationRelativeTo(null);
		        frameEnfermedades.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		        
		        // Crear y agregar el panel
		        EnfermedadesPanel enfermedadesPanel = new EnfermedadesPanel(minsap);
		        frameEnfermedades.add(enfermedadesPanel);
		        
		        // Mostrar la ventana
		        frameEnfermedades.setVisible(true);
		    }
		});
		mntmListaDeEnfermedades.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
		mnGestionarEnfermedades.add(mntmListaDeEnfermedades);
						
		JMenu mnReportes = new JMenu("Reportes ");
		mnReportes.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
		mnReportes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		menuBar.add(mnReportes);
		
		JMenuItem mntmEnfermosEnEl = new JMenuItem("Enfermos en el extranjero dada una enfermedad");
		mntmEnfermosEnEl.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
		        Minsap minsap = new Minsap();
		        // Cargar datos antes de mostrar la ventana
		        minsap.cargarTodosLosPacientes(
		            "C:\\herbert\\disease-management-system\\src\\data\\pacientesEnfermosNacional.txt",
		            "C:\\herbert\\disease-management-system\\src\\data\\pacientesEnfermosExtranjero.txt"
		        );
		        
		        // Crear ventana para el panel de búsqueda
		        JFrame frameBusqueda = new JFrame("Pacientes Extranjeros por Enfermedad");
		        frameBusqueda.setSize(800, 600);
		        frameBusqueda.setLocationRelativeTo(null);
		        frameBusqueda.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		        
		        // Crear y agregar el panel
		        BuscarPacientesExtranjerosEnfermedadPanel panelBusqueda = new BuscarPacientesExtranjerosEnfermedadPanel(minsap);
		        frameBusqueda.add(panelBusqueda);
		        
		        // Mostrar la ventana
		        frameBusqueda.setVisible(true);
		    }
		});
		mntmEnfermosEnEl.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
		mnReportes.add(mntmEnfermosEnEl);
		
		JMenuItem mntmEnfermedadesConMayor = new JMenuItem("Enfermedades con mayor cantidad de pacientes en cierto estado");
		mntmEnfermedadesConMayor.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
		        Minsap minsap = new Minsap();
		        // Cargar datos antes de mostrar la ventana
		        minsap.cargarTodosLosPacientes(
		            "C:\\herbert\\disease-management-system\\src\\data\\pacientesEnfermosNacional.txt",
		            "C:\\herbert\\disease-management-system\\src\\data\\pacientesEnfermosExtranjero.txt"
		        );
		        
		        EnfermedadMayoresXEstado enfermedadMayoresXEstado = EnfermedadMayoresXEstado.getInstance(minsap);
		        enfermedadMayoresXEstado.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		        enfermedadMayoresXEstado.setVisible(true);
		    }
		});
		mntmEnfermedadesConMayor.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
		mnReportes.add(mntmEnfermedadesConMayor);

		
		JMenuItem mntmBuscarPorEnfermedad = new JMenuItem("Filtrar pacientes nacionales por enfermedad");
		mntmBuscarPorEnfermedad.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	Minsap minsap = new Minsap();
		       
		        minsap.cargarTodosLosPacientes(
		            "C:\\herbert\\disease-management-system\\src\\data\\pacientesEnfermosNacional.txt",
		            "C:\\herbert\\disease-management-system\\src\\data\\pacientesEnfermosExtranjero.txt"
		        );
				
		        // Crear ventana para el panel de búsqueda
		        JFrame frameBusqueda = new JFrame("Pacientes por Enfermedad");
		        frameBusqueda.setSize(800, 600);
		        frameBusqueda.setLocationRelativeTo(null);
		        frameBusqueda.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		        
		        // Crear y agregar el panel
		        BuscarPacientesEnfermedadPanel panelBusqueda = new BuscarPacientesEnfermedadPanel(minsap);
		        frameBusqueda.add(panelBusqueda);
		        
		        // Mostrar la ventana
		        frameBusqueda.setVisible(true);
		    }
		});
		mntmBuscarPorEnfermedad.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
		mnReportes.add(mntmBuscarPorEnfermedad);
		
		JMenuItem mntmPorcentajeDePersonas = new JMenuItem("Porcentaje de personas con x enfermedad con respecto al total de personas enfermas");
		mntmPorcentajeDePersonas.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	Minsap minsap = new Minsap();
			       
		        minsap.cargarTodosLosPacientes(
		            "C:\\herbert\\disease-management-system\\src\\data\\pacientesEnfermosNacional.txt",
		            "C:\\herbert\\disease-management-system\\src\\data\\pacientesEnfermosExtranjero.txt"
		        );
		        String enfermedad = JOptionPane.showInputDialog(
		            "Introduzca el nombre de la enfermedad:"
		        );
		        
		        if (enfermedad != null && !enfermedad.trim().isEmpty()) {
		            double porcentaje = minsap.porcentajePersonasConEnfermedad(enfermedad.trim());
		            JOptionPane.showMessageDialog(
		                null,
		                String.format("Porcentaje de pacientes con '%s': %.2f%%", enfermedad, porcentaje),
		                "Resultado",
		                JOptionPane.INFORMATION_MESSAGE
		            );
		        }
		    }
		});
		mntmPorcentajeDePersonas.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
		mnReportes.add(mntmPorcentajeDePersonas);
		fotoFondo = new JPanel();
		fotoFondo = new JPanel(){
			private static final long serialVersionUID = 1L;

			public void paintComponent(Graphics g){
				Image img = Toolkit.getDefaultToolkit().getImage(Principal.class.getResource("/images/MenuPrincipalNew.jpg"));
				g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
			}
		};
		fotoFondo.setBorder(new EmptyBorder(5, 5, 5, 5));
		fotoFondo.setLayout(new BorderLayout(0, 0));
		setContentPane(fotoFondo);
	}

}
