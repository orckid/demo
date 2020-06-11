package com.orc.demo.util.io;

import java.io.*;

/**
 * @author orckid
 */
public class IoUtils {

    public static void writeFile(String filePath) {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }
            BufferedWriter out = new BufferedWriter(new FileWriter(filePath));
            out.write("写文件测试，第一行...");
            out.append("继续");
            out.newLine();
            out.write("第二行...");
            out.newLine();
            out.write("第3行...");
            out.flush();
            out.close();

        } catch (IOException ignored) {}

    }

    public static String readFile(String filePath) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader in = new BufferedReader(new FileReader(filePath));
            while (in.ready()) {
                sb.append(in.readLine()).append(File.separator);
            }
        } catch (IOException ignored) {}

        return sb.toString();
    }

}
