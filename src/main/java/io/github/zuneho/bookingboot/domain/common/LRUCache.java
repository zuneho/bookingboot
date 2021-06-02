package io.github.zuneho.bookingboot.domain.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache<K, V> {
    private static final float hashTableLoadFactor = 0.75f;
    private final LinkedHashMap<K, V> map;
    private final int cacheSize;

    public LRUCache(int cacheSize) {
        this.cacheSize = cacheSize;
        map = new LinkedHashMap<>(cacheSize, hashTableLoadFactor, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                return size() > LRUCache.this.cacheSize;
            }
        };
    }

    public synchronized V get(K key) {
        return map.get(key);
    }

    public synchronized void put(K key, V value) {
        map.put(key, value);
    }

    public synchronized void clear() {
        map.clear();
    }

    public synchronized int size() {
        return map.size();
    }

    public synchronized Collection<Map.Entry<K, V>> getAll() {
        return new ArrayList<>(map.entrySet());
    }
}
