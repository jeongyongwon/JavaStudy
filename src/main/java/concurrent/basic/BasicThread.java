package concurrent.basic;

public class BasicThread {
    public static void main(String[] args) {
        String name = Thread.currentThread().getName();
        System.out.println("thread name = " + name);
        MyThread mt1 = new MyThread("*");
        MyThread mt2 = new MyThread("+");

        //3. thread는 start() 메소르로 실행한다.
        /*
        * 기본적으로 프로그램은 main method가 끝날 때 프로그램이 종료된다
        * 하지만 멀티스레드일 때는 모든 스레드가 작업이 종료될 때 종료된다고 생각하면 된다
        * */
        mt1.start();
        mt2.start();

        System.out.println("end!");
    }
}
