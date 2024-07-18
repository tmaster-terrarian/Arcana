package dev.bscit.arcana;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import dev.bscit.arcana.item.ItemRegistry;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;

public class Arcana implements ModInitializer
{
    public static final String MOD_ID = "arcana";
    public static final Logger LOGGER = LogUtils.getLogger();

    @Override
    public void onInitialize()
    {
        ItemRegistry.init();
        ArcanaSpells.init();

        registerManaRegenCallback();

        LOGGER.info("Arcana has been initialized.");
    }

    private void registerManaRegenCallback()
    {
        ServerTickEvents.END_WORLD_TICK.register(server -> {
            server.getPlayers().forEach((player) -> {
                if(player == null) return;
                if(player.isDead()) return;

                var manaManager = player.getManaManager();
                if(manaManager == null) return;

                if(manaManager.getMaxMana() > 0)
                    manaManager.addMana(1);
            });
        });
    }
}
