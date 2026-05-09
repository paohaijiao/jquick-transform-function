package com.github.paohaijiao.function.date;


import com.github.paohaijiao.function.domain.JQuickBaseFunctionFunctionProvider;
import com.github.paohaijiao.spi.anno.Priority;
import com.github.paohaijiao.spi.constants.PriorityConstants;
import java.time.LocalDate;
import java.util.List;

@Priority(PriorityConstants.SYSTEM_MEDIUM)
public class JQuickTodayFunctionProvider extends JQuickBaseFunctionFunctionProvider {

    public JQuickTodayFunctionProvider() {
        super("today", "获取当前日期");
    }

    @Override
    public Object invoke(List<Object> args) {
        return LocalDate.now();
    }
}
