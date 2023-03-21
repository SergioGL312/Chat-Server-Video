package paquete;

import javax.swing.*;

import java.awt.*;
import java.io.*;
import java.net.*;

public class Servidor {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		MarcoServidor mimarco = new MarcoServidor();

		mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}

class MarcoServidor extends JFrame implements Runnable { // Para que este permanentemente a la escucha

	public MarcoServidor() {

		setBounds(1200, 300, 280, 350);

		JPanel milamina = new JPanel();

		milamina.setLayout(new BorderLayout());

		areatexto = new JTextArea();

		milamina.add(areatexto, BorderLayout.CENTER);

		add(milamina);

		setVisible(true);

		// Se crea el hilo
		Thread miHilo = new Thread(MarcoServidor.this); // Es lo mismo que this

		miHilo.start();

	}

	@Override
	public void run() { // El codigo que se encargara de estar a la escucha
		// TODO Auto-generated method stub

		// Prueba
		// System.out.println("Esto");
		
		try {
			ServerSocket servidor = new ServerSocket(1090); // Se abre puerta 9999 y esta a la escucha
			
			while (true) {
				Socket miSocket = servidor.accept(); // Que acepte las conexiones
				
				// Crear un flujo de entrada
				DataInputStream flujo_entrada = new DataInputStream(miSocket.getInputStream()); // por que socket viaja la info
				
				String mensaje_texto = flujo_entrada.readUTF();
				
				areatexto.append("\n" + mensaje_texto);
				
				miSocket.close();
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private JTextArea areatexto;
}
