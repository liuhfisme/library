package com.library.utils.collection;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

/**
 * Created by Administrator on 2018/11/20.
 */
public class MapCollection {
    public static void main(String[] args) {
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put(null, "A");
        hashMap.put("1", null);
        hashMap.put("2", null);
        hashMap.put("3", "3");
        hashMap.put(null, "B");
        System.out.println(hashMap);

        Map<String, String> hashTable = new Hashtable<>();
//        hashTable.put(null, "A");
//        hashTable.put("1", null);
//        hashTable.put("2", null);
        hashTable.put("3", "3");
//        hashTable.put(null, "B");
        System.out.println(hashTable);
    }
}
