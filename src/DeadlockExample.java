public class DeadlockExample {
    private static final Object resource1 = new Object();
    private static final Object resource2 = new Object();

    public static void main(String[] args) {
        Runnable procces1 = startProcces(resource1, resource2, "Thread 1");
        Runnable procces2 = startProcces(resource2, resource1, "Thread 2");

        Thread thread1 = new Thread(procces1);
        Thread thread2 = new Thread(procces2);

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Эта строка не должна быть достигнута из-за взаимной блокировки.");
    }

    private static Runnable startProcces(Object resource1, Object resource2, String thread) {
        return () -> {
            synchronized (resource1) {
                System.out.println(thread + ": удерживает " + resource1 + ".");

                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                ;

                System.out.println(thread + ": ожидает " + resource2 + ".");

                synchronized (resource2) {
                    System.out.println(thread + ": удерживает resources.");
                }
            }
        };
    }
}
