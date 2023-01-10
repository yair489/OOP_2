package part1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.Callable;

public class threadPool implements Callable <Integer>{
    private String nameFile;
    private int line;

    public threadPool(String nameFile){
        this.nameFile=nameFile;
        this.line=0;
    }



    public Integer call() throws Exception {
        String name= nameFile;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(name));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (true) {
            try {
                if (!(reader.readLine() != null)) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            line++;
        }
        try {
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return line;
    }
}
