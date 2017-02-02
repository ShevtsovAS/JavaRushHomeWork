package com.javarush.test.level20.lesson10.bonus04;

import java.io.*;
import java.util.*;

/* Свой список
Посмотреть, как реализован LinkedList.
Элементы следуют так: 1->2->3->4  и так 4->3->2->1
По образу и подобию создать Solution.
Элементы должны следовать так:
1->3->7->15
    ->8...
 ->4->9
    ->10
2->5->11
    ->12
 ->6->13
    ->14
Удалили 2 и 9
1->3->7->15
    ->8
 ->4->10
Добавили 16,17,18,19,20 (всегда добавляются на самый последний уровень к тем элементам, которые есть)
1->3->7->15
       ->16
    ->8->17
       ->18
 ->4->10->19
        ->20
Удалили 18 и 20
1->3->7->15
       ->16
    ->8->17
 ->4->10->19
Добавили 21 и 22 (всегда добавляются на самый последний уровень к тем элементам, которые есть.
Последний уровень состоит из 15, 16, 17, 19. 19 последний добавленный элемент, 10 - его родитель.
На данный момент 10 не содержит оба дочерних элемента, поэтому 21 добавился к 10. 22 добавляется в следующий уровень.)
1->3->7->15->22
       ->16
    ->8->17
 ->4->10->19
        ->21

Во внутренней реализации элементы должны добавляться по 2 на каждый уровень
Метод getParent должен возвращать элемент, который на него ссылается.
Например, 3 ссылается на 7 и на 8, т.е.  getParent("8")=="3", а getParent("13")=="6"
Строки могут быть любыми.
При удалении элемента должна удаляться вся ветка. Например, list.remove("5") должен удалить "5", "11", "12"
Итерироваться элементы должны в порядке добавления
Доступ по индексу запрещен, воспользуйтесь при необходимости UnsupportedOperationException
Должно быть наследование AbstractList<String>, List<String>, Cloneable, Serializable
Метод main в тестировании не участвует
*/
public class Solution
        extends AbstractList<String>
        implements List<String>, Cloneable, Serializable
{
    private int size = 0;
    private Node first;
    private Node last;
    private Node root;

    public Solution() {
        first = last = root = new Node();
    }

    public static void main(String[] args) {
        List<String> list = new Solution();
        for (int i = 1; i < 16; i++) {
            list.add(String.valueOf(i));
        }
        System.out.println("Expected 3, actual is " + ((Solution) list).getParent("8"));
        ((Solution) list).print();
        ((Solution) list).remove("2");
        ((Solution) list).remove("9");
        System.out.println("Удалили 2 и 9:");
        ((Solution) list).print();

        for (int i = 16; i < 21; i++) {
            list.add(String.valueOf(i));
        }
        System.out.println("Добавили 16,17,18,19,20");
        ((Solution) list).print();

        ((Solution) list).remove("18");
        ((Solution) list).remove("20");
        System.out.println("Удалили 18 и 20:");
        ((Solution) list).print();

        list.add("21");
        list.add("22");
        System.out.println("Добавили 21 и 22:");
        ((Solution) list).print();

        System.out.println("Expected null, actual is " + ((Solution) list).getParent("1"));
        System.out.println("Expected null, actual is " + ((Solution) list).getParent("2"));
        System.out.println("Expected 1, actual is " + ((Solution) list).getParent("3"));
        System.out.println("Expected 1, actual is " + ((Solution) list).getParent("4"));
        System.out.println("Expected null, actual is " + ((Solution) list).getParent("5"));
        System.out.println("Expected null, actual is " + ((Solution) list).getParent("6"));
        System.out.println("Expected 3, actual is " + ((Solution) list).getParent("7"));
        System.out.println("Expected 3, actual is " + ((Solution) list).getParent("8"));
        System.out.println("Expected null, actual is " + ((Solution) list).getParent("9"));
        System.out.println("Expected 4, actual is " + ((Solution) list).getParent("10"));
        System.out.println("Expected null, actual is " + ((Solution) list).getParent("11"));
        System.out.println("Expected null, actual is " + ((Solution) list).getParent("12"));
        System.out.println("Expected null, actual is " + ((Solution) list).getParent("13"));
        System.out.println("Expected null, actual is " + ((Solution) list).getParent("14"));
        System.out.println("Expected 7, actual is " + ((Solution) list).getParent("15"));
        System.out.println("Expected 7, actual is " + ((Solution) list).getParent("16"));
        System.out.println("Expected 8, actual is " + ((Solution) list).getParent("17"));
        System.out.println("Expected null, actual is " + ((Solution) list).getParent("18"));
        System.out.println("Expected 10, actual is " + ((Solution) list).getParent("19"));
        System.out.println("Expected null, actual is " + ((Solution) list).getParent("20"));
        System.out.println("Expected 10, actual is " + ((Solution) list).getParent("21"));
        System.out.println("Expected 15, actual is " + ((Solution) list).getParent("22"));

        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("myLinkList.ser"));
            outputStream.writeObject(list);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        list.clear();
        System.out.println("\nОчистили лист:");
        ((Solution) list).print();

        try {
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("myLinkList.ser"));
            list = (Solution) inputStream.readObject();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }

        System.out.println("\nЛист востановлен:");
        ((Solution) list).print();

        for (String s : list) {
            System.out.println(s);
        }
    }
    private void print() {
        int i = 0;
        for (Node x = root; x != null; x = x.next, i++) {
            String left = x.left == null ? null : x.left.item;
            String right = x.right == null ? null : x.right.item;
            System.out.printf("%s) node=%s left=%s right=%s\n", i, x.item, left, right);
        }
        System.out.println("size = " + size);
        System.out.println("=============================================================");
    }

    @Override
    public boolean add(String s) {
        final Node l = last;
        final Node p = findParent();
        final Node newNode = new Node(l, s, null, p, null, null);
        last = newNode;
        if (l == root) first = newNode;
        if (p.left == null) p.left = newNode;
        else p.right = newNode;
        l.next = newNode;
        size++;
        modCount++;
        return true;
    }

    private Node findParent() {
        if (size == 0) return root;
        if (last.parent.right == null) return last.parent;
        return last.parent.next;
    }

    public String getParent(String value) {
        Node current = getNode(value);
        Node parent = current == null ? null : current.parent;
        return parent == null ? null : parent.item;
    }

    private Node getNode(String s){
        if (s == null) {
            for (Node x = first; x != null; x = x.next) {
                if (x.item == null) return x;
            }
        } else {
            for (Node x = first; x != null; x = x.next) {
                if (s.equals(x.item)) return x;
            }
        }
        return null;
    }

    @Override
    public boolean remove(Object o) {
        String s = o == null ? null : String.valueOf(o);
        return remove(s);
    }

    public boolean remove(String s) {
        Node nodeToRemove = getNode(s);
        if (nodeToRemove != null) {
            unlink(nodeToRemove);
            if (nodeToRemove == nodeToRemove.parent.left) nodeToRemove.parent.left = null;
            if (nodeToRemove == nodeToRemove.parent.right) nodeToRemove.parent.right = null;
            if (nodeToRemove.left != null) remove(nodeToRemove.left.item);
            if (nodeToRemove.right != null) remove(nodeToRemove.right.item);
            return true;
        }
        return false;
    }

    private String unlink(Node x) {
        // assert x != null;
        final String element = x.item;
        final Node next = x.next;
        final Node prev = x.prev;

        if (prev == root) {
            first = next;
        } else {
            prev.next = next;
            x.prev = null;
        }

        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            x.next = null;
        }

        x.item = null;
        size--;
        modCount++;
        return element;
    }

    @Override
    public Iterator<String> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<String> {
        int cursor = 0;
        int lastRet = -1;
        int expectedModCount = modCount;
        Node currentNode = root.next;

        public boolean hasNext() {
            return cursor != size();
        }

        public String next() {
            checkForComodification();
            try {
                int i = cursor;
                String next = currentNode.item;
                lastRet = i;
                cursor = i + 1;
                currentNode = currentNode.next;
                return next;
            } catch (IndexOutOfBoundsException e) {
                checkForComodification();
                throw new NoSuchElementException();
            }
        }

        public void remove() {
            if (lastRet < 0)
                throw new IllegalStateException();
            checkForComodification();

            try {
                Solution.this.remove(currentNode.prev.item);
                if (lastRet < cursor)
                    cursor--;
                lastRet = -1;
                expectedModCount = modCount;
            } catch (IndexOutOfBoundsException e) {
                throw new ConcurrentModificationException();
            }
        }

        final void checkForComodification() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
        }
    }

    @Override
    public ListIterator<String> listIterator() {
        return super.listIterator();
    }

    @Override
    public ListIterator<String> listIterator(int index) {
        return super.listIterator(index);
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private static class Node implements Serializable {

        String item;
        Node next;
        Node prev;
        Node parent;
        Node left;
        Node right;
        Node(){}
        Node(Node prev, String item, Node next, Node parent, Node left, Node right) {
            this.item = item;
            this.next = next;
            this.prev = prev;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }
    }

    @Override
    public void clear() {
        root.next = null;
        root.left = null;
        root.right = null;
        for (Node x = first; x != null; ) {
            Node next = x.next;
            x.item = null;
            x.next = null;
            x.prev = null;
            x.parent = null;
            x.left = null;
            x.right = null;
            x = next;
        }
        first = last = root;
        size = 0;
        modCount++;
    }

    @Override
    public int indexOf(Object o) {
        int index = 0;
        if (o == null) {
            for (Node x = first; x != null; x = x.next) {
                if (x.item == null)
                    return index;
                index++;
            }
        } else {
            for (Node x = first; x != null; x = x.next) {
                if (o.equals(x.item))
                    return index;
                index++;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        int index = size;
        if (o == null) {
            for (Node x = last; x != null; x = x.prev) {
                index--;
                if (x.item == null)
                    return index;
            }
        } else {
            for (Node x = last; x != null; x = x.prev) {
                index--;
                if (o.equals(x.item))
                    return index;
            }
        }
        return -1;
    }

    public Object[] toArray() {
        Object[] result = new Object[size];
        int i = 0;
        for (Node x = first; x != null; x = x.next)
            result[i++] = x.item;
        return result;
    }

    @Override
    public boolean addAll(Collection<? extends String> c) {
        return addAll(size, c);
    }

    public String getFirst() {
        final Node f = first;
        if (f == null)
            throw new NoSuchElementException();
        return first.item;
    }

    public String getLast() {
        final Node l = last;
        if (l == null)
            throw new NoSuchElementException();
        return last.item;
    }

    // Запрещаем доступ по индексу
    @Override
    public String get(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String set(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends String> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }
}
