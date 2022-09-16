package presentacion;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

import interfaces.Fabrica;
import interfaces.IControlador;
import logica.ActividadDeportiva;
import logica.Clase;
import logica.Profesor;
import logica.Socio;
import logica.Usuario;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableModel;

import datatypes.DtActDeportiva;
import datatypes.DtClase;
import datatypes.DtProfesor;
import datatypes.DtSocio;
import datatypes.DtUsuario;
import excepciones.NoExisteClase;
import excepciones.NoExisteUsuario;
import excepciones.NoexsiteIntDep;
import excepciones.SinActDep;

import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ItemEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ConsultaUsuario extends JInternalFrame {
	
	private IControlador iC;
	
	private JComboBox<String> cBUsuario;
	private JTextPane txtDatosU;
	private JTable tabDatActD;
	private JTable tabDatClases;
	private JTextArea tADtClase;
	
	private static DefaultTableModel tablaAD;
	private static DefaultTableModel tablaCl;
	
	JScrollPane sPClas;
	JScrollPane sPAct;
	private JLabel lblNewLabel;
	private JLabel lbSocio;
	private JLabel lbProfe;
	private JLabel lbProfeact;
	
	public ConsultaUsuario(IControlador iC) {
		this.iC=iC;
		
		setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
        setClosable(true);
        setTitle("Consulta Usuario");
		
		setBounds(100, 100, 1059, 660);
		getContentPane().setLayout(null);
		
		cBUsuario = new JComboBox();
		cBUsuario.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				cargarDatosUsuario(cBUsuario.getSelectedItem().toString());
				tADtClase.setVisible(false);
			}
		});
		cBUsuario.setBounds(139, 64, 183, 22);
		getContentPane().add(cBUsuario);
		
		tADtClase = new JTextArea();
		tADtClase.setBackground(SystemColor.info);
		tADtClase.setBounds(30, 321, 328, 250);
		getContentPane().add(tADtClase);
		tADtClase.setVisible(false);
		
		txtDatosU = new JTextPane();
		txtDatosU.setBounds(30, 97, 328, 213);
		getContentPane().add(txtDatosU);
		
		sPAct = new JScrollPane();
		sPAct.setEnabled(false);
		sPAct.setBounds(407, 366, 578, 205);
		getContentPane().add(sPAct);
		
		tabDatActD = new JTable();
		tabDatActD.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tADtClase.setVisible(true);
				tabDatClases.clearSelection();
				DatosAct();

			}
		});
		sPAct.setViewportView(tabDatActD);
		
		tablaAD = new DefaultTableModel();	
		tabDatActD.setModel(tablaAD);
		
		tablaAD.addColumn("Nombre Actividad");
		tablaAD.addColumn("Descripcion");
		tablaAD.addColumn("Duracion");
		tablaAD.addColumn("Costo");	
		tablaAD.addColumn("Fecha de Reguistro");

		sPClas = new JScrollPane();
		sPClas.setEnabled(false);
		sPClas.setBounds(407, 94, 578, 205);
		getContentPane().add(sPClas);
		
		tabDatClases = new JTable();
		tabDatClases.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tabDatActD.clearSelection();
				tADtClase.setVisible(true);
				DatosClase();
			}
		});
		sPClas.setViewportView(tabDatClases);
		
		tablaCl = new DefaultTableModel();	
		tabDatClases.setModel(tablaCl);
		
		lblNewLabel = new JLabel("Seleccione Usuario:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(30, 28, 183, 25);
		getContentPane().add(lblNewLabel);
		
		lbSocio = new JLabel("Clases del Socio:");
		lbSocio.setFont(new Font("Tahoma", Font.BOLD, 13));
		lbSocio.setBounds(407, 63, 145, 22);
		getContentPane().add(lbSocio);
		
		lbProfe = new JLabel("Clases asignadas del profesor:");
		lbProfe.setFont(new Font("Tahoma", Font.BOLD, 13));
		lbProfe.setBounds(407, 64, 304, 20);
		getContentPane().add(lbProfe);
		
		lbProfeact = new JLabel("Actividades Deportivas asignadas al profesor:");
		lbProfeact.setFont(new Font("Tahoma", Font.BOLD, 13));
		lbProfeact.setBounds(407, 333, 335, 22);
		getContentPane().add(lbProfeact);
		
		tablaCl.addColumn("Nombre Clase");
		tablaCl.addColumn("Fecha");
		tablaCl.addColumn("Hora de Inicio");
		tablaCl.addColumn("Url");
		tablaCl.addColumn("Fecha de Registro");
		
		lbProfe.setVisible(false);
		lbProfeact.setVisible(false);
		lbSocio.setVisible(false);
		sPAct.setVisible(false);
		sPClas.setVisible(false);
		tabDatClases.setVisible(false);
		tabDatActD.setVisible(false);	

	}
	
	public void iniciarlizarComboBoxes() throws NoexsiteIntDep {
		txtDatosU.setText("");
		DefaultComboBoxModel<String> modelinstitucion;
		tADtClase.setVisible(false);
		tADtClase.setText("");
		try {
			modelinstitucion = new DefaultComboBoxModel<String>(iC.ListarUsuario());
			cBUsuario.setModel(modelinstitucion);
		} catch (NoExisteUsuario e) {
			setVisible(false);
			JOptionPane.showMessageDialog(null, "No puede crear Clase. \n" + e.getMessage()+ "", "Alta Clase", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
	public void cargarDatosUsuario(String nombreU) {

			DtUsuario User= iC.obtenerUsuario(nombreU);
			
			String cadena="Datos Usuario\n";
			if(User instanceof DtSocio) {
				DtSocio s=(DtSocio) User;
				
				
				cadena=cadena +"Nickname: "+User.getNickname()+"\n";
				cadena=cadena +"Nombre: "+User.getNombre()+"\n";
				cadena=cadena +"Apellido: "+User.getApellido()+"\n";
				cadena=cadena +"Fecha de Nacimiento: "+User.getFechaNac().getDate()+"/"+(User.getFechaNac().getMonth()+1)+"/"+(User.getFechaNac().getYear()+1900)+"\n";
				cadena=cadena +"Email: "+User.getEmail()+"\n\n";
				
				lbSocio.setVisible(true);
				sPClas.setVisible(true);
				
				lbProfe.setVisible(false);
				lbProfeact.setVisible(false);
				sPAct.setVisible(false);
				tabDatActD.setVisible(false);
				tabDatClases.setVisible(true);
				try {
					resetTable(tabDatClases, tablaCl);
					listarClaseS(s);
				} catch (NoExisteClase e) {
					// TODO Bloque catch generado automáticamente
					e.printStackTrace();
				}
				
			}
			else {
				DtProfesor p= (DtProfesor)User;
				cadena=cadena +"Nickname: "+User.getNickname()+"\n";
				cadena=cadena +"Nombre: "+User.getNombre()+"\n";
				cadena=cadena +"Apellido: "+User.getApellido()+"\n";
				cadena=cadena +"Fecha de Nacimiento: "+User.getFechaNac().getDate()+"/"+(User.getFechaNac().getMonth()+1)+"/"+(User.getFechaNac().getYear()+1900)+"\n";
				cadena=cadena +"Email: "+User.getEmail()+"\n";
				cadena=cadena +"Descripcion: "+ p.getDescripcion()+"\n";
				cadena=cadena +"Bibliografia: "+ p.getBiografia()+"\n";
				cadena=cadena +"Sitio Web: "+ p.getSitioWeb()+"\n";
				
				lbSocio.setVisible(false);
				
				lbProfe.setVisible(true);
				lbProfeact.setVisible(true);
				sPClas.setVisible(true);
				sPAct.setVisible(true);
				tabDatClases.setVisible(true);
				tabDatActD.setVisible(true);
				try {
					resetTable(tabDatClases, tablaCl);
					listarClaseP(p);
				} catch (NoExisteClase e) {
					// TODO Bloque catch generado automáticamente
					e.printStackTrace();
				}
				try {
					resetTable(tabDatActD, tablaAD);
					listarClaseA(p);
				} catch (SinActDep e) {
					// TODO Bloque catch generado automáticamente
					e.printStackTrace();
				}
				
				
				
			}
		 		 	
			txtDatosU.setText(cadena);				
	}
	public void listarClaseP(DtProfesor profe) throws NoExisteClase {
		
		List<DtClase> listClase = iC.listarDatoClasedeP(profe);
		for (DtClase c : listClase) {
			
			Object[] columna = new Object[5];

			if(c.getHoraInicio().getMinutes()<=9) {
				columna[2] = String.valueOf(c.getHoraInicio().getHours() + ":0" +c.getHoraInicio().getMinutes());
			}
			else {
				columna[2] = String.valueOf(c.getHoraInicio().getHours() + ":" +c.getHoraInicio().getMinutes());
			}
			String horaR;
			if(c.getFechaReg().getMinutes()<=9) {
				horaR=String.valueOf(c.getFechaReg().getHours() + ":0" +c.getFechaReg().getMinutes());
			}
			else {
				horaR=String.valueOf(c.getFechaReg().getHours() + ":" +c.getFechaReg().getMinutes());
			}
			
			columna[1] = String.valueOf(c.getFecha().getDate() +"/"+(c.getFecha().getMonth()+1) + "/" +(c.getFecha().getYear()+1900));	
			columna[0]= c.getNombre();
			columna[3]= c.getUrl();
			columna[4]= c.getFechaReg().getDate()+"/"+(c.getFechaReg().getMonth()+1)+"/"+(c.getFechaReg().getYear()+1900)+"  "+horaR;
			tablaCl.addRow(columna);
		}
		
	}
	
	public void listarClaseS(DtSocio socio) throws NoExisteClase {
		List<DtClase> listClase = iC.listarDatoClasedeS(socio);
		for (DtClase c : listClase) {
			
			Object[] columna = new Object[5];

			if(c.getHoraInicio().getMinutes()<=9) {
				columna[2] = String.valueOf(c.getHoraInicio().getHours() + ":0" +c.getHoraInicio().getMinutes());
			}
			else {
				columna[2] = String.valueOf(c.getHoraInicio().getHours() + ":" +c.getHoraInicio().getMinutes());
			}
			String horaR;
			if(c.getFechaReg().getMinutes()<=9) {
				horaR=String.valueOf(c.getFechaReg().getHours() + ":0" +c.getFechaReg().getMinutes());
			}
			else {
				horaR=String.valueOf(c.getFechaReg().getHours() + ":" +c.getFechaReg().getMinutes());
			}
			
			columna[1] = String.valueOf(c.getFecha().getDate() +"/"+(c.getFecha().getMonth()+1) + "/" +(c.getFecha().getYear()+1900));	
			columna[0]= c.getNombre();
			columna[3]= c.getUrl();
			columna[4]= c.getFechaReg().getDate()+"/"+(c.getFechaReg().getMonth()+1)+"/"+(c.getFechaReg().getYear()+1900)+"  "+horaR;
			tablaCl.addRow(columna);
		}
		
	}
	
	public void listarClaseA(DtProfesor profe) throws SinActDep {
		List<DtActDeportiva> listClase;
		try {
			listClase = iC.listarDatoActdepP(profe);
			for (DtActDeportiva c : listClase) {
				
				Object[] columna = new Object[5];
	
				String horaR;
				if(c.getFecha().getMinutes()<=9) {
					horaR=String.valueOf(c.getFecha().getHours() + ":0" +c.getFecha().getMinutes());
				}
				else {
					horaR=String.valueOf(c.getFecha().getHours() + ":" +c.getFecha().getMinutes());
				}
				columna[0]= c.getNombre();
				columna[1] =c.getDescripcion();	
				columna[2]= c.getDuracion() + " mins";
				columna[3]= c.getCosto()+"$";
				columna[4]= c.getFecha().getDate()+"/"+(c.getFecha().getMonth()+1)+"/"+(c.getFecha().getYear()+1900)+"  "+horaR;
				tablaAD.addRow(columna);
			}
		
		} catch (SinActDep e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
		
		
	}
	
	public void DatosClase() {
		String cadena="Clase:\n";
		cadena=cadena +"Nombre: "+ tabDatClases.getValueAt(tabDatClases.getSelectedRow(), 0).toString() +"\n";
		cadena=cadena +"Fecha de clase: "+tabDatClases.getValueAt(tabDatClases.getSelectedRow(), 1).toString()+"\n";
		cadena=cadena +"Hora de inicio: "+tabDatClases.getValueAt(tabDatClases.getSelectedRow(), 2).toString()+"\n";
		cadena=cadena +"Url: "+tabDatClases.getValueAt(tabDatClases.getSelectedRow(), 3).toString()+"\n";
		cadena=cadena +"Fecha de Reguistro: "+tabDatClases.getValueAt(tabDatClases.getSelectedRow(), 4).toString()+"\n\n";

		tADtClase.setText(cadena);
	}
	
	public void DatosAct() {
		String cadena="Actividad Deportiva:\n";
		cadena=cadena +"Nombre: "+ tabDatActD.getValueAt(tabDatActD.getSelectedRow(), 0).toString() +"\n";
		cadena=cadena +"Descripción: "+tabDatActD.getValueAt(tabDatActD.getSelectedRow(), 1).toString()+"\n";
		cadena=cadena +"Duración: "+tabDatActD.getValueAt(tabDatActD.getSelectedRow(), 2).toString()+ " min"+"\n";
		cadena=cadena +"Costo: $"+tabDatClases.getValueAt(tabDatActD.getSelectedRow(), 3).toString()+"\n";
		cadena=cadena +"Fecha: "+tabDatActD.getValueAt(tabDatActD.getSelectedRow(), 4).toString()+"\n\n";

		tADtClase.setText(cadena);
	}
	
	private void resetTable(JTable tabla, DefaultTableModel modelo){
		for (int i = 0; i < tabla.getRowCount(); i++) {
			modelo.removeRow(i);
			i-=1;
		}
	}
	
	
}

