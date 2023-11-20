import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;
import java.util.ArrayList;
import java.util.Scanner;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.StandardCopyOption;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.io.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

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

class Car {

	static String car(String brand, String model, String plate) {
		return brand + " " + model + " " + plate; 
	}

	static String owner(String fname, String lname) {
		return fname + " " + lname; 
	}

	static String user(String car, String owner) {
		return car + owner;
	}

}

class Entry {

	static String line2, line3;
	static int number;

	public static void entrycar() throws IOException {

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
		int numerLinii = number + 1;
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

		File create = new File("wjazd2.txt");
		if(!create.exists()) {
			create.createNewFile();
		}

        try {
            BufferedReader reader = new BufferedReader(new FileReader("wjazd.txt"));
            PrintWriter writer = new PrintWriter(new FileWriter("wjazd2.txt"));
            String line;
            int currentLine = 1;

            while ((line = reader.readLine()) != null) {
                if (currentLine == numerLinii) {
                    writer.println(line);
                }
                currentLine++;
            }

            reader.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

	}
	
}

class Exit {

	public static void exitcar() throws IOException {



	}

}

public class Main {

	static String fileName = "uzytkownicy.txt";
	static boolean menu = false;
	static long noOfLines = 0;
	static int wybor = 0;
    static int count = 0;
	static ArrayList<String> nazwisko = new ArrayList<String>();
	static ArrayList<String> tablica = new ArrayList<String>();
	static ArrayList<String> marka = new ArrayList<String>();
	static ArrayList<String> model = new ArrayList<String>();
	static ArrayList<String> imie = new ArrayList<String>();

    public static void main(String[] args) throws FileNotFoundException, IOException {
        while (true) {
            int wybor = menu();
		    switch(wybor) {

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
                    break;

                case 2:
                    write_out();
                    break;

                case 3:
                    Entry.entrycar();
                    break;

                case 4:
					Exit.exitcar();
                    break;

                case 5:

                    break;

                case 6:
                    System.exit(0);
                    break;
                
                default:
                    System.out.println("Błąd, spróbuj ponownie.");
                    break;

		    }

        }

	}

	static int menu() {	

        File file = new File("uzytkownicy.txt");
        
        if (count == 0) {
            System.out.println("|DOZÓR SAMOCHODÓW NA PARKINGU|");
            System.out.println("------------------------------");
        }

		System.out.println("Wybierz:");
		System.out.println("1. Wprowadź nowego użytkownika");
		System.out.println("2. Wypisz wszystkich użytkowników korzystających z parkingu");
		System.out.println("3 - Wjazd samochodu na parking");
		System.out.println("4 - Wyjazd samochodu z parkingu");
		System.out.println("5 - Logi parkowania");
		System.out.println("6 - Zakończ program");

		wybor = getInt();

		if (wybor == 3) {
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
        count++;
		return wybor;

	}
	
	static String rejestracja() {

		System.out.println("Podaj markę: ");
		marka.add(getString());

		System.out.println("Podaj model: ");
		model.add(getString());

		System.out.println("Podaj tablicę rejestracyjną: ");
		tablica.add(getString());

		System.out.println("Podaj imię: ");
		imie.add(getString());

		System.out.println("Podaj nazwisko: ");
		nazwisko.add(getString());

		return Car.user(String.valueOf(noOfLines) + "[Samochód: "+marka.get(marka.size()-1)+" "+model.get(model.size()-1)+", Tablica rejestracyjna: "+tablica.get(tablica.size()-1),", Imię: "+imie.get(imie.size()-1)+", Nazwisko: "+nazwisko.get(nazwisko.size()-1)+"]");

	}
	
	static void write_out() {

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

	public static String getString() {
		return new Scanner(System.in).nextLine();
	}
	public static int getInt() {
		return new Scanner(System.in).nextInt();
	}

}
