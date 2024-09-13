package cacheProject;
public interface EvictionPolicy<K, V> {
    void onPut(CacheEntity<K, V> entity);
    void onGet(CacheEntity<K, V> entity);
    void onRemove(CacheEntity<K, V> entity);
    CacheEntity<K, V> evict();
}