package dev.bscit.arcana;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.fabricmc.api.ModInitializer;

public class Arcana implements ModInitializer
{
    public static final String MOD_ID = "arcana";
    public static final Logger LOGGER = LogUtils.getLogger();

    @Override
    public void onInitialize()
    {
        ArcanaSpells.init();
        LOGGER.info("Arcana has been initialized.");
    }
}
