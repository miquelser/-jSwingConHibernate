package presentacion;


import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import datatypes.DtClase;
import datatypes.DtSocio;
import datatypes.DtUsuario;
import excepciones.UsuarioRepetidoExcepcion;
import interfaces.Fabrica;
import interfaces.IControlador;


import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Point;
import java.awt.SystemColor;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;

public class ModificarUsuario extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tableUsuarios;
	private JTextField txtNickname;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtEmail;

	
	private static DefaultTableModel model;
	private IControlador iC;
	private JDateChooser dateChooser;


	public ModificarUsuario(IControlador iC) {
		this.iC=iC;
		
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 239, 764, 211);
		contentPane.add(scrollPane);
		
		tableUsuarios = new JTable();
		
		model = new DefaultTableModel();
		tableUsuarios.setModel(model);
		
		model.addColumn("Nickname");
		model.addColumn("Nombre");
		model.addColumn("Apellido");
		model.addColumn("Email");
		model.addColumn("Fecha Nacimiento");
		
		tableUsuarios.getColumnModel().getColumn(0).setPreferredWidth(112);
		tableUsuarios.getColumnModel().getColumn(1).setPreferredWidth(154);
		tableUsuarios.getColumnModel().getColumn(2).setPreferredWidth(169);
		tableUsuarios.getColumnModel().getColumn(3).setPreferredWidth(188);
		tableUsuarios.getColumnModel().getColumn(4).setPreferredWidth(185);
		
		DefaultTableCellRenderer Alinear = new DefaultTableCellRenderer();
		Alinear.setHorizontalAlignment(SwingConstants.CENTER);
		tableUsuarios.getColumnModel().getColumn(4).setCellRenderer(Alinear);
		
		scrollPane.setViewportView(tableUsuarios);
		
		
		txtNickname = new JTextField();
		txtNickname.setEditable(false);
		txtNickname.setBounds(148, 66, 118, 20);
		contentPane.add(txtNickname);
		txtNickname.setColumns(10);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(148, 106, 118, 20);
		contentPane.add(txtNombre);
		
		txtApellido = new JTextField();
		txtApellido.setColumns(10);
		txtApellido.setBounds(148, 151, 118, 20);
		contentPane.add(txtApellido);
		
		txtEmail = new JTextField();
		txtEmail.setEditable(false);
		txtEmail.setColumns(10);
		txtEmail.setBounds(460, 66, 236, 20);
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
		
		JLabel lblFechaNacimiento = new JLabel("Fecha Nacimiento");
		lblFechaNacimiento.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFechaNacimiento.setBounds(339, 107, 157, 14);
		contentPane.add(lblFechaNacimiento);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEmail.setBounds(335, 67, 85, 14);
		contentPane.add(lblEmail);
		
		//Actualizar los datos.
		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {    				
		    	String nick = txtNickname.getText();
		    	String nombre = txtNombre.getText();
		    	String apellido = txtApellido.getText();

		    	Date fecha = dateChooser.getDate();
		    	fecha.setMonth(fecha.getMonth());//como esta guardad en string la pasamos a int
		    	
		    	iC.modificarUsuario(nick, nombre, apellido, fecha);
		    	
		    	JOptionPane.showMessageDialog(null, "El usuario "+nick+" se actualizo correctamente");
			
				limpiarCampos();
				//limpiarTabla();
				completarTabla();
			}
		});
		btnActualizar.setBounds(574, 179, 89, 38);
		contentPane.add(btnActualizar);
		
		JLabel lblNewLabel_1 = new JLabel("Actualizar Usuarios");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1.setBounds(260, 22, 236, 14);
		contentPane.add(lblNewLabel_1);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarCampos();
				limpiarTabla();
				setVisible(false);
				
			}
		});
		btnCancelar.setBounds(440, 179, 89, 38);
		contentPane.add(btnCancelar);
		
		dateChooser = new JDateChooser();
		dateChooser.getCalendarButton().setToolTipText("ghfhg");
		dateChooser.setToolTipText("");
		dateChooser.setBounds(475, 106, 145, 19);
		contentPane.add(dateChooser);
		
		
		
		tableUsuarios.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent Mouse_evt) {
				JTable tabla = (JTable) Mouse_evt.getSource();
				Point point = Mouse_evt.getPoint();
				int row = tabla.rowAtPoint(point);
				if(Mouse_evt.getClickCount() == 1) {
					txtNickname.setText(tableUsuarios.getValueAt(tableUsuarios.getSelectedRow(), 0).toString());
					txtNombre.setText(tableUsuarios.getValueAt(tableUsuarios.getSelectedRow(), 1).toString());
					txtApellido.setText(tableUsuarios.getValueAt(tableUsuarios.getSelectedRow(), 2).toString());
					txtEmail.setText(tableUsuarios.getValueAt(tableUsuarios.getSelectedRow(), 3).toString());								
					String fecha=tableUsuarios.getValueAt(tableUsuarios.getSelectedRow(), 4).toString();
					
					SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
					Date d;
					try {
						d = formatter.parse(fecha);
						dateChooser.setDate(d);

					} catch (ParseException e) {
						// TODO Bloque catch generado automáticamente
						e.printStackTrace();
					}
				}
				
			}
		});
	}
	public void completarTabla() {
		limpiarTabla();
		
    	List<DtUsuario> usuario = iC.ListarDtUsuario();
    	if(usuario.isEmpty()) {
    		tablaLimpia();
    	}
    	else {
    		for (DtUsuario dtUsuario : usuario) {
    			Object[] columna = new Object[5];
    			
    			columna[0]= dtUsuario.getNickname();
    			columna[1]= dtUsuario.getNombre();
    			columna[2]= dtUsuario.getApellido();
    			columna[3]= dtUsuario.getEmail();
    			columna[4]= dtUsuario.getFechaNac().getDate()+"-"+(dtUsuario.getFechaNac().getMonth()+1)+"-"+(dtUsuario.getFechaNac().getYear()+1900);
    			
    			model.addRow(columna);
    		}
    	}

	}
	public void tablaLimpia() {
		Object[] columna = new Object[5];
		columna[0]= "";
		columna[1]= "";
		columna[2]= "";
		columna[3]= "";
		columna[4]= "";


	}
	public void limpiarTabla(){
		int fila = tableUsuarios.getRowCount();
		for(int i = fila-1; i>=0; i--) {
			model.removeRow(i);
		}
	
	}	
	

	
	public void limpiarCampos() {
		txtNickname.setText("");
		txtNombre.setText("");
		txtApellido.setText("");
		txtEmail.setText("");
		dateChooser.setDate(null);
		
	}
}
