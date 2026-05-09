package com.github.paohaijiao.function.date;
import com.github.paohaijiao.function.domain.JQuickBaseMethodInvoker;
import com.github.paohaijiao.spi.anno.Priority;
import com.github.paohaijiao.spi.constants.PriorityConstants;
import java.time.LocalDateTime;
import java.util.List;

@Priority(PriorityConstants.SYSTEM_MEDIUM)
public class JQuickNowInvoker extends JQuickBaseMethodInvoker {

    public JQuickNowInvoker() {
        super("now", "获取当前日期时间");
    }

    @Override
    public Object invoke(List<Object> args) {
        return LocalDateTime.now();
    }
}
