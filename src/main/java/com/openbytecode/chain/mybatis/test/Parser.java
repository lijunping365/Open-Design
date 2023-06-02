package com.openbytecode.chain.mybatis.test;

import java.util.Map;

/**
 * @author lijunping
 */
public interface Parser {

    Map<String, String> parse(String response);
}
