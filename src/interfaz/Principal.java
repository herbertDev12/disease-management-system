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
		
		JMenu mnOpciones = new JMenu("Opciones");
		mnOpciones.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
		mnOpciones.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		menuBar.add(mnOpciones);
		
		JMenuItem mntmCerrarSesin = new JMenuItem("Cerrar Sesi\u00F3n");
		mntmCerrarSesin.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
		mnOpciones.add(mntmCerrarSesin);
		
		JMenuItem mntmCerrarPrograma = new JMenuItem("Cerrar Programa");
		mntmCerrarPrograma.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
		mnOpciones.add(mntmCerrarPrograma);
		
		JMenu mnGestion = new JMenu("Gesti\u00F3n");
		mnGestion.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
		mnGestion.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		menuBar.add(mnGestion);
		
		JMenuItem mntmBsqueda = new JMenuItem("B\u00FAsqueda");
		mntmBsqueda.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
		mnGestion.add(mntmBsqueda);
		
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
		
		JMenuItem mntmListaDePacientes = new JMenuItem("Lista de pacientes extranjeros ");
		mntmListaDePacientes.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
		mnGestionarPacientes.add(mntmListaDePacientes);
		
		JMenuItem mntmListaDePacientes_1 = new JMenuItem("Lista de pacientes nacionales ");
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
				
		JMenuItem mntmEliminarEnfermedad = new JMenuItem("Eliminar enfermedad");
		mntmEliminarEnfermedad.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
		mnGestionarEnfermedades.add(mntmEliminarEnfermedad);
		
		JMenu mnGestionarAnlisis = new JMenu("Gestionar An\u00E1lisis");
		mnGestionarAnlisis.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
		mnGestion.add(mnGestionarAnlisis);
		
		JMenuItem mntmAadirAnlisis = new JMenuItem("A\u00F1adir an\u00E1lisis");
		mntmAadirAnlisis.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
		mnGestionarAnlisis.add(mntmAadirAnlisis);
		
		JMenuItem mntmListaDeAnlisis = new JMenuItem("Lista de an\u00E1lisis");
		mntmListaDeAnlisis.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
		mnGestionarAnlisis.add(mntmListaDeAnlisis);
		
		JMenuItem mntmEliminarAnlisis = new JMenuItem("Eliminar an\u00E1lisis");
		mntmEliminarAnlisis.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
		mnGestionarAnlisis.add(mntmEliminarAnlisis);
		
		JMenu mnReportes = new JMenu("Reportes ");
		mnReportes.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
		mnReportes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		menuBar.add(mnReportes);
		
		JMenuItem mntmEnfermosEnEl = new JMenuItem("Enfermos en el extranjero dada una enfermedad");
		mntmEnfermosEnEl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/*
				FiltrarEnfermosEnExtranjero filtrarEnfermosEnExtranjero = FiltrarEnfermosEnExtranjero.getInstance();;
				filtrarEnfermosEnExtranjero.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				filtrarEnfermosEnExtranjero.setVisible(true);
				*/
			}
		});
		mntmEnfermosEnEl.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
		mnReportes.add(mntmEnfermosEnEl);
		
		JMenuItem mntmEnfermedadesConMayor = new JMenuItem("Enfermedades con mayor cantidad de pacientes en cierto estado ");
		mntmEnfermedadesConMayor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EnfermedadMayoresXEstado enfermedadMayoresXEstado = EnfermedadMayoresXEstado.getInstance();
				enfermedadMayoresXEstado.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				enfermedadMayoresXEstado.setVisible(true);
			}
		});
		mntmEnfermedadesConMayor.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
		mnReportes.add(mntmEnfermedadesConMayor);
		
		JMenuItem mntmCantidadDeInfectados = new JMenuItem("Cantidad de infectados en el exterior por fecha");
		mntmCantidadDeInfectados.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
		mnReportes.add(mntmCantidadDeInfectados);
		
		JMenuItem mntmPorcentajeDePersonas = new JMenuItem("Porcentaje de personas con x enfermedad");
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
