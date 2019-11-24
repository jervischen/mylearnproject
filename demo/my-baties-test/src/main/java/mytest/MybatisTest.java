package mytest;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import jdk.nashorn.internal.ir.debug.ObjectSizeCalculator;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import pojo.User;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author Chen Xiao
 * @since 2019-11-19 17:10
 */
public class MybatisTest {
    public static void main(String[] args) throws Exception {
        // 指定全局配置文件
        String resource = "mybatis-config.xml";
        // 读取配置文件
        InputStream inputStream = Resources.getResourceAsStream(resource);
        // 构建sqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        // 获取sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            // 操作CRUD，第一个参数：指定statement，规则：命名空间+“.”+statementId
            // 第二个参数：指定传入sql的参数：这里是用户id
            User user = sqlSession.selectOne("MyMapper.selectUser", 1);
            System.out.println(user);
        } finally {
            sqlSession.close();
        }

        System.out.println("Integer: " + ObjectSizeCalculator.getObjectSize(Integer.valueOf(1)));
        System.out.println("Long: " + ObjectSizeCalculator.getObjectSize(Long.valueOf(1L)));


        String a = "名";
        System.out.println("UTF-8编码长度:"+a.getBytes("UTF-8").length);
        System.out.println("GBK编码长度:"+a.getBytes("GBK").length);
        System.out.println("GB2312编码长度:"+a.getBytes("GB2312").length);
        System.out.println("==========================================");
    }

    @Test
    public void testSort(){
        User user1 = new User();
        user1.setId(1);
        user1.setAge(1);
        user1.setName("a");

        User user2 = new User();
        user2.setId(2);
        user2.setAge(10);
        user2.setName("b");

        List<User> list = Lists.newArrayList();
        list.add(user1);
        list.add(user2);

        System.out.println(list);
        Collections.sort(list);

        System.out.println(list);

        Map<String,String> a = Maps.newHashMap();
        System.out.println(a.size());
        List<String> ll = new ArrayList<>(a.size());
        System.out.println(ll.isEmpty());
    }

}
