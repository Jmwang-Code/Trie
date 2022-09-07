package com.cn.jmw.utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

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
                    synchronized (Object.class) {
                        SingletonEnum.SINGLETON.getInstance()
                                .add(String.valueOf((long) (Math.random() * Long.MAX_VALUE)))
                                .add(String.valueOf((long) (Math.random() * Long.MAX_VALUE)))
                                .build();
                    }
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
        System.out.println(executorService.isShutdown());
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
            return instance;
        }
    }

    private volatile List list = new ArrayList();

    //加锁保护
//    private ReentrantLock lock = new ReentrantLock();
    private NewLock lock = new NewLock();

    /**
     * @Author 写注释的暖男jmw
     * @Description stream调用，丝滑如水
     * @Date 18:12 2022/9/7
     */
    public StartLogPrinting add(String prefix)  {
        list.add(prefix);
        return this;
    }

    /**
     * @Author 写注释的暖男jmw
     * @Description stream构建调用方法
     * @Date 18:13 2022/9/7
     */
    public void build() {
        startLog(list);
        list.removeAll(list);
    }

    /**
     * @Author 写注释的暖男jmw
     * @Description 打印模板方法
     * @Date 13:29 2022/9/7
     */
    public void startLog(List<String> list) {
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
    private int maxRow(List<String> list) {
        final int[] ans = {Integer.MIN_VALUE};
        list.forEach(str -> ans[0] = Math.max(ans[0], str.length()));
        return ans[0];
    }

    /**
     * @Author 写注释的暖男jmw
     * @Description 拼接打印函数
     * @Date 13:30 2022/9/7
     */
    private void getLog(StringBuilder sb, List<String> list, int maxRow) {
        log.info(String.valueOf(sb.append("┌").append(getLogo(0, maxRow, 0)).append("┐")));
        sb.delete(0, sb.length());
        list.stream().forEach(str -> {
            log.info(String.valueOf(sb.append("│" + getLogo(1, maxRow, str.length()) + str + getLogo(-1, maxRow, str.length()) + "│")));
            sb.delete(0, sb.length());
        });
        log.info(String.valueOf(sb.append("└").append(getLogo(0, maxRow, 0)).append("┘")));
    }

    /**
     * @Author 写注释的暖男jmw
     * @Description 判断补全函数
     * @Date 13:30 2022/9/7
     */
    private String getLogo(int logo, int size, int length) {
        StringBuilder stringBuilder = new StringBuilder();
        if (logo == 0) for (int i = 0; i < size; i++) stringBuilder.append("─");
        else if (logo == 1) for (int i = 0; i < (size - length) / 2; i++) stringBuilder.append(" ");
        else for (int i = 0; i < size - length - (size - length) / 2; i++) stringBuilder.append(" ");
        return stringBuilder.toString();
    }

}


/***
 * @ClassName: NewLock
 * @Description:
 * @Auther: ycjiang
 * @Date: 2022/9/7 21:57
 * @version : V1.0
 */
@Slf4j
class NewLock {
    public boolean isLocked = false;
    public synchronized void lock()
            throws InterruptedException{
        while(isLocked){
            wait();
        }
        log.info(" this object is isLocked.....");
        isLocked = true;
    }

    public synchronized void unlock(){
        log.info(" this object is isUnLocked.....");
        isLocked = false;
        notify();
    }
}