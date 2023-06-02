package com.openbytecode.chain.mybatis.test.interceptor;


import com.openbytecode.chain.mybatis.Interceptor;
import com.openbytecode.chain.mybatis.Intercepts;
import com.openbytecode.chain.mybatis.Invocation;
import com.openbytecode.chain.mybatis.Signature;
import com.openbytecode.chain.mybatis.test.Parser;

/**
 * @author lijunping
 */
@Intercepts({@Signature(type = Parser.class,method = "parse",args = {String.class})})
public class ParserPlugin implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("ParserPlugin............Before");
        final Object proceed = invocation.proceed();//调用原方法
        System.out.println("ParserPlugin............After");
        return proceed;
    }
}