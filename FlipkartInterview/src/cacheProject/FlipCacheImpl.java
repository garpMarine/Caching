package cacheProject;

public class FlipCacheImpl<K, V> implements FlipCache<K, V> {
    private final DataStore<K, V> dataStore;
    private final EvictionPolicy<K, V> evictionPolicy;
    private int hitCount;
    private int accessCount;
    private final int capacity;

    public FlipCacheImpl(DataStoreFactory<K, V> dataStoreFactory, String dataStoreType, EvictionPolicyFactory<K, V> evictionPolicyFactory, String evictionPolicyType, int capacity) {
        this.dataStore = dataStoreFactory.getDataStore(dataStoreType);
        this.evictionPolicy = evictionPolicyFactory.getEvictionPolicy(evictionPolicyType, capacity);
        this.capacity = capacity;
        this.hitCount = 0;
        this.accessCount = 0;
    }
    /*
    1->1
    U1->4
    U2->5
    (U1-> 1->4) -- U2(1->5)



     */

    @Override
    public void put(K key, V value) {
        if (dataStore.size() >= capacity) { // Assume a fixed cache size for simplicity
            CacheEntity<K, V> evictEntity = evictionPolicy.evict();
            if (evictEntity != null) {
                dataStore.remove(evictEntity.getKey());
            }
        }
        CacheEntity<K, V> entity = new CacheEntity<>(key, value);
        dataStore.put(entity);
        evictionPolicy.onPut(entity);
    }

    @Override
    public V get(K key) {
        accessCount++;
        V value = dataStore.get(key);
        if (value != null) {
            hitCount++;
            evictionPolicy.onGet(new CacheEntity<>(key, value));
        }
        return value;
    }

    @Override
    public void remove(K key) {
        CacheEntity<K, V> entity = new CacheEntity<>(key, null);
        dataStore.remove(key);
        evictionPolicy.onRemove(entity);
    }

    @Override
    public void clear() {
        dataStore.clear();
    }

    @Override
    public int size() {
        return dataStore.size();
    }

    @Override
    public double getHitRatio() {
        return accessCount == 0 ? 0 : (double) hitCount / accessCount;
    }
}