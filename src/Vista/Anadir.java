package Vista;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Controlador.Controlador;
import Modelo.Mascota;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;

public class Anadir extends JFrame {
	private Controlador control;
	private JTextField txtId;
	private JTextField txtNombre;
	private JTextField txtEspecie;

	private int id;

	public Anadir(Controlador control) {
		this.control = control;
		setBounds(100, 100, 250, 200);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);

		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(22, 26, 46, 14);
		getContentPane().add(lblId);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(22, 51, 46, 14);
		getContentPane().add(lblNombre);

		JLabel lblEspecie = new JLabel("Especie:");
		lblEspecie.setBounds(22, 76, 46, 14);
		getContentPane().add(lblEspecie);

		txtId = new JTextField();
		txtId.setBounds(111, 23, 86, 20);
		getContentPane().add(txtId);
		txtId.setColumns(10);

		txtNombre = new JTextField();
		txtNombre.setBounds(111, 48, 86, 20);
		getContentPane().add(txtNombre);
		txtNombre.setColumns(10);

		txtEspecie = new JTextField();
		txtEspecie.setBounds(111, 73, 86, 20);
		getContentPane().add(txtEspecie);
		txtEspecie.setColumns(10);

		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(10, 127, 89, 23);
		getContentPane().add(btnGuardar);
		btnGuardar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				guardar();

			}
		});

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(135, 127, 89, 23);
		getContentPane().add(btnCancelar);
		btnCancelar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cerrar();

			}
		});
		setVisible(true);
	}

	public Anadir(Controlador control, Integer id) {
		this(control);
		this.id = id;
		txtId.setText(id.toString());
		txtId.setEnabled(false);

	}

	private void guardar() {

		control.editarDatos(new Mascota(Integer.parseInt(txtId.getText()), txtNombre.getText(), txtEspecie.getText()));

		this.cerrar();
	}

	private void cerrar() {
		this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}
}
