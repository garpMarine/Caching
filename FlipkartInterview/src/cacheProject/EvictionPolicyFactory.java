package cacheProject;
public class EvictionPolicyFactory<K, V> {
    public static final String LRU = "LRU";
    public static final String FIFO = "FIFO";

    public EvictionPolicy<K, V> getEvictionPolicy(String type, int capacity) {
        switch (type) {
            case LRU:
                return new LRUEvictionPolicy<>(capacity);
            case FIFO:
                return new FIFOEvictionPolicy<>(capacity);
            default:
                throw new IllegalArgumentException("Unknown eviction policy type");
        }
    }
}