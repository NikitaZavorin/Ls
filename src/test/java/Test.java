import javax.imageio.IIOException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

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
        String[] command = "-l -h -r -o test1Actual.txt .\\input".split(" ");

        LsCommands.main(command);

        File file1 = new File(".\\src\\test\\resources\\testActual\\test1Actual.txt");
        File file2 = new File(".\\src\\test\\resources\\testExpected\\test1Expected.txt");

        assertTrue(isEquals(file1.toPath(), file2.toPath()));
    }

    @org.junit.jupiter.api.Test
    void testLong() throws IOException {
        String[] command = "-l   .\\\\src\\\\test\\\\resources\\\\Output".split(" ");
        LsCommands.main(command);

        File file1 = new File(".\\src\\test\\resources\\testActual\\test2Actual.txt");
        File file2 = new File(".\\src\\test\\resources\\testExpected\\test2Expected.txt");

        assertTrue(isEquals(file1.toPath(), file2.toPath()));
    }

    @org.junit.jupiter.api.Test
    void testLong_Human() throws IOException {
        String[] command = "-l   .\\\\src\\\\test\\\\resources\\\\Output".split(" ");
        LsCommands.main(command);

        File file1 = new File(".\\src\\test\\resources\\testActual\\test3Actual.txt");
        File file2 = new File(".\\src\\test\\resources\\testExpected\\test3Expected.txt");

        assertTrue(isEquals(file1.toPath(), file2.toPath()));
    }

    @org.junit.jupiter.api.Test
    void testLHuman() throws IOException {
        String[] command = "-l   .\\\\src\\\\test\\\\resources\\\\Output".split(" ");
        LsCommands.main(command);

        File file1 = new File(".\\src\\test\\resources\\testActual\\test4Actual.txt");
        File file2 = new File(".\\src\\test\\resources\\testExpected\\test4Expected.txt");

        assertTrue(isEquals(file1.toPath(), file2.toPath()));
    }

    @org.junit.jupiter.api.Test
    void testLHuman_Revers() throws IOException {
        String[] command = "-l   .\\\\src\\\\test\\\\resources\\\\Output".split(" ");
        LsCommands.main(command);

        File file1 = new File(".\\src\\test\\resources\\testActual\\test5Actual.txt");
        File file2 = new File(".\\src\\test\\resources\\testExpected\\test5Expected.txt");

        assertTrue(isEquals(file1.toPath(), file2.toPath()));
    }

}
