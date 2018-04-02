package Util;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.swing.JProgressBar;

/**
 * Class to compute a list of tasks in parallel.
 * @author thiago.junqueira
 *
 * @param <T> The value type of the tasks.
 */
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.swing.JProgressBar;

/**
 * Class to compute a list of tasks in parallel.
 * @author thiago.junqueira
 *
 * @param <T> The value type of the tasks.
 */
public class ParallelJobs<T> {
    
    private static final int MAX_POOL_SIZE = 100;
    private BlockingQueue<T> queue;
    private List<T> jobList;
    private int jobSize;
    private int jobDone;
    private Jobs<T> interfaceJob;
    private JProgressBar progressBar;
    private Float estimateTimeToEnd = 0.f;
    private long startTime;
    int poolSize = 1;
    
    /**
     * Process a list of jobs in parallel.
     * @param jobList A list of jobs to be done.
     */
    public ParallelJobs(List<T> jobList, int poolSize) {
        this.jobList = jobList;
        this.jobSize = jobList.size();
        this.queue = new ArrayBlockingQueue<T>(jobSize);
        this.poolSize = poolSize > jobSize ? jobSize : poolSize;
        this.poolSize = this.poolSize > MAX_POOL_SIZE ? MAX_POOL_SIZE : this.poolSize;
    }
    
    /**
     * Process a list of jobs in parallel.
     * @param jobList A list of jobs to be done.
     * @param progressBar A progress to show the progress of the process (Optional).
     */
    public ParallelJobs(List<T> jobList, int poolSize, JProgressBar progressBar) {
        this.jobList = jobList;
        this.jobSize = jobList.size();
        this.queue = new ArrayBlockingQueue<T>(jobSize);
        this.poolSize = poolSize > jobSize ? jobSize : poolSize;
        this.poolSize = this.poolSize > MAX_POOL_SIZE ? MAX_POOL_SIZE : this.poolSize;
        this.progressBar = progressBar;
    }
    
    /**
     * Start to process the jobs. 
     * @param job A method to deal with the tasks. It can be passed using lambda expression.
     * @throws InterruptedException Thrown when a thread is waiting, sleeping, or otherwise 
     *      occupied, and the thread is interrupted, either before or during the activity.
     *      Occasionally a method may wish to test whether the current thread has been interrupted, 
     *      and if so, to immediately throw this exception.
     */
    public void startMultiThreadTask(Jobs<T> job) throws InterruptedException  {
        
        this.interfaceJob = job;
        
        Runnable producerTask = () -> producer();    
        Thread t1 = new Thread(producerTask);
            
        Runnable consumerTask = () -> consumer();
        Thread t2 = new Thread(consumerTask);
        
        t1.start();
        t2.start();
       
        t1.join();
        t2.join();
    
    }
    
    public Integer getEstimateTimeToEnd() {
        return estimateTimeToEnd.intValue();
    }
    
    private void producer() {
      
       try {
         for (T value : jobList) {
             queue.put(value);
         }
            
        } catch (InterruptedException e) {
            
            e.printStackTrace();
        }
   }
   
   private void consumer()  {
           
       jobDone = 0;
       System.out.println("Active: " + Thread.activeCount());
       startTime = System.currentTimeMillis();
       if (progressBar != null) {
           progressBar.setIndeterminate(true);
           progressBar.setValue(0);
       }
       
       boolean shouldStartJob = !queue.isEmpty();
       
       while (!shouldStartJob) {
        
           System.out.println("Wait");
           shouldStartJob = !queue.isEmpty();
       }
    
       //int poolSize = jobSize; // Optimize performance.
       //ExecutorService executor = Executors.newFixedThreadPool(poolSize);
       ExecutorService executor = Executors.newWorkStealingPool(poolSize); // Better for parallel tasks, it's more dynamically. 
       //System.out.println("Threads: " + Runtime.getRuntime().availableProcessors());
       //ExecutorService executor= Executors.newWorkStealingPool();
       
       Runnable jobTask = () -> {
            try {
                T value = queue.take();
                interfaceJob.job(value);
                consumerCallBack(value);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }   
        };  
        
        // Send all jobs to execution. It will wait on the queue if necessary.
        for (int i = 0; i < jobSize; i++) {
            executor.execute(jobTask); 
       }
        
        try {
            System.out.println("Attempt to shutdown executor");
            executor.shutdown();
            // Wait 8h until kill all executers. 
            executor.awaitTermination(48, TimeUnit.HOURS);
            
        } catch (InterruptedException e) {
            System.err.println("tasks interrupted");
            
        } finally {
            if (!executor.isTerminated()) {
                System.err.println("Cancel non-finished tasks");
            }
            
            executor.shutdownNow();
            shouldStartJob = false;
            long endTime = System.currentTimeMillis();
            System.out.println("Done!! It took: " + (endTime - startTime) / 1000.f + " seconds to finish."); 
        }
        
        try {
            Thread.sleep(100);
            System.out.println("Active: " + Thread.activeCount());
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
   }
   
   private void consumerCallBack(T value) {

            //System.out.println("Taken value: " + value + "; Queue size is: " + queue.size() + " Thread: " + Thread.currentThread().getName());
            jobDone++;
            long t = System.currentTimeMillis();
            estimateTimeToEnd = (t - startTime) * ((float)jobSize / jobDone - 1.f) * 0.001f;
            Integer progress = jobDone * 100 / jobSize;
            System.out.println("job done: " + jobDone +  "; Queue size is: " + queue.size() + "; "
                    + "Time left: " + estimateTimeToEnd.intValue() + " secs.");
            if (progressBar != null) {
                 progressBar.setIndeterminate(false);
                 progressBar.setValue(progress);
                 progressBar.setStringPainted(true);            
            }
   }
}
