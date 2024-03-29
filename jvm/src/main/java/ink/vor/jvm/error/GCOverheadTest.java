package ink.vor.jvm.error;

import java.util.ArrayList;
import java.util.List;

/**
 * @author muquanrui
 * @date 2022/10/3 20:23
 */

// -Xms10m -Xmx10m -XX:MaxDirectMemorySize=5m -XX:+PrintGCDetails
public class GCOverheadTest {
    public static void main(String[] args) {
        int i = 0;
        List<String> list = new ArrayList<>();
        try {
            while (true) {
                list.add(String.valueOf(i++).intern());
            }
        } catch (Exception e) {
            System.out.println("******i: " + i);
            e.printStackTrace();
        }
    }
}
