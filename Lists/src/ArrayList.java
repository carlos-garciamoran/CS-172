import java.util.Iterator;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private int capacity;
    private int length;
    private T[] data;

    public ArrayList() {
        capacity = 1;
        length = 0;
        data = (T[])new Object[capacity];
    }

    public void add(T item) {
        StdOut.printf("S l=%d, c=%d\n", length, capacity);
        if (length >= capacity) resize();
        assert length < capacity;
        data[length++] = item;
        StdOut.printf("E l=%d, c=%d\n", length, capacity);
    }

    private void resize() {
        int newCapacity = capacity * 2;
        T[] newData = (T[])new Object[newCapacity];
        for (int i = 0; i < length; ++i)
            newData[i] = data[i];
        capacity = newCapacity;
        data = newData;
    }

    public void clear() {
        capacity = 1;
        length = 0;
        data = (T[])new Object[capacity];
    }

    public boolean contains(T item) {
        for (int i = 0; i < length; i++)
            if (item == data[i])
                return true;

        return false;
    }

    public T get(int index) {
        return data[index];
    }

    public int indexOf(T item) {
        for (int i = 0; i < length; i++)
            if (item == data[i])
                return i;

        return -1;
    }

    public void removeAt(int index) {
        for (int i = 0; i < length; i++)
            if (index < i || (index == i && i < length-1))
                data[i] = data[i + 1];

        data[--length] = null;
        capacity /= 2;
    }

    public void set(int i, T item) {
        data[i] = item;
    }

    public int size() {
        return length;
    }

    public String toString() {
        String string = "[";
        StdOut.println(length);
        for (int i = 0; i < length; i++) {
            StdOut.println(i);
            string += data[i].toString();
            if (i != length - 1)
                string += ", ";
        }

        return string + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArrayList<?> that = (ArrayList<?>)o;
        if (length != that.length) return false;
        for (int i = 0; i < length; i++) {
            if (!Objects.equals(data[i], that.data[i])) {
                return false;
            }
        }
        return true;
    }

    public Iterator<T> iterator() {
        return new ArrayListIterator();
    }
    private class ArrayListIterator implements Iterator<T> {
        private int i = 0;

        public boolean hasNext() {
            return i < length;
        }

        public T next() {
            return data[i++];
        }
    }
}
