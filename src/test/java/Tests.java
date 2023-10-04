import org.junit.Test;

import java.io.*;
import java.util.List;

import static org.junit.Assert.*;

//ASSERT'S
public class Tests {

    @Test
    public void TestBinaryReadFile() throws FileNotFoundException {
        File file = new File("src\\test\\TestBinary.txt");
        int[] expected = new int[5];
        for (int i = 0; i < 5; i++) {
            expected[i] = i + 1;
        }
        int[] arrTest = new int[5];
        try (DataOutputStream outputStream = new DataOutputStream(new FileOutputStream(file))) {
            for (int temp : expected) {
                outputStream.writeInt(temp);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        try (DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file))) {
            for (int i = 0; i < expected.length; i++) {
                arrTest[i] = dataInputStream.readInt();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        assertArrayEquals(expected, arrTest);
    }

    @Test
    public void TestCharReadFile() throws IOException {
        File file = new File("src\\test\\TestChar.txt"); // todo
        assertTrue(file.exists());
        try (BufferedReader a = new BufferedReader(new FileReader(file))) {
            String b = a.readLine();
            char[] b1 = b.toCharArray();
            char[] expected = new char[]{'a', 'b', 'c', 'd', 'e'};
            assertArrayEquals(expected, b1);
        }
    }

    @Test
    public void TestRandomAccess() throws IOException {
        File file = new File("src\\test\\TestRandomAccessFile.txt");
        assertTrue(file.exists());
        int[] arr = new int[5];
        int[] expected = new int[5];
        expected[0] = 6;
        expected[1] = 7;
        expected[2] = 8;
        expected[3] = 9;
        expected[4] = 0;
        try (RandomAccessFile raf = new RandomAccessFile(file, "r")) {
            raf.seek(10);
            String text = raf.readLine();
            String[] textArr = text.split(" ");
            for (int i = 0; i < textArr.length; i++) {
                arr[i] = Integer.parseInt(textArr[i]);
            }
            assertArrayEquals(expected, arr);
        }
    }

    @Test
    public void TestListFile() {
        File file = new File("C:\\Users\\Win10Pro");
        assertTrue(file.exists());
        File[] listFile = file.listFiles(new MyFileNameFilter(".txt"));
        File[] expected = new File[3];
        expected[0] = new File("C:\\Users\\Win10Pro\\Xyinya.txt");
        expected[1] = new File("C:\\Users\\Win10Pro\\Xyinya1.txt");
        expected[2] = new File("C:\\Users\\Win10Pro\\Xyinya2.txt");
        assertNotNull(listFile);
        assertTrue(listFile.length != 0);
        assertArrayEquals(expected, listFile);
    }

    @Test
    public void TestSerializeAndDeserialize() throws IOException {
        final String fileName = "C:\\Users\\Win10Pro\\VvodAndVivod\\src\\test\\TestSerialize.json";
        List<Person> persons = List.of(
                new Person("Асаевич", "Никита", "Сергеевич", 23, 2, 2003),
                new Person("Шанаурин", "Сергей", "???", 2, 4, 2002)
        );
        List<Flat> flats = List.of(new Flat(64, 30, persons));
        Person housewife = new Person("Асаевич", "Никита", "Сергеевич", 23, 2, 2003);
        House house = new House("123123", "Проспект Мира 57(а)", housewife, flats);

        try (FileOutputStream file = new FileOutputStream(fileName)) {
            SerializeHumans ser = new SerializeHumans();
            ser.houseSerialize(house, file);
        }

        try (FileInputStream file = new FileInputStream(fileName)) {
            SerializeHumans ser = new SerializeHumans();
            House houseTest = ser.houseDeserialize(file);
            assertEquals(houseTest, house);
        }
    }
    @Test
    public void TestJsonSerialize() throws IOException {
        Person person = new Person("Асаевич", "Никита", "Сергеевич", 23, 2, 2003);

        String jsonString = SerializeHumansJackson.serialize(person);
        String expected = "{\"firstName\":\"Асаевич\",\"middleName\":\"Никита\",\"lastName\":\"Сергеевич\",\"bDay\":23,\"bMouth\":2,\"bYear\":2003}";

       assertEquals(expected, jsonString);
    }
    @Test
    public void TestJsonDeSerialize1() throws IOException
    {
        String human = "{\"firstName\":\"Асаевич\",\"middleName\":\"Никита\",\"lastName\":\"Сергеевич\",\"bDay\":23,\"bMouth\":2,\"bYear\":2003}";
        Person person = SerializeHumansJackson.deserialize(human);
        Person expected = new Person("Асаевич", "Никита", "Сергеевич", 23, 2, 2003);
        assertEquals(expected, person);
    }
    @Test
    public void TestJsonDeSerialize2() throws IOException
    {
        String human = "123456";
        Person person = SerializeHumansJackson.deserialize(human);
        assertNull(person);
    }
}
