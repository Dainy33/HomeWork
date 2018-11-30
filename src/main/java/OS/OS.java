package OS;

import Thread.UserThread;
import Util.DiskResourceProxy;
import manager.DiskManager;
import org.junit.Test;

public class OS {
    private static final int NUMBER_OF_USERS = 4;
    private static final int NUMBER_OF_DISKS = 2;
    private static final int NUMBER_OF_PRINTERS = 3;
    private static final String CLASSPATH = "/home/dainy33/Public/";


    private UserThread userThread1 = new UserThread(CLASSPATH + "USER1.txt");
    private UserThread userThread2 = new UserThread(CLASSPATH + "USER2.txt");
    private UserThread userThread3 = new UserThread(CLASSPATH + "USER3.txt");
    private UserThread userThread4 = new UserThread(CLASSPATH + "USER4.txt");


    @Test
    public void readTest() throws InterruptedException {
        System.out.println("[Simulation Started] " + NUMBER_OF_DISKS + " Disks, " + NUMBER_OF_USERS + " Users, " + NUMBER_OF_PRINTERS + " Printers");
        userThread1.start();
        userThread2.start();
        userThread3.start();
        userThread4.start();
        Thread.sleep(1000000000);
        System.out.println("__________________________________________");
    }

    @Test
    public void Proxy() {
        System.out.println(DiskResourceProxy.newInstance().request());
        System.out.println(DiskResourceProxy.newInstance().request());
        System.out.println(DiskResourceProxy.newInstance().request());
    }

}
/**
 * @program: Homework8-9
 * @description:
 * @author: Dainy33
 * @create: 2018-11-28 09:39
 **/
