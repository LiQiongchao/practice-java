package com.oio.practice;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author Liqc
 * @date 2019/5/15 10:02
 */
public class UtilTest {

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

