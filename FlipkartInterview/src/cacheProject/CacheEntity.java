package cacheProject;

/***Cache Entity**/
public class CacheEntity<K, V> {
    private K key;
    private V value;

    public CacheEntity(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }
}