package entity;

public class Disk {
    private static final int NUM_SECTORS = 1024;
    private StringBuffer sectors[] = new StringBuffer[NUM_SECTORS];

    public Disk() {

    }

    public void write(int diskNumber,int startingSector, StringBuffer data) {
        sectors[startingSector] = data;
        System.out.println("Disk " + diskNumber + " writing data " + data + " at sector " + startingSector);

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public StringBuffer read(int sector) {
        return sectors[sector];
    }
}
/**
 * @program: Homework8-9
 * @description:
 * @author: Dainy33
 * @create: 2018-11-28 09:39
 **/
