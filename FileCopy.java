import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.concurrent.TimeUnit;

public class FileCopy {

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