package cx.bean;

import lombok.experimental.Delegate;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Chen Xiao
 * @since 2019-11-07 19:21
 */
public class User {
    private interface SimpleCollection {
        boolean add(String item);
        boolean remove(Object item);
    }

    @Delegate(types = SimpleCollection.class)
    private final Collection<String> collection = new ArrayList<String>();

    public static void main(String[] args) {
        User user=new User();
        user.add("item1");//实际上加到collection中去了
    }
}
