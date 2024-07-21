package dev.bscit.arcana;

import org.jetbrains.annotations.Nullable;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import dev.bscit.arcana.attribute.ArcanaAttributes;
import dev.bscit.arcana.compat.ArcanaConfig;
import dev.bscit.arcana.component.ArcanaComponents;
import dev.bscit.arcana.item.ArcanaItems;
import dev.bscit.arcana.spell.ArcanaSpells;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigHolder;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;

import net.minecraft.util.Identifier;

public class Arcana implements ModInitializer
{
    public static final String MOD_ID = "arcana";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static Identifier of(String path)
    {
        return Identifier.of(MOD_ID, path);
    }

    @Nullable
    public static ConfigHolder<ArcanaConfig> CONFIG_HOLDER = null;

    public static ArcanaConfig getConfig()
    {
        if(CONFIG_HOLDER == null) return null;

        return CONFIG_HOLDER.getConfig();
    }

    @Override
    public void onInitialize()
    {
        CONFIG_HOLDER = AutoConfig.register(ArcanaConfig.class, JanksonConfigSerializer::new);

        ArcanaAttributes.init();
        ArcanaComponents.init();
        ArcanaSpells.init();
        ArcanaItems.init();

        registerManaRegenCallback();

        LOGGER.info("Arcana has been initialized.");
    }

    static int counter = 0;

    private void registerManaRegenCallback()
    {
        ServerTickEvents.END_WORLD_TICK.register(server -> {
            counter++;
            server.getPlayers().forEach((player) -> {
                if(player == null) return;
                if(player.isDead()) return;

                var manaManager = player.getManaManager();
                if(manaManager == null) return;

                if(manaManager.getMana() > manaManager.getMaxMana())
                    manaManager.setMana(manaManager.getMaxMana());

                if(counter % 20 == 0)
                {
                    if(manaManager.getMaxMana() > 0)
                        manaManager.addMana(1);
                }
            });
        });
    }
}
