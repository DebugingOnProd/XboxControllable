package org.lhq.utils.function;

public interface KeyBindingAccessor {
    boolean midnightcontrols$press();

    boolean midnightcontrols$unpress();

    default boolean midnightcontrols$handlePressState(boolean pressed) {
        if (pressed)
            return this.midnightcontrols$press();
        else
            return this.midnightcontrols$unpress();
    }
}
