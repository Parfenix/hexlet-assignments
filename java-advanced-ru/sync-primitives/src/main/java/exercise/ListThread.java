package exercise;

// BEGIN
public class ListThread extends Thread {
    private final SafetyList list;

    public ListThread (SafetyList list) {
        this.list = list;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 1000; i++) {
                list.add(i);
                Thread.sleep(1);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
// END
