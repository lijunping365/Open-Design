package com.openbytecode.chain.dubbo.test.provider;


import com.openbytecode.chain.dubbo.*;
import com.openbytecode.chain.dubbo.response.Response;
import com.openbytecode.chain.dubbo.response.Result;
import com.openbytecode.chain.dubbo.test.DubboInvoker;

/**
 * @author lijunping
 */
public class DubboFilterTest {
    public static void main(String[] args) throws Exception {

        FilterChainBuilder filterChainBuilder = new DefaultFilterChainBuilder();

        DubboInvoker<DemoService> invokerWithFilter = new DubboInvoker<>(DemoService.class);

        Invoker<?> invokerAfterBuild = filterChainBuilder.buildInvokerChain(invokerWithFilter, "echo,token,trace,timeout", "PROVIDER");

        Invocation invocation = new RpcInvocation(invokerWithFilter, "sayHello", new Object[]{"hello"}, DemoService.class.getName());
        Result result = invokerAfterBuild.invoke(invocation);
        Object value = result.getValue();
        Response response = (Response) value;
        System.out.println(response.getValue());
    }
}
