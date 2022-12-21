package com.oio.practice.python;

import org.python.core.PyFunction;
import org.python.core.PyInteger;
import org.python.core.PyObject;
import org.python.core.PyString;
import org.python.util.PythonInterpreter;

public class Function {
    public static void main(String[] args) {
        PythonInterpreter interpreter = new PythonInterpreter();
        interpreter.execfile("src/main/resources/python/print.py");
                
        // 第一个参数为期望获得的函数（变量）的名字，第二个参数为期望返回的对象类型
        PyFunction pyFunction = interpreter.get("add", PyFunction.class);
        int a = 5, b = 10;
        //调用函数，如果函数需要参数，在Java中必须先将参数转化为对应的“Python类型”
        PyObject pyobj = pyFunction.__call__(new PyInteger(a), new PyInteger(b));
        System.out.println("the answer is: " + pyobj);

        int c = 3, d = 6;
        //调用函数，如果函数需要参数，在Java中必须先将参数转化为对应的“Python类型”
        PyObject pyobj2 = pyFunction.__call__(new PyInteger(c), new PyInteger(d));
        System.out.println("the answer is: " + pyobj2);

        // 调用别的方法
        PyFunction printFunction = interpreter.get("print_method", PyFunction.class);
        PyObject printReturn = printFunction.__call__(new PyString("Lee"));
        System.out.println(printReturn);
    }

}