package part1;

import java.io.File;
import java.io.IOException;

public class Ex2 {


    public static void main(String[] args) throws IOException, InterruptedException {
        /**
         * In this class we use the three functions that we created in the Ex_f class,
         * functions that count the rows in different methods, and print the times each one took.
         */

        String [] nameFiles= Ex2_f.createTextFiles(1000,457,8675);

        long start,end,totalTime;

        System.out.println();
        start=System.nanoTime();
        int line= Ex2_f.getNumOfLines(nameFiles);
        System.out.println("num of getNumOfLines= "+line);
        end= System.nanoTime();
        System.out.println("time of without thread "+((start-end))+ " nanotime");

        System.out.println();
        start=System.nanoTime();
        int line1= Ex2_f.getNumOfLinesThreads(nameFiles);
        end= System.nanoTime();
        System.out.println("num of getNumOfLinesThreads= "+line1);
        System.out.println("time of  thread         "+(start-end)+ " nanotime");

        System.out.println();
        start=System.nanoTime();
        int line2= Ex2_f.getNumOfLinesThreadPool(nameFiles);
        end= System.nanoTime();
        System.out.println("num of getNumOfLinesThreadPool= "+line2);
        System.out.println("time of  thread          "+(start-end)+ " nanotime");

        System.out.println("resoult of total line =" +line2);
        for (int i=1;i<=nameFiles.length;i++){
            String name =nameFiles[i-1];
            File file = new File(name);
            file.delete();
            //System.out.println("delete");
        }
    }
}