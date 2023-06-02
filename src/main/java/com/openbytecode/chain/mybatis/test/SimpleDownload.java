package com.openbytecode.chain.mybatis.test;

/**
 * @author lijunping
 */
public class SimpleDownload implements Download{

    @Override
    public String download(String request) {
        System.out.println("执行了下载");
        return "dddddddddddddddddddddddddd";
    }
}
