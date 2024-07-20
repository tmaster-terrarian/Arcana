package dev.bscit.arcana.manager.mana;

import dev.bscit.arcana.attribute.ArcanaAttributes;
import dev.louis.nebula.manager.mana.NebulaManaManager;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.player.PlayerEntity;

public class ArcanaManaManager extends NebulaManaManager
{
    public ArcanaManaManager(PlayerEntity player)
    {
        super(player);
    }

    @Override
    public int getMaxMana()
    {
        if(player == null)
			return 0;

		EntityAttributeInstance attributeInstance = player.getAttributeInstance(ArcanaAttributes.PLAYER_MAX_MANA);

		if(attributeInstance == null)
			return 0;

		return (int)attributeInstance.getValue();
    }
}
