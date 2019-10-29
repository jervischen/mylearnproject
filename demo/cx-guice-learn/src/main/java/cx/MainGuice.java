package cx;

import com.google.inject.Injector;
import com.netflix.governator.guice.LifecycleInjector;
import cx.manager.Bmanager;

/**
 * @author Chen Xiao
 * @since 2019-10-29 19:47
 */
public class MainGuice {
    public static void main(String[] args) throws Exception {

        LifecycleInjector lifecycleInjector = LifecycleInjector.builder().usingBasePackages("cx").build();
        lifecycleInjector.getLifecycleManager().start();


        Injector injector = lifecycleInjector.createInjector();
        Bmanager instance = injector.getInstance(Bmanager.class);
        instance.excute();

    }
}
