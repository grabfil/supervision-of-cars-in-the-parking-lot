import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Entry {

    static String line2, line3;
	static int number;
	static ArrayList<String> entry_logs = new ArrayList<String>();

	public static void entrycar() throws IOException {

        DateTime dateTime = new DateTime();
        
        File create1 = new File("wjazd.txt");
		if(!create1.exists()) {
			create1.createNewFile();
		}

		String filePath = "wjazd.txt";
		int lineCount = 0;
        int c = 1;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(c+". "+line); //wypisywanie aut z pliku wjazd.txt
				lineCount++;
                c++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

		System.out.println(" Którym autem chcesz wjechać na parking? (Podaj numer)");
	 	number = Main.getInt();
		while (number <= 0 || number > lineCount) {
			System.out.println("Błąd, spróbuj ponownie");
			number = Main.getInt();
		}
		int numerLinii = number;

		File create = new File("wyjazd.txt");
		if(!create.exists()) {
			create.createNewFile();
		}

		String sourceFilePath = "wjazd.txt"; 
        String destinationFilePath = "wyjazd.txt"; 
        int lineToRemove = numerLinii; 
        String dateAndTime = "dnia " + dateTime.formatted_date() + " o godzinie " + dateTime.formatted_time() + " wjechał ";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(sourceFilePath));
            PrintWriter writer = new PrintWriter(new FileWriter(destinationFilePath, true));
            String line;
            int currentLine = 1;

            while ((line = reader.readLine()) != null) {
                if (currentLine == lineToRemove) {
                    writer.println(line); 
                    entry_logs.add(dateAndTime + line);
                }
                currentLine++;
            }

            reader.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Path path = Paths.get(filePath);
            List<String> lines = new ArrayList<>(Files.readAllLines(path));

            if (lineToRemove > 0 && lineToRemove <= lines.size()) {
                lines.remove(lineToRemove - 1);
                Files.write(path, lines); 
            } 
        } catch (IOException e) {
            e.printStackTrace();
        }

	}
    
}