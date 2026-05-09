package com.github.paohaijiao.function.domain;

import com.github.paohaijiao.function.core.JQuickMethodFunctionProvider;

import java.util.List;

/**
 * 方法调用器抽象基类
 */
public abstract class JQuickBaseFunctionFunctionProvider implements JQuickMethodFunctionProvider {

    protected final String methodName;
    protected final String description;

    public JQuickBaseFunctionFunctionProvider(String methodName, String description) {
        this.methodName = methodName;
        this.description = description;
    }

    @Override
    public String getMethodName() {
        return methodName;
    }

    @Override
    public String getDescription() {
        return description;
    }

    protected String asString(Object obj) {
        return obj != null ? obj.toString() : null;
    }

    protected int asInt(Object obj) {
        if (obj == null) return 0;
        if (obj instanceof Number) return ((Number) obj).intValue();
        return Integer.parseInt(obj.toString());
    }

    protected long asLong(Object obj) {
        if (obj == null) return 0L;
        if (obj instanceof Number) return ((Number) obj).longValue();
        return Long.parseLong(obj.toString());
    }

    protected double asDouble(Object obj) {
        if (obj == null) return 0.0;
        if (obj instanceof Number) return ((Number) obj).doubleValue();
        return Double.parseDouble(obj.toString());
    }

    protected boolean asBoolean(Object obj) {
        if (obj == null) return false;
        if (obj instanceof Boolean) return (Boolean) obj;
        return Boolean.parseBoolean(obj.toString());
    }

    protected void validateArgCount(List<Object> args, int expected) {
        if (args.size() != expected) {
            throw new IllegalArgumentException(
                    String.format("Method '%s' expects %d argument(s), got %d", methodName, expected, args.size())
            );
        }
    }

    protected void validateArgCountRange(List<Object> args, int min, int max) {
        if (args.size() < min || args.size() > max) {
            throw new IllegalArgumentException(String.format("Method '%s' expects %d-%d argument(s), got %d", methodName, min, max, args.size())
            );
        }
    }
}
