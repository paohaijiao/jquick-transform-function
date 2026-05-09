package com.github.paohaijiao.function.date;

import com.github.paohaijiao.function.domain.JQuickBaseFunctionFunctionProvider;
import com.github.paohaijiao.spi.anno.Priority;
import com.github.paohaijiao.spi.constants.PriorityConstants;
import java.util.List;

@Priority(PriorityConstants.SYSTEM_MEDIUM)
public class JQuickTimestampFunctionProvider extends JQuickBaseFunctionFunctionProvider {

    public JQuickTimestampFunctionProvider() {
        super("timestamp", "获取当前时间戳");
    }

    @Override
    public Object invoke(List<Object> args) {
        return System.currentTimeMillis();
    }
}