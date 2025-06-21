package exercise;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class SafetyList {
    // BEGIN
    private final List<Integer> list = Collections.synchronizedList(new ArrayList<>());

    public synchronized void add(int value) {
        list.add(value);
    }

    public synchronized int get(int index) {
        return list.get(index);
    }

    public synchronized int getSize() {
        return list.size();
    }
    // END
}
