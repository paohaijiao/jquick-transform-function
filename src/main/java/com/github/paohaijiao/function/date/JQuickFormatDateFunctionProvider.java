package com.github.paohaijiao.function.date;
import com.github.paohaijiao.function.domain.JQuickBaseFunctionFunctionProvider;
import com.github.paohaijiao.spi.anno.Priority;
import com.github.paohaijiao.spi.constants.PriorityConstants;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Priority(PriorityConstants.SYSTEM_MEDIUM)
public class JQuickFormatDateFunctionProvider extends JQuickBaseFunctionFunctionProvider {

    private static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public JQuickFormatDateFunctionProvider() {
        super("formatDate", "格式化日期 - 用法: formatDate(date, pattern)");
    }

    @Override
    public Object invoke(List<Object> args) {
        validateArgCountRange(args, 1, 2);
        Object dateObj = args.get(0);
        String pattern = args.size() > 1 ? asString(args.get(1)) : DEFAULT_PATTERN;
        LocalDateTime date = parseToLocalDateTime(dateObj);
        if (date == null) return null;
        return date.format(DateTimeFormatter.ofPattern(pattern));
    }

    private LocalDateTime parseToLocalDateTime(Object obj) {
        if (obj == null) return null;
        if (obj instanceof LocalDateTime) return (LocalDateTime) obj;
        if (obj instanceof LocalDate) return ((LocalDate) obj).atStartOfDay();
        if (obj instanceof java.util.Date) {
            return ((java.util.Date) obj).toInstant()
                    .atZone(java.time.ZoneId.systemDefault())
                    .toLocalDateTime();
        }
        return LocalDateTime.parse(obj.toString());
    }
}
