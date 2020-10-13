package com.everest.fastjson;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.everest.fastjson.pojo.Config;
import java.util.Map;

/**
 * @Date: 2020/9/21
 * @author: <a href="mailto:chrdw.p@gmail.com">Edison Zhe Wang</a>
 * Desc: 如何修改一个JSONObject对象
 */
public class FastJsonMainStarter {

    //    "textBucket":{ "default":[0, 99]}
    private static final String config =
            "{\"pipelines\":{\"Start\":{\"name\":\"dog\",\"age\":5,\"textId\":\"abce\"}}}";
    private static final String tKey = "textBucket";
    private static final String tValue = "{\"default\":[0,99]}";

    public static void main(String[] args) {
        JSONObject configObj = JSONObject.parseObject(config);
        Object tValueObj = JSONObject.parse(tValue);

        Config c = JSONObject.parseObject(config, Config.class);
        Map<String, Map<String, Object>> pipelines = c.getPipelines();
        //        JSONObject ret = JSONObject.parseObject(JSONObject.toJSONString(pipelines));
        JSONObject ret = (JSONObject) JSONObject.toJSON(pipelines);
        for (Map.Entry<String, Map<String, Object>> entry : pipelines.entrySet()) {
            JSONObject objUnderPipelines =
                    //                    JSONObject.parseObject(JSONObject.toJSONString(entry.getValue()));
                    (JSONObject) JSONObject.toJSON(entry.getValue());
            objUnderPipelines.put(tKey, tValueObj);
            ret.put(entry.getKey(), objUnderPipelines);
        }
        String jsonString = JSONObject.toJSONString(ret);
        // 一旦出现具体的引用类型就会导致转换为json时候出现转义符\
        // 同样,如果在体的引用类型那一层的下面还有嵌套的map,则会把map转化为String,就会出现转义符\
        // c.setPipelines(ret.toJavaObject(new TypeReference<Map<String, Map<String, String>>>() {}));
        configObj.put("pipelines", ret);
        Config cRet = configObj.toJavaObject(Config.class);
        System.out.println(jsonString);
        System.out.println(FastJsonMainStarter.config);
        System.out.println(configObj);
    }
}
