package cacheProject;

public interface DataStore<K, V> {
    void put(CacheEntity<K, V> entity);
    V get(K key);
    void remove(K key);
    void clear();
    int size();
}