package LiteCrm;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ProtcolBodyHours {
	
	String company;
	int hours;
	Date dateA;
	String dateStr;
	LocalDate ldateA;
	String txt;
	private List<ProtcolBodyHours> protocolBody = new ArrayList<>();
	private static final String fileNameProtocol = "protocols.txt";
	BufferedWriter fileWriter = null;
	StringBuffer stringBuffer = null;
	
	ProtcolBodyHours(String company, int hours, String dateStr, String txt){
		this.company = company;
		this.hours = hours;
		this.dateStr = dateStr;
		this.txt = txt;
	}
	
	ProtcolBodyHours(String company, int hours, Date dateA, String txt){
		this.company = company;
		this.hours = hours;
		this.dateA = dateA;
		this.txt = txt;
	}
	
	ProtcolBodyHours(String company, int hours, LocalDate ldateA, String txt){
		this.company = company;
		this.hours = hours;
		this.ldateA = ldateA;
		this.txt = txt;
	}
	
	public LocalDate getLdateA() {
		return ldateA;
	}

	public void setLdateA(LocalDate ldateA) {
		this.ldateA = ldateA;
	}

	ProtcolBodyHours(){
		
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}

	public Date getDateA() {
		return dateA;
	}

	public void setDateA(Date dateA) {
		this.dateA = dateA;
	}
	
	public int hoursTimeWork() {
		int y = 0;
		int x = 0;
		String hoursTime;
		String minutesTime;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Add hours worked");
		
		hoursTime = sc.nextLine();
		
		System.out.println("Add minutes worked");
		minutesTime = sc.nextLine();
		
		try {
		y = Integer.parseInt(hoursTime);
		x = Integer.parseInt(minutesTime);
			if(y > 12 || x > 59) {
				System.out.println("Max day work time is 12 hours and size of the minuts cannot be larger than 59 ");
				hoursTimeWork();
			} else {
				System.out.println("Przepracowano: " + y + " h " + x + " min");
			}
		}catch (Exception ex) {
			System.out.println("niepoprawna liczba");
		}
		return y;
	}
	
	
	public String actionsPerformed() {
		String text = null;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Add completed activities");
		
		text = sc.nextLine();
		
		if (text == null) {
			System.out.println("Please inster text");
			actionsPerformed();
		}else {
			System.out.println(text);
			
		}return text;
		
		
	}
	
	public String insertDate() {
		String checkDateFormat = null;
		SimpleDateFormat dateD = null;
		Scanner sc = new Scanner(System.in);
		String date = null;
		String formatter = null;
		Date strDate = null;
		int yearCheck = 0;
		String sbToSting = null;
		System.out.println("podaj datê");
		
		try {
			
			date = sc.nextLine();
			dateD = new SimpleDateFormat("yyy-MM-dd");
			strDate = dateD.parse(date);
			formatter = dateD.format(strDate);
			checkDateFormat = formatter.substring(0, 4);
			stringBuffer = new StringBuffer(formatter);
			yearCheck = Integer.parseInt(checkDateFormat);
			
			if(yearCheck > 2030 || yearCheck < 2019) {
				System.out.println("Use date beetwen 2019-2030");
				
				insertDate();
			}
			
			}catch(Exception ex ) {
				System.out.println("Wrong date format, use yyy-MM-dd");
				
				insertDate();						
			}
		sbToSting = stringBuffer.toString();
		
		return sbToSting;
			
	}
	
	
	public String insertDateLocal() {
		LocalDate today = null;
		
		today = LocalDate.now();
		String forrmater = today.format(DateTimeFormatter.ofPattern("yyy-MM-dd"));
		
		return forrmater;
		
	}
	
	public void protocolObject(String company, int hours, Date date, String txt ) {
		String newProtocol = company;
		int addHours = hours;
		Date dDate= date;
		String addActivities = txt;
		ProtcolBodyHours p = new ProtcolBodyHours(newProtocol, addHours, dDate, addActivities);
		
		protocolBody.add(p);
		
		
	}
	
	public void protocolObject(String company, int hours, LocalDate date, String txt ) {
		String newProtocol = company;
		int addHours = hours;
		LocalDate dDate = date;
		String addActivities = txt;
		ProtcolBodyHours p = new ProtcolBodyHours(newProtocol, addHours, dDate, addActivities);
		
		protocolBody.add(p);
		
		
	}
	
	public void protocolObject(String company, int hours, String date, String txt ) {
		String newProtocol = company;
		int addHours = hours;
		String dDate = date;
		String addActivities = txt;
		ProtcolBodyHours p = new ProtcolBodyHours(newProtocol, addHours, dDate, addActivities);
		
		protocolBody.add(p);
		
		
	}
	
	@Override 
	public String toString() {
		
			
		return company + "\n" +
				"Data: "+ dateStr +  "\n" +
				"Przepracowany czas: "+ hours +  "\n"  +
				"Wykonane czynnoœci "+ txt +  "\n";
	}
	
	public void showProtocolList() {
		System.out.println(protocolBody);
	}

	public void fileWriterSaveProtocol() {
		
		
		
			
			Thread t = new Thread(new SaveProtocol());
			t.start();
			
		
	}
	
	class SaveProtocol implements Runnable{

		@Override
		public void run() {
			
			try {
				fileWriter = new BufferedWriter(new FileWriter(fileNameProtocol, true));
				for(ProtcolBodyHours p : protocolBody) {
					fileWriter.write(p + System.lineSeparator());
					fileWriter.flush();
					fileWriter.close();
				}
				
			}catch (Exception ex) {
				System.out.println("File dont write");
				ex.printStackTrace();
			}
			
		}
		
		
	}
}
