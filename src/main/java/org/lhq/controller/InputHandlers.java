package org.lhq.controller;

import net.minecraft.client.MinecraftClient;

import net.minecraft.client.gui.screen.ingame.HandledScreen;
import org.jetbrains.annotations.NotNull;
import org.lhq.client.XboxControllableClient;
import org.lhq.ui.RingScreen;

import java.util.ArrayList;
import java.util.List;

public class InputHandlers {


    private InputHandlers() {
    }

    public static boolean inGame(MinecraftClient client, ButtonBinding binding) {
        return (client.currentScreen == null && XboxControllableClient.get().input.screenCloseCooldown <= 0) || client.currentScreen instanceof RingScreen;
    }

    /**
     * Returns whether the client is in an inventory or not.
     *
     * @param client  the client instance
     * @param binding the affected binding
     * @return true if the client is in an inventory, else false
     */
    public static boolean inInventory(@NotNull MinecraftClient client, @NotNull ButtonBinding binding) {
        return client.currentScreen instanceof HandledScreen;
    }


}
