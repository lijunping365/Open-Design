package com.openbytecode.chain.mybatis.test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lijunping
 */
public class SimpleParser implements Parser{
    @Override
    public Map<String, String> parse(String response) {
        System.out.println("执行解析");
        Map<String, String> map = new HashMap<>();
        map.put("key", response);
        map.put("value", response);
        return map;
    }
}
