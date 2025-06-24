package interfaz;


import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JList;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import logica.*;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;
public class FiltrarEnfermosEnExtranjero extends JFrame {
	private static FiltrarEnfermosEnExtranjero instancia;
	private JPanel contentPane;
	private JComboBox<String> comboBox;
	private Minsap minsap;
	private JList<String> list;


	 //Launch the application.

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FiltrarEnfermosEnExtranjero frame = new FiltrarEnfermosEnExtranjero();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	 // Create the frame.

	private FiltrarEnfermosEnExtranjero() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 630);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setForeground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		list = new JList<String>();
		list.setBounds(12, 59, 408, 511);
		contentPane.add(list);
		
		comboBox = new JComboBox<String>();
		comboBox.setFont(new Font("Arial Black", Font.BOLD, 14));
		comboBox.setToolTipText("Enfermedades a filtrar");
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String seleccion = (String) comboBox.getSelectedItem();
				ArrayList<EnfermoEnExtranjero> pacientes = minsap.filtroEnfermosEnExtranjero(seleccion);

				ArrayList<String> descripciones = new ArrayList<>();

				for (EnfermoEnExtranjero ext : pacientes) {
				    String descripcion = ext.getNombre() + " - ";

				    ArrayList<PaisVisitado> paises = ext.getPaisesVisitados();
				    if (paises.isEmpty()) {
				        descripcion += "Sin países registrados";
				    } else {
				        descripcion += "Países: ";
				        int contador = 0;
				        for (PaisVisitado pais : paises) {
				            descripcion += pais.getNombre() + " (" + pais.getTiempoEstancia() + ")";
				            contador++;
				            if (contador < paises.size()) {
				                descripcion += ", ";
				            }
				        }
				    }
				    descripciones.add(descripcion);
				}

				if (descripciones.isEmpty()) {
				    list.setListData(new String[] { "No se encontraron pacientes con esta enfermedad" });
				} else {
				    list.setListData(descripciones.toArray(new String[0]));
				}

			}
		});
		comboBox.setBounds(12, 13, 408, 33);
		contentPane.add(comboBox);
		
		minsap = new Minsap();
        ArrayList<Enfermedad> enfermedades = minsap.getAllEnfermedades() ;
		
		for(Enfermedad enf : enfermedades){
			comboBox.addItem(enf.getNombreComun());
		}
	}
	
	public static FiltrarEnfermosEnExtranjero getInstance() {
		if (instancia == null) {
			instancia = new FiltrarEnfermosEnExtranjero();
		}
		return instancia;
	}
	
}

