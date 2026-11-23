import java.io.IOException;
import java.nio.file.*;

public class FileManager {
    String filePath;
    String fileName;
    boolean fileExists;
    boolean lastOperationSucessful;

    boolean saveResults(String data) {
        try {
            if (filePath == null || filePath.isEmpty()) {
                lastOperationSucessful = false;
                return false;
            }

            Path path = Paths.get(filePath);

            Files.writeString(path, data);

            fileExists = Files.exists(path);
            lastOperationSucessful = true;
            return true;

        } catch (IOException e) {
            lastOperationSucessful = false;
            return false;
        }
    }

    String loadResults() {
        try {
            if (filePath == null || filePath.isEmpty()) {
                lastOperationSucessful = false;
                return "";
            }

            Path path = Paths.get(filePath);
            if (!Files.exists(path) || Files.isDirectory(path)) {
                lastOperationSucessful = false;
                return "";
            }

            String data = Files.readString(path);
            lastOperationSucessful = true;
            return data;

        } catch (IOException e) {
            lastOperationSucessful = false;
            return "";
        }
    }

    boolean clearResults() {
        try {
            if (filePath == null || filePath.isEmpty()) {
                lastOperationSucessful = false;
                return false;
            }

            Path path = Paths.get(filePath);

            if (!Files.exists(path)) {
                lastOperationSucessful = false;
                return false;
            }

            // Option 1: overwrite file with empty content
            Files.writeString(path, "");
            lastOperationSucessful = true;
            return true;

        } catch (IOException e) {
            lastOperationSucessful = false;
            return false;
        }
    }

    boolean checkFileExists() {
        try {
            if (filePath == null || filePath.isEmpty()) {
                fileExists = false;
                return false;
            }

            Path path = Paths.get(filePath);
            fileExists = Files.exists(path);
            lastOperationSucessful = fileExists;
            return fileExists;

        } catch (Exception e) {
            fileExists = false;
            lastOperationSucessful = false;
            return false;
        }
    }

    boolean createFile() {
        try {
            if (filePath == null || filePath.isEmpty()) {
                lastOperationSucessful = false;
                return false;
            }

            Path path = Paths.get(filePath);

            if (!Files.exists(path)) {
                Files.createFile(path);
            }

            fileExists = Files.exists(path);
            lastOperationSucessful = fileExists;
            return fileExists;

        } catch (IOException e) {
            fileExists = false;
            lastOperationSucessful = false;
            return false;
        }
    }

    boolean validateFile() {
        try {
            if (filePath == null || filePath.isEmpty()) {
                lastOperationSucessful = false;
                return false;
            }

            Path path = Paths.get(filePath);

            boolean valid = Files.exists(path) && Files.isReadable(path) && Files.isWritable(path);
            fileExists = valid;
            lastOperationSucessful = valid;
            return valid;

        } catch (Exception e) {
            fileExists = false;
            lastOperationSucessful = false;
            return false;
        }
    }

    String getFilePath() {
        return filePath;
    }

    boolean getOperationalStatus() {
        return lastOperationSucessful;
    }
}