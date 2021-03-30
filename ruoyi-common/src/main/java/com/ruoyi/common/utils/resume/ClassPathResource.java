package com.ruoyi.common.utils.resume;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description: 电话号码正则
 * @Author ych
 * @Date 2021/3/21
 */
public class ClassPathResource {

    public static boolean isMobileNO(String mobiles) {
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
//        System.out.println(m.matches() + "---");
        return m.matches();
    }

    public static void main(String[] args) {




    }

}
