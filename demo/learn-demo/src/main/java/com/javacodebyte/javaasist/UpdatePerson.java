package com.javacodebyte.javaasist;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.Modifier;
import javassist.util.HotSwapper;

import java.lang.reflect.Method;

/**
 * @author Chen Xiao
 * @since 2021-09-10 17:46
 */
public class UpdatePerson {
    public static void update() throws Exception {
        ClassPool pool = ClassPool.getDefault();
        CtClass cc = pool.get("PersonService");

        CtMethod personFly = cc.getDeclaredMethod("personFly");
        personFly.insertBefore("System.out.println(\"起飞之前准备降落伞\");");
        personFly.insertAfter("System.out.println(\"成功落地。。。。\");");


        //新增一个方法
        CtMethod ctMethod = new CtMethod(CtClass.voidType, "joinFriend", new CtClass[]{}, cc);
        ctMethod.setModifiers(Modifier.PUBLIC);
        ctMethod.setBody("{System.out.println(\"i want to be your friend\");}");
        cc.addMethod(ctMethod);

        Object person = cc.toClass().newInstance();
        // 调用 personFly 方法
        Method personFlyMethod = person.getClass().getMethod("personFly");
        personFlyMethod.invoke(person);
        //调用 joinFriend 方法
        Method execute = person.getClass().getMethod("joinFriend");
        execute.invoke(person);


        System.out.println("======修改之后调用========");
        new PersonService().personFly();
    }

    public static void main(String[] args) {
        try {
//            update();

            updateAndMod();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void updateAndMod() throws Exception {

        PersonService p = new PersonService();
        p.personFly();

        ClassPool pool = ClassPool.getDefault();
        CtClass cc = pool.get("PersonService");

        CtMethod personFly = cc.getDeclaredMethod("personFly");
        personFly.setBody("System.out.println(\"修改方法\");");

        new HotSwapper(8000).reload("PersonService", cc.toBytecode());

        p.personFly();

        System.in.read();
    }
}
