import java.io.Serializable;
import java.util.*;

public class FitHashSet<E extends Fit<E> & Comparable<E>> extends AbstractSet<E> implements Set<E>, Cloneable, Serializable {

    private static final long serialVersionUID = 2921593367793152579L;
    
    private transient Map<E, E> backingMap;

    public FitHashSet() {
        this.backingMap = new HashMap<E, E>();
    }

    public FitHashSet(Collection<? extends E> collection) {
        this.backingMap = new HashMap<E,E>(Math.max((int) (collection.size()/.75f) + 1, 16));
        addAll(collection);
    }

    public FitHashSet(int initialCapacity) {
        this.backingMap = new HashMap<E, E>(initialCapacity);
    }

    public FitHashSet(int initialCapacity, float loadFactor) {
        this.backingMap = new HashMap<E, E>(initialCapacity, loadFactor);
    }

    @Override
    public boolean add(final E e) {
        if(e == null)
            return false;
        final E existing = backingMap.get(e);
        if(existing == null)
            return backingMap.put(e, e) == null;
        return replaceIfNecessary(existing, e.getFittest(existing));
    }

    private boolean replaceIfNecessary(final E oldValue, final E newValue) {
        if (oldValue == newValue)
            return false;
        return backingMap.put(newValue, newValue) == null;
    }

    @Override
    public Iterator<E> iterator() {
        return backingMap.keySet().iterator();
    }

    @Override
    public int size() {
        return backingMap.size();
    }
}

