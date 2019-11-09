package cx.bean;

import lombok.*;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Chen Xiao
 * @since 2019-11-07 18:00
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
@Slf4j
public class Dbean {
    private String name;

    private String address;

    @SneakyThrows
    @Synchronized("name")
    public void testAn(@NonNull String str){
        log.info("123");
        System.out.println(str);
    }

}
