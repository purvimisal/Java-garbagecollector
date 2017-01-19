//Start of the Program GCObjectsDemo.java  
public class GCObjectsDemo extends Thread // creating Thread by extending Thread class
{
    public static void main(String[] args) {
        new GCObjectsDemo().start(); // new object is created for GCObjectsDemo class & thread will start the execution
    }
    public void run() // this method gets executed automatically when the thread starts executed  
        {
            long start = System.currentTimeMillis(); //returns the time since January 1st 1970 in milliseconds
            long then = start; // Initializes to the start time
            while (true) { // sleep random delay taking a delay Random number of seconds     
                try {
                    int delay = (int) Math.round(100 * Math.random());
                    Thread.sleep(delay); // thread will sleep for these many seconds
                } catch (InterruptedException e) {} // we need to handle "InterruptedException" may throw this exception if it is  interrupted	

                // create random number of objects
                int count = (int) Math.round(Math.random() * 10 * 1000);
                for (int i = 0; i < count; i++) {
                    new GCObject(); // will create New GCobject
                }
                // log stats to console
                long now = System.currentTimeMillis(); // returns the time since January 1st 1970 in milliseconds
                if (now - then > 1000) {
                    System.out.println(
                        ((now - start) / 1000) + " s\t" + // Converts to Seconds from Milliseconds , the time elapsed so far. 
                        GCObject.created + " created\t" + // These many objects are Created so far in ONE second 
                        GCObject.freed + " freed" // These many objects are candidates for the next Garbage Collection (objects that are no longer referenced to) 
                    );
                    then = now;
                }
            }
        }
}

class GCObject {
    static long created; // static variable part of class
    static long freed;

    public GCObject() {
        created++; //Objects getting created in one second 
    }
    public void finalize() {
        freed++; //Objects getting freed in one second 
    }
}
//End of the Program GCObjectsDemo.java
