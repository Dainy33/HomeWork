package manager;

public class PrinterResourceProxy extends ResourceManager{

    PrinterResourceProxy()
    {
        isFree = new boolean[NUMBER_OF_PRINTERS];
        for (int i=0; i<isFree.length; ++i)
            isFree[i] = true;
    }
}
/**
 * @program: Homework8-9
 * @description:
 * @author: Dainy33
 * @create: 2018-11-29 15:44
 **/
