package HilosYSocket;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor implements Runnable{
	
	private Socket clienteSocket;
	
	//constructor
	public Servidor(Socket clienteSocket) {
		this.clienteSocket = clienteSocket;
	}
	
	public static void main(String[] args) {
		
		try {
			//creacion de socket
			ServerSocket serverSocket = new ServerSocket(5001); 
			System.out.println("Chat iniciado, esperando a cliente.....");
			int i = 0;
			
			while(true) {
				//ya esta a la escucha esperando a que se nos conecte un cliente
				Socket clienteSocket2 = serverSocket.accept();
				//cuado el cliete se ha conectado se salta a la siguiene linea
				System.out.println("Cliente conectado desde " + clienteSocket2.getInetAddress());
				
				i++;
				
				//ahora creamos un hilo para soltar a este cliente en ese hilo para volver a dejar en escuchar al servidor y poder atender a mas de un cliente a la vez
				new Thread(new Servidor(clienteSocket2), "Cliente " + i).start();
				//volvemos al inico del while y estamos esperando a un nuevo cliente
			}
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void run() {
		String inputLine;
		try {
			System.out.println("Abierto hilo");
			//creamos un buffer de escritura para poder escribir en el cliente
			PrintWriter out = new PrintWriter(clienteSocket.getOutputStream(), true);
			//generamos un buffer de lectura para poder leer lo que nos indica el cliente
			BufferedReader in = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()));
			
			out.println("Menú del servidor:\n1.Pizzería \n2.Hamburguesería \n3.HotDog \n4.FoodTruck \n5.Vegetariana");
			out.println("Dime algo");
			
			
			//nos quedamos a la espera de leer lo que nos dice
			while((inputLine = in.readLine()) != null) {
				String response  = inputLine;
				System.out.println("Llego esto: " + inputLine);
				switch (response) {
					case "1":
						out.println("Ha entrado en el menú de la pizzería");
						out.println("Elije otra opción o cierra lo conexion");
						break;
					case "2":
						out.println("Ha entrado en el menú de la hamburguesería");
						out.println("Elije otra opción o cierra lo conexion");
						break;
					case "3":
						out.println("Ha entrado en el menú de la hotDog");
						out.println("Elije otra opción o cierra lo conexion");
						break;
					case "4":
						out.println("Ha entrado en el menú de la Foodtruck");
						out.println("Elije otra opción o cierra lo conexion");
						break;
					case "5":
						out.println("Ha entrado en el menú de la Vegetariana");
						out.println("Elije otra opción o cierra lo conexion");
						break;
					case "q":
						//para cortar la conexion con el servidor nos salimos cuando llegue "q"
						out.println("Se ha salido del cliente");
						out.println("Gracias");
						clienteSocket.close();
						break;
					default: 
						out.println("Opción incorrecta, elije otra opción del menú o si quiere salir pulse 'q'");
				}
			}
			
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
}