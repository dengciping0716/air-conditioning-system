package com.dengciping.ydroid.airconditioningsystem.data.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * $desc$
 *
 * @author DengCiPing
 * @date 2017/8/6 下午9:21
 */

@JsonInclude(JsonInclude.Include.NON_NULL)

public class AirData extends CommonBean {

    //温度
    @JsonProperty("name")
    public String name;

    //温度
    @JsonProperty("temp")
    public float temp;
    //湿度
    @JsonProperty("humidity")
    public float humidity;

    public AirData(String name, float temp, float humidity) {
        this.name = name;
        this.temp = temp;
        this.humidity = humidity;
    }
}
