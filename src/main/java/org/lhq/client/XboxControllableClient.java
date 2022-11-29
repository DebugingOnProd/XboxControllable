package org.lhq.client;

import net.fabricmc.api.ClientModInitializer;

import org.lhq.XboxControllable;
import org.lhq.controller.ControllerInput;


public class XboxControllableClient extends XboxControllable implements ClientModInitializer {
    private static XboxControllableClient INSTANCE;
    public final ControllerInput input = new ControllerInput();


    public static XboxControllableClient get() {
        return INSTANCE;
    }
    @Override
    public void onInitializeClient() {
        INSTANCE = this;

    }
}
