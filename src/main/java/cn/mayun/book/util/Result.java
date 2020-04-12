package cn.mayun.book.util;

import org.springframework.ui.ModelMap;

public class Result {

    public static String suc(ModelMap modelMap, String info) {
        modelMap.put("suc", true);
        modelMap.put("info", info);
        return "info";
    }

    public static String adminSuc(ModelMap modelMap, String info) {
        modelMap.put("suc", true);
        modelMap.put("info", info);
        return "admin_info";
    }

    public static String fail(ModelMap modelMap, String info) {
        modelMap.put("suc", false);
        modelMap.put("info", info);
        return "info";
    }

    public static String adminFail(ModelMap modelMap, String info) {
        modelMap.put("suc", false);
        modelMap.put("info", info);
        return "admin_info";
    }

    public static String auth(ModelMap modelMap) {
        modelMap.put("suc", false);
        modelMap.put("info", "您无权访问这个页面，去其它地方看看吧！");
        return "info";
    }

}
