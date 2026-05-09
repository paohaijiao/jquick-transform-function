package com.github.paohaijiao.function.manager;

import com.github.paohaijiao.console.JConsole;
import com.github.paohaijiao.function.core.JQuickMethodFunctionProvider;
import com.github.paohaijiao.spi.ServiceLoader;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 方法调用管理器 - 基于你的 SPI 管理所有方法调用
 *
 * @author Martin
 * @version 2.0.0
 */
public class JQuickMethodInvocationManager {

    private static final JConsole log = JConsole.initConsoleEnvironment();

    private static volatile JQuickMethodInvocationManager instance;

    private final Map<String, JQuickMethodFunctionProvider> invokerRegistry = new ConcurrentHashMap<>();

    private final List<JQuickMethodFunctionProvider> allInvokers;

    private final AtomicBoolean prettyPrintEnabled = new AtomicBoolean(true);

    private final Map<String, List<JQuickMethodFunctionProvider>> groupedInvokers = new ConcurrentHashMap<>();

    private JQuickMethodInvocationManager() {
        this.allInvokers = ServiceLoader.loadServicesByPriority(JQuickMethodFunctionProvider.class);
        for (JQuickMethodFunctionProvider invoker : allInvokers) {
            invokerRegistry.put(invoker.getMethodName(), invoker);
            String group = extractGroup(invoker.getDescription());
            List<JQuickMethodFunctionProvider> list = groupedInvokers.get(group);
            if (list == null) {
                list = new ArrayList<>();
                groupedInvokers.put(group, list);
            }
            list.add(invoker);
        }
        log.debug("JQuickMethodInvocationManager initialized with " + allInvokers.size() + " methods");
    }

    public static JQuickMethodInvocationManager getInstance() {
        if (instance == null) {
            synchronized (JQuickMethodInvocationManager.class) {
                if (instance == null) {
                    instance = new JQuickMethodInvocationManager();
                }
            }
        }
        return instance;
    }

    /**
     * 调用方法
     */
    public Object invoke(String methodName, Object... args) {
        List<Object> argList = args == null ? Collections.emptyList() : Arrays.asList(args);
        return invoke(methodName, argList);
    }

    /**
     * 调用方法
     */
    public Object invoke(String methodName, List<Object> args) {
        JQuickMethodFunctionProvider invoker = invokerRegistry.get(methodName);
        if (invoker == null) {
            log.error("Method not found: " + methodName);
            throw new IllegalArgumentException("Method not found: " + methodName);
        }
        log.debug("Invoking method: " + methodName + " with args: " + args);
        return invoker.invoke(args);
    }

    /**
     * 检查方法是否存在
     */
    public boolean hasMethod(String methodName) {
        return invokerRegistry.containsKey(methodName);
    }

    /**
     * 获取所有支持的方法
     */
    public List<String> getSupportedMethods() {
        return new ArrayList<>(invokerRegistry.keySet());
    }

    /**
     * 获取所有方法调用器
     */
    public Collection<JQuickMethodFunctionProvider> getAllInvokers() {
        return Collections.unmodifiableCollection(allInvokers);
    }

    /**
     * 根据方法名获取调用器
     */
    public Optional<JQuickMethodFunctionProvider> getInvoker(String methodName) {
        return Optional.ofNullable(invokerRegistry.get(methodName));
    }

    /**
     * 动态注册方法
     */
    public void registerInvoker(JQuickMethodFunctionProvider invoker) {
        invokerRegistry.put(invoker.getMethodName(), invoker);
        String group = extractGroup(invoker.getDescription());
        List<JQuickMethodFunctionProvider> list = groupedInvokers.get(group);
        if (list == null) {
            list = new ArrayList<>();
            groupedInvokers.put(group, list);
        }
        list.add(invoker);
        log.info("Registered method: " + invoker.getMethodName() + " - " + invoker.getDescription());
    }

    /**
     * 注销方法
     */
    public void unregisterInvoker(String methodName) {
        JQuickMethodFunctionProvider removed = invokerRegistry.remove(methodName);
        if (removed != null) {
            String group = extractGroup(removed.getDescription());
            List<JQuickMethodFunctionProvider> groupList = groupedInvokers.get(group);
            if (groupList != null) {
                groupList.remove(removed);
                if (groupList.isEmpty()) {
                    groupedInvokers.remove(group);
                }
            }
            log.info("Unregistered method: " + methodName);
        } else {
            log.warn("Method not found for unregister: " + methodName);
        }
    }

