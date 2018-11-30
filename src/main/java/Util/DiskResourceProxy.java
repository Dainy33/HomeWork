package Util;

public class DiskResourceProxy extends ResourceProxy {

    private static DiskResourceProxy diskResourceProxy;

    public DiskResourceProxy()
    {
        super(NUMBER_OF_DISKS);
    }

    public synchronized static DiskResourceProxy newInstance() {

        if(diskResourceProxy==null){
            diskResourceProxy = new DiskResourceProxy();
        }
        return diskResourceProxy;
    }
}
/**
 * @program: Homework8-9
 * @description:
 * @author: Dainy33
 * @create: 2018-11-29 15:44
 **/
