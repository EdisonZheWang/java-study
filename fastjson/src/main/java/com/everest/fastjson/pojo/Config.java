package com.everest.fastjson.pojo;

import java.util.Map;

/**
 * @Date: 2020/9/21
 * @author: <a href="mailto:chrdw.p@gmail.com">Edison Zhe Wang</a>
 */

public class Config {

    /**
     * 此处如果定义成Map<String, Map<String, String>>就会导致最里面的类型退化,出现转义符\
     */
    private Map<String, Map<String, Object>> pipelines;

    public Map<String, Map<String, Object>> getPipelines() {
        return pipelines;
    }

    public void setPipelines(Map<String, Map<String, Object>> pipelines) {
        this.pipelines = pipelines;
    }
}
