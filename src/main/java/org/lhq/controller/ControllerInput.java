package org.lhq.controller;

import java.util.HashMap;
import java.util.Map;

public class ControllerInput {
    private static final Map<Integer, Integer> BUTTON_COOLDOWNS = new HashMap<>();
    // Cooldowns
    public int actionGuiCooldown = 0;
    public boolean ignoreNextARelease = false;
    public boolean ignoreNextXRelease = false;
    private double targetYaw = 0.0;
    private double targetPitch = 0.0;
    private float prevXAxis = 0.f;
    private float prevYAxis = 0.f;
    private int targetMouseX = 0;
    private int targetMouseY = 0;
    private float mouseSpeedX = 0.f;
    private float mouseSpeedY = 0.f;
    public int inventoryInteractionCooldown = 0;
    public int screenCloseCooldown = 0;
}
