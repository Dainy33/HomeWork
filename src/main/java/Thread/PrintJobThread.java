package Thread;

import entity.Printer;
import manager.DiskManager;

public class PrintJobThread implements Runnable{

    private String printerName ;

    private DiskManager diskManager = null;

    private Printer printer = null;

    private String fileName = null;

    public PrintJobThread(String printerName, DiskManager diskManager,String fileName) {
        this.printerName = printerName;
        this.diskManager = diskManager;
        this.fileName = fileName;
        this.printer = new Printer(printerName,diskManager);
    }

    @Override
    public void run() {
        //TODO
        printer.print(fileName);
    }
}
/**
 * @program: Homework8-9
 * @description:
 * @author: Dainy33
 * @create: 2018-11-28 10:40
 **/
