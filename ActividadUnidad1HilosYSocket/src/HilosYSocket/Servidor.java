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
			int numeroClientes = 0;
			
			while(true) {
				//ya esta a la escucha esperando a que se nos conecte un cliente
				Socket clienteSocket2 = serverSocket.accept();
				//cuado el cliete se ha conectado se salta a la siguiene linea
				System.out.println("Cliente conectado desde " + clienteSocket2.getInetAddress());
				
				numeroClientes++;
				
				//ahora creamos un hilo para soltar a este cliente en ese hilo para volver a dejar en escuchar al servidor y poder atender a mas de un cliente a la vez
				new Thread(new Servidor(clienteSocket2), "Cliente " + numeroClientes).start();
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
			
			out.println("Bienvenido al menú del calendario de eventos:\n1.Ver eventos del día \n2.Agregar nuevo evento \n3.Ver eventos del mes \n4.Editar detalles de un evento \n5.Eliminar evento");
			out.println("Seleccione una de las opciones con 1,2,3,4 o 5, en caso de querer salir pulse 'q'");
			
			
			//nos quedamos a la espera de leer lo que nos dice
			while((inputLine = in.readLine()) != null) {
				String response  = inputLine;
				System.out.println("El cliente ha elegido la opción " + inputLine);
				switch (response) {
					case "1":
						out.println("Estos son los eventos del día......");
						out.println("Elije otra opción o cierre lo conexion con 'q'");
						break;
					case "2":
						out.println("En este apartado se puede agregar un nuevo evento al calendario....");
						out.println("Elije otra opción o cierre lo conexion con 'q'");
						break;
					case "3":
						out.println("Estos son los eventos del mes....");
						out.println("Elije otra opción o cierre lo conexion con 'q'");
						break;
					case "4":
						out.println("¿De qué evento quieres editar sus detalles?...");
						out.println("Elije otra opción o cierre lo conexion con 'q'");
						break;
					case "5":
						out.println("¿Que evento quiere eliminar?....");
						out.println("Elije otra opción o cierre lo conexion con 'q'");
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