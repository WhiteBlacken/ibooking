package com.huawei.ibooking.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Author qxy
 * @Date 2022/5/19 15:54
 * @Version 1.0
 */
public class TimeUtils {
    /**
     * String转LocalDateTime
     */
    public static LocalDateTime stringToLocalDateTime(String s) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH");
        return LocalDateTime.parse(s, df);
    }

}
