import org.junit.Test;

public class SomeTest {

    @Test
    public void test() throws Exception {
        System.out.println("begin, pid=" + pid());
        Thread.sleep(1000);
        //Thread.currentThread().join();
        System.out.println("end, pid=" + pid());
    }

    public static long pid() {
        String processName =
                java.lang.management.ManagementFactory.getRuntimeMXBean().getName();
        return Long.parseLong(processName.split("@")[0]);
    }
}