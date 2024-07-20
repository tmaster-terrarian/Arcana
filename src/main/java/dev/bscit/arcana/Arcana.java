package dev.bscit.arcana;

import org.jetbrains.annotations.Nullable;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import dev.bscit.arcana.compat.ArcanaConfig;
import dev.bscit.arcana.item.ItemRegistry;
import dev.bscit.arcana.spell.SpellRegistry;

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

    public static Identifier of(String path){
        return Identifier.of(MOD_ID, path);
    }

    @Nullable
    public static ConfigHolder<ArcanaConfig> CONFIG_HOLDER = null;

    public static ArcanaConfig getConfig(){
        if(CONFIG_HOLDER == null) return null;

        return CONFIG_HOLDER.getConfig();
    }

    @Override
    public void onInitialize()
    {
        ItemRegistry.init();
        SpellRegistry.init();

        registerManaRegenCallback();

        LOGGER.info("Arcana has been initialized.");
    }

    private void registerManaRegenCallback()
    {
        CONFIG_HOLDER = AutoConfig.register(ArcanaConfig.class, JanksonConfigSerializer::new);

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
