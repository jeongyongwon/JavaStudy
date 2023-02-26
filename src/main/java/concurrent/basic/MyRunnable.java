package concurrent.basic;

public class MyRunnable implements Runnable{

    private String str;

    public MyRunnable(String str) {
        this.str = str;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        System.out.println("thread name  ==>  " + name);
        for(int i=0; i < 10; i++) {
            System.out.println(str);
            try {
                Thread.sleep(1000); // 1초간 쉰다
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
