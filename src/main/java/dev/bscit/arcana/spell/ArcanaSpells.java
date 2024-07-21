package dev.bscit.arcana.spell;

import dev.bscit.arcana.Arcana;
import dev.louis.nebula.api.spell.SpellType;

public class ArcanaSpells
{
    public static final SpellType<?> CLOUD_JUMP = SpellType.register(
        Arcana.of("cloud_jump"),
        SpellType.Builder.create(CloudJumpSpell::new, 5)
    );

    public static void init()
    {
        
    }
}
