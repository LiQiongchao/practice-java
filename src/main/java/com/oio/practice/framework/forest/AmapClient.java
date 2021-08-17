package com.oio.practice.framework.forest;

import com.dtflys.forest.annotation.Get;

import java.util.Map;

/**
 * @author Liqc
 * @date 2021/3/12 11:30
 */
public interface AmapClient {

    /**
     * @Get注解代表该方法专做GET请求
     * 在url中的${0}代表引用第一个参数，${1}引用第二个参数
     *
     * @param longitude
     * @param latitude
     * @return
     */
    @Get("http://ditu.amap.com/service/regeo?longitude=${0}&latitude=${1}")
    Map getLocation(String longitude, String latitude);


}
