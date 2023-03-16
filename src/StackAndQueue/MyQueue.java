package StackAndQueue;

import java.util.LinkedList;

public class MyQueue<E>{

    private int size = 0;
    private Node<E> first;
    private Node<E> last;

    public boolean offer(E e){
        Node<E> lastNode = last;
        Node<E> newNode = new Node<>(lastNode, e, null);
        lastNode = newNode;
        if(lastNode == null){
            first = newNode;
        }else{
            last = newNode;
        }

        size++;
        return true;
    }

    public E poll() {
        final Node<E> f = first;
        return (f == null) ? null : unlinkFirst(f);
    }

    private E unlinkFirst(Node<E> f) {
        final E element = f.item;
        final Node<E> next = f.next;
        f.item = null;
        f.next = null;
        first = next;
        if (next == null)
            last = null;
        else
            next.prev = null;
        size--;
        return element;
    }

    public E peek(){
        return first == null ? null : first.item;
    }

    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }
}
