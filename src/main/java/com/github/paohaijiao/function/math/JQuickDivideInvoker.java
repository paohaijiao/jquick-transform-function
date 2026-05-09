package com.github.paohaijiao.function.math;

import com.github.paohaijiao.function.domain.JQuickBaseMethodInvoker;
import com.github.paohaijiao.spi.anno.Priority;
import com.github.paohaijiao.spi.constants.PriorityConstants;
import java.util.List;

@Priority(PriorityConstants.SYSTEM_HIGH)
public class JQuickDivideInvoker extends JQuickBaseMethodInvoker {

    public JQuickDivideInvoker() {
        super("divide", "除法 - 用法: divide(a, b, ...) 返回 a/b/...");
    }

    @Override
    public Object invoke(List<Object> args) {
        if (args.size() < 2) {
            throw new IllegalArgumentException("除法至少需要2个参数");
        }
        double result = asDouble(args.get(0));
        for (int i = 1; i < args.size(); i++) {
            double divisor = asDouble(args.get(i));
            if (divisor == 0) {
                throw new ArithmeticException("除数不能为零");
            }
            result /= divisor;
        }
        return result;
    }
}
