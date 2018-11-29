package manager;

import Util.ResourceManager;

public class AAAResourceProxy extends ResourceManager {

    private static AAAResourceProxy aaaResourceProxy;

    public AAAResourceProxy()
    {
        super(NUMBER_OF_PRINTERS);
    }

    public synchronized static AAAResourceProxy newInstance() {
        if (aaaResourceProxy == null) {
            aaaResourceProxy = new AAAResourceProxy();
        }
        return aaaResourceProxy;
    }
}
/**
 * @program: Homework8-9
 * @description:
 * @author: Dainy33
 * @create: 2018-11-29 15:44
 **/
