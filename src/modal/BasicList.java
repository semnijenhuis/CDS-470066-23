package modal;

public class BasicList<T> {

    private T data;
    private BasicList<T> rest;

    boolean isEmpty() {
        return false;
    }
    int getCount() {
        return 0;
    }
    boolean contains(T data) {
        return false;
    }


}
