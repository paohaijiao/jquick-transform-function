/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Copyright (c) [2025-2099] Martin (goudingcheng@gmail.com)
 */
package com.github.paohaijiao.function.random;

/**
 * packageName com.github.paohaijiao.function.random
 *
 * @author Martin
 * @version 1.0.0
 * @since 2026/5/9
 */
import com.github.paohaijiao.function.domain.JQuickBaseMethodInvoker;
import com.github.paohaijiao.spi.anno.Priority;
import com.github.paohaijiao.spi.constants.PriorityConstants;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Priority(PriorityConstants.SYSTEM_MEDIUM)
public class JQuickRandomColorInvoker extends JQuickBaseMethodInvoker {

    private static final String[] PRESET_COLORS = {
            "#FF0000", "#00FF00", "#0000FF", "#FFFF00", "#FF00FF", "#00FFFF",
            "#FFA500", "#800080", "#008000", "#FFC0CB", "#FF6347", "#40E0D0"
    };

    public JQuickRandomColorInvoker() {
        super("randomColor", "生成随机颜色 - 用法: randomColor(type?) " +
                "type: 'hex', 'rgb', 'preset'");
    }

    @Override
    public Object invoke(List<Object> args) {
        validateArgCountRange(args, 0, 1);

        String type = args.size() > 0 ? asString(args.get(0)) : "hex";

        switch (type.toLowerCase()) {
            case "rgb":
                int r = ThreadLocalRandom.current().nextInt(256);
                int g = ThreadLocalRandom.current().nextInt(256);
                int b = ThreadLocalRandom.current().nextInt(256);
                return String.format("rgb(%d, %d, %d)", r, g, b);
            case "preset":
                int index = ThreadLocalRandom.current().nextInt(PRESET_COLORS.length);
                return PRESET_COLORS[index];
            case "hex":
            default:
                return String.format("#%06x", ThreadLocalRandom.current().nextInt(0xFFFFFF + 1));
        }
    }
}
