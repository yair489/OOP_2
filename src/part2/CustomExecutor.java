package part2;

import part2.Task;
import part2.TaskType;

import java.util.Objects;
import java.util.concurrent.*;

public class CustomExecutor{// implements ExecutorService {

    private PriorityBlockingQueue queue;
    private int numOfCores,corePoolSize,maxPoolSize;
    private ThreadPoolExecutor threadPool_c;
    private int maxvalue;

    /**
     * constructor
     *Initializes the ThreadPoolExecutor to have half of the currently available processors,
     * and with the maximum number of processors minus one whose wait marker is 0.3 (in seconds)
     * and stores in a PriorityBlockingQueue
     */
    public CustomExecutor() {
        queue = new PriorityBlockingQueue<Runnable>();
        this.numOfCores   = Runtime.getRuntime().availableProcessors();
        this.corePoolSize = numOfCores/2;
        this.maxPoolSize  = numOfCores-1;

        this.threadPool_c = new ThreadPoolExecutor
                (corePoolSize, maxPoolSize,
                        300, TimeUnit.MILLISECONDS,
                        queue);
        maxvalue=10;
    }

    /**
     *Tests are made on a task and executed on it,
     *  execute() method can only accept a Runnable Task
     * @param task
     * @return task1 of a kind RunnableFuture
     * @param <T>
     */
    private <T> Future<T> submit(Task<T> task){


        if (task==null|| task.getTaskType()==null){
            throw new NullPointerException();
        }
        if (task == null || task.getCallable() == null)
            throw new NullPointerException();
        this.maxvalue = Math.min(  task.getTaskType().getPriorityValue() , this.maxvalue);
        threadPool_c.execute(task);
        return task;

    }

    /**
     * Takes the data, creates a Task from it and sends it to the submit function above
     * @param cal of kind calabke
     * @param task of kind TaskType
     * @return task1 of a kind RunnableFuture
     */
    public Future<Object> submit(Callable cal, TaskType task){
        if (cal==null || task==null ){
            throw new RuntimeException("one of the paramter is null");
        }
        Task tempTask =  Task.createTask(cal,task);
        return submit(tempTask);
    }
    public Future<Object> submit(Callable cal){
        if (cal==null ){
            throw new RuntimeException("Callable is null");
        }
        Task tempTask =  Task.createTask(cal);
        return submit(tempTask);
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
                threadPool_c.shutdown();
                threadPool_c.awaitTermination(300,TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }while (!threadPool_c.isTerminated());


    }

    /**
     *getter and setter
     */
    public int getCurrentMax() {
        return maxvalue;
    }
    public void setCorePoolSize(int corePoolSize) {
        this.corePoolSize = corePoolSize;
    }
    public void setMaxPoolSize(int maxPoolSize) {
        this.maxPoolSize = maxPoolSize;
    }
    public PriorityBlockingQueue getQueue() {
        return queue;
    }




}