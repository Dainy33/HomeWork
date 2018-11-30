package manager;

import Util.DiskResourceProxy;
import entity.Disk;
import entity.FileInfo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class DiskManager {

    private static DiskManager diskManager;

    private static final int NUMBER_OF_DISKS = 2;

    private static DirectoryManager directoryManager;

    private static Disk[] disk = new Disk[NUMBER_OF_DISKS];

    private static final ReadWriteLock []rwLock = new ReentrantReadWriteLock[2];
    private static final Lock []readLock = new Lock[2];
    private static final Lock []writeLock = new Lock[2];

    //private int[] diskFreeSectors = {0, 0};
    private static int[] diskFreeSectors = new int[NUMBER_OF_DISKS];


    public DiskManager() {

        directoryManager = new DirectoryManager();

        for (int i = 0; i < NUMBER_OF_DISKS; i++) {
            disk[i] = new Disk();
            diskFreeSectors[i] = 0;
            rwLock[i] = new ReentrantReadWriteLock();
            readLock[i] = rwLock[i].readLock();
            writeLock[i] = rwLock[i].writeLock();
        }
    }

    public static DiskManager newInstance() {

        if (diskManager == null) {
            diskManager = new DiskManager();
        }

        return diskManager;
    }


    public void write(String key, StringBuffer[] content, int fileLength) {

        //diskNumber必须为方法的局部变量
        int  diskNumber = -1;

        try {

            diskNumber = DiskResourceProxy.newInstance().request();

            System.out.println(Thread.currentThread().getName() + " created file " + key + " on disk" + diskNumber + " at sector " + diskFreeSectors[diskNumber]);

            int startingSector = diskFreeSectors[diskNumber];
            FileInfo fileInfo = new FileInfo(diskNumber, startingSector, fileLength);

            directoryManager.enter(key, fileInfo);
            for (int i = 0; i < fileLength; i++) {
                disk[diskNumber].write(startingSector + i, content[i]);
            }
            diskFreeSectors[diskNumber] = startingSector + fileLength;

        } finally {

            DiskResourceProxy.newInstance().release(diskNumber);
        }
    }

    public StringBuffer[] read(String key) {
        try {

            FileInfo fileInfo = directoryManager.lookup(key);

            int diskNumber = fileInfo.getDiskNumber();
            int startingSector = fileInfo.getStartingSector();
            int fileLength = fileInfo.getFileLength();

            StringBuffer[] stringBuffers = new StringBuffer[fileLength];

            for (int i = 0; i < fileLength; i++) {
                stringBuffers[i] = disk[diskNumber].read(startingSector + i);
                //System.out.println(stringBuffers[i]);
            }

            return stringBuffers;
        } finally {
           // readLock.unlock();
        }
    }

    public int[] getDiskFreeSectors() {
        return diskFreeSectors;
    }
}
/**
 * @program: Homework8-9
 * @description:
 * @author: Dainy33
 * @create: 2018-11-28 09:44
 **/
