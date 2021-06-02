package io.github.zuneho.bookingboot.domain.common;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

//https://sup2is.github.io/2019/10/31/linked-hash-map.html
//https://gomguard.tistory.com/115
public class LRUCacheTest {

    @Test
    public void test() {
        int cacheSize = 3; //50
        String[] cities = {"jeju", "pangyo", "seoul", "newyork", "la", "jeju", "pangyo", "seoul", "newyork", "la"};

//        int cacheSize = 3; //21
//        String[] cities = {"jeju", "pangyo", "seoul", "jeju", "pangyo", "seoul","jeju", "pangyo", "seoul"};

//        int cacheSize = 2; //60
//        String[] cities = {"jeju", "pangyo", "seoul", "newyork", "la","sanfrancisco", "seoul","rome", "paris", "jeju","newyork", "rome"};

//        int cacheSize = 5; //52
//        String[] cities = {"jeju", "pangyo", "seoul", "newyork", "la", "sanfrancisco", "seoul", "rome", "paris", "jeju", "newyork", "rome"};

//        int cacheSize = 2; //16
//        String[] cities = {"jeju", "pangyo", "NewYork", "newyork"};

//        int cacheSize = 0; //25
//        String[] cities = {"jeju", "pangyo", "seoul", "newyork", "la"};

        int cacheHit = 0;
        int cacheMiss = 0;

        LRUCache<String, String> cache = new LRUCache<>(cacheSize);

        for (String city : cities) {
            String cityVal = cache.get(city.toLowerCase());
            if (cityVal != null) {
                cacheHit++;
            } else {
                cacheMiss++;
                cache.put(city.toLowerCase(), city.toLowerCase());
            }
            System.out.println("findText=[" + city + "] - cache[" + cacheSize + "]=" + cache.getAll());
        }
        System.out.println("cacheHit=" + cacheHit);
        System.out.println("cacheMiss=" + cacheMiss);
        System.out.println("totalSpendTime=" + (cacheHit + (cacheMiss * 5)));

        assertThat(cache.size()).isEqualTo(cacheSize);
    }

}