    /**
     * 检查是否启用美化输出
     */
    public boolean isPrettyPrintEnabled() {
        return prettyPrintEnabled.get();
    }

    /**
     * 设置是否启用美化输出
     */
    public void setPrettyPrintEnabled(boolean enabled) {
        this.prettyPrintEnabled.set(enabled);
        log.debug("Pretty print enabled: " + enabled);
    }

    /**
     * 获取方法数量
     */
    public int getMethodCount() {
        return invokerRegistry.size();
    }

    /**
     * 按优先级排序的方法列表
     */
    public List<JQuickMethodFunctionProvider> getInvokersSortedByPriority() {
        List<JQuickMethodFunctionProvider> sorted = new ArrayList<>(allInvokers);
        Collections.sort(sorted, new Comparator<JQuickMethodFunctionProvider>() {
            @Override
            public int compare(JQuickMethodFunctionProvider a, JQuickMethodFunctionProvider b) {
                return Integer.compare(b.getPriority(), a.getPriority());
            }
        });
        return sorted;
    }

    /**
     * 按方法名排序的方法列表
     */
    public List<JQuickMethodFunctionProvider> getInvokersSortedByName() {
        List<JQuickMethodFunctionProvider> sorted = new ArrayList<>(allInvokers);
        Collections.sort(sorted, new Comparator<JQuickMethodFunctionProvider>() {
            @Override
            public int compare(JQuickMethodFunctionProvider a, JQuickMethodFunctionProvider b) {
                return a.getMethodName().compareTo(b.getMethodName());
            }
        });
        return sorted;
    }

