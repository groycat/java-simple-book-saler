package cn.mayun.book.util;

import org.springframework.ui.ModelMap;

public class Result {

    public static String suc(ModelMap modelMap, String info) {
        modelMap.put("suc", true);
        modelMap.put("info", info);
        return "info";
    }

    public static String fail(ModelMap modelMap, String info) {
        modelMap.put("suc", false);
        modelMap.put("info", info);
        return "info";
    }

}
