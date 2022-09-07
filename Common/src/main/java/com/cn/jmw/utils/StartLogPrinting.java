package com.cn.jmw.utils;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 写注释的暖男jmw
 * @Description 启动公共类的时候打印出启动信息
 * @date 2022年09月07日 11:19
 * @Version 1.0
 */
@Slf4j
public class StartLogPrinting {

    /**
     * @Author 写注释的暖男jmw
     * @Description 打印模板方法
     * @Date 13:29 2022/9/7
     */
    public static void startLog(List<String> list){
        StringBuilder stringBuilder = new StringBuilder();
        //判断最大行
        int maxRow = maxRow(list);
        //拼接字符串
        getLog(stringBuilder,list,maxRow+6);
    }

    /**
     * @Author 写注释的暖男jmw
     * @Description 寻找最大行
     * @Date 13:29 2022/9/7
     */
    private static int maxRow(List<String> list){
        final int[] ans = {Integer.MIN_VALUE};
        list.forEach(str -> ans[0] = Math.max(ans[0],str.length()));
        return ans[0];
    }

    /**
     * @Author 写注释的暖男jmw
     * @Description 拼接打印函数
     * @Date 13:30 2022/9/7
     */
    private static void getLog(StringBuilder sb,List<String> list,int maxRow) {
        log.info(String.valueOf(sb.append("┌").append(getLogo(0,maxRow,0)).append("┐")));
        sb.delete(0,sb.length());
        list.stream().forEach(str -> {
            log.info(String.valueOf(sb.append("│"+getLogo(1,maxRow,str.length())+str+getLogo(-1,maxRow,str.length())+"│")));
            sb.delete(0,sb.length());
        });
        log.info(String.valueOf(sb.append("└").append(getLogo(0,maxRow,0)).append("┘")));
    }

    /**
     * @Author 写注释的暖男jmw
     * @Description 判断补全函数
     * @Date 13:30 2022/9/7
     */
    private static String getLogo(int logo,int size,int length){
        StringBuilder stringBuilder = new StringBuilder();
        if (logo==0)for (int i = 0; i < size; i++) stringBuilder.append("─");
        else if (logo==1)for (int i = 0; i < (size-length)/2; i++) stringBuilder.append(" ");
        else for (int i = 0; i < size-length-(size-length)/2; i++) stringBuilder.append(" ");
        return stringBuilder.toString();
    }

}
