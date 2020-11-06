
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Run {

    public static void main(String[] args) throws Exception {

        List<String> classNames = new ArrayList();

        ZipInputStream zip = new ZipInputStream(new FileInputStream("/Users/leventyildiz/development/git/seker-bank/sbank-workshop/training-examples/news-rss/target/news-rss-1.0.jar"));
        for (ZipEntry entry = zip.getNextEntry(); entry != null; entry = zip.getNextEntry()) {
            if (!entry.isDirectory() && entry.getName().endsWith(".class") && entry.getName().startsWith("com/ba")) {
                // This ZipEntry represents a class. Now, what class does it represent?
                String className = entry.getName().replace('/', '.'); // including ".class"
                classNames.add(className.substring(0, className.length() - ".class".length()));
            }
        }

        classNames.stream().filter(c -> !c.equals("com.ba.BaseModel")).forEach(className -> {
            try {

                Class<?> clz = Class.forName(className);

                Method method = clz.getDeclaredMethod("getEntries");

                Object object = clz.getConstructor().newInstance();
                method.invoke(object);

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
