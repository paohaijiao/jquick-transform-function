package com.github.paohaijiao.function.date;


import com.github.paohaijiao.function.domain.JQuickBaseMethodInvoker;
import com.github.paohaijiao.spi.anno.Priority;
import com.github.paohaijiao.spi.constants.PriorityConstants;
import java.time.LocalDate;
import java.util.List;

@Priority(PriorityConstants.SYSTEM_MEDIUM)
public class JQuickTodayInvoker extends JQuickBaseMethodInvoker {

    public JQuickTodayInvoker() {
        super("today", "获取当前日期");
    }

    @Override
    public Object invoke(List<Object> args) {
        return LocalDate.now();
    }
}
