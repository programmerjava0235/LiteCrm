package LiteCrm;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;


public class CollectionsOperation implements KlientFunction{
	private  List<Klient> bufferedReaderClientLIst = new ArrayList<Klient>();
	BufferedReader bf = null;
	
	
	private static final String fileName = "klienci.txt";
	
	public List<Klient> bufferedReaderCollection() throws IOException {
				String line;		
	
			bf = new BufferedReader(new InputStreamReader (new FileInputStream(fileName), "UTF-8"));
						if(bf!=null)  		{	
			  while((line = bf.readLine()) != null) {
			  
			  String[] split = line.split(";"); 
			  String nameCompany = split[0].substring(3).toUpperCase();
			  String adress = split[1]; 
			  String phoneNumber = split[2]; 
			  String emailAdress = split[3];
			  
			  Klient k = new Klient(nameCompany, adress, phoneNumber, emailAdress);
			  bufferedReaderClientLIst.add(k); 
			  
			  }
						}else {
							System.out.println("File is not found");
						}
			   
		return bufferedReaderClientLIst;
		
	}
	public void show() throws IOException {
		
		this.bufferedReaderCollection();
		System.out.println(bufferedReaderClientLIst); 
	}
	
		public TreeSet<Klient> sortMethod() throws IOException{
			
			
			this.bufferedReaderCollection();	
			
			TreeSet<Klient> sort = new TreeSet<Klient>(new MySortClass());
			for(Klient t : bufferedReaderClientLIst )
			sort.add(t);
			System.out.println(sort);
			return sort;
		}

	
	  public void runBufferedReader() {
	  
	  Thread t = new Thread(new CreateList()); t.start();
	  
	  
	  }
	 
	  public void runSortList() {
		  Thread t1 = new Thread(new SortLister()); t1.start();
		  
		  
	  }
	  
	  
	  public void findNameCompany(String getNameCompany) throws IOException {
		  
		 this.bufferedReaderCollection();
		 System.out.println(bufferedReaderClientLIst);
		  
		 System.out.println("Podaj nazwê firmy:");
		 String nameCompany = getNameCompany;
		 System.out.println("Nazwa firmy: ");
		 System.out.println(getNameCompany);
		  
				 bufferedReaderClientLIst.stream()	 
		  .filter(klient ->   klient.getName().toLowerCase().startsWith(nameCompany.trim().toLowerCase()) )
		  .map(klient ->"Nazwa firmy: "+ klient.getName() + ", Adres: " + klient.getAdress() +", Numer Kontaktowy: "+ klient.getContactNumber() + ", Email: " +klient.getAdressEmail())
		  .forEach(System.out::println); 
		  
		 
	  }
	
	  public void runFindNameCompany() {
		  Thread t2 = new Thread(new FindNameCompany()); t2.start();
	  }
	  	
	  private class FindNameCompany implements Runnable{

		@Override
		public void run() {
			CollectionsOperation o = new CollectionsOperation();
			try {
				String nameCompany=null;
				o.findNameCompany(nameCompany);
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			
		}
		  
	  }
	  
	  private class CreateList implements Runnable{
	  
	  
	  
	  @Override public void run() {
	  
	  CollectionsOperation o = new CollectionsOperation(); try {
	  
	  o.bufferedReaderCollection(); 
	  
	  } catch (IOException e) {
	  
	  e.printStackTrace(); }
	  
	  
	  }
	  
	  }
	  
	  private class SortLister implements Runnable{

		  @Override public void run() {
			  
			  CollectionsOperation o = new CollectionsOperation(); 
			  try {
				o.sortMethod();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			   
			  }
		  		  
	  }
	 
	  class MySortClass implements Comparator<Klient>{

		@Override
		public int compare(Klient o1, Klient o2) {
			
			return o1.getName().compareTo(o2.getName());
		}
		  
	  }

}
