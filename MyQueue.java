package com.company;

import java.lang.reflect.Array;

/**
 * @author Alexander Kjeserud
 *
 * A circular queue made for the airport simulation assignment
 *
 */
public class MyQueue<E> {


    private E[] queue;
    public final int length;
    private int size = 0;
    private int first = 0;
    private int last = -1;

    /**
     * Constructor for MyQueue. The wildcard part of the code is taken from stackoverflow.
     *
     *  @param c The class of object the queue contains
     *  @param size Number of positions in the queue
     *
     */
    public MyQueue(Class<E[]> c, int size){
        queue = c.cast(Array.newInstance(c.getComponentType(), size));
        this.length = size;
    }
    /**
     *  Returns the next element in the queue. null if the queue is empty
     *  @return E
     */
    public E poll(){
        E ret = null;
        if (size != 0){
            ret = queue[first];
            queue[first] = null;
            size--;
            first = (first+1)%length;
        }
        return ret;
    }
    /**
     * Places an object in the back of the queue and returns true.
     * If the queue is full it will return false.
     *
     * @param entity An object of the class the queue contains
     * @return Boolean
     */
    public boolean put(E entity){
        if (size != length) {
            size++;
            last = (last+1)%length;
            queue[last] = entity;
            return true;
        }
        return false;
    }
    /**
     * Returns the number of elements in the queue
     * @return int
     */
    public int size(){
        return size;
    }
}
