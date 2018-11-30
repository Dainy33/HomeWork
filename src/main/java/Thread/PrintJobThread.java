package Thread;

import entity.Printer;

public class PrintJobThread implements Runnable{

    private Printer printer = null;

    private String fileName = null;

    public PrintJobThread(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void run() {
        //TODO
        this.printer = new Printer();
        printer.print(fileName);
    }
}
/**
 * @program: Homework8-9
 * @description:
 * @author: Dainy33
 * @create: 2018-11-28 10:40
 **/
