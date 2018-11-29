package entity;

import Util.PrinterResourceProxy;
import manager.DiskManager;

public class Printer {

    private static final String CLASSPATH = "/home/dainy33/Public/";


    private DiskManager diskManager = null;

    public Printer(DiskManager diskManager) {
        this.diskManager = diskManager;
    }

    public void print(String fileName) {

        int printerNumber = PrinterResourceProxy.newInstance().request();

        StringBuffer[] stringBuffers = diskManager.read(fileName);
        for (StringBuffer stringBuffer : stringBuffers) {
            System.out.println("Printer " + printerNumber + " printing data " + stringBuffer);
            try {
                Thread.sleep(2750);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        PrinterResourceProxy.newInstance().release(printerNumber);

    }
}
/**
 * @program: Homework8-9
 * @description:
 * @author: Dainy33
 * @create: 2018-11-28 09:39
 **/
