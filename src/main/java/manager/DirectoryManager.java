package manager;

import entity.FileInfo;

import java.util.Hashtable;

public class DirectoryManager {
    //<FileName,FileInfo>
    private Hashtable<String, FileInfo> directory = new Hashtable<String, FileInfo>();


    public void enter(String key, FileInfo file) {
        directory.put(key, file);
    }

    public FileInfo lookup(String key) {
        FileInfo fileInfo = directory.get(key);
        return fileInfo;
    }
}
/**
 * @program: Homework8-9
 * @description:
 * @author: Dainy33
 * @create: 2018-11-28 09:44
 **/
