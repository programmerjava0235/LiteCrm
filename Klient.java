package LiteCrm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Klient {
	
	private String name;
	private String adress;
	private String contactNumber;
	private String adressEmail;
	Klient t;
	private  List<Klient> clientList = new ArrayList<Klient>();
	private static final String fileName = "klienci.txt";
	OutputStreamWriter outputStreamWrite = null;
	OutputStream       outputStream = null; 
	BufferedReader bf = null;
	
	Klient(){
		
	}
	
	
	Klient(String name, String adress, String contactNumber, String adressEmail){
		this.adress = adress;
		this.adressEmail = adressEmail;
		this.name = name;
		this.contactNumber = contactNumber;
		
	}

	public String getName() {
		return name;
	}


	
	public void setNazwa(String name) {
		this.name = name;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String  getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String numerKontaktowy) {
		this.contactNumber = contactNumber;
	}

	public String getAdressEmail() {
		return adressEmail;
	}

	public void setAdressEmail(String adressEmail) {
		this.adressEmail = adressEmail;
	}
	
	public void bufferedReader()  {
		
				
		try {
			
			bf = new BufferedReader(new InputStreamReader (new FileInputStream(fileName), "UTF-8"));
			
			Thread  t = new Thread(new Output());
			t.start();
			
		} catch (FileNotFoundException e) {
			System.out.println("File is not found");
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			System.out.println("Problem with formating data");
			e.printStackTrace();
		}finally {
			
			if (bf == null) {
				System.out.println("File not loaded");
				try {
					bf.close();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
			}
		}
		
		
		
	}
	
	
	public void addClient(String addClient) {
		
		String[] newClient = addClient.split(";");
		 
		Klient n = new Klient(newClient[0], newClient[1], newClient[2],
				newClient[3]);
					
			clientList.add(n);
					
	}
	
	
	public void fileWriterSave() {
		 	try {
				outputStream       = new FileOutputStream(fileName, true);
				outputStreamWrite = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
				
				Thread t = new Thread(new FileSave());
				t.start();
				
			} catch (FileNotFoundException e) {
				
				e.printStackTrace();
			} 
		
	}
	
	
	@Override
	public String toString() {
		
		
		return  
				 name + 
				";" + adress + 
				";" + contactNumber + 
				";" + adressEmail;
	}
	
	public static int rowsCount()  {
		BufferedReader bf = null;
		int x = 0;
				try {
			bf= new BufferedReader(new FileReader(fileName));
			while(bf.readLine() != null) x++;
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}finally {
			try {
				bf.close();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
				System.out.println(x);
		return x+1;
	}
	
	public String takeClientNumber()    {
	
		String line = null;
		
		try (Stream<String> lines = Files.lines(Paths.get(fileName), StandardCharsets.UTF_8)) {
			Scanner sc = new Scanner(System.in);
			int number;
			number = sc.nextInt();
			
		    line = lines.skip(number-1).findFirst().get();
		    
		} catch (NoSuchElementException   e)
		{
			System.out.println("W bazie danych brakuje klienta o podanym numerze");
			
			}catch(IOException ex) {
				System.out.println("The file did not load ");
				ex.printStackTrace();
			}
		return line;
		}
	
	public void protocolMaker() throws IOException {
		Klient k = new Klient();
		String strK = k.takeClientNumber() ;
		
		int x = 0;
		System.out.println("1-Actualy Date, 2-Insert Date 3-Go backwards");
		if (x == 1) {
			LocalDate dzis = LocalDate.now();
			System.out.println(dzis);
		} else if( x == 2) {
			String date = null;
			System.out.println("Insert Date: yyy-MM-dd");
			Scanner sc = new Scanner(System.in);
			date = sc.nextLine();
			try {
				Date dateD = new SimpleDateFormat("yyy-MM-dd").parse(date);
			} catch (ParseException e) {
				System.out.println("Wrong date format, use yyy-MM-dd");
				protocolMaker();
				e.printStackTrace();
			}finally {
				sc.close();
			}
			
		} else if( x==3 ) {
			
		} else {
			System.out.println("Nie ma takiej funkcji /n"+
													"/n"+
													"/n");
			protocolMaker();
		}
				
	}
	

	
	class FileSave implements Runnable{

		@Override
		public void run() {
			
			
		try {
						 
			 for (Klient k : clientList) {
				 outputStreamWrite.write( rowsCount() +". " +k + System.lineSeparator());
				 System.out.println(k);
			 }
		} catch (IOException e) {
			System.out.println("Cannot open the file");
			e.printStackTrace();
		} finally {
					if(  outputStreamWrite == null) {
				
				System.out.println("System can only read file or file is not found.");
				
			} else {
				try {
					 outputStreamWrite.close();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
			}
			
		}
			
		}
		
		
		
	}
	
	class Output implements Runnable{

		@Override
		public void run() {
			String line = null;
			
			try {
			do {
					line = bf.readLine();
					if (line != null) {
					System.out.println(line);
					}
			}while (line !=null);   
			
		}catch (Exception ex) {
			System.out.println("nie dzia³a");
			ex.printStackTrace();
		}
			
			
			
		
	}

}
}
