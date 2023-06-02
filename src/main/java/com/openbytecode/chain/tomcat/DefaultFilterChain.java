package com.openbytecode.chain.tomcat;


/**
 * @author lijunping
 */
public class DefaultFilterChain implements FilterChain{

    public static final int INCREMENT = 10;
    /**
     * Filters.
     */
    private Filter[] filters = new Filter[0];

    /**
     * The int which is used to maintain the current position
     * in the filter chain.
     */
    private int pos = 0;

    /**
     * The int which gives the current number of filters in the chain.
     */
    private int n = 0;

    @Override
    public void addFilter(Filter filter) {
        if (n == filters.length) {
            Filter[] newFilters = new Filter[n + INCREMENT];
            System.arraycopy(filters, 0, newFilters, 0, n);
            filters = newFilters;
        }
        filters[n++] = filter;
    }

    @Override
    public void doFilter(HttpRequest request, HttpResponse response){
        if (pos < n) {
            //获取第pos个filter
            Filter filter = filters[pos++];
            filter.doFilter(request, response, this);
        }
    }
}
