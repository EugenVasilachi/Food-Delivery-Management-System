package data_layer;

import java.io.FileWriter;
import java.io.IOException;

public class FileWriterClass {
    private String filetoWrite;

    public FileWriterClass(String fileToWrite) {
        this.filetoWrite = fileToWrite;
    }

    public void writeToFile(String str) {
        FileWriter f;
        try {
            f = new FileWriter(this.filetoWrite);
            f.write(str);
            f.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
