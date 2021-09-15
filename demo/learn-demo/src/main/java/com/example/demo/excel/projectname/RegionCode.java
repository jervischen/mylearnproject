package com.example.demo.excel.projectname;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Chen Xiao
 * @since 2020-05-26 09:46
 */
@Data
public class RegionCode {

    private String provinceCode;


    private String provinceName;


    private List<Area> area = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegionCode that = (RegionCode) o;
        return Objects.equals(provinceCode, that.provinceCode) &&
                Objects.equals(provinceName, that.provinceName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(provinceCode, provinceName);
    }

    @Data
    static class Area{
        private String cityCode;
        private String cityName;
    }

}
