package LiteCrm;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;

public class Menu {

	public void runAplication() {
	
	Scanner sc = new Scanner(System.in);
	String menu = null;
	
	System.out.println("Select function"
			+ "\n" + "1-Add Client"
			+ "\n" + "2-Show cuhtomers"
			+ "\n" + "3-Add protocol hours"
			+ "\n" + "4-Find name company"
			+ "\n" + "5-Sort client list"
			+ "\n" + "6-Exit");
	
	menu = sc.nextLine();
	
	try {
		
		int x = Integer.parseUnsignedInt(menu);
		System.out.println(x);
		
			if(x == 1 || x == 2 || x == 3 || x==4 || x==5 || x==6) {
		switch (x) {
		case 1:
			addClient();
			break;
		case 2:
			getData();
			break;
		case 3:
			protocolMaker();
			break;
		case 4:
			String nameComapny=null;
			getNameCompany(nameComapny);
			break;
		case 5:
			sortListByName();
			break;
		case 6:
			finishProgram();
			break;
		}
		}else {
			System.out.println("Nie ma takiej funkcji");
		}
		
	}catch (Exception e) {
		e.printStackTrace();
	runAplication();
	}finally {
		sc.close();
	}
	
	}
	
	public static void finishProgram() {
		System.out.println("Adios");
	}
	
	public static void getData() throws IOException {
		
		Klient client = new Klient();
		
		client.bufferedReader();
		
		Menu menu = new Menu();
		menu.backMenu();
		
	}
	
	public static void getData2() throws IOException {
		
		Klient client = new Klient();
		
		client.bufferedReader();
		
		
	}
	public static void addClient() {
		String nameCompany = null;
		String adress = null;
		String phoneNumber = null;
		String emailAdress = null;
		
		
		
		Klient m = new Klient();
		Scanner sc = new Scanner(System.in);


		System.out.println("Podaj nazwê firmy");
		nameCompany = sc.nextLine();

		System.out.println("Podaj adres firmy");
		adress = sc.nextLine();

		System.out.println("Podaj numer kontaktowy");
		phoneNumber = sc.nextLine();


		System.out.println("Podaj adres email");
		emailAdress = sc.nextLine();
		Klient k = new Klient();
		k.addClient(nameCompany+ ";" + adress +";" + phoneNumber + ";"+ emailAdress);
		k.fileWriterSave();
		
		Menu menu = new Menu();
		menu.backMenu();
		
	}
	
	public  void backMenu() {
		System.out.println("What you want to do ? 1- Quit, 2-Back to menu");
		String menuNumber = null;
		Scanner sc = new Scanner(System.in);
		Menu m = new Menu();
		
		try {
		menuNumber = sc.nextLine();
		int x = Integer.parseUnsignedInt(menuNumber);
		if ( x == 1 ) {
			finishProgram();
		}else if ( x == 2) {
			m.runAplication();
		}else {
			System.out.println("Nie ma takiej funkcji, prze¿ucam Cie do menu");
			m.runAplication();
		}
		
		} catch (Exception e) {
			System.out.println("niepoprawna liczba");
		}finally {
			
			sc.close();
		}
	}

	public static void addBusinessMeeting() throws IOException {
		getData();
		System.out.println("Select Client Number: Example n+1");
		Klient client = new Klient();
		client.takeClientNumber();
		
		Menu menu = new Menu();
		menu.backMenu();
	}

	public static void getNameCompany(String getNameComapny) throws IOException {
		CollectionsOperation k = new CollectionsOperation();
		  
		  System.out.println("Name Comapny:");
		  Scanner sc = new Scanner(System.in);
		  String str = sc.nextLine();
		  k.findNameCompany(str);	
		  
		  Menu menu = new Menu();
			menu.backMenu();
	}
	
	public static void sortListByName() {
		CollectionsOperation k = new CollectionsOperation();
		k.runSortList();
		
		Menu menu = new Menu();
		menu.backMenu();
	}

	public static void protocolMaker() throws IOException {
		Klient k = new Klient();
		String strK = null ;
		String date = null;
		String dateD = null;
		String today = null;
		String menuNumberParse = null;
		String aP=null;
		int hTW = 0;
		int x = 0;
		
		
		System.out.println("Select Client Number: Example n+1");
			
			getData2();
			strK = k.takeClientNumber();
			System.out.println(strK);
			
			Scanner sc = new Scanner(System.in);
			Menu menu = new Menu();
			
		System.out.println("1-Actualy Date, 2-Insert Date 3-Go backwards");
		try {	
		
		menuNumberParse = sc.nextLine();
		 x = Integer.parseInt(menuNumberParse);
		}catch (Exception ex) {
			System.out.println("niepoprawna liczba");
		}
		
		if (x == 1) {
			
			
			ProtcolBodyHours pBH = new ProtcolBodyHours();
			
			today = pBH.insertDateLocal();
			
			hTW = pBH.hoursTimeWork();
			
			aP = pBH.actionsPerformed();
			
			ProtcolBodyHours newProtocolBody = new ProtcolBodyHours();
			newProtocolBody.protocolObject(strK, hTW, today, aP);
			newProtocolBody.showProtocolList();
			newProtocolBody.fileWriterSaveProtocol();
			System.out.println("------------------");
			
		
			menu.backMenu();
		} else if( x == 2) {
			System.out.println("Insert Date: yyy-MM-dd");
			
			
			
			ProtcolBodyHours pBH = new ProtcolBodyHours();
			
			dateD = pBH.insertDate();
			
			hTW = pBH.hoursTimeWork();
			
			aP = pBH.actionsPerformed();
			
			ProtcolBodyHours newProtocolBody = new ProtcolBodyHours();
			newProtocolBody.protocolObject(strK, hTW, dateD, aP);
			newProtocolBody.showProtocolList();
			newProtocolBody.fileWriterSaveProtocol();
			System.out.println("------------------");
			
			menu.backMenu();
								
		} else if( x==3 ) {
			
			menu.runAplication();
		} else {
			System.out.println("Nie ma takiej funkcji /n"+
													"/n"+
													"/n");
			protocolMaker();
		}
		
		
	}
	

	
}
