### Object-Oriented Programming - Ex2

Assignment 2

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

**1. createTextFiles** - this function create the text files andThis function creates the text files and the number of lines in each file randomly.  
It return an array with the names of the files.  
**2. getNumOfLines** - this function get an array of files and returns the total number of rows of all files.  
**3. getNumOfLinesThreads** - this function get an array of files and returns the total number of rows of all files using threads.  
**4. getNumOfLinesThreadsPool** - this function get an array of files and returns the total number of rows of all files using ThreadPool.  

![תמונה מונחה](https://user-images.githubusercontent.com/120071641/211654272-5a8d7143-eb3d-44d5-93b1-d361da479d98.jpeg)


**MyThreadFile** -  
Represent a Thread that calculate the total number of rows of all files using threads.  
  
**threadPool** -   
Represent the Thread that calculate the total number of rows of all files using threads in function number 4 (getNumOfLinesThreadsPool).  

**Tests** -  
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
In the second function, the calculation of the rows is done by several threads at the same time, but after they finished  
calculating their row, they died and more were created each time.
In the third function, the calculation of the rows is also done by several threads at the same time, but after they finished  
calculating their row, they did not die, but moved on to the calculation of the next row,This is because it was managed in threadspool
 
From here you can understand the reasons for the difference between the running times.
