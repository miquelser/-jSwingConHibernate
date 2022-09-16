package presentacion;

import java.awt.EventQueue;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;

import datatypes.DtActDeportiva;
import datatypes.DtInstitucionD;
import datatypes.DtUsuario;
import excepciones.ActividadRepetidaExcepcion;
import excepciones.NoexsiteIntDep;
import interfaces.Fabrica;
import interfaces.IControlador;
import logica.MostrarTiempo;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class AltaActividad extends JInternalFrame {
	private static final long serialVersionUID = 1L;


	private JTextField txtNombre;
	private JTextField txtDescripcion;
	private JTextField txtDuracion;
	private JTextField txtCosto;
	private JLabel lblInstitucion;
	private JComboBox<String> cbxIntitucion;
	
	
	private IControlador iC;
	private JLabel fondo;
	private JLabel lblMosFech;
	private JLabel lblMosHor;
	public JLabel mostrarFecha;
	public JLabel mostrarHora;
	
	MostrarTiempo fech= new MostrarTiempo();

	public AltaActividad(IControlador iC) {
		this.iC= iC;
		setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
        setClosable(true);
        setTitle("Agregar una Actividad");
		
        
		
		
		setBounds(100, 100, 519, 439);
		getContentPane().setLayout(null);
		
		cbxIntitucion = new JComboBox<String>();
		cbxIntitucion.setBounds(127, 34, 100, 22);
		getContentPane().add(cbxIntitucion);
		
		lblInstitucion = new JLabel("Institución");
		lblInstitucion.setBounds(50, 38, 67, 14);
		getContentPane().add(lblInstitucion);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(50, 84, 46, 14);
		getContentPane().add(lblNombre);
		
		
		
		txtNombre = new JTextField();
		txtNombre.setBounds(127, 81, 100, 20);
		getContentPane().add(txtNombre);
		txtNombre.setColumns(10);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setBounds(127, 124, 100, 20);
		getContentPane().add(txtDescripcion);
		txtDescripcion.setColumns(10);
		
		JLabel lblDrescipcion = new JLabel("Descripción");
		lblDrescipcion.setBounds(50, 127, 94, 14);
		getContentPane().add(lblDrescipcion);
		
		JLabel lblDuracion = new JLabel("Duración");
		lblDuracion.setBounds(50, 167, 67, 14);
		getContentPane().add(lblDuracion);
		
		txtCosto = new JTextField();
		txtCosto.setBounds(127, 211, 100, 20);
		getContentPane().add(txtCosto);
		txtCosto.setColumns(10);
		
		JLabel lblCosto = new JLabel("Costo");
		lblCosto.setBounds(50, 214, 67, 14);
		getContentPane().add(lblCosto);
		
		JLabel lblNewLabel = new JLabel("en minutos");
		lblNewLabel.setBounds(237, 168, 89, 13);
		getContentPane().add(lblNewLabel);
		
				
				    	
				    	
				
				JButton btnAceptar = new JButton("Aceptar");
				btnAceptar.setBounds(367, 265, 89, 23);
				getContentPane().add(btnAceptar);
				
				JButton btnCancelar = new JButton("Cancelar");
				btnCancelar.setBounds(268, 265, 89, 23);
				getContentPane().add(btnCancelar);
				
				txtDuracion = new JTextField();
				txtDuracion.setBounds(127, 165, 100, 20);
				getContentPane().add(txtDuracion);
				txtDuracion.setColumns(10);
				
				fondo = new JLabel("");
				fondo.setBounds(0, 0, 477, 311);
				getContentPane().add(fondo);
				
				lblMosFech = new JLabel("Fecha");
				lblMosFech.setBounds(31, 332, 67, 14);
				getContentPane().add(lblMosFech);
				
				lblMosHor = new JLabel("Hora");
				lblMosHor.setBounds(217, 332, 67, 14);
				getContentPane().add(lblMosHor);
				
				mostrarFecha = new JLabel(fech.fechaComp);
				mostrarFecha.setBounds(82, 332, 94, 14);
				getContentPane().add(mostrarFecha);
				
				
				mostrarHora = new JLabel(fech.horaComp);			
				mostrarHora.setBounds(268, 332, 100, 14);
				getContentPane().add(mostrarHora);
				
				Imagen();
				
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						limpiarCampos();
					}
				});
				btnAceptar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						DtActDeportiva dtAD = null;
						Fabrica fab = Fabrica.getInstancia();
				    	IControlador icon = fab.getIControlador();
				    	Date fechaReg = new Date();
				    	
				
				    	String nom = txtNombre.getText();
				    	String des = txtDescripcion.getText();
				    	String dur = txtDuracion.getText();
				    	String cos = txtCosto.getText();
				    	
				    	   	
				        	        
				        try {
				        	Integer duracion = Integer.valueOf(dur);
					        float costo = Float.valueOf(cos);
							dtAD= new DtActDeportiva(nom, des, duracion, costo, fechaReg);	
							try {
								icon.altaActividad(dtAD, cbxIntitucion.getSelectedItem().toString());
				        		JOptionPane.showMessageDialog(null, "Se agregó satisfactoriamente una actividad");
				        		limpiarCampos();
							} catch (ActividadRepetidaExcepcion e1) {
								JOptionPane.showMessageDialog(null, "No puede crear Intitucion. \n" + e1.getMessage()+ "", "Alta Actividad Deportiva", JOptionPane.ERROR_MESSAGE);
				 				e1.printStackTrace();
							}
				        }
				        catch (NumberFormatException ex){
				        	JOptionPane.showMessageDialog(null, "Los datos de Costo o Duracion se an ingresado\nincorrectamente");
				        	ex.printStackTrace();
				        }
				        		    	
				        
					}
				});
				addComponentListener(new ComponentAdapter() {
					@Override
					public void componentResized(ComponentEvent e) {
						fondo.setBounds(0,0,getWidth(),getHeight());
						Imagen();
					}
				});
	}
	


	public void iniciarlizarComboBoxes() {	
			DefaultComboBoxModel<String> modelinstitucion;
			try {
				modelinstitucion = new DefaultComboBoxModel<String>(iC.listarInstituciones());
				cbxIntitucion.setModel(modelinstitucion);
			} catch (NoexsiteIntDep e) {
			
				e.printStackTrace();
			}
			
	}	

	public void limpiarCampos() {
		txtNombre.setText("");
		txtDescripcion.setText("");
		txtDuracion.setText("");
		txtCosto.setText("");
				
	}	
	public void Imagen() {
		ImageIcon imagen= new ImageIcon(Principal.class.getResource("/recursosF/logo.jpg"));
		Icon icono= new ImageIcon(imagen.getImage().getScaledInstance(fondo.getWidth(),fondo.getHeight(),Image.SCALE_DEFAULT));
		fondo.setIcon(icono);
	}
	
	/*public void mostrarTiempo() {
		
		mostrarFecha.setText(fech.fechaComp);
		mostrarHora.setText(fech.horaComp);				
	}*/
}
