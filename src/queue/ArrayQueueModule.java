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
    private static int head = 0;

    // Pred: element != null
    // Post: n' = n + 1 && a[1] = element && for i = 1..n: a[i] = a'[i + 1]
    public static void push(final Object element) {
        Objects.requireNonNull(element);
        ensureCapacity(size + 1);
        head = getId(head - 1);
        elements[head] = element;
        size++;
    }

    // Pred: element != null
    // Post: (if exists i: a[i] == element && for all j [0, i) a[j] != element, R = j
    // else R = -1) && immutable && n = n'
    public static int indexOf(final Object element) {
        for(int i = 0; i < size; i++) {
            if(elements[getId(head + i)].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    // Pred: element != null
    // Post: R = max(-1, j : a[j] == element, j = 1..n) && immutable && n = n'
    public static int lastIndexOf(final Object element) {
        for(int i = 0; i < size; i++) {
            if(elements[getId(getTail() - i - 1)].equals(element)) {
                return size - i - 1;
            }
        }
        return -1;
    }

    // Pred: n >= 1
    // Post: R = a[n] && n' = n && immutable(n')
    public static Object peek() {
        assert size >= 1;
        return elements[getId(getTail() - 1)];
    }

    // Pred: n >= 1
    // Post: R = a[n] && n' = n - 1 && for i = 1...n': a'[i] = a[i + 1]
    public static Object remove() {
        assert size >= 1;
        size--;
        Object result = elements[getTail()];
        elements[getTail()] = null;
        return result;
    }

    // Pred: element != null
    // Post: n' = n + 1 && a[n'] = element && immutable(n)
    public static void enqueue(final Object element) {
        Objects.requireNonNull(element);
        ensureCapacity(size + 1);
        elements[getTail()] = element;
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
        elements = new Object[2];
    }

    private static int getTail() {
        return getId(head + size);
    }
}
