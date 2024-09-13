package cacheProject;

public class DataStoreFactory<K, V> {
    public static final String IN_MEMORY = "IN_MEMORY";
    // Additional data store types can be added here as constants

    public DataStore<K, V> getDataStore(String type) {
        switch (type) {
            case IN_MEMORY:
                return new InMemoryDataStore<>();
            // Additional cases for other data store types
            default:
                throw new IllegalArgumentException("Unknown data store type");
        }
    }
}