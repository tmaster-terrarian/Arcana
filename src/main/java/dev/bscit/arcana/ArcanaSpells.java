package dev.bscit.arcana;

import dev.bscit.arcana.spell.CloudJumpSpell;

import dev.louis.nebula.api.spell.SpellType;
import net.minecraft.resources.ResourceLocation;

public class ArcanaSpells
{
    public static final SpellType<?> CLOUD_JUMP = SpellType.register(ResourceLocation.fromNamespaceAndPath(Arcana.MOD_ID, "cloud_jump"), SpellType.Builder.create(CloudJumpSpell::new, 2));

    public static void init()
    {
        
    }
}
