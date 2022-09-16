package presentacion;


import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import datatypes.DtInstitucionD;
import excepciones.InstitucionRepetidaExcepcion;
import interfaces.Fabrica;
import interfaces.IControlador;

import javax.swing.JInternalFrame;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class AltaInstitucion extends JInternalFrame {
	
	private static final long serialVersionUID = 1L;
	private JTextField txtUrl;
	private JTextField txtDes;
	private JTextField txtNom;

	private IControlador iC;
	private JLabel fondo;

	public AltaInstitucion(IControlador iC) {
		
		/*addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				fondo.setBounds(0,0,getWidth(),getHeight());
				Imagen();
			}
		});*/
		this.iC=iC;
		setBounds(100, 100, 572, 312);
		getContentPane().setLayout(null);
		
		JLabel lblUrl = new JLabel("Url");
		lblUrl.setBounds(233, 122, 75, 12);
		getContentPane().add(lblUrl);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(233, 59, 75, 14);
		getContentPane().add(lblNombre);
		
		JLabel lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setBounds(233, 90, 75, 14);
		getContentPane().add(lblDescripcion);
		
		txtNom = new JTextField();
		txtNom.setBounds(304, 56, 98, 20);
		getContentPane().add(txtNom);
		txtNom.setColumns(10);
		
		txtDes = new JTextField();
		txtDes.setBounds(304, 87, 98, 20);
		getContentPane().add(txtDes);
		txtDes.setColumns(10);
		
		txtUrl = new JTextField();
		txtUrl.setBounds(303, 118, 99, 20);
		getContentPane().add(txtUrl);
		txtUrl.setColumns(10);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnCancelar.setBounds(300, 216, 89, 23);
		getContentPane().add(btnCancelar);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DtInstitucionD DtInstitucionD = null;
		    	
		    	String nom = txtNom.getText();
		    	String des = txtDes.getText();
		    	String url = txtUrl.getText();
			
				DtInstitucionD=new DtInstitucionD(nom, des,url, null, null);
		    	
				if(txtNom.getText().isEmpty() || txtDes.getText().isEmpty() || txtUrl.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "Completar todos los campos para ingresar una Institución");
				}else {
		    	
		    	try {
					iC.altaInstitucion(DtInstitucionD);
					
					JOptionPane.showMessageDialog(null, "La institución "+nom+" se ha creado exitosamente");
					limpiarCampos();
					//setVisible(false);	
					
				} catch (InstitucionRepetidaExcepcion exe) {
					exe.printStackTrace();
					//limpiarCampos();
					txtNom.setForeground(Color.RED);
					JOptionPane.showMessageDialog(null, exe.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}	
		}	
			}
		});
		setResizable(true);
	    setIconifiable(true);
	    setMaximizable(true);
	    //setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
	    setClosable(true);
	    setTitle("Agregar Institución");
		btnAceptar.setBounds(444, 216, 89, 23);
		getContentPane().add(btnAceptar);
		
		fondo = new JLabel("");
		fondo.setBounds(0, 0, 560, 283);
		getContentPane().add(fondo);
		Imagen();
		
		addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				fondo.setBounds(0,0,getWidth(),getHeight());
				Imagen();
			}
		});
	}
	
	public void Imagen() {
		ImageIcon imagen= new ImageIcon(Principal.class.getResource("/recursosF/chanclas.jpg"));
		Icon icono= new ImageIcon(imagen.getImage().getScaledInstance(fondo.getWidth(),fondo.getHeight(),Image.SCALE_DEFAULT));
		fondo.setIcon(icono);
	}
	
	public void limpiarCampos() {
		txtNom.setText("");
		txtDes.setText("");
		txtUrl.setText("");		
	}
}
