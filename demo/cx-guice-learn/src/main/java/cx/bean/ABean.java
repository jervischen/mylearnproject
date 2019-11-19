package cx.bean;

import lombok.Data;

/**
 * @author Chen Xiao
 * @since 2019-10-29 19:49
 */
public class ABean {

    private String name;

    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "ABean{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
