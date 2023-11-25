package HilosYSocket;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


public class Cliente {
	public static void main(String[] args) {
		
		try {
				Socket socket = new Socket("localhost", 5001);
				//creamos un buffer de escritura para poder escribir en el servidor
				PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
				//generamos un buffer de lectura para poder leer lo que nos indica el servidor
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				
				while(true) {
					String inputline = in.readLine();
					System.out.println(inputline);
					inputline = in.readLine();
					System.out.println(inputline);
					inputline = in.readLine();
					System.out.println(inputline);
					inputline = in.readLine();
					System.out.println(inputline);
					inputline = in.readLine();
					System.out.println(inputline);
					inputline = in.readLine();
					System.out.println(inputline);
					inputline = in.readLine();
					System.out.println(inputline);
					Scanner sc = new Scanner(System.in);
					
					String response = null;
					while(true) {
						if(response != null) {
							inputline = in.readLine();
							System.out.println(inputline);
							inputline = in.readLine();
							System.out.println(inputline);
						}
						response = sc.nextLine();
						out.println(response);
					}
				}
			}
			catch(IOException e){
				e.printStackTrace();
			}
		}
}