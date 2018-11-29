package entity;

import manager.DiskManager;

public class Printer {

    private static final String CLASSPATH = "/home/dainy33/Public/";

    private String printerName ;

    private DiskManager diskManager = null;

    public Printer(String printerName,DiskManager diskManager) {
        this.printerName = printerName;
        this.diskManager = diskManager;
    }

    public void print(String fileName){
        StringBuffer[] stringBuffers = diskManager.read(fileName);
        for (StringBuffer stringBuffer:stringBuffers) {
            System.out.println(stringBuffer);
        }

    }
}
/**
 * @program: Homework8-9
 * @description:
 * @author: Dainy33
 * @create: 2018-11-28 09:39
 **/
