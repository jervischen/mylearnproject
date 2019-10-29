package cx.manager;

import com.google.inject.Inject;
import com.netflix.governator.annotations.AutoBindSingleton;

/**
 * @author Chen Xiao
 * @since 2019-10-29 20:01
 */
@AutoBindSingleton
public class Bmanager {

    @Inject
    Amanager amanager;

    public void excute(){
        amanager.excute();
        System.out.println("BBB");
    }
}
