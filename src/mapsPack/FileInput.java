package mapsPack;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileInput {
    private static FileInput INSTANCE;
    private FileReader fileReader = null;
    private BufferedReader bufferedReader = null;

    private FileInput() {
        try {
            fileReader = new FileReader("input.txt");
            bufferedReader = new BufferedReader(fileReader);
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public String getInput(){
        StringBuilder stringBuilder = new StringBuilder();
        String s;
        try {
            while ((s = bufferedReader.readLine()) != null){
                stringBuilder.append(s).append("\n");
            }
        } catch (IOException e){
            System.out.println("Error at reading from a file.");
        }
        return stringBuilder.toString();
    }

    public static FileInput getInstance() {
        if (INSTANCE == null){
            INSTANCE = new FileInput();
        }
        return INSTANCE;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        bufferedReader.close();
        fileReader.close();
    }
}
