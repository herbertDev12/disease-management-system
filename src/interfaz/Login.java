package interfaz;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	
	private JPanel barraSuperior;
	private JLabel lblControlDeAcceso;
	private JButton btnmin;
	private JButton btnX;
	
	private JLabel lblFoto;
	private JLabel lblIniciarSesion;
	private JLabel lblUsuario;
	private JLabel lblContrasena;
	private JLabel lblUserPassError;
	private JTextField txfUsuario;
	private JPasswordField passwordField;
	private JTextField passwordText;
	private JButton btnInicioSesion;

	private int xMause, yMause;
	private boolean contrasenaVisible = false;

	public Login() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/images/Minsap.jpg")));
		setTitle("Autenticación MINSAP");
		setResizable(false);
		setBounds(100, 100, 848, 463);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(255, 255, 255));
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		contentPane.add(getBarraSuperior());
		contentPane.add(getLblFoto());
		contentPane.add(getLblIniciarSesion());
		contentPane.add(getLblUsuario());
		contentPane.add(getLblContrasena());
		contentPane.add(getLblUserPassError());
		contentPane.add(getTxfUsuario());
		contentPane.add(getPasswordField());
		contentPane.add(getPasswordText());
		contentPane.add(getBtnInicioSesion());

		this.setUndecorated(true); 
		this.setVisible(true);

		setLocationRelativeTo(null);
	}
	
	private JPanel getBarraSuperior() {
		if (barraSuperior == null) {
			barraSuperior = new JPanel();
			barraSuperior.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					xMause = e.getX();
					yMause = e.getY();
				}
			});
			barraSuperior.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
			barraSuperior.setBounds(10, 21, 848, 30);
			barraSuperior.setForeground(new Color(255, 255, 255));
			barraSuperior.setBorder(new LineBorder(new Color(0, 0, 0), 2));
			barraSuperior.setBackground(new Color(95, 158, 160));
			barraSuperior.setBounds(0, 0, 848, 30);
			contentPane.add(barraSuperior);
			barraSuperior.setLayout(null);

			
			barraSuperior.add(getLblControlDeAcceso());
			barraSuperior.add(getBtnmin());
			barraSuperior.add(getBtnX());

			barraSuperior.addMouseMotionListener(new MouseMotionAdapter() {
				public void mouseDragged(MouseEvent e) {
					int x = e.getXOnScreen();
					int y = e.getYOnScreen();
					setLocation(x-xMause,y-yMause);
				}
			});
		}

		return barraSuperior;
	}
	
	private JLabel getLblControlDeAcceso() {
		if (lblControlDeAcceso == null) {
			lblControlDeAcceso = new JLabel("Control de Acceso MINSAP");
			lblControlDeAcceso.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
			lblControlDeAcceso.setBounds(10, 5, 369, 23);
		}

		return lblControlDeAcceso;
	}
	
	private JButton getBtnmin() {
		if (btnmin == null) {
			btnmin = new JButton("-");
			btnmin.setFocusPainted(false);
			btnmin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnmin.setForeground(new Color(0, 0, 0));
			btnmin.setFont(new Font("Bahnschrift", Font.PLAIN, 22));
			btnmin.setBorder(new LineBorder(new Color(0, 0, 0), 2));
			btnmin.setBackground(new Color(95, 158, 160));
			btnmin.setBounds(774, 0, 38, 30);

			btnmin.addMouseListener(new MouseAdapter() {
				
				public void mouseEntered(MouseEvent arg0) {
					btnmin.setForeground(new Color(0, 255, 255));
				}
				
				public void mouseExited(MouseEvent arg0) {
					btnmin.setForeground(new Color(0, 0, 0));
				}
			});

			btnmin.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent arg0) {
					setState(ICONIFIED);
				}
			});
		}

		return btnmin;
	}
	
	private JButton getBtnX() {
		if (btnX == null) {
			btnX = new JButton("X");
			btnX.setFocusPainted(false);
			btnX.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnX.setBackground(new Color(95, 158, 160));
			btnX.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
			btnX.setForeground(new Color(0, 0, 0));
			btnX.setBorder(new LineBorder(new Color(0, 0, 0), 2));
			btnX.setBounds(810, 0, 38, 30);

			btnX.addMouseListener(new MouseAdapter() {

				public void mouseEntered(MouseEvent arg0) {
					btnX.setForeground(new Color(255, 0, 0));
				}
				
				public void mouseExited(MouseEvent arg0) {
					btnX.setForeground(new Color(0, 0, 0));
				}
			});
			btnX.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					dispose();
				}
			});
		}

		return btnX;
	}
	
	
	private JLabel getLblFoto() {
		if (lblFoto == null) {
			lblFoto = new JLabel("");
			lblFoto.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
			lblFoto.setHorizontalAlignment(SwingConstants.CENTER);
			lblFoto.setIcon(new ImageIcon(Login.class.getResource("/images/Minsap.jpg")));
			lblFoto.setBounds(0, 29, 299, 432);
		}

		return lblFoto;
	}
	
	private JLabel getLblIniciarSesion() {
		if (lblIniciarSesion == null) {
			lblIniciarSesion = new JLabel("Inicio Sesión");
			lblIniciarSesion.setForeground(new Color(0, 0, 0));
			lblIniciarSesion.setBackground(new Color(255, 255, 255));
			lblIniciarSesion.setFont(new Font("Bahnschrift", Font.PLAIN, 24));
			lblIniciarSesion.setBounds(459, 99, 145, 43);
		}

		return lblIniciarSesion;
	}

	private JLabel getLblUsuario() {
		if (lblUsuario == null) {
			lblUsuario  = new JLabel("Usuario:");
			lblUsuario.setBackground(new Color(255, 255, 255));
			lblUsuario.setForeground(new Color(0, 0, 0));
			lblUsuario.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
			lblUsuario.setBounds(417, 171, 107, 22);
		}

		return lblUsuario;
	}
	
	private JLabel getLblContrasena() {
		if (lblContrasena == null) {
			lblContrasena = new JLabel("Contraseña:");
			lblContrasena.setForeground(new Color(0, 0, 0));
			lblContrasena.setBackground(new Color(255, 255, 255));
			lblContrasena.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
			lblContrasena.setBounds(417, 272, 121, 22);
		}

		return lblContrasena;
	}
	
	private JLabel getLblUserPassError() {
		if (lblUserPassError == null) {
			lblUserPassError = new JLabel("Usuario o contraseña incorrecta");
			lblUserPassError.setForeground(new Color(165, 42, 42));
			lblUserPassError.setFont(new Font("Bahnschrift", Font.PLAIN, 18));
			lblUserPassError.setBounds(417, 414, 276, 36);
			lblUserPassError.setVisible(false);
		}

		return lblUserPassError;
	}
	
	private JTextField getTxfUsuario() {
		if (txfUsuario == null) {
			txfUsuario = new JTextField();
			txfUsuario.setToolTipText("");
			txfUsuario.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
			txfUsuario.setForeground(Color.BLACK);
			txfUsuario.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
			txfUsuario.setBackground(new Color(60, 179, 113));
			txfUsuario.setBounds(417, 219, 212, 25);
			txfUsuario.setColumns(10);

			
			txfUsuario.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					txfUsuario.setText(" ");
				}
			});
		}

		return txfUsuario;
	}
	
	private JPasswordField getPasswordField() {
		if (passwordField == null) {
			passwordField = new JPasswordField();
			passwordField.setBackground(new Color(60, 179, 113));
			passwordField.setEchoChar('*');
			passwordField.setForeground(Color.BLACK);
			passwordField.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
			passwordField.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
			passwordField.setBounds(417, 313, 212, 25);
		}

		return passwordField;
	}
	
	private JTextField getPasswordText() {
		if (passwordText == null) {
			passwordText = new JTextField();
			passwordText.setForeground(Color.BLACK);
			passwordText.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
			passwordText.setColumns(10);
			passwordText.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
			passwordText.setBackground(new Color(60, 179, 113));
			passwordText.setBounds(441, 313, 199, 25);
		}

		return passwordText;
	}
	
	private JButton getBtnInicioSesion() {
		if (btnInicioSesion == null) {
			btnInicioSesion = new JButton("Iniciar Sesión");
			btnInicioSesion.addKeyListener(new KeyAdapter() {
				
				@Override
				public void keyTyped(KeyEvent arg0) {
					
					String user = txfUsuario.getText();
					String password;

					
					if(contrasenaVisible)
						password = passwordText.getText();
					else
						password = String.valueOf(passwordField.getPassword());

					if(usuarioValido(user, password)){
						
						try {
							Principal frame = new Principal();
							
							dispose();
							
						} catch (Exception e) {
							e.printStackTrace();
						}
					}else{
						lblUserPassError.setText("Usuario o contraseña no válidos");
						lblUserPassError.setVisible(true);
					}	
					
				}
			});
			btnInicioSesion.setFocusPainted(false);
			btnInicioSesion.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnInicioSesion.setBorder(new LineBorder(new Color(0, 0, 0), 2));
			btnInicioSesion.setFont(new Font("Bahnschrift", Font.PLAIN, 18));
			btnInicioSesion.setForeground(new Color(0, 0, 0));
			btnInicioSesion.setBackground(new Color(255, 255, 255));
			btnInicioSesion.setBounds(456, 365, 148, 36);

			btnInicioSesion.addMouseListener(new MouseAdapter() {

				
				public void mouseEntered(MouseEvent arg0) {
					btnInicioSesion.setBackground(new Color(60, 179, 113));
				}
				
				public void mouseExited(MouseEvent arg0) {
					btnInicioSesion.setBackground(new Color(255, 255, 255));
				}
			});

			btnInicioSesion.addActionListener(new ActionListener() {	
				
				public void actionPerformed(ActionEvent arg0) {

					String user = txfUsuario.getText();
					String password;

					if(contrasenaVisible)
						password = passwordText.getText();
					else
						password = String.valueOf(passwordField.getPassword());

					if(usuarioValido(user, password)){
						
						try {
							Principal frame = new Principal();
							
							dispose();
							
						} catch (Exception e) {
							e.printStackTrace();
						}
					}else{
						lblUserPassError.setText("Usuario o contraseña no válidos");
						lblUserPassError.setVisible(true);
					}	
				}
			});

		}
		
		return btnInicioSesion;
	}
	
	
	private boolean usuarioValido(String user, String password){
		boolean usuarioValido = false;

		if(user.equals("admin") && password.equals("1234"))
			usuarioValido = true;

		return usuarioValido;
	}

}
