package queue;

public class ArrayQueueModuleTest {
    public static void fill() {
        for (int i = 0; i < 10; i++) {
            ArrayQueueModule.enqueue(i);
        }
    }

    public static void dump() {
        while (!ArrayQueueModule.isEmpty()) {
            Object first1 = ArrayQueueModule.element();
            Object first2 = ArrayQueueModule.dequeue();
            assert first1 == first2;
            System.out.println(
                    ArrayQueueModule.size() + 1 + " " +
                            first1
            );
        }
    }

    public static void main(String[] args) {
        fill();
        ArrayQueueModule.clear();
        assert ArrayQueueModule.isEmpty();
        fill();
        dump();
    }
}
