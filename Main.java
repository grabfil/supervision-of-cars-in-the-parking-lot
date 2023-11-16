import java.util.Scanner;
import java.io.*;

public class Main
{
	int wybor;
	public int menu()
	{	
		Main myObj = new Main();
		System.out.println("|DOZÓR SAMOCHODÓW NA PARKINGU|");
		System.out.println("------------------------------");
		System.out.println("Wybierz:");
		System.out.println("1. Wprowadź nowego użytkownika");
		System.out.println("2. Wypisz wszystkich użytkowników korzystających z parkingu");
		System.out.println("3 - Wjazd samochodu na parking");
		System.out.println("4 - Wyjazd samochodu z parkingu");
		System.out.println("5 - Logi parkowania");
		myObj.wybor = getInt();
		while((myObj.wybor < 1 || myObj.wybor > 5)){
			System.out.println("Błąd, spróbuj ponownie.");
			myObj.wybor = getInt();
		}
		return myObj.wybor;
	}

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
	
	static String rejestracja()
	{
		String a, b, c, d, e;
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
		return user("["+a+", "+b+", "+c,", "+d+", "+e+"]+");
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException
    {
		Main instance = new Main();
		switch(instance.menu())
		{
			case 1:
				File create = new File("uzytkownicy.txt");
				if(!create.exists()) {
					create.createNewFile();
				}
				PrintWriter write = new PrintWriter("uzytkownicy.txt");
				write.println(rejestracja());
				write.close();
				break;
			case 2:

				break;
			case 3:

				break;
			case 4:

				break;
			case 5:

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