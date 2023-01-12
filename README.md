# Object-Oriented Programming - Ex2



## Thread

**What is a thread?**

A thread is a single sequential flow of control within a program.

Threads allows a program to operate more efficiently by doing multiple things at the same time.
Threads can be used to perform complicated tasks in the background without interrupting the main program.
Every thread has a priority. Threads with higher priority are executed in preference to threads with lower priority. 
Threads who have finished their jobs will die. It won't consume any more CPU time.

## ThreadPool

**What is threadPool?**

A thread pool is a software design pattern for achieving concurrency of execution in a computer program.

A thread pool reuses previously created threads to execute current tasks and offers a solution to the problem of thread
cycle overhead and resource thrashing.
A thread pool manages a set of anonymous threads that perform work on request. The threads do not terminate right away.
When one of the threads completes a task, the thread becomes idle, ready to be dispatched to another task. 


## Our assignment
In this assignment we sent text files in different methods (withot thread, with thread and threadPool). 
In addition, we created new type that provides an asynchronous task with priority and a ThreadPool type that supports tasks
priority.


## Part 1

In this part we created a number of text files, using 4 functions we calculated the total number of rows in the files.
(We checked on 1000 files)
**1. createTextFiles** - this function create the text files andThis function creates the text files and the number of lines in each file randomly.  
It return an array with the names of the files.  

**2. getNumOfLines** - this function get an array of files and returns the total number of rows of all files.  

**3. getNumOfLinesThreads** - this function get an array of files and returns the total number of rows of all files using threads.  

**4. getNumOfLinesThreadsPool** - this function get an array of files and returns the total number of rows of all files using ThreadPool.  

![תמונה מונחה](https://user-images.githubusercontent.com/120071641/211654272-5a8d7143-eb3d-44d5-93b1-d361da479d98.jpeg)


### MyThreadFile  -  
Represent a Thread that calculate the total number of rows of all files using threads.  
  
### threadPool -   
Represent the Thread that calculate the total number of rows of all files using threads in function number 4 (getNumOfLinesThreadsPool).  

 ### Tests -  
  Tests that we made.


## The differences between the running times of the functions  
  
  ![image](https://user-images.githubusercontent.com/120071641/211655054-27cf5d6e-3f0b-47bb-bd23-34bb9c2fc7f1.png)

The times shown in the program were measured in nano times. 
After converting the times to seconds:  

**1. getNumOfLines** - 4.1844 seconds.  

**2. getNumOfLinesThreads** - 0.24007 seconds.  

**3. getNumOfLinesThreadsPool** - 0.21242 seconds. 

As we can see, the function without using threads took the most time to calculate the total number of rows of the files,  
while the function using threadsPool took the least time.
The function used by threads took the average time between the above 2 functions.
In the first function, the rows are calculated one after the other, not simultaneously.
In the second function, the calculation of the rows is done by several threads at the same time, but after they finished calculating their row, they died and more were created each time.
In the third function, the calculation of the rows is also done by several threads at the same time, but after they finished  
calculating their row, they did not die, but moved on to the calculation of the next row,This is because it was managed in threadspool
 
From here we can understand the reasons for the difference between the running times.


## part 2 

## Summary
In Java, there is no built-in option to prioritize an asynchronous task. Java does allow you to set a priority for the Thread that runs the task, but not for the task itself. Therefore, we are in a problem when we want to prioritize an asynchronous task. In addition, we learned to use ThreadPool, which is adapted to runnable problems, while we created a callable task:

The purpose of the task is to build such a ThreadPool that contains a PriorityQueue into which tasks of the task type (which contain callable) can be inserted, therefore we created:

Task class: the class implements callable to be an asynchronous task with a return value and in addition Typetask to have a certain priority

The CustomExecutor class which creates tasks of type task and enters a ThreadPool which will execute tasks according to priority.


In this part we created new type that provides an asynchronous task with priority and a ThreadPool type that supports tasks
priority.

 ### TaskType -  
 The TaskType class: a given class which actually determines the priority of the task,
 (Represent the task type.)

### Task -  
An operation that can be run asynchronously with priority.
Contains a TaskType, each type has a numerical value which determines the priority of the task.  
It will contain a method with a generic return value. If the operation cannot be performed, an exception will be thrown
 (Exception).  
 
 The class implements Callable to have an asynchronous task with some return value

The class implements Comparable so that we put the objects into the PriorityBlockingQueue, it will compare to the type we made.

The class inherits from FutureTask because FutureTask is of type RunnableFuture and thus it can be inserted into the ThreadPool

In addition to the class there is a datamember of type TypeTask which contains the priority of the task
The class has 2 constructors: the first accepts Callable and TaskType , and the second accepts Callable both send to the father the variable from the Callable mask using the super command, the constructors are defined as private and can be accessed using createTask, which are Factory methods.
 
 


### CustomExcecutor -  
A type of ThreadPool that runs Task type operations asynchronously according to priority.
A task queue will be maintained which arranges the elements in the queue according to their priority,
from low to high at any given moment

The CustomExecutor class: which actually produces the Task type objects we built and puts a location in the priority queue, this is done by building a ThreadPool in a more extensive way:

Constructor: executes the super command: in which he initializes the minimum and maximum number of threads, the excess thread is allowed to be in the air for 0.3 seconds, and inserts a PriorityBlockingQueue (which will arrange the members according to the compareto we implemented in the class task)

The class also has 3 submits: the first accepts Task, the second accepts Callable and also TaskType, the third accepts Callable

The first and the second: we receive the parameters and createTask and send it to the first submit in it (there are basic tests) in which we execute the execute command
for the task to enter the queue in the ThreadPool.

The class inherits from ThreadPoolExecutor because we needed access to the beforeExecute method which helps in implementing getCurrentMax according to the limiting conditions, we will immediately specify getCurrentMax.

getCurrentMax: A method that should return the maximum value that is currently in the queue, this method must not access the queue.
That's why we built an array that is updated for the first time in submit and increases the value by one in the appropriate place in the array, in the beforeExecute method we check the highest value that is currently in the queue and download it from the array, so the array is updated with what is in the queue every moment. At the end of the getCurrentMax function you will find out what is the first cell that is greater than 0 and return the cell number.

 ### Tests -  
  Tests that we made.
  
  ## add picture of the diagrama
![image](https://user-images.githubusercontent.com/118690651/212040889-35c82dbd-c027-46a7-8ca1-147b00bb5ab4.png)

