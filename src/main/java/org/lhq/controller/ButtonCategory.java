package org.lhq.controller;


import org.jetbrains.annotations.NotNull;
import org.lhq.common.Identifier;

import java.util.ArrayList;
import java.util.List;

/**
 * 按钮分类
 */
public class ButtonCategory {
    private final List<ButtonBinding> bindings = new ArrayList<>();
    private final Identifier id;
    private final int priority;

    public ButtonCategory( Identifier id, int priority) {
        this.id = id;
        this.priority = priority;
    }
    public void registerBinding(@NotNull ButtonBinding binding) {
        if (this.bindings.contains(binding))
            throw new IllegalStateException("Cannot register twice a button binding in the same category.");
        this.bindings.add(binding);
    }
}
