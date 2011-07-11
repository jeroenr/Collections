import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Utility class to help merging complex list structures
 */
public class ListMergeUtil {

    public static <T extends List<E>, E> T merge(T merged, T a, T b, FoundMatchCallback<T, E> foundMatchCallback) {
        final List<E> thisCopy = new ArrayList<E>(a), thatCopy = new ArrayList<E>(b);
        final Iterator<E> thisCopyIterator = thisCopy.iterator();
        while (thisCopyIterator.hasNext()) {
            final E current = thisCopyIterator.next();
            final Iterator<E> thatCopyIterator = thatCopy.iterator();
            while (thatCopyIterator.hasNext()) {
                final E mutation = thatCopyIterator.next();
                if (current.equals(mutation)) {
                    // found match
                    foundMatchCallback.handleFoundMatch(merged, current, mutation);
                    thisCopyIterator.remove();
                    thatCopyIterator.remove();
                }
            }
        }
        merged.addAll(thisCopy);
        merged.addAll(thatCopy);
        return merged;
    }

    static abstract class FoundMatchCallback<T extends List<? super E>, E> {
        abstract T handleFoundMatch(T result, E current, E mutation);
    }

    private ListMergeUtil() throws InstantiationException {
        throw new InstantiationException("You can only have one instance of this class");
    }
}