package com.openbytecode.chain.mybatis.test.interceptor;

import com.openbytecode.chain.mybatis.Interceptor;
import com.openbytecode.chain.mybatis.Intercepts;
import com.openbytecode.chain.mybatis.Invocation;
import com.openbytecode.chain.mybatis.Signature;
import com.openbytecode.chain.mybatis.test.Download;

/**
 * @author lijunping
 */
@Intercepts({@Signature(type = Download.class,method = "download",args = {String.class})})
public class DownloadPlugin implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("DownloadPlugin............Before");
        final Object proceed = invocation.proceed();//调用原方法
        System.out.println("DownloadPlugin............After");
        return proceed;
    }
}