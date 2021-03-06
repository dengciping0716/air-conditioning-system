package com.dengciping.ydroid.airconditioningsystem.data.bean;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.HashMap;
import java.util.Map;

import cn.droidlover.xdroidmvp.base.databinding.ItemModel;

/**
 * $desc$
 *
 * @author DengCiPing
 * @date 2017/8/6 下午9:29
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonBean implements ItemModel {

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public int getItemViewLayoutId() {
        return 0;
    }
}
