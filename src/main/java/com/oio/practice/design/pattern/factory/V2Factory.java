package com.oio.practice.design.pattern.factory;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Liqc
 * @date 2019/6/24 10:15
 */
public class V2Factory {

    public static void main(String[] args) {
        try {
            AxService axService = DispatchRequestFactory.createAxService();
            axService.insert();
            DispatchRequestFactory.createAxbService().insert();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }


}

class DispatchRequestFactory {

    private final static String HOST = "";
    private final static String axbClass = "com.oio.practice.design.pattern.factory.SanXiAxbServiceImpl";
    private final static String axClass = "com.oio.practice.design.pattern.factory.GuanXiAxServiceImpl";

    public static AxbService createAxbService() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        AxbService axbService = (AxbService) Class.forName(axbClass).newInstance();
        return axbService;
    }

    public static AxService createAxService() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        AxService axService = (AxService) Class.forName(axClass).newInstance();
        return axService;
    }

}


interface AxbService {
    void insert();
    void delete();
}

interface AxService {
    void insert();
    void delete();
}

@Slf4j
class SanXiAxbServiceImpl implements AxbService {

    @Override
    public void insert() {
        log.info("san xi axb insert");
    }

    @Override
    public void delete() {
        log.info("san xi axb delete");
    }
}

@Slf4j
class SanXiAxServiceImpl implements AxService {

    @Override
    public void insert() {
      log.info("san xi ax insert");
    }

    @Override
    public void delete() {
        System.out.printf("san xi ax delete");
    }
}

@Slf4j
class GuanXiAxServiceImpl implements AxService {

    @Override
    public void insert() {
      log.info("guang xi ax insert");
    }

    @Override
    public void delete() {
        System.out.printf("guang xi ax delete");
    }
}







