package com.agent;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.util.HotSwapper;

import java.lang.instrument.Instrumentation;

/**
 * @author Chen Xiao
 * @since 2021-09-13 16:24
 */
public class MyPreMainAgent {

    //方法名和参数都是固定的 premain表示在主程序运行之前运行
    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("PreMain start");
//        System.out.println(agentArgs);
//        System.out.println(inst);
    }

    public static void agentmain(String agentArgs, Instrumentation inst) throws Exception {
        System.out.println("agentmain start");
//        System.out.println(agentArgs);
//        System.out.println(inst);




        ClassPool pool = ClassPool.getDefault();
        CtClass cc = pool.get("javaagent.Main");

        CtMethod personFly = cc.getDeclaredMethod("a");
        personFly.setBody("System.out.println(\"修改方法\");");

        new HotSwapper(8000).reload("javaagent.Main", cc.toBytecode());

    }
}
