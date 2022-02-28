package queue;
import static queue.ArrayQueueADT.*;

public class ArrayQueueADTTest {
    public static void fill(ArrayQueueADT queue, String queueId) {
        for (int i = 0; i < 10; i++) {
            enqueue(queue, queueId + i);
        }
    }

    public static void dump(ArrayQueueADT queue) {
        while (!isEmpty(queue)) {
            Object first1 = element(queue);
            Object first2 = dequeue(queue);
            assert first1 == first2;
            System.out.println(
                    size(queue) + 1 + " " +
                            first1
            );
        }
    }

    public static void main(String[] args) {
        ArrayQueueADT queue1 = create();
        ArrayQueueADT queue2 = create();
        fill(queue1, "q_1_");
        fill(queue2, "q_2_");
        clear(queue1);
        clear(queue2);
        assert isEmpty(queue1);
        assert isEmpty(queue2);
        fill(queue1, "q_1_");
        fill(queue2, "q_2_");
        dump(queue1);
        dump(queue2);
    }
}