    /**
     * 搜索方法（支持模糊匹配）
     */
    public List<JQuickMethodFunctionProvider> searchMethods(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return new ArrayList<>(allInvokers);
        }
        final String lowerKeyword = keyword.toLowerCase();
        List<JQuickMethodFunctionProvider> results = new ArrayList<>();
        for (JQuickMethodFunctionProvider invoker : allInvokers) {
            if (invoker.getMethodName().toLowerCase().contains(lowerKeyword) ||
                    (invoker.getDescription() != null && invoker.getDescription().toLowerCase().contains(lowerKeyword))) {
                results.add(invoker);
            }
        }
        return results;
    }

    /**
     * 获取按分组的方法
     */
    public Map<String, List<JQuickMethodFunctionProvider>> getGroupedInvokers() {
        return Collections.unmodifiableMap(groupedInvokers);
    }

    /**
     * 提取分组名（从描述中提取前缀，如 "[Core]" 或 "[Database]"）
     */
    private String extractGroup(String description) {
        if (description == null || description.isEmpty()) {
            return "General";
        }
        Pattern pattern = Pattern.compile("^\\[([^\\]]+)\\]");
        Matcher matcher = pattern.matcher(description);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return "General";
    }

    /**
     * 重复字符串（Java 8 兼容）
     */
    private String repeat(String str, int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(str);
        }
        return sb.toString();
    }

    /**
     * 打印所有注册的方法（根据开关决定使用美化版还是基础版）
     */
    public void printRegisteredMethods() {
        if (!prettyPrintEnabled.get()) {
            printRegisteredMethodsBasic();
            return;
        }
        printGitHubTable();
    }

    /**
     * 基础版打印（简洁）
     */
    public void printRegisteredMethodsBasic() {
        log.info("=== Registered Methods (" + allInvokers.size() + ") ===");
        for (JQuickMethodFunctionProvider invoker : allInvokers) {
            log.info("  - " + invoker.getMethodName() + ": " + invoker.getDescription());
        }
    }

    /**
     * GitHub 风格表格打印（完美对齐）
     */
    public void printGitHubTable() {
        if (allInvokers.isEmpty()) {
            log.info("No methods registered.");
            return;
        }
        // 计算每列的最大宽度
        int maxNameLen = 12; // "Method Name" 的长度
        int maxDescLen = 11; // "Description" 的长度
        int maxPriorityLen = 8; // "Priority" 的长度
        for (JQuickMethodFunctionProvider invoker : allInvokers) {
            maxNameLen = Math.max(maxNameLen, invoker.getMethodName().length());
            int descLen = invoker.getDescription() != null ? invoker.getDescription().length() : 0;
            maxDescLen = Math.max(maxDescLen, descLen);
            maxPriorityLen = Math.max(maxPriorityLen, String.valueOf(invoker.getPriority()).length());
        }

        // 添加内边距（GitHub 风格通常两边各加一个空格）
        maxNameLen += 2;
        maxDescLen += 2;
        maxPriorityLen += 2;

        // 打印表头
        String header = "| " + padRight("Method Name", maxNameLen) + " | " +
                padRight("Description", maxDescLen) + " | " +
                padRight("Priority", maxPriorityLen) + " |";
        log.info(header);
        // 打印分隔线
        String separator = "|" + repeat("-", maxNameLen + 2) + "|" +
                repeat("-", maxDescLen + 2) + "|" +
                repeat("-", maxPriorityLen + 2) + "|";
        log.info(separator);

        // 打印数据行
        for (JQuickMethodFunctionProvider invoker : allInvokers) {
            String name = invoker.getMethodName();
            String desc = invoker.getDescription() != null ? invoker.getDescription() : "";
            String priority = String.valueOf(invoker.getPriority());
            String row = "| " + padRight(name, maxNameLen) + " | " +
                    padRight(desc, maxDescLen) + " | " +
                    padRight(priority, maxPriorityLen) + " |";
            log.info(row);
        }
        log.info("");
        log.info("> **Total**: " + allInvokers.size() + " methods | **Groups**: " + groupedInvokers.size());
    }

    /**
     * GitHub 风格分组表格
     */
    public void printGitHubGroupedTable() {
        if (!prettyPrintEnabled.get()) {
            printGroupedMethodsBasic();
            return;
        }
        log.info("");
        log.info("## Methods by Group");
        log.info("");
        for (Map.Entry<String, List<JQuickMethodFunctionProvider>> entry : groupedInvokers.entrySet()) {
            String group = entry.getKey();
            List<JQuickMethodFunctionProvider> invokers = entry.getValue();
            // 计算组内最大方法名和描述长度
            int maxNameLen = group.length() + 2;
            int maxDescLen = 0;
            for (JQuickMethodFunctionProvider invoker : invokers) {
                maxNameLen = Math.max(maxNameLen, invoker.getMethodName().length());
                int descLen = invoker.getDescription() != null ?
                        invoker.getDescription().replaceFirst("^\\[[^\\]]+\\]", "").trim().length() : 0;
                maxDescLen = Math.max(maxDescLen, descLen);
            }
            maxNameLen += 2;
            maxDescLen = Math.max(maxDescLen, 20) + 2;
            // 打印组标题
            log.info("### 📁 " + group);
            log.info("");
            // 打印表格头
            String header = "| " + padRight("Method", maxNameLen) + " | " + padRight("Description", maxDescLen) + " |";
            log.info(header);
            String separator = "|" + repeat("-", maxNameLen + 2) + "|" + repeat("-", maxDescLen + 2) + "|";
            log.info(separator);
            // 打印数据行
            for (JQuickMethodFunctionProvider invoker : invokers) {
                String name = invoker.getMethodName();
                String desc = invoker.getDescription() != null ? invoker.getDescription() : "";
                // 移除分组标记
                desc = desc.replaceFirst("^\\[[^\\]]+\\]", "").trim();
                if (desc.isEmpty()) {
                    desc = "-";
                }
                String row = "| " + padRight(name, maxNameLen) + " | " + padRight(desc, maxDescLen) + " |";
                log.info(row);
            }
            log.info("");
        }
        log.info("> **Total**: " + allInvokers.size() + " methods in " + groupedInvokers.size() + " groups");
    }

    /**
     * GitHub 风格详细表格
     */
    public void printGitHubDetailedTable() {
        if (!prettyPrintEnabled.get()) {
            printDetailedMethodsBasic();
            return;
        }
        log.info("");
        log.info("## Detailed Methods Information");
        log.info("");
        // 计算各列宽度
        int maxIndexLen = 4;
        int maxNameLen = 12;
        int maxPriorityLen = 8;
        for (int i = 0; i < allInvokers.size(); i++) {
            maxIndexLen = Math.max(maxIndexLen, String.valueOf(i + 1).length());
            maxNameLen = Math.max(maxNameLen, allInvokers.get(i).getMethodName().length());
            maxPriorityLen = Math.max(maxPriorityLen, String.valueOf(allInvokers.get(i).getPriority()).length());
        }
        maxIndexLen += 2;
        maxNameLen += 2;
        maxPriorityLen += 2;
        // 打印表头
        String header = "| " + padRight("#", maxIndexLen) + " | " +
                padRight("Method Name", maxNameLen) + " | " +
                padRight("Priority", maxPriorityLen) + " | Description |";
        log.info(header);
        String separator = "|" + repeat("-", maxIndexLen + 2) + "|" +
                repeat("-", maxNameLen + 2) + "|" +
                repeat("-", maxPriorityLen + 2) + "|" +
                repeat("-", 20) + "|";
        log.info(separator);
        // 打印数据行
        for (int i = 0; i < allInvokers.size(); i++) {
            JQuickMethodFunctionProvider invoker = allInvokers.get(i);
            String index = String.valueOf(i + 1);
            String name = invoker.getMethodName();
            String priority = String.valueOf(invoker.getPriority());
            String desc = invoker.getDescription() != null ? invoker.getDescription() : "-";
            // 处理长描述
            if (desc.length() > 50) {
                desc = desc.substring(0, 47) + "...";
            }
            String row = "| " + padRight(index, maxIndexLen) + " | " +
                    padRight(name, maxNameLen) + " | " +
                    padRight(priority, maxPriorityLen) + " | " + desc + " |";
            log.info(row);
        }
        log.info("");
        log.info("> **Total**: " + allInvokers.size() + " methods registered");
    }

    /**
     * 打印带有对齐的简单表格
     */
    public void printAlignedTable() {
        if (!prettyPrintEnabled.get()) {
            printRegisteredMethodsBasic();
            return;
        }
        if (allInvokers.isEmpty()) {
            log.info("No methods registered.");
            return;
        }
        // 计算每列的最大宽度
        int maxNameLen = 0;
        int maxDescLen = 0;
        for (JQuickMethodFunctionProvider invoker : allInvokers) {
            maxNameLen = Math.max(maxNameLen, invoker.getMethodName().length());
            int descLen = invoker.getDescription() != null ? invoker.getDescription().length() : 0;
            maxDescLen = Math.max(maxDescLen, descLen);
        }
        // 添加边距
        maxNameLen = Math.max(maxNameLen, 12) + 2;
        maxDescLen = Math.max(maxDescLen, 11) + 2;
        // 打印表头
        log.info("┌" + repeat("─", maxNameLen + 2) + "┬" + repeat("─", maxDescLen + 2) + "┐");
        log.info("│ " + padCenter("Method Name", maxNameLen) + " │ " +
                padCenter("Description", maxDescLen) + " │");
        log.info("├" + repeat("─", maxNameLen + 2) + "┼" + repeat("─", maxDescLen + 2) + "┤");
        // 打印数据行
        for (JQuickMethodFunctionProvider invoker : allInvokers) {
            String name = invoker.getMethodName();
            String desc = invoker.getDescription() != null ? invoker.getDescription() : "";
            log.info("│ " + padRight(name, maxNameLen) + " │ " +
                    padRight(desc, maxDescLen) + " │");
        }
        log.info("└" + repeat("─", maxNameLen + 2) + "┴" + repeat("─", maxDescLen + 2) + "┘");
        log.info("");
        log.info("Total: " + allInvokers.size() + " methods");
    }

    /**
     * 按分组打印注册的方法（统一入口）
     */
    public void printGroupedMethods() {
        if (!prettyPrintEnabled.get()) {
            printGroupedMethodsBasic();
            return;
        }
        printGitHubGroupedTable();
    }

    /**
     * 基础版分组打印
     */
    public void printGroupedMethodsBasic() {
        log.info("=== Methods by Group ===");
        for (Map.Entry<String, List<JQuickMethodFunctionProvider>> entry : groupedInvokers.entrySet()) {
            log.info("[" + entry.getKey() + "] - " + entry.getValue().size() + " methods");
            for (JQuickMethodFunctionProvider invoker : entry.getValue()) {
                log.info("    - " + invoker.getMethodName() + ": " + invoker.getDescription());
            }
        }
    }

    /**
     * 打印详细的方法信息（统一入口）
     */
    public void printDetailedMethods() {
        if (!prettyPrintEnabled.get()) {
            printDetailedMethodsBasic();
            return;
        }
        printGitHubDetailedTable();
    }

    /**
     * 基础版详细打印
     */
    public void printDetailedMethodsBasic() {
        log.info("=== Detailed Methods Information ===");
        for (JQuickMethodFunctionProvider invoker : allInvokers) {
            log.info("Method: " + invoker.getMethodName());
            log.info("  Description: " + invoker.getDescription());
            log.info("  Priority: " + invoker.getPriority());
            log.info("  Class: " + invoker.getClass().getName());
        }
    }

    /**
     * 打印简洁表格（统一入口，保持兼容）
     */
    public void printCompactTable() {
        printAlignedTable();
    }

    /**
     * 打印统计信息
     */
    public void printStats() {
        log.info("");
        log.info("╔════════════════════════════════════════════════════════════════╗");
        log.info("║                    JQuickMethodInvocation Stats                ║");
        log.info("╠════════════════════════════════════════════════════════════════╣");
        log.info(String.format("║  Total Methods:      %-37d ║", allInvokers.size()));
        log.info(String.format("║  Total Groups:        %-37d ║", groupedInvokers.size()));
        log.info(String.format("║  Pretty Print:        %-37s ║", prettyPrintEnabled.get() ? "ON" : "OFF"));
        log.info("╚════════════════════════════════════════════════════════════════╝");
        log.info("");
    }

    /**
     * 导出方法列表到字符串
     */
    public String exportMethodsList() {
        StringBuilder sb = new StringBuilder();
        sb.append("| Method Name | Description | Priority |\n");
        sb.append("|-------------|-------------|----------|\n");
        for (JQuickMethodFunctionProvider invoker : allInvokers) {
            sb.append("| ").append(invoker.getMethodName())
                    .append(" | ").append(invoker.getDescription())
                    .append(" | ").append(invoker.getPriority()).append(" |\n");
        }
        return sb.toString();
    }

    /**
     * 导出为 Markdown 表格
     */
    public String exportMarkdownTable() {
        StringBuilder sb = new StringBuilder();
        sb.append("## Registered Methods\n\n");

        // 计算列宽
        int maxNameLen = 12;
        int maxDescLen = 11;
        for (JQuickMethodFunctionProvider invoker : allInvokers) {
            maxNameLen = Math.max(maxNameLen, invoker.getMethodName().length());
            int descLen = invoker.getDescription() != null ? invoker.getDescription().length() : 0;
            maxDescLen = Math.max(maxDescLen, descLen);
        }

        String header = "| " + padRight("Method Name", maxNameLen) + " | " +
                padRight("Description", maxDescLen) + " | Priority |\n";
        String separator = "|" + repeat("-", maxNameLen + 2) + "|" +
                repeat("-", maxDescLen + 2) + "|----------|\n";
        sb.append(header);
        sb.append(separator);
        for (JQuickMethodFunctionProvider invoker : allInvokers) {
            sb.append("| ").append(padRight(invoker.getMethodName(), maxNameLen))
                    .append(" | ").append(padRight(invoker.getDescription(), maxDescLen))
                    .append(" | ").append(invoker.getPriority()).append(" |\n");
        }
        return sb.toString();
    }

    /**
     * 左对齐并填充空格
     */
    private String padRight(String s, int width) {
        if (s == null) s = "";
        if (s.length() >= width) {
            return s.substring(0, width);
        }
        return s + repeat(" ", width - s.length());
    }

    /**
     * 居中对齐
     */
    private String padCenter(String s, int width) {
        if (s == null) s = "";
        if (s.length() >= width) {
            return s.substring(0, width);
        }
        int leftPad = (width - s.length()) / 2;
        int rightPad = width - s.length() - leftPad;
        return repeat(" ", leftPad) + s + repeat(" ", rightPad);
    }
}