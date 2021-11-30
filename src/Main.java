import java.io.*;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class Main {

    public static File folder = new File("C:/Users/HL183/Desktop/9.4知识竞赛");

    public static void main(String[] args) {
        final List<String> all = read();
        final List<String> current = getFiles();
        System.out.println("目前已交: " + current.size() + "人");
        all.removeAll(current);
        System.out.println("还有" + all.size() + "人没交");
        System.out.println("没交的人有：" + String.join(",", all));
    }

    public static List<String> read() {
        final List<String> result = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(new File(folder, "classmates.txt"));
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String read;
            while ((read = bufferedReader.readLine()) != null) {
                result.add(read);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("文件未找到!");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("读取文件出错!");
        }
        return result;
    }

    public static List<String> getFiles() {
        final List<String> classmates = new ArrayList<>();
        File[] files = folder.listFiles(pathname -> pathname.getName().endsWith(".png") || pathname.getName().endsWith(".jpg"));
        if (files != null && files.length != 0) {
            for (File file : files) {
                final String name = file.getName().replace(".png", "").replace(".jpg", "");
                classmates.add(name);
            }
        }
        return classmates;
    }

    public static void write() {
        final List<String> classmates = getFiles();
        FileWriter writer = null;
        BufferedWriter bufferedWriter = null;
        try {
            writer = new FileWriter(new File(folder, "classmates.txt"), true);
            bufferedWriter = new BufferedWriter(writer, 100);
            BufferedWriter finalBufferedWriter = bufferedWriter;
            classmates.forEach(s -> {
                try {
                    finalBufferedWriter.write(s);
                    finalBufferedWriter.newLine();
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("写入文件出错!");
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("写入文件出错!");
        } finally {
            try {
                if (bufferedWriter != null) {
                    bufferedWriter.flush();
                    bufferedWriter.close();
                }
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("关闭数据流出错!");
            }
        }
    }
}
