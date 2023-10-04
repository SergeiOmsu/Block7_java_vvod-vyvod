import java.io.*;

public class SerializeHumans {
    public void houseSerialize(House house, OutputStream fileName) {
        try (ObjectOutput out = new ObjectOutputStream(fileName)) {
            out.writeObject(house);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public House houseDeserialize(InputStream fileName) {
        try (ObjectInputStream in = new ObjectInputStream(fileName)) {
            return (House) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}