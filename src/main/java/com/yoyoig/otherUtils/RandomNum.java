package com.yoyoig.otherUtils;

/**
 * 6位随机验证码
 *
 * @author junqiang.xiao@hand-china.com
 * @date 2018/3/1
 */
public final class RandomNum {
    public static int getRandNum(int min, int max) {
        int randNum = min + (int) (Math.random() * ((max - min) + 1));
        return randNum;
    }

    public static int getRanomdum() {
        return getRandNum(100000, 999999);
    }

}
