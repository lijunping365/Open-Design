package com.openbytecode.chain.dubbo;

/**
 * @author lijunping
 */
public class RpcInvocation implements Invocation{

    private String methodName;

    private Object[] arguments;

    private String interfaceName;

    private transient Invoker<?> invoker;

    public RpcInvocation(Invocation invocation) {
        this(invocation, null);
    }

    public RpcInvocation(Invocation invocation, Invoker<?> invoker) {
        this(invoker, invocation.getMethodName(), invocation.getArguments(), invocation.getServiceName());
    }

    public RpcInvocation(Invoker<?> invoker, String methodName, Object[] arguments, String interfaceName){
        this.invoker = invoker;
        this.methodName = methodName;
        this.arguments = arguments == null ? new Object[0] : arguments;
        this.interfaceName = interfaceName;
    }

    @Override
    public Invoker<?> getInvoker() {
        return invoker;
    }

    @Override
    public String getMethodName() {
        return methodName;
    }

    @Override
    public String getServiceName() {
        return interfaceName;
    }

    @Override
    public Object[] getArguments() {
        return arguments;
    }

    public void setInvoker(Invoker<?> invoker) {
        this.invoker = invoker;
    }
}
