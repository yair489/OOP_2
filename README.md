### OOP_2

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


