package org.lhq;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.Identifier;
import org.lhq.common.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XboxControllable implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger(XboxControllable.class);
    public static final Identifier CONTROLS_MODE_CHANNEL = new Identifier(Constants.CONTROLS_MODE_CHANNEL.toString());


    @Override
    public void onInitialize() {
        LOGGER.info("我的世界手柄驱动初始化");
        LOGGER.info("mod init end");
    }
}
