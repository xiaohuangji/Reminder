package com.reminder.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.Map;

public class JsonUtil {

	private static ObjectMapper mapper = new ObjectMapper();
	
//	public static ObjectMapper getMapper(){
//		return mapper;
//	}

    public static <T> String toJson(T o){
        try{
            return mapper.writeValueAsString(o);
        }catch(Exception e){
            return null;
        }
    }

    public static <T> T fromJson(String jsonstr,Class<T> clazz){
        try{
            return mapper.readValue(jsonstr, clazz);
        }catch (Exception e){
            return null;
        }
    }

    public static Map<String,String> jsonToMap(InputStream inputStream){
        try{
            return mapper.readValue(inputStream, Map.class);
        }catch(Exception e){
            return null;
        }
    }

//    public static Map<String, String> jsonToMap(String data){
//        Map<String, String> map = gson.fromJson(data, new TypeToken<Map<String, String>>() {}.getType());
//        return map;
//    }

    public static void main(String[] args) {
//        List<String> list=new ArrayList<String>();
//        list.add("15901025224");
//        list.add("15901025225");
//        list.add("15901025226");
//        String listJson=JsonUtil.toJson(list);
//        System.out.println(listJson);
//        List<String> list1=JsonUtil.fromJson(listJson,List.class);
//        System.out.println(list1);

        System.out.println(System.currentTimeMillis()+1000000000);

    }
}
