package Util;

public class PrinterResourceProxy {

    private static boolean isFree[];
    private static final int NUMBER_OF_PRINTERS = 3;
    private static PrinterResourceProxy printerResourceProxy;

    public PrinterResourceProxy() {
        isFree = new boolean[NUMBER_OF_PRINTERS];
        for (int i = 0; i < isFree.length; ++i) {
            isFree[i] = true;
        }
        //System.out.println("Init PrinterResourceProxy");
    }

    public synchronized static PrinterResourceProxy newInstance() {

        if (printerResourceProxy == null) {
            printerResourceProxy = new PrinterResourceProxy();
        }
        return printerResourceProxy;
    }

    public synchronized int request() {
        while (true) {
            for (int i = 0; i < isFree.length; ++i)
                if (isFree[i]) {
                    isFree[i] = false;
                    return i;
                }
            try {
                this.wait(); // block until someone releases a Resource
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void release(int index) {
        isFree[index] = true;
        this.notify(); // let a waiting thread run
    }

}
/**
 * @program: Homework8-9
 * @description:
 * @author: Dainy33
 * @create: 2018-11-29 15:44
 **/
