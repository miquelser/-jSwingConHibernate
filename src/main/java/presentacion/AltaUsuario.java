package presentacion;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import datatypes.DtClase;
import datatypes.DtInstitucionD;
import datatypes.DtProfesor;
import datatypes.DtSocio;
import datatypes.DtUsuario;
import excepciones.InstitucionRepetidaExcepcion;
import excepciones.NoexsiteIntDep;
import excepciones.UsuarioRepetidoExcepcion;
import interfaces.Fabrica;
import interfaces.IControlador;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextPane;
import javax.swing.SpinnerDateModel;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.Box;
import java.awt.Dimension;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JCalendar;
import javax.swing.JComboBox;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;

public class AltaUsuario extends JInternalFrame {


	private static final long serialVersionUID = 1L;
	private JTextField txtNickname;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtEmail;
	private JTextField txtSitioweb;
	private IControlador iC;
	private JComboBox<String> txtIns;
	
	private JDateChooser dateChooser;

	
	public AltaUsuario(IControlador iC) {
		
		this.iC= iC;
		setResizable(true);
		setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
        setClosable(true);
        setTitle("Alta de Usuario");

		setBackground(Color.LIGHT_GRAY);
		setBounds(100, 100, 844, 525);
		getContentPane().setLayout(null);
		
		JPanel contentPane = new JPanel();
		contentPane.setLayout(null);
		contentPane.setBounds(0, 0, 828, 495);
		getContentPane().add(contentPane);
		
		
		txtNickname = new JTextField();
		txtNickname.setColumns(10);
		txtNickname.setBounds(148, 66, 118, 20);
		contentPane.add(txtNickname);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(148, 106, 118, 20);
		contentPane.add(txtNombre);
		
		txtApellido = new JTextField();
		txtApellido.setColumns(10);
		txtApellido.setBounds(148, 151, 118, 20);
		contentPane.add(txtApellido);
		
		txtEmail = new JTextField();
		txtEmail.setForeground(Color.BLACK);
		txtEmail.setColumns(10);
		txtEmail.setBounds(126, 197, 180, 20);
		contentPane.add(txtEmail);
		
		JLabel lblNewLabel = new JLabel("Nickname");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(53, 67, 85, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNombre.setBounds(53, 109, 85, 14);
		contentPane.add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblApellido.setBounds(53, 152, 85, 14);
		contentPane.add(lblApellido);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEmail.setBounds(53, 198, 85, 14);
		contentPane.add(lblEmail);
		JLabel lblNewLabel_1 = new JLabel("Registrar Usuarios");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1.setBounds(260, 22, 236, 20);
		contentPane.add(lblNewLabel_1);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 txtIns.removeAllItems();
					txtEmail.setForeground(Color.BLACK);
					txtNickname.setForeground(Color.BLACK);
				 setVisible(false);
			}
		});
		btnCancelar.setBounds(444, 400, 89, 38);
		contentPane.add(btnCancelar);
		
		JPanel panelProfe = new JPanel();

		panelProfe.setBounds(351, 123, 446, 266);
		contentPane.add(panelProfe);
		panelProfe.setLayout(null);
		
		JLabel lblInstitucion = new JLabel("Institucion");
		lblInstitucion.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblInstitucion.setBounds(10, 31, 85, 14);
		panelProfe.add(lblInstitucion);
		
		txtSitioweb = new JTextField();
		txtSitioweb.setColumns(10);
		txtSitioweb.setBounds(124, 235, 188, 20);
		panelProfe.add(txtSitioweb);
		
		JLabel lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDescripcion.setBounds(10, 64, 85, 14);
		panelProfe.add(lblDescripcion);
		
		JLabel lblInstitucion_1_1 = new JLabel("Biografia");
		lblInstitucion_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblInstitucion_1_1.setBounds(10, 153, 85, 14);
		panelProfe.add(lblInstitucion_1_1);
		
		JLabel lblInstitucion_1_1_1 = new JLabel("Sitio web");
		lblInstitucion_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblInstitucion_1_1_1.setBounds(10, 236, 85, 14);
		panelProfe.add(lblInstitucion_1_1_1);
		
		JTextArea txtDesc = new JTextArea();
		txtDesc.setBounds(124, 61, 312, 27);
		panelProfe.add(txtDesc);
		
		JTextArea txtBio = new JTextArea();
		txtBio.setBounds(124, 150, 312, 27);
		panelProfe.add(txtBio);
		
		txtIns= new JComboBox();
		txtIns.setBounds(124, 29, 188, 22);
		panelProfe.add(txtIns);
		
		JRadioButton rbtnSocio = new JRadioButton("Socio");
		JRadioButton rbtnProfe = new JRadioButton("Profesor");

		
		
		rbtnSocio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rbtnSocio.isSelected()) {
					panelProfe.setVisible(false);
					rbtnProfe.setSelected(false);
					//rbtnProfe.setEnabled(false);
				}
			}
		});
			
		rbtnSocio.setBounds(351, 65, 109, 23);
		contentPane.add(rbtnSocio);
		panelProfe.setVisible(false);
		

		rbtnProfe.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				//panelProfe.setVisible(false);
			}

		});
		rbtnProfe.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(rbtnProfe.isSelected()) {
					panelProfe.setVisible(true);
					rbtnSocio.setSelected(false);
				}

			}
		});
		
		
		rbtnProfe.setBounds(504, 65, 109, 23);
		contentPane.add(rbtnProfe);
	
		//iniciarlizarComboBoxes();
		
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			//private Object txtIns;
			public void actionPerformed(ActionEvent e) {
			    					
			    	String nick = txtNickname.getText();
			    	String nombre = txtNombre.getText();
			    	String apellido = txtApellido.getText();
			    	String email = txtEmail.getText();
			    	
			    	Date fech = dateChooser.getDate();
			    	fech.setMonth(fech.getMonth());//como esta guardad en string la pasamos a int 
				
			    	DtUsuario dtusuario=null;
				if(rbtnSocio.isSelected()) {
					dtusuario=new DtSocio(nick, nombre, apellido, email, fech, null);
				}if(rbtnProfe.isSelected()) {
		  	    	String desc = txtDesc.getText();
		  	    	String bio = txtBio.getText();
		  	    	String sitweb = txtSitioweb.getText();
		  	    	
 	
		  	    	dtusuario=new DtProfesor(nick, nombre, apellido, email, fech, desc, bio, sitweb, null); 
	  	  			
	  	  			txtDesc.replaceSelection("");
	  	  			txtBio.replaceSelection("");
				}


				if(txtNickname.getText().isEmpty() || txtEmail.getText().isEmpty() || txtApellido.getText().isEmpty() || txtNombre.getText().isEmpty() || dateChooser.getDate()==null){
					JOptionPane.showMessageDialog(null, "Completar los campos obligatorios");
				}
				else {
					try {
						if(rbtnProfe.isSelected()) {
							String institucionDepo = txtIns.getSelectedItem().toString();
							iC.agregarUsuario(dtusuario, institucionDepo);
							txtSitioweb.setText("");
							txtDesc.setText("");
							txtBio.setText("");
						
						}else {
							iC.agregarUsuario(dtusuario, "noEsProfesor");
						}
						
						rbtnProfe.setSelected(false);
						rbtnSocio.setSelected(false);
						txtIns.removeAllItems();
						JOptionPane.showMessageDialog(null, "El usuario "+nick+" se ha creado con éxito");
						limpiarCampos();
						txtEmail.setForeground(Color.BLACK);
						txtNickname.setForeground(Color.BLACK);
						setVisible(false);
					} catch (UsuarioRepetidoExcepcion e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
						//txtNickname.setBorder();
						txtEmail.setForeground(Color.RED);
						txtNickname.setForeground(Color.RED);
			
					}
				}
			}
		});
		btnAceptar.setBounds(562, 400, 89, 38);
		contentPane.add(btnAceptar);
        
        JLabel lblNewLabel_2 = new JLabel("Fecha de nacimiento:");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel_2.setBounds(53, 258, 180, 14);
        contentPane.add(lblNewLabel_2);
        
        dateChooser = new JDateChooser();
		dateChooser.getCalendarButton().setToolTipText("ghfhg");
		dateChooser.setToolTipText("");
		dateChooser.setBounds(210, 258, 118, 19);
		contentPane.add(dateChooser);
        
	}

	public void setComboBox() {	
		DefaultComboBoxModel<String> modelinstitucion;
		try {
			modelinstitucion = new DefaultComboBoxModel<String>(iC.listarInstituciones());
			txtIns.setModel(modelinstitucion);
		} catch (NoexsiteIntDep e) {
		
			e.printStackTrace();
		}
		
}		
	
	

	public void limpiarCampos() {
		txtNickname.setText("");
		txtNombre.setText("");
		txtApellido.setText("");
		txtEmail.setText("");
		txtSitioweb.setText("");
		dateChooser.setDate(null);
	}
}
