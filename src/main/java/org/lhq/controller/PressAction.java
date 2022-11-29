package org.lhq.controller;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.StickyKeyBinding;
import org.lhq.common.ButtonState;
import org.lhq.utils.function.KeyBindingAccessor;

/**
 * 按下的动作回调
 */
@FunctionalInterface
public interface PressAction {
    PressAction DEFAULT_ACTION = (client, button, value, action) -> {
        if (action == ButtonState.REPEAT || client.currentScreen != null){
            return false;
        }

        button.asKeyBinding().ifPresent(binding -> {
            if (binding instanceof StickyKeyBinding)
                binding.setPressed(button.pressed);
            else
                ((KeyBindingAccessor) binding).midnightcontrols$handlePressState(button.isButtonDown());
        });
        return true;
    };

    boolean press(MinecraftClient client, ButtonBinding button, float value,  ButtonState action);

}
