package lab.task7;

import java.util.AbstractList;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

class CustomArray<T> extends AbstractList<T> implements Collection<T> {
    private final int from;
    private final int to;
    private final T[] array;

    public CustomArray(int from, int to) {
        if (from > to) {
            throw new IllegalArgumentException("'from' should be less than or equal to 'to'");
        }
        this.from = from;
        this.to = to;
        this.array = (T[]) new Object[to - from + 1];
    }

    @Override
    public int size() {
        return to - from + 1;
    }

    @Override
    public T get(int index) {
        if (index >= size() / 2 + 1) {
            throw new IndexOutOfBoundsException("Index is out of range");
        }

        return array[size() / 2 + index];
    }

    @Override
    public boolean add(T element) {
        throw new UnsupportedOperationException("This operation is not supported");
    }

    @Override
    public T set(int index, T element) {
        if (index >= size() / 2 + 1) {
            throw new IndexOutOfBoundsException("Index is out of range");
        }
        T oldValue = array[index + size() / 2];
        array[index + size() / 2] = element;
        return oldValue;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private int currentIndex = from;

            @Override
            public boolean hasNext() {
                return currentIndex <= to;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return array[currentIndex++ - from];
            }
        };
    }
}

public class Main {
    public static void main(String[] args) {
        try {
            int from = -10, to = 10;
            CustomArray<Integer> array = new CustomArray<>(from, to);
            for (int i = from; i <= to; i++) {
                array.set(i, i);
            }
            System.out.println("Array: " + array);
            System.out.println("Array element by index -5: " + array.get(-5));
            System.out.println("Size: " + array.size());

            System.out.println("Iterating through the array:");
            Iterator<Integer> iterator = array.iterator();
            while (iterator.hasNext()) {
                System.out.print(iterator.next());
                if (iterator.hasNext()) {
                    System.out.print(", ");
                }
            }
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}