package Util;

public class PrinterResourceProxy extends ResourceProxy {

    private static PrinterResourceProxy printerResourceProxy;

    public PrinterResourceProxy()
    {
        super(NUMBER_OF_PRINTERS);
    }

    public static PrinterResourceProxy newInstance() {

        if(printerResourceProxy==null){
            printerResourceProxy = new PrinterResourceProxy();
        }
        return printerResourceProxy;
    }
}
/**
 * @program: Homework8-9
 * @description:
 * @author: Dainy33
 * @create: 2018-11-29 15:44
 **/
