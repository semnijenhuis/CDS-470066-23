package modal;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class RecursiveList<T> extends BasicList<T> implements Iterable<T>{

    private T data;
    private RecursiveList<T> rest;

    public void setData(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }
        assert  isEmpty();
        this.data = data;
        this.rest = new RecursiveList<T>();

    }

    public static void main(String[] args) {
        RecursiveList<String> list = new RecursiveList<String>();
        System.out.println(list);
        list.setData("Frederik");
        System.out.println(list);

    }

    public boolean isEmpty() {
        assert (data==null) == (rest==null) : "Either data and rest are filled or both must be empty.";
        return data == null;
    }

    @Override
    public String toString() {
        if(data==null){
            return "[]";
        }
        else {
            return "["+data.toString()+ " " + rest.toString()+']';
        }
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        Iterable.super.forEach(action);
    }

    @Override
    public Spliterator<T> spliterator() {
        return Iterable.super.spliterator();
    }
}
