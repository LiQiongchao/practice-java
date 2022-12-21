package com.oio.practice.python;

import org.python.util.PythonInterpreter;

/**
 * @Author: Liqc
 * @Date: 2022/12/21 18:05
 */
public class RunPythonInJava {

    public static void main(String[] args) {
        PythonInterpreter interpreter = new PythonInterpreter();
        //选择执行的的Python语句
        // interpreter.exec("a='hello world'; ");
        // interpreter.exec("print a;");

        //我在这里使用相对路径，注意区分
        interpreter.execfile("src/main/resources/python/hello.py");
    }

}
