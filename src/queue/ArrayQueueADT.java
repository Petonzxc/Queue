package queue;

/*
Model: a[1]..a[n]
Invariant: n>=0 && for i = 1..n: a[i] != null

Let immutable(n): for i = 1..n : a'[i] = a[i]

Pred: element != null
Post: n' = n + 1 && a[n'] = element && immutable(n)
enqueue(element)

Pred: n >= 1
Post: R = a[1] && immutable(n) && n' = n
element

Pred: n >= 1
Post: R = a[1] && n' = n - 1 && for i = 1...n': a'[i] = a[i + 1]
dequeue

Pred: true
Post: R = n && n' = n && immutable(n)
size

Pred: true
Post: R = (n == 0) && n' = n && immutable(n)
isEmpty

Pred: true
Post: n' = 0
clear
 */

import java.util.Objects;

public class ArrayQueueADT {
    private Object[] elements = new Object[2];
    private int size;
    private int head, tail;

    public static ArrayQueueADT create() {
        final ArrayQueueADT queue = new ArrayQueueADT();
        queue.elements = new Object[2];
        queue.head = 0;
        queue.size = 0;
        queue.tail = 0;
        return queue;
    }

    // Pred: element != null
    // Post: n' = n + 1 && a[n'] = element && immutable(n)
    public static void enqueue(final ArrayQueueADT queue, final Object element) {
        Objects.requireNonNull(element);
        ensureCapacity(queue, queue.size + 1);
        queue.elements[queue.tail] = element;
        queue.tail = getId(queue, queue.tail + 1);
        queue.size++;
    }

    // Pred: n >= 1
    // Post: R = a[1] && immutable(n) && n' = n
    public static Object element(final ArrayQueueADT queue) {
        assert queue.size >= 1;
        return queue.elements[queue.head];
    }

    // Pred: n >= 1
    // Post: R = a[1] && n' = n - 1 && for i = 1...n': a'[i] = a[i + 1]
    public static Object dequeue(ArrayQueueADT queue) {
        assert queue.size >= 1;
        Object result = queue.elements[queue.head];
        queue.elements[queue.head] = null;
        queue.head = getId(queue, queue.head + 1);
        queue.size--;
        return result;
    }

    private static int getId(final ArrayQueueADT queue, int id) {
        return (id + queue.elements.length) % queue.elements.length;
    }

    private static void ensureCapacity(ArrayQueueADT queue, final int capacity) {
        if(queue.elements.length < capacity) {
            Object[] newElements = new Object[capacity * 2];
            for(int i = 0; i < queue.size; i++) {
                newElements[i] = queue.elements[getId(queue, queue.head + i)];
            }
            queue.head = 0;
            queue.tail = queue.size;
            queue.elements = newElements;
        }
    }

    //Pred: true
    //Post: R = n && n' = n && immutable(n)
    public static int size(final ArrayQueueADT queue) {
        return queue.size;
    }

    // Pred: true
    // Post: R = (n == 0) && n' = n && immutable(n)
    public static boolean isEmpty(final ArrayQueueADT queue) {
        return queue.size == 0;
    }

    // Pred: true
    // Post: n' = 0
    public static void clear(ArrayQueueADT queue) {
        queue.size = 0;
        queue.head = 0;
        queue.tail = 0;
        queue.elements = new Object[2];
    }
}
