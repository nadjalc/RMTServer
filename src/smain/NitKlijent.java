package smain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class NitKlijent implements  Runnable{

	private Socket soketZaKomunikaciju;

	public Socket getSoketZaKomunikaciju() {
		return soketZaKomunikaciju;
	}
	public void setSoketZaKomunikaciju(Socket soketZaKomunikaciju) {
		this.soketZaKomunikaciju = soketZaKomunikaciju;
	}
	@Override
	public void run() {
		BufferedReader odKlijenta;
		try {
			odKlijenta = new BufferedReader(new InputStreamReader(soketZaKomunikaciju.getInputStream()));
			PrintStream odServera = new PrintStream(soketZaKomunikaciju.getOutputStream());

			while (true) {


				System.out.println("Server ocekuje poruku!");
				String izbor = odKlijenta.readLine(); //operacija
				if(izbor.equals("KRAJ")){
					break;
				}

				odServera.println("server kaze hajdeee ");

				ServerSocket podaciSoket = new ServerSocket(18413);
				Socket soketZaPodatke = podaciSoket.accept();

				BufferedReader dataOdKlijenta = new BufferedReader(new InputStreamReader(soketZaPodatke.getInputStream()));
				PrintStream dataOdServera = new PrintStream(soketZaPodatke.getOutputStream());
				String izborKlijenta = dataOdKlijenta.readLine();


				String [] niz = izborKlijenta.split("##");
				double izborServera = 0;//rezultat
				String a1 = niz[0];
				String b1 = niz[1];
				double a = Double.parseDouble(a1);
				double b = Double.parseDouble(b1);


				if(izbor.equals("1")){
					izborServera = a+b;
				}
				if(izbor.equals("2")){
					izborServera = a-b;
				}
				if(izbor.equals("3")){
					izborServera = a*b;
				}
				if(izbor.equals("4")){
					izborServera = a/b;
				}






				dataOdServera.println(izborServera);
				podaciSoket.close();
			}
			soketZaKomunikaciju.close();
		} catch (SocketException d){
			System.out.println("Klijent se iskljucio");
		}catch (IOException e) {
			e.printStackTrace();
		}

	}



}
