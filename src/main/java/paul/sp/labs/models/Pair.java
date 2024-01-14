package paul.sp.labs.models;

// Exista deja in javafx dar nu vreau sa mai adaug un dependency pentru atat
public class Pair<T1, T2> {
    public T1 first;
    public T2 second;

    public Pair(T1 first, T2 second) {
        this.first = first;
        this.second = second;
    }
}
