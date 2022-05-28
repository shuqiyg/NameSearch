/********************************************** 
Workshop # 7
Course: JAC433
Last Name:Yang
First Name:Shuqi
ID:132162207
Section:NBB 
This assignment represents my own work in accordance with Seneca Academic Policy. 
Signature 
Date:2022-03-25
**********************************************/ 
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Ranking {
	int year, rank = 0;
	String babyName, sex, rankingResult;
	private BufferedReader bufReader;
	private  ArrayList<String> maleArray = new ArrayList<String>();
	private ArrayList<String> femaleArray = new ArrayList<String>();
	
	public Ranking(int year, String name, String sex) {
		this.year = year;
		this.babyName = name;
		this.sex = sex;
		String fileName = "babynamesranking" + Integer.toString(year) + ".txt";
		openFile(fileName);
		read(bufReader);
	}
	
	public void openFile(String fileName) { 
		//intantiate the buffered reader chained with inputstramreader chained to fileinputstream
		try {
			bufReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
			//read(bufReader);
		}catch(IOException ioException) {
			System.err.println("Can not open file. Please try again.");
			System.exit(1);
		}
	}
	
	public void closeFile() {
		try {
			if(bufReader != null) {
				bufReader.close();
			}
		}catch(IOException ioException) {
			System.err.println("Error Closing the file. Terminating...");
			System.exit(1);
		}
	}
	
	public void read(BufferedReader bufReader) {
		try {
			while(bufReader.ready()) {
				String str = bufReader.readLine();
				String[] wordsSplit = str.split("\\s+");
				String mString = wordsSplit[1] + " " + wordsSplit[2];
				String fString = wordsSplit[3] + " " + wordsSplit[4];
				maleArray.add(mString);
				femaleArray.add(fString);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("Reading Error..");
		}
	}
	
	public String find() {
		String sName;
		if(this.sex.equals("M")) {
			sName = "Boy";
			int i = 0;
			for(String boy: maleArray) {
				i++;
				String[] bRecord = boy.split("\\s");
				if(bRecord[0].equals(this.babyName)) {
					//rank = i;
					return sName + " name " + babyName + " is ranked #" + i + " in " + year + " year";
				}
			}
		}else {
			sName = "Girl";
			int j = 0;
			for(String girl: femaleArray) {
				j++;
				String[] fRecord = girl.split("\\s");
				if(fRecord[0].equals(this.babyName)) {
					//rank = j;
					return sName + " name " + babyName + " is ranked #" + j + " in " + year + " year";
				}
			}
		}
		return sName + " name " + this.babyName + " is NOT FOUND in this year's ranking\n I guess it's not popular...";
		
	}
}
