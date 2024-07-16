package dev.bscit.arcana;

import dev.louis.nebula.api.spell.SpellType;
import dev.bscit.arcana.spell.CloudJumpSpell;
import net.minecraft.util.Identifier;

public class ArcanaSpells {
    public static final SpellType<?> CLOUD_JUMP = SpellType.register(Identifier.of(Arcana.MOD_ID, "cloud_jump"), SpellType.Builder.create(CloudJumpSpell::new, 2));
    public static void init() {

    }
}
