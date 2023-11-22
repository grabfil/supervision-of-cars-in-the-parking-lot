import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

public class Logs {
    
    static int lineCount = 0;

	public static void parking() throws IOException {

        File create = new File("parking_logs.txt");
		if(!create.exists()) {
			create.createNewFile();
		}

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("parking_logs.txt"))) {
            for (String entry : Entry.entry_logs) {
                writer.write(entry);
                writer.newLine(); 
            }
            for (String exit : Exit.exit_logs) {
                writer.write(exit);
                writer.newLine(); 
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

	}

}