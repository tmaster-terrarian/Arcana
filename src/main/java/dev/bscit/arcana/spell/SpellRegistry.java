package dev.bscit.arcana.spell;

import dev.bscit.arcana.Arcana;
import dev.louis.nebula.api.spell.SpellType;
import net.minecraft.util.Identifier;

public class SpellRegistry
{
    public static final SpellType<?> CLOUD_JUMP = SpellType.register(
        Identifier.of(Arcana.MOD_ID, "cloud_jump"),
        SpellType.Builder.create(CloudJumpSpell::new, 40)
    );

    public static void init()
    {
        
    }
}
