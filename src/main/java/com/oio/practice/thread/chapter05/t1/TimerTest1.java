package com.oio.practice.thread.chapter05.t1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * 测试TaskTimer
 *  如果以守护线程创建，在未来要执行的任务还没有执行，线程就会消亡。
 *  未来时间要执行的任务会在未来的时间执行。
 *  如果执行时间是个过去的时间，则会立刻执行。
 * @Author: LiQiongchao
 * @Date: 2020/4/18 22:48
 */
public class TimerTest1 {

    public static Timer timer = new Timer();
    // 建议使用ScheduledExecutorService，而不是Timer
//    public static ScheduledExecutorService service = new ScheduledThreadPoolExecutor(3);


    static TimerTask task = new TimerTask() {
        @Override
        public void run() {
            System.out.println("运行了！时间为：" + new Date());
        }
    };

    public static void main(String[] args) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = "2020-04-18 23:08:00";
        Date date = simpleDateFormat.parse(dateStr);
        System.out.println("任务执行时间： " + dateStr +", 启动时间：" + new Date());
        timer.schedule(task, date);
        /*
        任务执行时间： 2020-04-18 23:08:00, 启动时间：Sat Apr 18 23:07:48 CST 2020
        运行了！时间为：Sat Apr 18 23:08:00 CST 2020
         */
    }

}
