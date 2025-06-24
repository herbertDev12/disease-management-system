package interfaz;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import logica.Minsap;
import logica.Enfermedad;
import java.awt.SystemColor;

public class EnfermedadMayoresXEstado extends JFrame {
	private static EnfermedadMayoresXEstado instancia;
	private JPanel contentPane;
	private JList<String> listaDatos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EnfermedadMayoresXEstado frame = new EnfermedadMayoresXEstado();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private EnfermedadMayoresXEstado() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 630);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		listaDatos = new JList<>();
		listaDatos.setBounds(12, 68, 408, 502);
		contentPane.add(listaDatos);
		
		JButton Curados = new JButton("Curados");
		Curados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 Minsap minsap = new Minsap();
			        ArrayList<Enfermedad> lista = minsap.enfermedadMayoresCurados();

			        ArrayList<String> datos = new ArrayList<>();
			        for (Enfermedad e : lista) {
			            String info = "Nombre: " + e.getNombreComun();
			                         
			            datos.add(info);
			        }

			        listaDatos.setListData(datos.toArray(new String[0]));
			}
		});
		Curados.setBounds(12, 30, 97, 25);
		contentPane.add(Curados);
		
		JButton Activos = new JButton("Activos");
		Activos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				  Minsap minsap = new Minsap();
			        ArrayList<Enfermedad> lista = minsap.enfermedadMayoresActivos();

			        ArrayList<String> datos = new ArrayList<>();
			        for (Enfermedad e : lista) {
			            String info = "Nombre: " + e.getNombreComun();
			                         
			            datos.add(info);
			        }

			        listaDatos.setListData(datos.toArray(new String[0]));
			    }
			});

		Activos.setBounds(121, 30, 97, 25);
		contentPane.add(Activos);
		
		JButton muertos = new JButton("Muertos");
		muertos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				  Minsap minsap = new Minsap();
			        ArrayList<Enfermedad> lista = minsap.enfermedadMayoresMuertos();

			        ArrayList<String> datos = new ArrayList<>();
			        for (Enfermedad e : lista) {
			            String info = "Nombre: " + e.getNombreComun();
			                         
			            datos.add(info);
			        }

			        listaDatos.setListData(datos.toArray(new String[0]));
			}
		});
		muertos.setBounds(230, 30, 97, 25);
		contentPane.add(muertos);
	}
	
	public static EnfermedadMayoresXEstado getInstance() {
		if (instancia == null) {
			instancia = new EnfermedadMayoresXEstado();
		}
		return instancia;
	}

}
