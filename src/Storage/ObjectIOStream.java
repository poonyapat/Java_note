package Storage;

import java.io.*;

public class ObjectIOStream {
    public void writeObject(Serializable sObj, String fileName) {
        try {
            FileOutputStream fileOut = new FileOutputStream("data/" + fileName);
            ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
            objOut.writeObject(sObj);
            fileOut.close();
        } catch (FileNotFoundException e) {
            File file = new File("data");
            if (!file.exists()) {
                if (!file.mkdir())
                    System.err.println("Cannot find/create 'data dir'");
                else
                    writeObject(sObj, fileName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Object readObject(String fileName) {
        Object object = null;
        try {
            FileInputStream fileIn = new FileInputStream("data/" + fileName);
            ObjectInputStream objIn = new ObjectInputStream(fileIn);
            object = objIn.readObject();
        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return object;
    }

    public AccountSystem readAccountSystemFile(String fileName) {
        Object object = readObject(fileName);
        if (object instanceof AccountSystem)
            return (AccountSystem) object;
        else
            return null;
    }

    public UserData readUserDataFile(String fileName) {
        Object object = readObject(fileName);
        if (object instanceof UserData)
            return (UserData) object;
        else
            return null;
    }
}
