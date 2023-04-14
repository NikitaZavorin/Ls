import javax.imageio.IIOException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.SimpleTimeZone;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Test {
    public static boolean isEquals(Path first, Path second) {
        try {
            if (Files.size(first) != Files.size(second))
                return false;
            byte[] first1 = Files.readAllBytes(first);
            byte[] second1 = Files.readAllBytes(second);
            return Arrays.equals(first1, second1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


    @org.junit.jupiter.api.Test
    void test1() throws IOException {
        String[] command = "-l -h -r -o .\\src\\test\\resources\\output\\Empty.txt .\\src\\test\\resources\\input".split(" ");

        LsCommands.main(command);
        FileInputStream file = new FileInputStream(".\\src\\test\\resources\\output\\Empty.txt");
        int c = file.read();
        String ans = "";
        while (c != -1) {
            ans += (char) c;
            c = file.read();
        }

        assertEquals("0.8916015625 KB\n" +
                "13/04/2023 21:57:44\n" +
                "lol.txt\n" +
                "0.0029296875 KB\n" +
                "13/04/2023 21:57:50\n" +
                "lol2.txt\n", ans);
    }

    @org.junit.jupiter.api.Test
    void testLong() throws IOException {
        String[] command = "-l -o .\\src\\test\\resources\\output\\Empty.txt .\\src\\test\\resources\\input".split(" ");
        LsCommands.main(command);
        FileInputStream file = new FileInputStream(".\\src\\test\\resources\\output\\Empty.txt");
        int c = file.read();
        String ans = "";
        while (c != -1) {
            ans += (char) c;
            c = file.read();
        }
        assertEquals("lol.txt\n" +
                "1681412264903 \n" +
                "913 Bytes\n" +
                "lol2.txt\n" +
                "1681412270165 \n" +
                "3 Bytes\n", ans);
    }

    @org.junit.jupiter.api.Test
    void testLong_Human() throws IOException {
        String[] command = "-l -h -o .\\src\\test\\resources\\output\\Empty.txt .\\src\\test\\resources\\input".split(" ");
        LsCommands.main(command);
        FileInputStream file = new FileInputStream(".\\src\\test\\resources\\output\\Empty.txt");
        int c = file.read();
        String ans = "";
        while (c != -1) {
            ans += (char) c;
            c = file.read();
        }
        assertEquals("lol.txt\n" +
                "13/04/2023 21:57:44\n" +
                "0.8916015625 KB\n" +
                "lol2.txt\n" +
                "13/04/2023 21:57:50\n" +
                "0.0029296875 KB\n",ans);
    }

    @org.junit.jupiter.api.Test
    void testLHuman() throws IOException {
        String[] command = "-h -o .\\src\\test\\resources\\output\\Empty.txt .\\src\\test\\resources\\input".split(" ");
        LsCommands.main(command);
        FileInputStream file = new FileInputStream(".\\src\\test\\resources\\output\\Empty.txt");
        int c = file.read();
        String ans = "";
        while (c != -1) {
            ans += (char) c;
            c = file.read();
        }
        assertEquals("lol.txt\n" +
                "lol2.txt\n",ans);


    }

    @org.junit.jupiter.api.Test
    void testLHuman_Revers() throws IOException {
        String[] command = "-l -r -o .\\src\\test\\resources\\output\\Empty.txt .\\src\\test\\resources\\input".split(" ");
        LsCommands.main(command);
        FileInputStream file = new FileInputStream(".\\src\\test\\resources\\output\\Empty.txt");
        int c = file.read();
        String ans = "";
        while (c != -1) {
            ans += (char) c;
            c = file.read();
        }
        assertEquals("913 Bytes\n" +
                "1681412264903 \n" +
                "lol.txt\n" +
                "3 Bytes\n" +
                "1681412270165 \n" +
                "lol2.txt\n",ans);
    }

}
