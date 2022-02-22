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

public class ArrayQueueModule {
    private static Object[] elements = new Object[2];
    private static int size = 0;
    private static int head = 0, tail = 0;

    // Pred: element != null
    // Post: n' = n + 1 && a[n'] = element && immutable(n)
    public static void enqueue(final Object element) {
        Objects.requireNonNull(element);
        ensureCapacity(size + 1);
        elements[tail] = element;
        tail = getId(tail + 1);
        size++;
    }

    // Pred: n >= 1
    // Post: R = a[1] && immutable(n) && n' = n
    public static Object element() {
        assert size >= 1;
        return elements[head];
    }

    // Pred: n >= 1
    // Post: R = a[1] && n' = n - 1 && for i = 1...n': a'[i] = a[i + 1]
    public static Object dequeue() {
        assert size >= 1;
        Object result = elements[head];
        elements[head] = null;
        head = getId(head + 1);
        size--;
        return result;
    }

    private static int getId(int id) {
        return (id + elements.length) % elements.length;
    }

    private static void ensureCapacity(final int capacity) {
        if(elements.length < capacity) {
            Object[] newElements = new Object[capacity * 2];
            for(int i = 0; i < size; i++) {
                newElements[i] = elements[getId(head + i)];
            }
            head = 0;
            tail = size;
            elements = newElements;
        }
    }

    //Pred: true
    //Post: R = n && n' = n && immutable(n)
    public static int size() {
        return size;
    }

    // Pred: true
    // Post: R = (n == 0) && n' = n && immutable(n)
    public static boolean isEmpty() {
        return size == 0;
    }

    // Pred: true
    // Post: n' = 0
    public static void clear() {
        size = 0;
        head = 0;
        tail = 0;
        elements = new Object[2];
    }
}
