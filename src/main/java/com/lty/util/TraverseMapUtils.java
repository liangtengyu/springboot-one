package com.lty.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * 遍历Map
 */
public class TraverseMapUtils {

    /**
     * map 转换 str
     * @param param
     * @return
     */
    public static String mapToStr(TreeMap<String,Object> param){
        Iterator<Map.Entry<String, Object>> it = param.entrySet().iterator();
        StringBuffer str = new StringBuffer();
        while (it.hasNext()) {
            Map.Entry<String, Object> entry = it.next();
            str.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
        return str.toString().substring(0,str.length()-1);
    }

    /**
     * 字符串转Map
     *  使用场景： 字符串格式为：{id=23, username=15058007262}
     * @param param
     * @return
     */
    public static Map<String,Object> strToMap(String param){
        param = param.substring(1,param.length()-1);
        String[] arr = param.split(",");
        Map<String,Object> returnMap = new HashMap<String,Object>();
        for(int i = 0;i<arr.length;i++){
            returnMap.put(arr[i].substring(0,arr[i].indexOf("=")).trim(),arr[i].substring(arr[i].indexOf("=")+1,arr[i].length()).trim());
        }
        return returnMap;
    }

    public static void main(String[] args) {
        System.out.println(strToMap("{id=23, username=15058007262}"));
    }

}
