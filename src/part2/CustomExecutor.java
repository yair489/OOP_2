package part2;

import java.util.concurrent.*;

public class CustomExecutor extends  ThreadPoolExecutor  {

    private static PriorityBlockingQueue queue= new PriorityBlockingQueue<Runnable>();

    private static int numOfCores = Runtime.getRuntime().availableProcessors();

   private int[] CurrentMax;



    /**
     * constructor
     *Initializes the ThreadPoolExecutor to have half of the currently available processors,
     * and with the maximum number of processors minus one whose wait marker is 0.3 (in seconds)
     * and stores in a PriorityBlockingQueue
     */
    public CustomExecutor() {

        super(numOfCores/2,numOfCores-1, 300, TimeUnit.MILLISECONDS,
                queue);
       this.CurrentMax= new int[11];
       for (int i=0 ;i<CurrentMax.length;i++){
           CurrentMax[i]=0;
       }
        System.out.println(CurrentMax.length + "arr");
    }

    /**
     *Tests are made on a task and executed on it,
     *  execute() method can only accept a Runnable Task
     * @param task
     * @return task1 of a kind RunnableFuture
     * @param <T>
     */
    public   Future submit(Task task){


        if (task==null|| task.getTaskType()==null){
            throw new NullPointerException();
        }
        if (task == null || task.getCallable() == null)
            throw new NullPointerException();

        int priority = task.getTaskType().getPriorityValue();
      //System.out.println(priority+" oriority submit");
        this.CurrentMax[priority]=CurrentMax[priority]+1;

        //threadPool_c.execute(task);

        execute(task);
        return task;

    }


    /**
     * Takes the data, creates a Task from it and sends it to the submit function above
     * @param cal of kind calabke
     * @param task of kind TaskType
     * @return task1 of a kind RunnableFuture
     */

    public Future submit(Callable cal, TaskType task){

        Task tempTask =  Task.createTask(cal,task);
        return submit(tempTask);
    }
    @Override
    public Future submit(Callable cal){
        if (cal==null ){
            throw new RuntimeException("Callable is null");
        }
        Task tempTask =  Task.createTask(cal);
        return submit(tempTask);
    }
    @Override
//    protected synchronized void afterExecute(Runnable t, Throwable r) {
//     int curent= getCurrentMax();
//        System.out.println(curent +" after execute");
//     if (0<curent && curent >=10)
//         this.CurrentMax[curent]=CurrentMax[curent]-1;
//    }

    protected void beforeExecute(Thread t, Runnable r) {
        int priority = getCurrentMax();
        System.out.println(priority);
        if (1 <= priority && priority <= 3)
            CurrentMax[priority]--;
    }

    /**
     *The class allows to stop the activity of a CustomExecutor instance as follows:
     * a. Do not allow the introduction of additional tasks to the queue
     * b. Completing all tasks remaining in the queue
     * c. Termination of all tasks currently in progress in the C threads collection
     */
    public void gracefullyTerminate(){


        do {
            try {

                super.shutdown();
                super.awaitTermination(300,TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }while (!super.isTerminated());


    }
    /**
     *getter and setter
     */

    public static int getNumOfCores() {
        return numOfCores;
    }

    public static void setNumOfCores(int numOfCores) {
        CustomExecutor.numOfCores = numOfCores;
    }


    public  int getCurrentMax() {

        for (int i = 1; i <= 10; i++) {
            if (this.CurrentMax[i]>0)
                return i;
        }
        return 0;
    }

}