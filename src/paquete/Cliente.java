package paquete;

import javax.swing.*;
import java.awt.event.*;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;

public class Cliente {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		MarcoCliente mimarco = new MarcoCliente();

		mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}

class MarcoCliente extends JFrame {

	public MarcoCliente() {

		setBounds(600, 300, 280, 350);

		LaminaMarcoCliente milamina = new LaminaMarcoCliente();

		add(milamina);

		setVisible(true);
	}

}

class LaminaMarcoCliente extends JPanel {

	public LaminaMarcoCliente() {

		JLabel texto = new JLabel("CLIENTE");

		add(texto);

		campo1 = new JTextField(20);

		add(campo1);

		miboton = new JButton("Enviar");

		EnviarTexto miEvento = new EnviarTexto();

		miboton.addActionListener(miEvento);

		add(miboton);

	}

	private class EnviarTexto implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			// System.out.println(campo1.getText());

			try {
				// Se construye el socket de conexion cliente a servidor con ip y port para
				// comunicarse
				Socket miSocket = new Socket("192.168.137.1", 1090); // Puente virtual

				// Construye el fLujo de datos para poder trasladar info de cliente a server
				DataOutputStream flujo_salida = new DataOutputStream(miSocket.getOutputStream()); // circula por el
																									// socket

				flujo_salida.writeUTF(campo1.getText());

				flujo_salida.close();

			} catch (IOException e1) {
				// TODO Auto-generated catch block
				System.out.println(e1.getMessage() + " Aqui");
			}

		}

	}

	private JTextField campo1;

	private JButton miboton;

}