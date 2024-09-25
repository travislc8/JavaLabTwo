package src;

/**
 * Interface for a object that can be set as complete or not complete
 */
public interface Completable {
    public void complete();

    public boolean isComplete();
}
