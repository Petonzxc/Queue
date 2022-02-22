package queue;

public class ArrayQueueTest1 {
    public static void fill(ArrayQueue queue, String queueId) {
        for (int i = 0; i < 10; i++) {
            queue.enqueue(queueId + i);
        }
    }

    public static void dump(ArrayQueue queue) {
        while (!queue.isEmpty()) {
            Object first1 = queue.element();
            Object first2 = queue.dequeue();
            assert first1 == first2;
            System.out.println(
                    queue.size() + 1 + " " +
                            first1
            );
        }
    }

    public static void main(String[] args) {
        ArrayQueue queue1 = new ArrayQueue();
        ArrayQueue queue2 = new ArrayQueue();
        fill(queue1, "q_1_");
        fill(queue2, "q_2_");
        queue1.clear();
        queue2.clear();
        assert queue1.isEmpty();
        assert queue2.isEmpty();
        fill(queue1, "q_1_");
        fill(queue2, "q_2_");
        dump(queue1);
        dump(queue2);
    }
}
