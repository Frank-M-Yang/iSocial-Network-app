package app.socialnetwork.datastructure;

public class LinkedList {

    private class ListElement {
        private Object el1;
        private ListElement el2;

        public ListElement(Object el, ListElement nextElement) {
            el1 = el;
            el2 = nextElement;
        }

        public ListElement(Object el) {
            this(el, null);
        }

        public Object first() {
            return el1;
        }

        public ListElement rest() {
            return el2;
        }

        public void setFirst(Object value) {
            el1 = value;
        }

        public void setRest(ListElement value) {
            el2 = value;
        }
    }

    private ListElement head;

    public LinkedList() {
        head = null;
    }

    public void addFirst(Object o) {
        head = new ListElement(o, head);
    }

    public Object getFirst() {
        if (head == null) {
            throw new RuntimeException("List is empty");
        }
        return head.first();
    }

    public Object get(int n) {
        if (n < 0 || n >= size()) {
            throw new IndexOutOfBoundsException("Index: " + n);
        }
        ListElement d = head;
        while (n > 0) {
            d = d.rest();
            n--;
        }
        return d.first();
    }

    public int size() {
        int count = 0;
        ListElement d = head;
        while (d != null) {
            count++;
            d = d.rest();
        }
        return count;
    }

    public void set(int n, Object o) {
        if (n < 0 || n >= size()) {
            throw new IndexOutOfBoundsException("Index: " + n + ", Size: " + size());
        }
        
        ListElement d = head;
        while (n > 0) {
            d = d.rest();
            n--;
        }
        d.setFirst(o);
    }

    public Object getLast() {
        if (head == null) {
            throw new RuntimeException("List is empty");
        }
        
        ListElement d = head;
        while (d.rest() != null) {
            d = d.rest();
        }
        return d.first();
    }

    public void addLast(Object o) {
        if (head == null) {
            head = new ListElement(o);
            return;
        }

        ListElement d = head;
        while (d.rest() != null) {
            d = d.rest();
        }
        d.setRest(new ListElement(o));
    }

    public boolean contains(Object o) {
        ListElement d = head;
        while (d != null) {
            if (d.first().equals(o)) {
                return true;
            }
            d = d.rest();
        }
        return false;
    }

    public void removeFirst() {
        if (head != null) {
            head = head.rest();
        }
    }

    public void removeLast() {
        if (head == null) {
            return; 
        }
        
        if (head.rest() == null) {
            head = null;  
            return;
        }

        
        ListElement d = head;
        while (d.rest().rest() != null) {
            d = d.rest();
        }
        d.setRest(null);
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void fropple() {
        if (head == null || head.rest() == null) {
            return;
        }

        ListElement prev = null;
        ListElement current = head;
        ListElement newHead = head.rest();

        while (current != null && current.rest() != null) {
            ListElement first = current;
            ListElement second = current.rest();
            ListElement nextPair = second.rest();

            second.setRest(first);
            first.setRest(nextPair);

            if (prev != null) {
                prev.setRest(second);
            }

            prev = first;
            current = nextPair;
        }

        head = newHead;
    }

    public void append(LinkedList list2) {
        if (list2 == null || list2.head == null) {
            return;
        }

        if (this.head == null) {
            this.head = list2.head;
            return;
        }

        ListElement tail = this.head;
        while (tail.rest() != null) {
            tail = tail.rest();
        }

        tail.setRest(list2.head);
    }

    public String toString() {
        String s = "(";
        ListElement d = head;
        while (d != null) {
            s += d.first().toString();
            s += " ";
            d = d.rest();
        }
        s += ")";
        return s;
    }
}