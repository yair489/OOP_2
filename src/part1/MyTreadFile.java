package part1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MyTreadFile extends Thread{
    private String nameFile;
    private int line;

    public MyTreadFile(String nameFile){
        this.nameFile=nameFile;
        this.line=0;
    }
    @Override
    public void run() {


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



    }

    public int getLine() {
        return line;
    }
}
