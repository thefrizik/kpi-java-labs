import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Власна колекція, що реалізує Set за допомогою двозв'язного списку.
 * Згідно з C2=1 (Set), C3=2 (Двозв'язний список).
 * Узагальнена для типу T (в нашому випадку буде Track).
 */
class DoublyLinkedSet<T> extends AbstractSet<T> {
    
    // Внутрішній вузол для двозв'язного списку
    private class Node {
        T data;
        Node prev;
        Node next;
        
        Node(T data) {
            this.data = data;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    // Конструктор 1: Порожній
    public DoublyLinkedSet() {
        head = null;
        tail = null;
        size = 0;
    }

    // Конструктор 2: Із передачею одного об'єкта
    public DoublyLinkedSet(T element) {
        this();
        add(element);
    }

    // Конструктор 3: Із передачею стандартної колекції об'єктів
    public DoublyLinkedSet(Collection<? extends T> collection) {
        this();
        addAll(collection);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean add(T element) {
        // Оскільки це Set, перевіряємо на унікальність
        if (contains(element)) {
            return false;
        }
        
        Node newNode = new Node(element);
        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
        return true;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node current = head;
            private Node lastReturned = null;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                lastReturned = current;
                current = current.next;
                return lastReturned.data;
            }
            
            @Override
            public void remove() {
                if (lastReturned == null) {
                    throw new IllegalStateException();
                }
                
                Node prevNode = lastReturned.prev;
                Node nextNode = lastReturned.next;
                
                if (prevNode == null) {
                    head = nextNode;
                } else {
                    prevNode.next = nextNode;
                }
                
                if (nextNode == null) {
                    tail = prevNode;
                } else {
                    nextNode.prev = prevNode;
                }
                
                size--;
                lastReturned = null; // запобігає подвійному виклику remove()
            }
        };
    }
}

// Виконавчий метод (тестування з класом Track з лаб. 5)
public class Lab6 {
    public static void main(String[] args) {
        System.out.println("Тестування власної колекції (Set на базі Двозв'язного списку):");
        
        // 1. Порожній конструктор
        DoublyLinkedSet<String> emptySet = new DoublyLinkedSet<>();
        emptySet.add("Track 1");
        System.out.println("Розмір emptySet: " + emptySet.size());
        
        // 2. Конструктор з одним елементом
        DoublyLinkedSet<String> singleElementSet = new DoublyLinkedSet<>("Track 2");
        System.out.println("Розмір singleElementSet: " + singleElementSet.size());
        
        // 3. Конструктор зі стандартною колекцією
        java.util.List<String> standardList = java.util.Arrays.asList("Track 3", "Track 4", "Track 3"); 
        DoublyLinkedSet<String> collectionSet = new DoublyLinkedSet<>(standardList);
        
        System.out.println("\nВміст collectionSet (має бути без дублікатів):");
        for (String track : collectionSet) {
            System.out.println("- " + track);
        }
        
        // Видалення через ітератор
        System.out.println("\nВидаляємо 'Track 3'...");
        Iterator<String> iterator = collectionSet.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().equals("Track 3")) {
                iterator.remove();
            }
        }
        
        System.out.println("Вміст після видалення:");
        for (String track : collectionSet) {
            System.out.println("- " + track);
        }
    }
}
