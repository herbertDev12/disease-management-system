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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import logica.Minsap;
import logica.Enfermedad;

import java.awt.SystemColor;

public class EnfermedadMayoresXEstado extends JFrame {
    private static EnfermedadMayoresXEstado instancia;
    private JPanel contentPane;
    private JTextArea textArea; // Cambiamos JList por JTextArea
    private Minsap minsap;

    private EnfermedadMayoresXEstado(Minsap minsap) {
        this.minsap = minsap;
        initialize();
    }

    private void initialize() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 630);
        contentPane = new JPanel();
        contentPane.setBackground(SystemColor.activeCaption);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        // Cambiamos a JTextArea con JScrollPane
        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(12, 68, 408, 502);
        contentPane.add(scrollPane);
        
        JButton Curados = new JButton("Curados");
        Curados.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                ArrayList<Enfermedad> lista = minsap.enfermedadMayoresCurados();
                mostrarDatos(lista, "Curados");
            }
        });
        Curados.setBounds(12, 30, 97, 25);
        contentPane.add(Curados);
        
        JButton Activos = new JButton("Activos");
        Activos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                ArrayList<Enfermedad> lista = minsap.enfermedadMayoresActivos();
                mostrarDatos(lista, "Activos");
            }
        });
        Activos.setBounds(121, 30, 97, 25);
        contentPane.add(Activos);
        
        JButton muertos = new JButton("Muertos");
        muertos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                ArrayList<Enfermedad> lista = minsap.enfermedadMayoresMuertos();
                mostrarDatos(lista, "Muertos");
            }
        });
        muertos.setBounds(230, 30, 97, 25);
        contentPane.add(muertos);
    }
    
    private void mostrarDatos(ArrayList<Enfermedad> lista, String estado) {
        StringBuilder sb = new StringBuilder();
        sb.append("Enfermedades con mayor cantidad de pacientes ").append(estado).append(":\n\n");
        
        for (Enfermedad e : lista) {
            sb.append("Nombre: ").append(e.getNombreComun()).append("\n");
            sb.append(" - Curados: ").append(e.getCurados()).append("\n");
            sb.append(" - Activos: ").append(e.getActivos()).append("\n");
            sb.append(" - Muertos: ").append(e.getMuertos()).append("\n");
            sb.append(" - Hombres: ").append(e.getCantidadPacientesHombres()).append("\n");
            sb.append(" - Mujeres: ").append(e.getCantidadPacientesMujeres()).append("\n\n");
        }
        
        textArea.setText(sb.toString());
    }
    // Método para obtener la instancia con referencia a Minsap
    public static EnfermedadMayoresXEstado getInstance(Minsap minsap) {
        if (instancia == null) {
            instancia = new EnfermedadMayoresXEstado(minsap);
        }
        return instancia;
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Minsap minsap = new Minsap();
                    minsap.cargarTodosLosPacientes("ruta/nacionales.txt", "ruta/extranjeros.txt");
                    
                    EnfermedadMayoresXEstado frame = new EnfermedadMayoresXEstado(minsap);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

