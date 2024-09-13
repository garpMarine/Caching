package cacheProject;
import java.util.HashMap;
import java.util.Map;

public class InMemoryDataStore<K, V> implements DataStore<K, V> {
    private final Map<K, CacheEntity<K, V>> store;

    public InMemoryDataStore() {
        this.store = new HashMap<>();
    }

    @Override
    public void put(CacheEntity<K, V> entity) {
        store.put(entity.getKey(), entity);
    }

    @Override
    public V get(K key) {
        CacheEntity<K, V> entity = store.get(key);
        return entity != null ? entity.getValue() : null;
    }

    @Override
    public void remove(K key) {
        store.remove(key);
    }

    @Override
    public void clear() {
        store.clear();
    }

    @Override
    public int size() {
        return store.size();
    }
}