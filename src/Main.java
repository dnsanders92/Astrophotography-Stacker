import Utils.FolderManager;
import java.io.File;

import static Utils.ConsoleIO.*;
import static Utils.SearchImages.*;

public class Main {
    public static void main(String[] args) {

        String filePath = FolderManager.getFolderName();
        File[] tiffFiles = getTiffs(filePath);

        printArray(tiffFiles);
    }
}