package Test;

import Util.DiskResourceProxy;
import Util.PrinterResourceProxy;
import entity.Printer;

public class SafeThread extends Thread{
    @Override
    public void run() {
        int i = DiskResourceProxy.newInstance().request();
        System.out.println(i);
    }
}
/**
 * @program: Homework8-9
 * @description:
 * @author: Dainy33
 * @create: 2018-11-30 11:53
 **/
