package manager;

import entity.Disk;
import entity.FileInfo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class DiskManagerProxy {

    private static DiskManagerProxy diskManagerProxy;

    private static final int NUMBER_OF_DISKS = 2;

    private DirectoryManager directoryManager = new DirectoryManager();

    private final ReadWriteLock rwLock = new ReentrantReadWriteLock();
    private final Lock readLock = rwLock.readLock();
    private final Lock writeLock = rwLock.writeLock();


    private int[] diskFreeSectors = {0, 0};
    //private int []ffdiskFreeSectors = new int[NUMBER_OF_DISKS];

    private Disk[] disk = new Disk[NUMBER_OF_DISKS];

    public DiskManagerProxy() {
        for (int i = 0; i < NUMBER_OF_DISKS; i++) {
            disk[i] = new Disk();
        }
    }

    public static DiskManagerProxy newInstance(){
        if(diskManagerProxy==null){
            diskManagerProxy = new DiskManagerProxy();
        }
        return diskManagerProxy;
    }

    public void write(int diskNumber, String key, StringBuffer[] content, int fileLength) {
        try {
            writeLock.lock();

            int startingSector = diskFreeSectors[diskNumber];
            FileInfo fileInfo = new FileInfo(diskNumber, startingSector, fileLength);

            directoryManager.enter(key, fileInfo);
            for (int i = 0; i < fileLength; i++) {
                disk[diskNumber].write(startingSector + i, content[i]);
            }
            diskFreeSectors[diskNumber] = startingSector + fileLength;

        }finally {
            writeLock.unlock();
        }
    }

    public StringBuffer[] read(String key) {
        try{
            readLock.lock();

            FileInfo fileInfo = directoryManager.lookup(key);

            int diskNumber = fileInfo.getDiskNumber();
            int startingSector = fileInfo.getStartingSector();
            int fileLength = fileInfo.getFileLength();

            StringBuffer []stringBuffers = new StringBuffer[fileLength];

            for (int i=0;i<fileLength;i++){
                stringBuffers[i] = disk[diskNumber].read(startingSector+i);
                //System.out.println(stringBuffers[i]);
            }

            return stringBuffers;
        }finally {
            readLock.unlock();
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
