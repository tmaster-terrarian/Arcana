package dev.bscit.arcana.component;

import com.mojang.serialization.Codec;

import dev.bscit.arcana.Arcana;

import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ArcanaComponents
{
    public static final ComponentType<Integer> MAX_MANA = Registry.register(
        Registries.DATA_COMPONENT_TYPE,
        Arcana.of("max_mana"),
        ComponentType.<Integer>builder().codec(Codec.INT).build()
    );

    public static void init()
    {
        
    }
}
