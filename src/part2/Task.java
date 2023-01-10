package part2;

import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class Task<E> extends FutureTask <E> implements Callable<E>,Comparable<Task<E>> {
    private TaskType taskType;
    private Callable<E> task;

    /**
     * constructor
     * @param v kind of Callable
     * @param t kind of TaskType
     */
    private Task(Callable<E> v, TaskType t){
        super(v);
        this.taskType=t;
        this.task =v;
    }
    private Task( Callable<E> v){
        super(v);
        this.taskType=TaskType.OTHER;
        this.task =v;

    }

    //factory

    /**
     * In order to create a Task you need:
     * Java's built-in type of a task that can be executed asynchronously with a return value
     * factory
     * @param callable
     * @param <E>
     * @return constructor
     *
     */
    public static<E> Task<E> createTask(Callable<E> callable){

        return  new Task(callable);
    }

    /**
     *In order to create a Task you need:
     * Java's built-in type of a task that can be executed asynchronously with an appended return value taskType
     * factory
     * @param callable
     * @param <E>
     * @return constructor
     */
    public static<E> Task<E> createTask(Callable<E> callable,TaskType taskType){

        return  new Task(callable,taskType);
    }
//    public static part2.Task createTask(Callable v, part2.TaskType t){
//        if (v==null) {
//            return null;
//        }
//       return  new part2.Task( v,  t);
//    }
//    public static part2.Task createTask(Callable v){
//        if (v==null) {
//            return null;
//        }
//        return new part2.Task(v);
//    }



    @Override
    public E call() throws Exception {
        if (this.task!=null){
            return (E) this.task.call();
        }else {
            return null;
        }
    }

    @Override
    public int compareTo(Task<E> o) {
        return Integer.compare(this.getTaskType().getPriorityValue(), o.getTaskType().getPriorityValue());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task<?> task1 = (Task<?>) o;
        return taskType.getPriorityValue() == task1.taskType.getPriorityValue() && task==task1.task;
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskType, task);
    }

    @Override
    public String toString() {
        return "part2.Task{" +
                "taskType=" + taskType +
                ", Priority=" + this.getTaskType().getPriorityValue() +
                ", Callable=" + this.task +
                '}';
    }

    //getter and set
    public TaskType getTaskType() {
        return taskType;
    }
    public void setTaskType(TaskType taskType) {
        this.taskType = taskType;
    }

    public void setTaskCallable(Callable task) {
        this.task = task;
    }

    public Callable<E> getCallable() {
        return task;
    }
}
