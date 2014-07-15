package com.reminder.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.Map;

public class JsonUtil {
	
	 private static Gson gson= new GsonBuilder().create();
	
	 public static <T> String toJson(T o){
		return gson.toJson(o);
	 }
	
	 public static <T> T fromJson(String jsonstr,Class<T> clazz){
		return gson.fromJson(jsonstr, clazz);
	 }

     public static Map<String, String> jsonToMap(String data){
        Map<String, String> map = gson.fromJson(data, new TypeToken<Map<String, String>>() {}.getType());
        return map;
     }

}