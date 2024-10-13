public class DeadlockExample {
    private static final Object resource1 = new Object();
    private static final Object resource2 = new Object();

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            synchronized (resource1) {
                System.out.println("Thread 1: удерживает resource 1.");

                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {};

                System.out.println("Thread 1: ожидает resource 2.");

                synchronized (resource2) {
                    System.out.println("Thread 1: удерживает resource 1 и 2.");
                }
            }
        });

         Thread thread2 = new Thread(() -> {
             synchronized (resource2) {
                 System.out.println("Thread 2: удерживает resource 2.");

                 try {
                     Thread.sleep(50);
                 } catch (InterruptedException e) {};

                 System.out.println("Thread 2: ожидает resource 1.");

                 synchronized (resource1) {
                     System.out.println("Thread 1: удерживает resource 2 и 1.");
                 }
             }
         });

         thread1.start();
         thread2.start();
    }
}
