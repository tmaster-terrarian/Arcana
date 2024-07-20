package dev.bscit.arcana.attribute;

import dev.bscit.arcana.Arcana;
import net.minecraft.entity.attribute.ClampedEntityAttribute;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttribute.Category;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;

public class ArcanaAttributes
{
    public static final RegistryEntry<EntityAttribute> PLAYER_MAX_MANA = register(
        "player.max_mana",
        new ClampedEntityAttribute("attribute.arcana.name.player.max_mana", 20.0, 0.0, 4096.0).setTracked(true).setCategory(Category.POSITIVE)
    );

    private static RegistryEntry<EntityAttribute> register(String id, EntityAttribute attribute)
    {
        return Registry.registerReference(Registries.ATTRIBUTE, Arcana.of(id), attribute);
    }

    public static void init()
    {
        
    }
}
