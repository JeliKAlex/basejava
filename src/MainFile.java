import java.io.File;

public class MainFile {
    public static void main(String[] args) {
        String filePath = ("../basejava");
        File dir = new File(filePath);
        int indentLevel = 0;

        getAllFiles(dir, indentLevel);
    }

    public static void getAllFiles(File dir, int level) {
        if (dir.isDirectory()) {
            System.out.println(getIndentLevel(level) + "[" + dir.getName() + "]");

            File[] files = dir.listFiles();
            if (files != null) {
                for (File file : files) {
                    getAllFiles(file, level++);
                }
            }
        } else {
            System.out.println(getIndentLevel(level) + dir.getName());
        }
    }

    public static String getIndentLevel(int level) {
        return "  ".repeat(Math.max(0, level));
    }
}
