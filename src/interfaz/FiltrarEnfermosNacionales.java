package interfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JList;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import logica.Minsap;
import logica.Paciente;
import logica.Enfermedad;

public class FiltrarEnfermosNacionales extends JFrame {
	
	private static FiltrarEnfermosNacionales instancia;
	private JPanel contentPane;
	private JComboBox<String> comboBox;
	private JList<String> listaDatos;
	private Minsap minsap;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FiltrarEnfermosNacionales frame = new FiltrarEnfermosNacionales();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private FiltrarEnfermosNacionales() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 630);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		minsap = new Minsap();

		comboBox = new JComboBox<String>();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String seleccion = (String) comboBox.getSelectedItem();
				ArrayList<Paciente> pacientes = minsap.filtroEnfermoNacional(seleccion);

				ArrayList<String> nombres = new ArrayList<String>();
				for (Paciente p : pacientes) {
					nombres.add(p.getNombre());
				}

				if (nombres.isEmpty()) {
					listaDatos.setListData(new String[] { "No se encontraron pacientes nacionales con esta enfermedad" });
				} else {
					listaDatos.setListData(nombres.toArray(new String[nombres.size()]));
				}
			}
		});
		comboBox.setBounds(12, 13, 408, 49);
		contentPane.add(comboBox);

		listaDatos = new JList<String>();
		listaDatos.setBounds(12, 75, 408, 495);
		contentPane.add(listaDatos);

		ArrayList<Enfermedad> enfermedades = minsap.getAllEnfermedades();
		for (Enfermedad enf : enfermedades) {
			comboBox.addItem(enf.getNombreComun());
		}
	}
	public static FiltrarEnfermosNacionales getInstance() {
		if (instancia == null) {
			instancia = new FiltrarEnfermosNacionales();
		}
		return instancia;
	}
}
