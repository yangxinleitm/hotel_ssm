package cn.hotel.controller.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.Feature;

import java.util.List;
import java.util.Map;

public class RestUtils {
    public RestUtils() {
    }

    public static <T> T getModel(RestModel rest, Class<T> clazz) {
        if (rest != null && rest.getCode() != null && rest.getData() != null && "200".equals(rest.getCode())) {
            T model = JSON.parseObject(JSON.toJSONString(rest.getData()), clazz);
            return model;
        } else {
            return null;
        }
    }

    public static <T> List<T> getModelList(RestModel rest, Class<T> clazz) {
        if (rest != null && rest.getCode() != null && rest.getData() != null && "200".equals(rest.getCode())) {
            List<T> model = JSON.parseArray(JSON.toJSONString(rest.getData()), clazz);
            return model;
        } else {
            return null;
        }
    }

    public static <T> Map<String, T> getMap(RestModel rest, Class<T> clazz) {
        if (rest != null && rest.getCode() != null && rest.getData() != null && "200".equals(rest.getCode())) {
            Map<String, T> model = (Map)JSON.parseObject(JSON.toJSONString(rest.getData()), new TypeReference<Map<String, T>>() {
            }, new Feature[0]);
            return model;
        } else {
            return null;
        }
    }

    public static Map<String, Object> getMap(RestModel rest) {
        if (rest != null && rest.getCode() != null && rest.getData() != null && "200".equals(rest.getCode())) {
            Map<String, Object> map = (Map)rest.getData();
            return map;
        } else {
            return null;
        }
    }

    public static List<Map<String, Object>> getMapList(RestModel rest) {
        if (rest != null && rest.getCode() != null && rest.getData() != null && "200".equals(rest.getCode())) {
            List<Map<String, Object>> mapList = (List)rest.getData();
            return mapList;
        } else {
            return null;
        }
    }

    public static Boolean isSuccess(RestModel rest) {
        return rest != null && rest.getCode() != null && "200".equals(rest.getCode()) ? true : false;
    }
}
