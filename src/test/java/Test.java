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
        String[] command = "-l -h -o .\\src\\test\\resources\\output\\Empty.txt .\\src\\test\\resources\\input".split(" ");

        LsCommands.main(command);
        FileInputStream file = new FileInputStream(".\\src\\test\\resources\\output\\Empty.txt");
        int c = file.read();
        String ans = "";
        while (c != -1) {
            ans += (char) c;
            c = file.read();
        }

        assertEquals("lol.txt13/04/2023 21:57:440.8916015625 KB lol2.txt13/04/2023 21:57:500.0029296875 KB ", ans);
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
        assertEquals("lol.txt1681412264903 913 Bytes lol2.txt1681412270165 3 Bytes ", ans);
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
        assertEquals("lol.txt13/04/2023 21:57:440.8916015625 KB lol2.txt13/04/2023 21:57:500.0029296875 KB ", ans);
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
        assertEquals("[lol.txt, lol2.txt]", ans);


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
        assertEquals("913 Bytes 1681412264903 lol.txt 3 Bytes 1681412270165 lol2.txt", ans);
    }

}
