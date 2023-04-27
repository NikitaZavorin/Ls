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


    void outputInfo(String inputFile, String outputFile) throws IOException {
        if (inputFile == null) {
            throw new IllegalArgumentException("Input error.");
        }
        File file = new File(inputFile);
        if (!file.exists()) {
            throw new IllegalArgumentException("Input error.");
        }
        StringBuilder build = new StringBuilder();
        //ArrayList<String> list = new ArrayList<String>();
        if (file.isDirectory()) {
            if (!longFormat) build = Info(inputFile);
            if (longFormat) build = DirectionInfo(inputFile);
        }
        if (file.isFile()) build = fileList(inputFile);
        if (outputFile.equals("")) {
            if (!reverse || longFormat || file.isFile()) {
                System.out.println(build);
                /*for (String line : list) {
                    System.out.println(line);
                }*/
            } else {
                Collections.reverse(Collections.singletonList(build));
                System.out.println(build);
                /*for (String line : list) {
                    System.out.println(line);
                }*/
            }
        } else {
            write(build, outputFile);
        }
    }


    private StringBuilder Info(String inputFile) {
        File file = new File(inputFile);
        File[] listOfFiles = file.listFiles();
        //String[] list = new String[listOfFiles.length];
        StringBuilder[] build = new StringBuilder[listOfFiles.length];
        if (listOfFiles.length == 0) {
            throw new IllegalArgumentException("Directory is empty");
            //System.out.println("Directory is empty");
        } else {
            for (int i = 0; i < listOfFiles.length; i++) {
                build[i] = new StringBuilder(listOfFiles[i].getName());
            }
            Arrays.stream(build).sorted();
            //Arrays.sort(list);
            return new StringBuilder(String.valueOf(Arrays.asList(build)));
            //return new ArrayList<>(Arrays.asList(list));
        }
        //return new ArrayList<>(Arrays.asList(list));
    }


    StringBuilder DirectionInfo(String inputFile) {
        File file = new File(inputFile);
        File[] listOfFiles = file.listFiles();
        //Replace to stringbuilder
        StringBuilder build = new StringBuilder();
        //ArrayList<String> list = new ArrayList<>();
        if (listOfFiles.length == 0) throw new IllegalArgumentException("Directory is empty");
        //System.out.println("Directory is empty");
        if (reverse) {
            for (int i = listOfFiles.length - 1; i >= 0; i--) {
                if (!humanReadable) {
                    build.append(listOfFiles[i].getName());
                    build.append(listOfFiles[i].lastModified()).append(" ");
                    build.append(listOfFiles[i].length()).append(" Bytes");
                    build.append(" ");
                } else {
                    build.append(listOfFiles[i].getName());
                    build.append(getTime(listOfFiles[i]));
                    build.append(fromBytes(listOfFiles[i]));
                    build.append(" ");
                }
            }
        } else {
            for (File file1 : listOfFiles) {
                if (!humanReadable) {
                    build.append(file1.getName());
                    build.append(file1.lastModified()).append(" ");
                    build.append(file1.length()).append(" Bytes");
                    build.append(" ");
                } else {
                    build.append(file1.getName());
                    build.append(getTime(file1));
                    build.append(fromBytes(file1));
                    build.append(" ");
                }
            }
        }


        return build;
    }


    private static String getTime(File file) {
        SimpleDateFormat Str = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return Str.format(file.lastModified());
    }

    private StringBuilder fileList(String inputFile) {
        File file = new File(inputFile);
        //ArrayList<String> list = new ArrayList<String>();
        StringBuilder builder = new StringBuilder();
        if (!humanReadable) {
            builder.append(file.getName());
            builder.append(file.lastModified()).append(" ");
            builder.append(file.length()).append(" Bytes");
            builder.append(" ");
        } else {
            builder.append(file.getName());
            builder.append(getTime(file));
            builder.append(fromBytes(file));
            builder.append(" ");
        }
        return builder;
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


    private void write(StringBuilder list, String outputFile) {
        try {
            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(outputFile)));
            if (!reverse) {
                String l = list.toString();
                writer.write(l);
                /*for (String line : list) {
                    writer.write(line + "\n");
                }*/
                writer.flush();
                writer.close();

            } else {
                list.reverse();
                //Collections.reverse(list);
                /*for (String line : list) {
                    writer.write(line + "\n");
                }*/
                String l = list.toString();
                writer.write(l + "\n");
                writer.flush();
                writer.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


}
