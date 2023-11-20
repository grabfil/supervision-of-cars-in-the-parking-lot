import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Stream;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.concurrent.TimeUnit;
import java.nio.file.StandardCopyOption;

class FileCopy {
    public static void copy() {
        String sourceFilePath = "uzytkownicy.txt"; 
        String destinationFilePath = "wjazd.txt";

        Path sourcePath = Paths.get(sourceFilePath);
        Path destinationPath = Paths.get(destinationFilePath);

        long lastModifiedTime = 0;

        try {
            while (true) {
                BasicFileAttributes attr = Files.readAttributes(sourcePath, BasicFileAttributes.class);
                long newModifiedTime = attr.lastModifiedTime().to(TimeUnit.MILLISECONDS);

                if (newModifiedTime > lastModifiedTime) {
                    lastModifiedTime = newModifiedTime;
                    Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
					break;
                }

                TimeUnit.SECONDS.sleep(1);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Car
{
	static String car(String brand, String model, String plate)
	{
		return brand + " " + model + " " + plate; 
	}

	static String owner(String fname, String lname)
	{
		return fname + " " + lname; 
	}

	static String user(String car, String owner)
	{
		return car + owner;
	}
}

class Entry 
{
	static int number;
	static String line2, line3;
	public static void entrycar() throws IOException
	{
		String filePath = "wjazd.txt";
		int lineCount = 0;
		
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
				lineCount++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
		System.out.println(lineCount+" Którym autem chcesz wjechać na parking? (Podaj numer)");
	 	number = Main.getInt();
		while (number < 0 || number > lineCount-1) {
			System.out.println("Błąd, spróbuj ponownie");
			number = Main.getInt();
		}
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			int currentLine = 1;
            while ((line2 = br.readLine()) != null) {
                if (currentLine == number+1) {
                    line3 = line2;
                    break;
                }
                currentLine++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
		File create = new File("wyjazd.txt");
		if(!create.exists()) {
			create.createNewFile();
		}
		PrintWriter write = new PrintWriter(new FileWriter("wyjazd.txt", true));
		write.println(line3);
		write.close();
	}
	
}

public class Main
{
	static boolean menu = false;
	static int wybor = 0;
	static String fileName = "uzytkownicy.txt";
	static long noOfLines = 0;
	static ArrayList<String> marka = new ArrayList<String>();
	static ArrayList<String> model = new ArrayList<String>();
	static ArrayList<String> tablica = new ArrayList<String>();
	static ArrayList<String> imie = new ArrayList<String>();
	static ArrayList<String> nazwisko = new ArrayList<String>();

	static int menu()
	{	
		System.out.println("|DOZÓR SAMOCHODÓW NA PARKINGU|");
		System.out.println("------------------------------");
		System.out.println("Wybierz:");
		System.out.println("1. Wprowadź nowego użytkownika");
		System.out.println("2. Wypisz wszystkich użytkowników korzystających z parkingu");
		System.out.println("3 - Wjazd samochodu na parking");
		System.out.println("4 - Wyjazd samochodu z parkingu");
		System.out.println("5 - Logi parkowania");
		System.out.println("6 - Zakończ program");
		wybor = getInt();
        File file = new File("uzytkownicy.txt");
		if (wybor == 3){
 			if (file.exists()) {
            	if (file.length() == 0) {
					System.out.println("Najpierw musisz wprowadzić nowego użytkownika");
					wybor = getInt();
            	} else {
                	return wybor;
            	}
        	} else {
            	System.out.println("Najpierw musisz wprowadzić nowego użytkownika");
				wybor = getInt();
        	}
		}
       
		while((wybor < 1 || wybor > 6)){
			System.out.println("Błąd, spróbuj ponownie.");
			wybor = getInt();
		}
		return wybor;
	}
	
	static String rejestracja()
	{
		String a = null, b = null, c = null, d = null, e = null;
		System.out.println("Podaj markę: ");
		a = getString();
		System.out.println("Podaj model: ");
		b = getString();
		System.out.println("Podaj tablicę rejestracyjną: ");
		c = getString();
		System.out.println("Podaj imię: ");
		d = getString();
		System.out.println("Podaj nazwisko: ");
		e = getString();
		marka.add("a");		
		model.add("b");		
		tablica.add("c");
		imie.add("d");
		nazwisko.add("e");
		return Car.user(String.valueOf(noOfLines) + " ["+a+" "+b+", "+c,", "+d+" "+e+"]");
	}
	
	static void write_out()
	{
		try {
			File myObj = new File("uzytkownicy.txt");
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
			  String data = myReader.nextLine();
			  System.out.println(data);
			}
			myReader.close();
		  } catch (FileNotFoundException e) {
			e.printStackTrace();
		  	}
	}

	public static void main(String[] args) throws FileNotFoundException, IOException
    {
		switch(menu())
		{
			case 1:
				File create = new File("uzytkownicy.txt");
				if(!create.exists()) {
					create.createNewFile();
				}
				try (Stream<String> fileStream = Files.lines(Paths.get(fileName))) {
  					noOfLines = (int) fileStream.count();
				}
				PrintWriter write = new PrintWriter(new FileWriter("uzytkownicy.txt", true));
				write.println(rejestracja());
				write.close();
				FileCopy.copy();
				menu();
				break;
			case 2:
				write_out();
				menu();
				break;
			case 3:
				Entry.entrycar();
				menu();
				break;
			case 4:

				menu();
				break;
			case 5:

				menu();
				break;
			case 6:
				System.exit(0);
				break;
		}

	}

	public static String getString()
	{
		return new Scanner(System.in).nextLine();
	}
	public static int getInt()
	{
		return new Scanner(System.in).nextInt();
	}

}
