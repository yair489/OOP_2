package part1;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.*;

public class Ex2_f {

    /**
     *The function will create N files with different line numbers
     * @param n  The amount of files that the user requested to create
     * @param seed Creates a new random number generator using a
     * single long seed
     * @param bound
     * @return
     */
    public static String[] createTextFiles(int n, int seed, int bound) {

        int line=0;
        String [] NumOfFile = new String[n];
        Random rand = new Random(seed);
        try {
            for (int i=1;i<=n;i++){
                File myObj = new File("file_" + i+".text");
                if (myObj.createNewFile()) {
                   // System.out.println("File created: " + myObj.getName());
                } else {
                    System.out.println("File already exists.");
                }
                int x = rand.nextInt(bound);
                line+=x;
                FileWriter myWriter = new FileWriter("file_" + i+".text");

                for (int index=0;index<x;index++){

                       myWriter.write(("The best time is now!\n"));

                      // System.out.println("Successfully wrote to the file."+i);

               }
                myWriter.close();

                NumOfFile[i-1]="file_" + i+".text";
                System.out.println("file_ " + i+".text  " +"line= "+x );
            }

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }



       return NumOfFile;

    }

    /**\
     *The function takes the array of files and calculates the number of lines in them
     * @param fileNames An array of files
     * @return the number of lines in files
     * @throws IOException
     */
    public static int getNumOfLines(String[] fileNames) throws IOException {
        int lines = 0;

        for(int i=0;i<fileNames.length; i++){
            String name= fileNames[i];
            BufferedReader reader = new BufferedReader(new FileReader(name));
            while (reader.readLine() != null) lines++;
            reader.close();
        }


        return lines;
    }

    /**
     * The function takes the array of files and calculates the number of lines in them
     * @param fileNames An array of files
     * @return totalLine the number of lines in files
     * @throws InterruptedException
     */
    public static int getNumOfLinesThreads(String[] fileNames) throws InterruptedException {
        MyTreadFile[] threadlLine= new MyTreadFile[fileNames.length];
        int totalLine=0;
        for (int i=0;i<fileNames.length;i++){

            threadlLine[i]= new MyTreadFile(fileNames[i]);
            threadlLine[i].start();
        }
        for (int i=0;i<fileNames.length;i++) {
            threadlLine[i].join();
        }
        for (MyTreadFile Line : threadlLine){
            totalLine+=Line.getLine();
        }

        return totalLine;
    }

    /**
     * The function takes the array of files and calculates the number of lines in them
     * @param fileNames An array of files
     * @return totalLine the number of lines in files
     */
    public static int getNumOfLinesThreadPool(String[] fileNames) {
        ExecutorService threadPool = Executors.newFixedThreadPool(fileNames.length);
        int i,total=0;

        ArrayList<Future<Integer>> futures= new ArrayList<>();
        for (i = 0; i<fileNames.length;i++){
            Callable<Integer> t=  new threadPool(fileNames[i]);


            futures.add(threadPool.submit(t));


        }
        //part1.threadPool.shutdown();
        for (i = 0; i<fileNames.length;i++){
            try {
                total +=futures.get(i).get();
                //System.out.println(futures.get(i).get());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }


        threadPool.shutdown();

        return total;

    }
}
