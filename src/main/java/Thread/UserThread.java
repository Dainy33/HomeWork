package Thread;

import manager.DiskManager;

import java.io.*;

public class UserThread extends Thread {

    private static final int LENGTH = 50;

    private String filePath = null;

    private String key = null;

    private StringBuffer[] content = new StringBuffer[LENGTH];

    private int length = 0;

    public UserThread(String filePath) {
        this.filePath = filePath;
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

                    key = str.substring(".save ".length());

                } else if (str.startsWith(".end")) {


                    DiskManager.newInstance().write(key, content, length);

                } else if (str.startsWith(".print")) {

                    String fileName = str.substring(".print ".length());

                    Thread thread = new Thread(new PrintJobThread(fileName));
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
