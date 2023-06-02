package com.openbytecode.chain.mybatis.test;

import com.openbytecode.chain.mybatis.InterceptorChain;
import com.openbytecode.chain.mybatis.test.interceptor.DownloadPlugin;
import com.openbytecode.chain.mybatis.test.interceptor.GlobalPlugin;
import com.openbytecode.chain.mybatis.test.interceptor.ParserPlugin;

import java.util.Map;

/**
 * @author lijunping
 */
public class PluginTest {

    public static void main(String[] args) {
        InterceptorChain interceptorChain = new InterceptorChain();

        interceptorChain.addInterceptor(new DownloadPlugin());
        interceptorChain.addInterceptor(new ParserPlugin());
        interceptorChain.addInterceptor(new GlobalPlugin());

        Download downloadProxy = (Download) interceptorChain.pluginAll(new SimpleDownload());
        Parser parserProxy = (Parser) interceptorChain.pluginAll(new SimpleParser());

        String response = downloadProxy.download("aaaaaa");
        Map<String, String> parse = parserProxy.parse(response);


        System.out.println(parse.toString());
    }
}
