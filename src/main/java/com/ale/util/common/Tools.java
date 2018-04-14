package com.ale.util.common;

import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author alewu
 * @date 2017/11/13 21:56
 * @description 常用工具类
 */
public class Tools {

    private static final String ID_REGEX = "^\\d{15}|\\d{18}$";

    private static final String EMAIL_REGEX = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";

    private static final String PHONE_REGEX = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9]))\\\\d{8}$";


    /**
     * 随机生成四位数验证码
     *
     * @return
     */
    public static int getRandomNum() {
        Random r = new Random();
        // (Math.random()*(999999-100000)+100000)
        return r.nextInt(900000) + 100000;
    }

    /**
     * 验证身份证
     *
     * @param identityCard 身份证
     * @return
     */
    public static boolean checkIdentityCard(String identityCard) {
        return check(ID_REGEX, identityCard);
    }

    /**
     * 验证邮箱
     *
     * @param email 邮箱
     * @return 布尔值
     */
    public static boolean checkEmail(String email) {
        return check(EMAIL_REGEX, email);
    }

    /**
     * 验证手机号码
     *
     * @param phoneNumber 手机号码
     * @return 布尔值
     */
    public static boolean checkPhoneNumber(String phoneNumber) {
        return check(PHONE_REGEX, phoneNumber);
    }

    public static boolean check(String regex, String target) {
        boolean flag;
        try {
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(target);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }


    public static void main(String[] args) {
        System.out.println(checkEmail("z1@werecom"));
        System.out.println(Tools.generateUUID());
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 107; i++) {
            list.add(i);
        }
        List<List<Integer>> lists = subList(list, 10);
        for (List<Integer> integers : lists) {
            System.out.println(Arrays.toString(integers.toArray()));
        }
        System.out.println();
    }

    public static String generateUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String getPrintSize(long size) {
        BigDecimal fileSize = new BigDecimal(size);
        BigDecimal megabyte = new BigDecimal(1024 * 1024);
        float returnValue = fileSize.divide(megabyte, 2, BigDecimal.ROUND_UP)
                .floatValue();
        if (returnValue > 1) {
            return (returnValue + "MB");
        }
        BigDecimal kilobyte = new BigDecimal(1024);
        returnValue = fileSize.divide(kilobyte, 2, BigDecimal.ROUND_UP)
                .floatValue();
        return (returnValue + "KB");
    }


    public static String toUpperFirstChar(String str) {
        char[] charArray = str.toCharArray();
        charArray[0] -= 32;
        return String.valueOf(charArray);
    }


    public static <T> List<List<T>> subList(List<T> list, int blockSize) {
        List<List<T>> lists = new ArrayList<List<T>>();
        if (list != null && blockSize > 0) {
            int listSize = list.size();
            if (listSize <= blockSize) {
                lists.add(list);
                return lists;
            }
            int batchSize = listSize / blockSize;
            int remain = listSize % blockSize;
            for (int i = 0; i < batchSize; i++) {
                int fromIndex = i * blockSize;
                int toIndex = fromIndex + blockSize;
                System.out.println("fromIndex=" + fromIndex + ", toIndex=" + toIndex);
                lists.add(list.subList(fromIndex, toIndex));
            }
            if (remain > 0) {
                System.out.println("fromIndex=" + (listSize - remain) + ", toIndex=" + (listSize));
                lists.add(list.subList(listSize - remain, listSize));
            }
        }
        return lists;
    }


}
