package org.lhq.ui;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import org.lhq.client.XboxControllableClient;

public class RingScreen extends Screen {
    protected final XboxControllableClient mod;
    public RingScreen() {
        super(Text.literal("xbox.menu.title.ring"));
        this.mod = XboxControllableClient.get();
    }
}
