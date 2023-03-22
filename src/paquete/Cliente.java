package paquete;

import javax.swing.*;
import java.awt.event.*;
import java.io.*;
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
		
		nick = new JTextField(5);
		
		add(nick);

		JLabel texto = new JLabel(" CHAT ");

		add(texto);
		
		ip = new JTextField(8);
		
		add(ip);
		
		campoChat = new JTextArea(12, 20);
		
		add(campoChat);

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
				Socket miSocket = new Socket("localhost", 9999); // Puente virtual

				// Se empaquetan los datos en un obj 
				PaqueteEnvio datos = new PaqueteEnvio();
				
				datos.setNick(nick.getText());
				
				datos.setIp(ip.getText());
				
				datos.setMensje(campo1.getText());
				
				// Flujo para enviar el obj datos por la red a servidor
				ObjectOutputStream paquete_datos = new ObjectOutputStream(miSocket.getOutputStream());
				
				paquete_datos.writeObject(datos);
				
				miSocket.close();
				
				/*
				// Construye el fLujo de datos para poder trasladar info de cliente a server
				DataOutputStream flujo_salida = new DataOutputStream(miSocket.getOutputStream()); // circula por el
																									// socket

				flujo_salida.writeUTF(campo1.getText());

				flujo_salida.close();*/

			} catch (IOException e1) {
				// TODO Auto-generated catch block
				System.out.println(e1.getMessage() + " Aqui");
			}

		}

	}

	private JTextField campo1, nick, ip;
	
	private JTextArea campoChat;

	private JButton miboton;

}

class PaqueteEnvio implements Serializable {
	
	private String nick, ip, mensje;

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getMensje() {
		return mensje;
	}

	public void setMensje(String mensje) {
		this.mensje = mensje;
	}
	
}