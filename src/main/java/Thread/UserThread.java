package Thread;

import manager.DiskManager;

import java.io.*;

public class UserThread extends Thread {

    private static final int LENGTH = 50;

    private String filePath = null;

    private DiskManager diskManager = null;

    private String key = null;

    private int diskNumber = 0;

    private StringBuffer[] content = new StringBuffer[LENGTH];

    private int length = 0;

    public UserThread(String filePath,DiskManager diskManager) {
        this.filePath = filePath;
        this.diskManager = diskManager;
    }

    @Override
    public void run() {
        try {

            File file = new File(filePath);// Text文件
            BufferedReader br = new BufferedReader(new FileReader(file));// 构造一个BufferedReader类来读取文件
            String str = null;
            while ((str = br.readLine()) != null) {
                if (str.startsWith(".save")) {
                    //reset
                    key = null;
                    content = new StringBuffer[LENGTH];
                    length = 0;
                    diskNumber = 0;

                    key = str.substring(".save ".length());

                    int[] diskFreeSectors = diskManager.getDiskFreeSectors();
                    System.out.println(Thread.currentThread().getName() + " created file " + key + " on disk" + diskNumber +" at sector " + diskFreeSectors[diskNumber]);

                } else if (str.startsWith(".end")) {


                    //TODO getDisk  diskNumber = ResourceManager.getDiskNumber();
                    diskManager.write(diskNumber, key, content, length);

                } else if (str.startsWith(".print")) {

                    String fileName = str.substring(".print ".length());

                    //TODO getPrinter OK?
                    Thread thread = new Thread(new PrintJobThread("0",diskManager,fileName));
                    thread.start();

                } else {
                    content[length] = new StringBuffer(str);
                    length++;
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
/**
 * @program: Homework8-9
 * @description:
 * @author: Dainy33
 * @create: 2018-11-28 09:39
 **/
