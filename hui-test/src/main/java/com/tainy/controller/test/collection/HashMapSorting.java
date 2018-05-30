package com.tainy.controller.test.collection;

import io.swagger.models.auth.In;

import java.util.*;

/**
 * @author Tainy
 * @date 2018/5/25 10:41
 */
public class HashMapSorting {

    private static HashMap<Integer, User> sorting(HashMap<Integer, User> map){
        // 首先拿到map集合的键值对
        Set<Map.Entry<Integer, User>> entrySet = map.entrySet();
        // 将set集合转成list集合
        List<Map.Entry<Integer, User>> list = new ArrayList<>(entrySet);
        // 对list进行排序
        Collections.sort(list, new Comparator<Map.Entry<Integer, User>>() {
            @Override
            public int compare(Map.Entry<Integer, User> o1, Map.Entry<Integer, User> o2) {
                // 根据要求排序
                if(o1.getValue().getAge() - o2.getValue().getAge() > 0){
                    return 1;
                }else if(o1.getValue().getAge() - o2.getValue().getAge() < 0){
                    return -1;
                }
                return 0;
            }
        });
        LinkedHashMap<Integer, User> linkedHashMpa = new LinkedHashMap<>();
        list.stream().forEach(e -> {
            linkedHashMpa.put(e.getKey(), e.getValue());
        });

        return linkedHashMpa;
    }
    public static void main(String[] args) {
        HashMap<Integer, User> userHashMap = new HashMap<>();
        userHashMap.put(1, new User("张三", 24));
        userHashMap.put(2, new User("李四", 21));
        userHashMap.put(3, new User("王五", 27));
        System.out.println(userHashMap);
        userHashMap = sorting(userHashMap);
        System.out.println(userHashMap);

    }
}
