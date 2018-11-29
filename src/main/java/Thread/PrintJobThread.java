package Thread;

import Util.PrinterResourceProxy;
import entity.Printer;
import manager.DiskManager;

public class PrintJobThread implements Runnable{

    private DiskManager diskManager = null;

    private Printer printer = null;

    private String fileName = null;

    public PrintJobThread(DiskManager diskManager,String fileName) {
        this.diskManager = diskManager;
        this.fileName = fileName;
    }

    @Override
    public void run() {
        //TODO
        this.printer = new Printer(diskManager);
        printer.print(fileName);
    }
}
/**
 * @program: Homework8-9
 * @description:
 * @author: Dainy33
 * @create: 2018-11-28 10:40
 **/
