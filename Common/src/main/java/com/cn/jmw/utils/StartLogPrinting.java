package com.cn.jmw.utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 写注释的暖男jmw
 * @Description 启动公共类的时候打印出启动信息
 * @date 2022年09月07日 11:19
 * @Version 1.0
 */
@Slf4j
public class StartLogPrinting<E> {

    /**
     * @Author 写注释的暖男jmw
     * @Description 测试案例 多线程情况下调用单例对象
     * @Date 18:11 2022/9/7
     */
    @Test
    public void main() throws ExecutionException, InterruptedException, TimeoutException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        CopyOnWriteArrayList<FutureTask<Integer>> integers = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 10; i++) {
            FutureTask<Integer> integerFutureTask = new FutureTask<>(new Callable<Integer>() {
                @Override
                public Integer call() {
                    SingletonEnum.SINGLETON.getInstance()
                            .add(String.valueOf((long) (Math.random() * Long.MAX_VALUE)))
                            .add(String.valueOf((long) (Math.random() * Long.MAX_VALUE)))
                            .add(String.valueOf((long) (Math.random() * Long.MAX_VALUE)))
                            .build();
                    return 1;
                }
            });
            executorService.submit(integerFutureTask);
            integers.add(integerFutureTask);
        }
        for (int i = 0; i < integers.size(); i++) {
            FutureTask<Integer> integerFutureTask = integers.get(i);
            integerFutureTask.get(10, TimeUnit.SECONDS);
        }
        executorService.shutdown();
    }

    /**
     * @Author 写注释的暖男jmw
     * @Description 单例模式-枚举 防止反射穿透
     * @Date 18:08 2022/9/7
     */
    public enum SingletonEnum {
        SINGLETON;
        private StartLogPrinting instance = null;

        private SingletonEnum() {
            instance = new StartLogPrinting();
        }

        public StartLogPrinting getInstance() {
            lock.lock();
            return instance;
        }
    }

    private volatile List list = new ArrayList();

    private static ReentrantLock lock = new ReentrantLock();

    /***
     * @Description:
     * @Auther: ycjiang
     * @Date: 2022/9/7 21:57
     */
    @Deprecated
    public StartLogPrinting addFirst(E e) throws InterruptedException {
        list.add(e);
        return this;
    }

    public StartLogPrinting add(E e) {
        list.add(e);
        try {
            if (list.size() == 1 && !objToString(e)) {
                log.info(e.getClass().getSimpleName() + " : The toString method is not overridden!");
            }
        } catch (InvocationTargetException ex) {
            throw new RuntimeException(ex);
        } catch (IllegalAccessException ex) {
            throw new RuntimeException(ex);
        }
        return this;
    }

    /**
     * @Author 写注释的暖男jmw
     * @Description stream构建调用方法
     * @Date 18:13 2022/9/7
     */
    public void build() {
        try {
            startLog(list);
            list.removeAll(list);
        } finally {
            lock.unlock();
        }
    }

    /**
     * @Author 写注释的暖男jmw
     * @Description 打印模板方法
     * @Date 13:29 2022/9/7
     */
    public void startLog(List<E> list) {
        StringBuilder stringBuilder = new StringBuilder();
        //判断最大行
        int maxRow = maxRow(list);
        //拼接字符串
        getLog(stringBuilder, list, maxRow + 6);
    }

    /**
     * @Author 写注释的暖男jmw
     * @Description 寻找最大行
     * @Date 13:29 2022/9/7
     */
    private int maxRow(List<E> list) {
        final int[] ans = {Integer.MIN_VALUE};
        list.forEach(str -> ans[0] = Math.max(ans[0], str.toString().length()));
        return ans[0];
    }

    public static boolean objToString(Object object) throws InvocationTargetException, IllegalArgumentException, IllegalAccessException {
        long toString = Arrays.stream(object.getClass().getMethods()).filter(method -> method.getName().equals("toString")).count();
        return toString == 0 ? false : true;
    }

    /**
     * @Author 写注释的暖男jmw
     * @Description 拼接打印函数
     * @Date 13:30 2022/9/7
     */
    private void getLog(StringBuilder sb, List<E> list, int maxRow) {
        log.info(String.valueOf(sb.append("┌").append(getLogo(0, maxRow, 0)).append("┐")));
        sb.delete(0, sb.length());
        list.stream().forEach(str -> {
            int length = str.toString().length();
            log.info(String.valueOf(sb.append("│" + getLogo(1, maxRow, length) + str + getLogo(-1, maxRow, length) + "│")));
            sb.delete(0, sb.length());
        });
        log.info(String.valueOf(sb.append("└").append(getLogo(0, maxRow, 0)).append("┘")));
    }

    final static String LOGO1 = "─";
    final static String LOGO2 = " ";

    /**
     * @Author 写注释的暖男jmw
     * @Description 判断补全函数
     * @Date 13:30 2022/9/7
     */
    private String getLogo(int logo, int size, int length) {
        StringBuilder stringBuilder = new StringBuilder();
        if (logo == 0) for (int i = 0; i < size; i++) stringBuilder.append(LOGO1);
        else if (logo == 1) for (int i = 0; i < (size - length) / 2; i++) stringBuilder.append(LOGO2);
        else for (int i = 0; i < size - length - (size - length) / 2; i++) stringBuilder.append(LOGO2);
        return stringBuilder.toString();
    }

}