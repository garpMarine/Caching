package cacheProject;

public class Main {
    public static void main(String []args){
        DataStoreFactory<Integer, Integer> dataStoreFactory = new DataStoreFactory<>();
        EvictionPolicyFactory<Integer, Integer> evictionPolicyFactory = new EvictionPolicyFactory<>();

        FlipCache<Integer, Integer> cache = new FlipCacheImpl<>(
                dataStoreFactory,
                DataStoreFactory.IN_MEMORY,
                evictionPolicyFactory,
                EvictionPolicyFactory.LRU,
                3
        );

        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));
        cache.put(3, 3);
        System.out.println(cache.get(2));
        cache.put(4, 4); //
        System.out.println(cache.get(1));// 4 2 3
        System.out.println(cache.get(3));// 453
        System.out.println(cache.get(4));
        cache.put(5, 5);
        System.out.println(cache.get(2));
        System.out.println(cache.get(4));
        System.out.println(cache.get(2));

        System.out.println("Hit Ratio: " + cache.getHitRatio());
    }
}
