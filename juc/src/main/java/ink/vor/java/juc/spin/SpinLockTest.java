package ink.vor.java.juc.spin;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author muquanrui
 * @date 2022/10/1 12:55
 */

public class SpinLockTest {
    public static void main(String[] args) {
        SpinLockTest spinLockTest = new SpinLockTest();
        new Thread(() -> {
            spinLockTest.myLock();
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLockTest.myUnlock();
        }, "AA").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            spinLockTest.myLock();
            spinLockTest.myUnlock();
        }, "BB").start();
    }

    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void myLock() {
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + "\t come in o_o");
        while (!atomicReference.compareAndSet(null, thread));
    }

    public void myUnlock() {
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread, null);
        System.out.println(Thread.currentThread().getName() + "\t come out !!!");
    }
}
