package com.job.conf;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MobileTes {
    public static boolean testPhone(String str) {
        Pattern pat = Pattern.compile("^[1][3578][0-9]{9}$");
        Matcher mat = pat.matcher(str);
        return mat.find();
    }
}
