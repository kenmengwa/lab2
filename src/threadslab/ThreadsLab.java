/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threadslab;
import java.util.concurrent.ExecutorService;  
import java.util.concurrent.Executors;  
import java.util.concurrent.TimeUnit;

/**
 *
 * @author badi
 */
public class ThreadsLab {
    public static int age, yrs;
    public static int ages[] = {20, 45, 47, 38, 35, 67, 18, 34};
    
            static class Processor implements Runnable{
                private int id;
                public Processor (int id){
                this.id = id;
                }
                
                @Override
                synchronized public void run() {  
                System.out.println(Thread.currentThread().getName()+" starting.... " + id);
                MyThread mt = new MyThread("");
                mt.t1.start();try{
                    mt.t1.join();
                }catch (Exception e){
                    System.out.print(e.getMessage());
                }
                mt.t2.start();try{
                    mt.t2.join();
                }catch (Exception e){
                    System.out.print(e.getMessage());
                }
                mt.t3.start();
                try { Thread.sleep(5000);  
                    }catch (InterruptedException e) 
                        { 
                            System.out.println(" interrupt: " + e.getMessage()); 
                        }  
                //call processmessage method that sleeps the thread for 2 seconds  
                System.out.println(Thread.currentThread().getName()+ "completed " + id);//prints thread name  
                }  
}
            
            
    public static class MyThread {
        private String task;
        MyThread (String typeOfTaks){
		this.task = typeOfTaks;
        }
        
        //
        static Thread t1 = new Thread (new Runnable()
                {
                @Override
                public void run(){
                    synchronized(ages){
                        System.out.println("locked age array");
                        }
                    
                    for (int i = 0; i < ages.length; i++) {
                        age = ages[i];
                        yrs = age -18;
                        System.out.println("It has been " + yrs + " years "
                        + "since you were abe to vote \n");
                        }
                        try{t1.sleep(5000);}catch(InterruptedException ie){};
                    }
                });

        static Thread t2 = new Thread(new Runnable()
                    {
                    @Override
                    public void run(){
                        for (int i = 0; i < ages.length; i++) {
                            age = ages[i];
                            System.out.println("you are " + age + " years old \n");
                            }
                        try{t2.sleep(5000);}catch(InterruptedException ie){};
                        }
                    });
    
        static Thread t3 = new Thread(new Runnable()
                    {
                    @Override
                    public void run(){
                        for (int i = 0; i < ages.length; i++) {
                            age = ages[i];
                            yrs = age -18;
                            int voted = (yrs / 5) +1;
                            System.out.println("You have voted " + voted + " times\n");
                            }
                        try{t3.sleep(5000);}catch(InterruptedException ie){};
                        }
                    });
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MyThread mt = new MyThread("");
        Thread ourThreads[] = {mt.t1,mt.t2,mt.t3};

        ExecutorService executor = Executors.newFixedThreadPool(2);
        for (int i=0; i<ourThreads.length;i++){
            executor.submit(ourThreads[i]);
        }
            executor.shutdown();
            System.out.println("All tasks submitted");
                try {
                    executor.awaitTermination(1, TimeUnit.DAYS);
                    } catch (InterruptedException ex) {
                    
                    }
                System.out.println("All tasks completed");
    }
    

                
    
    
}
