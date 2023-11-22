import java.util.stream.Stream;
import java.util.ArrayList;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.*;

public class Main {

	static String fileName = "uzytkownicy.txt";
	static boolean menu = false;
	static long noOfLines = 0;
	static int wybor = 0;
    static int count = 0;
	static ArrayList<String> marka = new ArrayList<String>();
	static ArrayList<String> model = new ArrayList<String>();
	static ArrayList<String> tablica = new ArrayList<String>();
	static ArrayList<String> imie = new ArrayList<String>();
	static ArrayList<String> nazwisko = new ArrayList<String>();
	static int list = 0;

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
                    write.println(Main.rejestracja());
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
					Logs.parking();
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
		System.out.println("1. Wprowadź nowego użytkownika");
		System.out.println("2. Wypisz wszystkich użytkowników korzystających z parkingu");
		System.out.println("3 - Wjazd samochodu na parking");
		System.out.println("4 - Wyjazd samochodu z parkingu");
		System.out.println("5 - Logi parkowania");
		System.out.println("6 - Zakończ program");
		System.out.print("Wybierz: ");

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

		list++;

		return Car.user("[Samochód: "+marka.get(marka.size()-1)+" "+model.get(model.size()-1)+", Tablica rejestracyjna: "+tablica.get(tablica.size()-1),", Imię: "+imie.get(imie.size()-1)+", Nazwisko: "+nazwisko.get(nazwisko.size()-1)+"]");

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