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
				String operacija = odKlijenta.readLine(); //operacija
				if(operacija.equals("KRAJ")){
					break;
				}

				odServera.println("server kaze hajdeee ");

				ServerSocket podaciSoket = new ServerSocket(18413);
				Socket soketZaPodatke = podaciSoket.accept();

				BufferedReader dataOdKlijenta = new BufferedReader(new InputStreamReader(soketZaPodatke.getInputStream()));
				PrintStream dataOdServera = new PrintStream(soketZaPodatke.getOutputStream());
				String izborKlijenta = dataOdKlijenta.readLine();


				String [] niz = izborKlijenta.split("##");
				double rezultat = Double.parseDouble(niz[0]);//rezultat
				for (int i = 1; i < niz.length; i++) {
					
					double sadasnji = Double.parseDouble(niz[i]);
					
					if(operacija.equals("1")){
						rezultat = rezultat + sadasnji;
					}
					if(operacija.equals("2")){
						rezultat = rezultat - sadasnji;
					}
					if(operacija.equals("3")){
						rezultat = rezultat * sadasnji;
					}
					if(operacija.equals("4")){
						rezultat = rezultat / sadasnji;
					}
					
				}








				dataOdServera.println(rezultat);
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
