package com.zmf.utils;

import java.util.List;
import java.util.Map;

/**
 * @Auther: zmf
 * @Date: 2019-01-16 10:09
 * @Description:
 * 判断list 以及map集合是否为空。
 */
public class ListUtils {
    public static List<?> emptyList(List<?> list){
        if (list.size() <= 0 || list == null) {
            return null;
        }
        return list;
    }
    public static Map<?,?> emptyMap(Map<?,?> map){
        if(map.size() <= 0  || map == null){
            return null;
        }
        return map;
    }
}
