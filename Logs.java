import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Logs {
    
    static int lineCount = 0;

	public static void parking() throws IOException {
        String filePath = "wjazd.txt";
        Path path = Paths.get(filePath);
        if(Files.exists(path)){
            System.out.println("Wjazdy: ");
            try (BufferedReader br = new BufferedReader(new FileReader("wjazd.txt"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                    lineCount++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Żadne auto jeszcze nie wjechało");
        }
        
        String filePath2 = "wyjazd.txt";
        Path path2 = Paths.get(filePath2);
        if(Files.exists(path2)){
            System.out.println("Wyjazdy: ");
            try (BufferedReader br = new BufferedReader(new FileReader("wyjazd.txt"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                    lineCount++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            System.out.println("Żadne auto jeszcze nie wyjechało");
        }
        

	}

}
