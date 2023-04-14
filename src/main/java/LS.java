import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class LS {

    private final boolean longFormat;
    private final boolean humanReadable;
    private final boolean reverse;


    public LS(boolean longFormat,
              boolean humanReadable,
              boolean reverse) {

        this.longFormat = longFormat;
        this.humanReadable = humanReadable;
        this.reverse = reverse;
    }


    void OutputInfo(String inputFile, String outputFile) throws IOException {
        if (inputFile == null) {
            System.err.println("Input error.");
        } else {
            File file = new File(inputFile);
            if (!file.exists()) {
                System.err.println("Input error.");
            } else {
                ArrayList<String> list = new ArrayList<String>();
                if (file.isDirectory()) {
                    if (!longFormat) list = Info(inputFile);
                    if (longFormat) list = DirectionInfo(inputFile);
                }
                if (file.isFile()) list = fileList(inputFile);
                if (outputFile.equals("")) {
                    if (!reverse || longFormat || file.isFile()) {
                        for (String line : list) {
                            System.out.println(line);
                        }
                    } else {
                        Collections.reverse(list);
                        for (String line : list) {
                            System.out.println(line);
                        }
                    }
                } else {
                    write(list, outputFile);
                }
            }
        }
    }


    private ArrayList<String> Info(String inputFile) {
        File file = new File(inputFile);
        File[] listOfFiles = file.listFiles();
        String[] list = new String[listOfFiles.length];
        if (listOfFiles.length == 0) {
            System.out.println("Directory is empty");
        } else {
            for (int i = 0; i < listOfFiles.length; i++) {
                list[i] = listOfFiles[i].getName();
            }
            Arrays.sort(list);
            return new ArrayList<String>(Arrays.asList(list));
        }
        return new ArrayList<String>(Arrays.asList(list));
    }


    @NotNull
    ArrayList<String> DirectionInfo(String inputFile) {
        ArrayList<String> Info = new ArrayList<>();
        File file = new File(inputFile);
        File[] listOfFiles = file.listFiles();
        ArrayList<String> list = new ArrayList<String>();
        if (listOfFiles.length == 0) System.out.println("Directory is empty");
        if (reverse) {
            for (int i = listOfFiles.length - 1; i >= 0; i--) {
                if (humanReadable == false) {
                    list.add(listOfFiles[i].getName());
                    list.add(listOfFiles[i].lastModified() + " ");
                    list.add(listOfFiles[i].length() + " Bytes");
                } else {
                    list.add(listOfFiles[i].getName());
                    list.add(getTime(listOfFiles[i]));
                    list.add(fromBytes(listOfFiles[i]));
                }
            }
        } else {
            for (File file1 : listOfFiles) {
                if (humanReadable == false) {
                    list.add(file1.getName());
                    list.add(file1.lastModified() + " ");
                    list.add(file1.length() + " Bytes");
                } else {
                    list.add(file1.getName());
                    list.add(getTime(file1));
                    list.add(fromBytes(file1));
                }
            }
        }


        return list;
    }


    private static String getTime(File file) {
        SimpleDateFormat Str = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return Str.format(file.lastModified());
    }

    private ArrayList<String> fileList(String inputFile) {
        File file = new File(inputFile);
        ArrayList<String> list = new ArrayList<String>();
        if (humanReadable == false) {
            list.add(file.getName());
            list.add(file.lastModified() + " ");
            list.add(file.length() + " Bytes");
        } else {
            list.add(file.getName());
            list.add(getTime(file));
            list.add(fromBytes(file));
        }
        return list;
    }

    String fromBytes(File file) {
        String Line = new String();
        Long Lengh = file.length();
        if (Lengh >= 1073741824) {
            Line += (double) Lengh / 1073741824.0 + " GB";
        } else {
            if (Lengh >= 1048576) {
                Line += (double) Lengh / 1048576.0 + " MB";
            } else {
                Line += (double) Lengh / 1024.0 + " KB";
            }
        }
        return Line;
    }


    private void write(ArrayList<String> list, String outputFile) {
        try {
            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(outputFile)));
            if (!reverse) {
                for (String line : list) {
                    writer.write(line + "\n");
                }
                writer.flush();
                writer.close();

            } else {
                Collections.reverse(list);
                for (String line : list) {
                    writer.write(line + "\n");
                }
                writer.flush();
                writer.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


}
