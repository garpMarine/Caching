package cacheProject;
import java.util.*;

public class LRUEvictionPolicy<K, V> implements EvictionPolicy<K, V> {
    private final Map<K, CacheEntity<K, V>> cache;
    private final Deque<CacheEntity<K, V>> deque;
    private final int capacity;

    public LRUEvictionPolicy(int capacity) {
        this.cache = new HashMap<>();
        this.deque = new LinkedList<>();
        this.capacity = capacity;
    }

    @Override
    public void onPut(CacheEntity<K, V> entity) {
        if (cache.containsKey(entity.getKey())) {
            deque.remove(entity);
        } else {
            if (cache.size() >= capacity) {
                evict();
            }
        }
        cache.put(entity.getKey(), entity);
        deque.addFirst(entity);
    }

    @Override
    public void onGet(CacheEntity<K, V> entity) {
        if (!cache.containsKey(entity.getKey())) {
            return;
        }
        deque.remove(entity);
        deque.addFirst(entity);
    }

    @Override
    public void onRemove(CacheEntity<K, V> entity) {
        cache.remove(entity.getKey());
        deque.remove(entity);
    }

    @Override
    public CacheEntity<K, V> evict() {
        if (deque.isEmpty()) {
            return null;
        }
        CacheEntity<K, V> lruEntity = deque.removeLast();
        cache.remove(lruEntity.getKey());
        return lruEntity;
    }
}