package Model.ProgramState.ADT;

public interface MyIFileTable<T,G> {

        void add(T key, G value);
        void update(T key, G value);
        G lookup(T key);
        boolean isDefined(T key);
        String toString();

        void remove(T key);
        boolean isEmpty();


}
