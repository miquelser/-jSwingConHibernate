package presentacion;


import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Point;

import datatypes.DtInstitucionD;
import datatypes.DtUsuario;
import excepciones.InstitucionRepetidaExcepcion;
import excepciones.NoexsiteIntDep;
import interfaces.Fabrica;
import interfaces.IControlador;

import javax.swing.JInternalFrame;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.awt.event.ActionEvent;

public class ModificarInstitucion extends JInternalFrame {
	
	private static final long serialVersionUID = 1L;
	private JTextField txtUrl;
	private JTextField txtDes;
	private JTextField txtNom;

	private IControlador iC;
	private JScrollPane scrollPane;
	private static JTable tableAd;
	
	private static DefaultTableModel tabAD;

	public ModificarInstitucion(IControlador iC) {
		this.iC=iC;
		setBounds(100, 100, 828, 444);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Modificar Institucion Deportiva");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(10, 11, 364, 42);
		getContentPane().add(lblNewLabel);
		
		JLabel lblUrl = new JLabel("Url:");
		lblUrl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUrl.setBounds(45, 121, 98, 12);
		getContentPane().add(lblUrl);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNombre.setBounds(45, 58, 98, 14);
		getContentPane().add(lblNombre);
		
		JLabel lblDescripcion = new JLabel("Descripcion:");
		lblDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDescripcion.setBounds(45, 89, 98, 14);
		getContentPane().add(lblDescripcion);
		
		txtNom = new JTextField();
		txtNom.setEditable(false);
		txtNom.setBounds(126, 57, 168, 20);
		getContentPane().add(txtNom);
		txtNom.setColumns(10);
		
		txtDes = new JTextField();
		txtDes.setBounds(126, 86, 262, 20);
		getContentPane().add(txtDes);
		txtDes.setColumns(10);
		
		txtUrl = new JTextField();
		txtUrl.setBounds(126, 119, 169, 20);
		getContentPane().add(txtUrl);
		txtUrl.setColumns(10);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnCancelar.setBounds(572, 377, 89, 23);
		getContentPane().add(btnCancelar);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
		    	
		    	String des = txtDes.getText();
		    	String url = txtUrl.getText();
			
		    	//DtInstitucionD DtInstitucion=new DtInstitucionD(nom, des,url, null, null);
		    	
				if(txtNom.getText().isEmpty() || txtDes.getText().isEmpty() || txtUrl.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "No puede dejar campos basios para ingresar una Institución");
				}else {
					iC.modificarInstitucion(txtNom.getText(), des,url);
					
					JOptionPane.showMessageDialog(null, "La institución "+txtNom.getText()+" se ha Modificado con exito");
					limpiarCampos();
					completarTabla();
				}
			}
		});
		setResizable(true);
	    setIconifiable(true);
	    setMaximizable(true);
	    //setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
	    setClosable(true);
	    setTitle("Modificar Institución");
		btnAceptar.setBounds(700, 377, 89, 23);
		getContentPane().add(btnAceptar);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 159, 793, 207);
		getContentPane().add(scrollPane);
		
		tableAd = new JTable();
		tableAd.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				JTable tabla = (JTable) e.getSource();
				Point point = e.getPoint();
				int row = tabla.rowAtPoint(point);
				if(e.getClickCount() == 1) {
					txtNom.setText(tableAd.getValueAt(tableAd.getSelectedRow(), 0).toString());
					txtDes.setText(tableAd.getValueAt(tableAd.getSelectedRow(), 1).toString());
					txtUrl.setText(tableAd.getValueAt(tableAd.getSelectedRow(), 2).toString());				
				}
			}
		});
		scrollPane.setViewportView(tableAd);
		
		tabAD = new DefaultTableModel();
		tableAd.setModel(tabAD);
		
		tabAD.addColumn("Nombre Intitucion");
		tabAD.addColumn("Descripcion");
		tabAD.addColumn("Url");
	}
	
	public void completarTabla() {
		Resettable(tableAd,tabAD);
		
    	List<DtInstitucionD> Ints;
		try {
			Ints = iC.listadoDtIntiDtInstitucion();
    		for (DtInstitucionD I : Ints) {
    			Object[] columna = new Object[3];
    			columna[0]= I.getNombre();
    			columna[1]= I.getDescripcion();
    			columna[2]= I.getUrl();
    			
    			tabAD.addRow(columna);
    		}
		} catch (NoexsiteIntDep e) {
			e.printStackTrace();
		}
    	

	}
	
	private static void Resettable(JTable tabla, DefaultTableModel modelo){
		for (int i = 0; i < tabla.getRowCount(); i++) {
			modelo.removeRow(i);
			i-=1;
		}
	}
	public void limpiarCampos() {
		txtNom.setText("");
		txtDes.setText("");
		txtUrl.setText("");		
	}
}
