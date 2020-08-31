package com.oio.practice;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import org.assertj.core.util.Lists;
import org.junit.Test;

import java.net.Inet4Address;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author Liqc
 * @date 2019/5/15 10:02
 */
public class UtilTest {

    @Test
    public void randomTest() {
        ArrayList<String> nums = Lists.newArrayList("13112414926", "18675470301", "13265647689", "18575973626", "13035710059", "18576297154", "15622999472", "18587724054", "18577203204", "15676228479", "15677454057", "15678522104", "18577704615", "15677877439", "18577946594", "18679084763", "13257081547", "13247827674", "18679340341", "13263944807", "18507951407", "18579665943", "13097318394", "13044986145", "13177996934", "13116304004", "17586434994", "15697048540", "18687594249", "13298974768", "18589622418", "13220044160", "18691796641", "13028704917", "15693267427", "13263249482", "13239444274", "15593084573", "15595428742", "17697414564");
        Set<Integer> set = new HashSet<>(11);
        while (set.size() < 10) {
            int i = new Random().nextInt(39);
            if (set.contains(i)) {
                continue;
            }
            System.out.println(nums.get(i));
            set.add(i);
        }

    }


    @Test
    public void timeUnitTest() {
        long l = System.currentTimeMillis();
        System.out.println(l);
        System.out.println(TimeUnit.NANOSECONDS.convert(l, TimeUnit.MILLISECONDS));
        System.out.println(System.nanoTime());
    }

    @Test
    public void contains() {
        List<String> AXB_CHARGING_CALL_TYPE = Arrays.asList("10", "11", "21");
        System.out.println(AXB_CHARGING_CALL_TYPE.contains("10"));
        System.out.println(AXB_CHARGING_CALL_TYPE.contains("20"));
        System.out.println("10,11,21".contains("11"));
        System.out.println("10,11,21".contains("20"));
    }

    public static int g(int n) {
        if (n % 2 == 0)
            return n/10;
        return g(g(n/10));
    }


    @Test
    public void javaTest() {
        System.out.println(g(3124013));

    }

    @Test
    public void dateTest() {
//        LocalDateTime localDateTime = LocalDateTime.now().atZone(ZoneId.of("Asia/Shanghai")).plusHours(-12);
        int a = 12;
        LocalDateTime localDateTime = LocalDateTime.now().plusHours(-a);
        System.out.println(localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
//        System.out.println(localDateTime.toInstant(ZoneOffset.of("GMT+8")));
        System.out.println(localDateTime.toInstant(ZoneOffset.UTC));

    }

    @Test
    public void gsonTest() {
        CallLimit callLimit = new CallLimit();
        callLimit.setAppkey("haowaihao");
        callLimit.setMaxCall(1);
        callLimit.setExpiration(24);
        callLimit.setDuration(0);
        System.out.println(new Gson().toJson(callLimit));
    }

    public class CallLimit {

        private Integer id;
        private String appkey;
        private Integer duration;
        private Integer maxCall;
        private Integer expiration;
        private Date createTime;
        @Expose(serialize = false)
        private Date updateTime;
        @Expose(serialize = false)
        private Integer status;

        @Expose(serialize = false, deserialize = false)
        private String telB;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getAppkey() {
            return appkey;
        }

        public void setAppkey(String appkey) {
            this.appkey = appkey;
        }

        public Integer getDuration() {
            return duration;
        }

        public void setDuration(Integer duration) {
            this.duration = duration;
        }

        public Integer getMaxCall() {
            return maxCall;
        }

        public void setMaxCall(Integer maxCall) {
            this.maxCall = maxCall;
        }

        public Integer getExpiration() {
            return expiration;
        }

        public void setExpiration(Integer expiration) {
            this.expiration = expiration;
        }

        public Date getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Date createTime) {
            this.createTime = createTime;
        }

        public Date getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(Date updateTime) {
            this.updateTime = updateTime;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public String getTelB() {
            return telB;
        }

        public void setTelB(String telB) {
            this.telB = telB;
        }

        @Override
        public String toString() {
            return "CallLimit{" +
                    "id=" + id +
                    ", appkey='" + appkey + '\'' +
                    ", duration=" + duration +
                    ", maxCall=" + maxCall +
                    ", expiration=" + expiration +
                    ", createTime=" + createTime +
                    ", updateTime=" + updateTime +
                    ", status=" + status +
                    ", telB='" + telB + '\'' +
                    '}';
        }
    }

}
class Example{
    public static void Change(int x, int[] y, int[] z) {
        x = 1;		y[0] = 2;
        z = new int[5];		z[0] = 555;
    }
    public static void main(String[] args) {
        int x = 111;
        int[] y = { 222, 333, 444, 555 };
        int[] z = { 666, 777, 888, 999 };
        Change(x, y, z);
        System.out.printf("%d,%d,%d\n",x,y[0],z[0]);
        int a=1 ,b=2;
        boolean cc = !(a < b) && !(a > b);
    }
}


