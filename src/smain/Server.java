package smain;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) {
		try {

			System.out.println("Server pokrenut!");
			@SuppressWarnings("resource")
			ServerSocket osluskujuciSoket = new ServerSocket(1908);
			while (true) {
				Socket soketZaKomunikaciju = osluskujuciSoket.accept();
				NitKlijent noviKorisnik = new NitKlijent();
				noviKorisnik.setSoketZaKomunikaciju(soketZaKomunikaciju);

				Thread novaNit = new Thread(noviKorisnik);
				novaNit.start();
				
			}
			
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}

}
