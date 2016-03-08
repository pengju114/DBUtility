package pj.structure.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;

/**
 *
 * @author pengju
 */
public class PJMap extends HashMap implements Serializable {

    public void load(String filePath) throws IOException, ClassNotFoundException {
        load(new File(filePath));
    }

    public void load(File file) throws IOException, ClassNotFoundException {
        if (file.exists() && file.isFile()) {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
            PJMap map = null;
            try {
                map = (PJMap) in.readObject();
            } catch (IOException e) {
                System.out.println(e);
                throw e;
            } catch (ClassNotFoundException e) {
                System.out.println(e);
                throw e;
            } finally {
                in.close();
                if (map != null) {
                    putAll(map);
                }
            }
        }
    }

    public void store(String filePath) throws IOException {
        store(new File(filePath));
    }

    public void store(File file) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file, false));//覆盖原文件
        try {
            out.writeObject(this);
        } catch (IOException e) {
            System.out.println(e);
            throw e;
        } finally {
            out.close();
        }
    }
}
