package cacheProject;
import java.util.*;

public class FIFOEvictionPolicy<K, V> implements EvictionPolicy<K, V> {
    private final Queue<CacheEntity<K, V>> queue;
    private final Map<K, CacheEntity<K, V>> cache;
    private final int capacity;

    public FIFOEvictionPolicy(int capacity) {
        this.queue = new LinkedList<>();
        this.cache = new HashMap<>();
        this.capacity = capacity;
    }

    @Override
    public void onPut(CacheEntity<K, V> entity) {
        if (cache.size() == capacity) {
            CacheEntity<K, V> oldestEntity = queue.poll();
            cache.remove(oldestEntity.getKey());
        }
        queue.add(entity);
        cache.put(entity.getKey(), entity);
    }

    @Override
    public void onGet(CacheEntity<K, V> entity) {
        // No special action needed for FIFO
    }

    @Override
    public void onRemove(CacheEntity<K, V> entity) {
        cache.remove(entity.getKey());
        queue.remove(entity);
    }

    @Override
    public CacheEntity<K, V> evict() {
        CacheEntity<K, V> oldestEntity = queue.poll();
        cache.remove(oldestEntity.getKey());
        return oldestEntity;
    }
}