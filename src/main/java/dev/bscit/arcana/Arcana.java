package dev.bscit.arcana;

import com.mojang.logging.LogUtils;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;

public class Arcana implements ModInitializer {
    public static final String MOD_ID = "arcana";
    public static final Logger LOGGER = LogUtils.getLogger();

    @Override
    public void onInitialize() {
        ArcanaSpells.init();
        LOGGER.info("Arcana has been initialized.");
    }
}
