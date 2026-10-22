import Utils.FolderManager;
import static Utils.ConsoleIO.*;

public class Main {
    public static void main(String[] args) {

        String filePath = FolderManager.getFolderName();
        println(filePath);
    }
}