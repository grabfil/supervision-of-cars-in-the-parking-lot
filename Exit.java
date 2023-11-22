import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

public class Exit {
    
    static String line2, line3;
	static int number;
    


	public static void exitcar() throws IOException {
        Path path2 = Paths.get("wyjazd.txt");
        long fileSize = Files.size(path2);
        if (fileSize == 0) {
            System.out.println("Brak aut na parkingu");
            System.exit(0);
        }
		String filePath = "wyjazd.txt";
		int lineCount = 0;
        int c = 1;
        
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(c+". "+line);
				lineCount++;
                c++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

		System.out.println(" Którym autem chcesz wyjechać z parkingu? (Podaj numer)");
	 	number = Main.getInt();
		while (number <= 0 || number > lineCount) {
			System.out.println("Błąd, spróbuj ponownie");
			number = Main.getInt();
		}
		int numerLinii = number;

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

		String sourceFilePath = "wyjazd.txt";
        String destinationFilePath = "wjazd.txt"; 
        int lineToRemove = numerLinii; 

        try {
            BufferedReader reader = new BufferedReader(new FileReader(sourceFilePath));
            PrintWriter writer = new PrintWriter(new FileWriter(destinationFilePath, true));
            String line;
            int currentLine = 1;

            while ((line = reader.readLine()) != null) {
                if (currentLine == lineToRemove) {
                    writer.println(line);
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
