import java.util.Iterator;
import java.util.Objects;

public class LinkedList<T> implements List<T> {
    private Node head;

    private class Node {
        T item;
        Node next;

        public Node(T item) {
            this.item = item;
            this.next = null;
        }
    }

    public LinkedList() {
        head = null;
    }

    public void add(T item) {
        Node n = new Node(item);
        if (head == null) {
            head = n;
        }
        else {
            assert head != null;
            Node i = this.head;
            while (i.next != null)
                i = i.next;

            assert i != null;
            i.next = n;
        }
    }

    public void clear() {
        this.head = null;
    }

    public boolean contains(T item) {
        Node n = this.head;

        while (n != null) {
            if (n.item == item) return true;
            n = n.next;
        }

        return false;
    }

    public T get(int index) {
        int i = 0;
        Node n = head;
        while (i < index) {
            n = n.next;
            ++i;
        }
        return n.item;
    }

    public int indexOf(T item) {
        int i = 0;
        Node n = head;
        while (n != null) {
            if (n.item == item)     return i;
            n = n.next;
            i++;
        }

        return -1;
    }

    public void removeAt(int index) {
        if (index == 0) {
            head = head.next;
            return;
        }
        assert index > 0;
        int i = 0;
        Node n = head;
        Node p = null;
        while (i < index) {
            p = n;
            n = n.next;
            ++i;
        }
        assert p != null;
        p.next = n.next;
    }

    public void set(int i, T item) {
        int j = 0;
        Node n = head;
        while (n != null) {
            if (j == i) {
                n.item = item;
            }
            n = n.next;
            ++j;
        }
    }

    public int size() {
        int length = 0;
        Node n = head;

        while (n != null) {
            n = n.next;
            ++length;
        }

        return length;
    }

    public String toString() {
        String string = "[";
        Node n = head;

        while (n != null) {
            string += n.item.toString();
            n = n.next;
            if (n != null) string += ", ";
        }

        return string + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LinkedList that = (LinkedList)o;
        Node thatN = that.head;
        for (Node thisN = this.head; thisN != null || thatN != null; thisN = thisN.next, thatN = thatN.next) {
            if (((thisN == null) != (thatN == null)) || (!Objects.equals(thisN.item, thatN.item))) {
                return false;
            }
        }
        return true;
    }

    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }
    private class LinkedListIterator implements Iterator<T> {
        Node current = head;

        public boolean hasNext() {
            return current != null;
        }

        public T next() {
            T item = current.item;
            current = current.next;
            return item;
        }
    }
}
