package cx.bean;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Chen Xiao
 * @since 2019-11-07 17:46
 */
@Data
@EqualsAndHashCode(callSuper =true)
public class BBean extends ABean {

    private int age;
}
