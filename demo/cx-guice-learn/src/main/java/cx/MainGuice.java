package cx;

import com.google.common.collect.Lists;
import com.google.inject.Injector;
import com.netflix.governator.guice.LifecycleInjector;
import cx.manager.Bmanager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Chen Xiao
 * @since 2019-10-29 19:47
 */
public class MainGuice {
    public static void main(String[] args) throws Exception {

//        LifecycleInjector lifecycleInjector = LifecycleInjector.builder().usingBasePackages("cx").build();
//        lifecycleInjector.getLifecycleManager().start();
//
//
//        Injector injector = lifecycleInjector.createInjector();
//        Bmanager instance = injector.getInstance(Bmanager.class);
//        instance.excute();

        List<String> nameList = Lists.newArrayList();
        nameList.add("abc");
        nameList.add("eeee");
        nameList.add("xxxxx");
        List<String> collect = nameList.stream().filter(a -> a.length() > 3).collect(Collectors.toList());

        System.out.println(nameList);
        System.out.println(collect);

        Map<String, String> collect1 = nameList.stream().collect(Collectors.toMap(key -> key+"1", val -> val));
        System.out.println(collect1);

        Optional<String> reduce = nameList.stream().reduce((a, b) -> a + b);
        System.out.println(reduce.get());
    }

    public void testSyntacticSugar(){
        ArrayList<String> list = Lists.newArrayList();
        list.add("a");
        list.add("b");
        for (String s : list) {
            System.out.println(s);
        }

        for (int i=0;i<list.size();i++){
            System.out.println(list.get(i));
        }
    }
}
