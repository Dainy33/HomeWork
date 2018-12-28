package com.dainy33.naughty.Util;

import java.util.concurrent.ConcurrentHashMap;

public class DiskResourceProxy {

    private static boolean isFree[];
    private static final int NUMBER_OF_DISKS = 2;
    private static DiskResourceProxy diskResourceProxy;

    private DiskResourceProxy() {
        isFree = new boolean[NUMBER_OF_DISKS];
        for (int i = 0; i < isFree.length; ++i) {
            isFree[i] = true;
        }
        //System.out.println("Init PrinterResourceProxy");
    }

    public synchronized static DiskResourceProxy newInstance() {

        if (diskResourceProxy == null) {
            diskResourceProxy = new DiskResourceProxy();
        }
        return diskResourceProxy;
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
