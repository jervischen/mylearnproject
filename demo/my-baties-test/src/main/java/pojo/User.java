package pojo;

import lombok.Data;

/**
 * @author Chen Xiao
 *
 * @since 2019-11-19 17:08
 */
@Data
public class User implements Comparable<User>{

    private long id;
    private String name;
    private int age;

    @Override
    public int compareTo(User o) {
        if (this.age == o.getAge()){
            return 0;
        }

        if (this.age > o.getAge()){
            return -1;
        }


        if (this.age < o.getAge()){
            return 1;
        }

        return 0;
    }
}
