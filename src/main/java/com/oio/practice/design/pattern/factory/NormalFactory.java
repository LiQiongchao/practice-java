package com.oio.practice.design.pattern.factory;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 工厂模式
 * @author Liqc
 * @date 2019/6/24 9:39
 */
public class NormalFactory {

    public static void main(String[] args) {
        User user = new User();
        IFactory factory = new AccessFactory();
        IUser iUser = factory.createIUser();
        iUser.insert(user);
        iUser.get(1);
    }

}

interface IFactory{
    IUser createIUser();
}

class SqlServerFactory implements IFactory {

    @Override
    public IUser createIUser() {
        return new SqlServerIUser();
    }
}


class AccessFactory implements IFactory {

    @Override
    public IUser createIUser() {
        return new AccessIUser();
    }
}

@Data
class User {
    private String name;
    private Integer age;
    private Integer sex;
}


interface IUser {
    void insert(User user);
    User get(int id);
}

@Slf4j
class SqlServerIUser implements IUser {

    @Override
    public void insert(User user) {
      log.info("insert sqlserver user");
    }

    @Override
    public User get(int id) {
        log.info("get sqlserver user uid:{}", id);
        return null;
    }
}

@Slf4j
class AccessIUser implements IUser {

    @Override
    public void insert(User user) {
      log.info("insert access user");
    }

    @Override
    public User get(int id) {
        log.info("get access user");
        return null;
    }
}

