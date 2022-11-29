package org.lhq.controller;

import it.unimi.dsi.fastutil.ints.Int2FloatMap;
import it.unimi.dsi.fastutil.ints.Int2FloatOpenHashMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import org.jetbrains.annotations.NotNull;
import org.lhq.common.ButtonState;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class InputManager {


    public static final InputManager INPUT_MANAGER = new InputManager();
    private static final List<ButtonBinding> BINDINGS = new ArrayList<>();
    private static final List<ButtonCategory> CATEGORIES = new ArrayList<>();
    public static final Int2ObjectMap<ButtonState> STATES = new Int2ObjectOpenHashMap<>();
    public static final Int2FloatMap BUTTON_VALUES = new Int2FloatOpenHashMap();
    public int prevTargetMouseX = 0;
    public int prevTargetMouseY = 0;
    public int targetMouseX = 0;
    public int targetMouseY = 0;

    protected InputManager() {
    }
    /**
     * Returns whether the specified binding is registered or not.
     *
     * @param binding the binding to check
     * @return true if the binding is registered, else false
     */
    public static boolean hasBinding(@NotNull ButtonBinding binding) {
        return BINDINGS.contains(binding);
    }

    public static void sortBindings() {
        synchronized (BINDINGS) {
            var sorted = BINDINGS.stream()
                    .sorted(Collections.reverseOrder(Comparator.comparingInt(binding -> binding.getButton().length))).toList();
            BINDINGS.clear();
            BINDINGS.addAll(sorted);
        }
    }
    public static @NotNull ButtonBinding registerBinding(@NotNull ButtonBinding binding) {
        if (hasBinding(binding))
            throw new IllegalStateException("Cannot register twice a button binding in the registry.");
        BINDINGS.add(binding);
        return binding;
    }
}
