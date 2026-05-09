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
package com.github.paohaijiao.function.collection;

/**
 * packageName com.github.paohaijiao.function.collection
 *
 * @author Martin
 * @version 1.0.0
 * @since 2026/5/9
 */
import com.github.paohaijiao.function.domain.JQuickBaseMethodInvoker;
import com.github.paohaijiao.spi.anno.Priority;
import com.github.paohaijiao.spi.constants.PriorityConstants;
import java.util.List;
import java.util.Collection;
import java.util.Map;

@Priority(PriorityConstants.SYSTEM_HIGH)
public class JQuickIsEmptyInvoker extends JQuickBaseMethodInvoker {

    public JQuickIsEmptyInvoker() {
        super("isEmpty", "判断对象是否为空 - 支持String/Collection/Map/Array");
    }

    @Override
    public Object invoke(List<Object> args) {
        validateArgCount(args, 1);
        Object obj = args.get(0);
        if (obj == null) return true;
        if (obj instanceof String) return ((String) obj).isEmpty();
        if (obj instanceof Collection) return ((Collection<?>) obj).isEmpty();
        if (obj instanceof Map) return ((Map<?, ?>) obj).isEmpty();
        if (obj instanceof Object[]) return ((Object[]) obj).length == 0;

        return false;
    }
}
