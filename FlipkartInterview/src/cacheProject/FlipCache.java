package cacheProject;

public interface FlipCache<K, V> {
    void put(K key, V value);
    V get(K key);
    void remove(K key);
    void clear();
    int size();
    double getHitRatio();
}