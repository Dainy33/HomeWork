package Util;

//一个父类 (不是单例)  两个类继承它  子类都是单例模式 一个类操作父类的属性  另一个类的同一属性也变了 综上 他们公用一个单例父类?
public class ResourceProxy {
    protected static boolean isFree[];
    protected static final int NUMBER_OF_USERS = 4;
    protected static final int NUMBER_OF_DISKS = 2;
    protected static final int NUMBER_OF_PRINTERS = 3;

    public  ResourceProxy(int numberOfItems) {
        isFree = new boolean[numberOfItems];
        for (int i = 0; i < isFree.length; ++i) {
            isFree[i] = true;
        }
        System.out.println("Init ResourceProxy");
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
 * @create: 2018-11-28 11:49
 **/
