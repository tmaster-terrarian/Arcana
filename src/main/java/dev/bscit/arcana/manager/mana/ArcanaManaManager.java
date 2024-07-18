package dev.bscit.arcana.manager.mana;

import dev.louis.nebula.manager.mana.NebulaManaManager;
import net.minecraft.entity.player.PlayerEntity;

public class ArcanaManaManager extends NebulaManaManager
{
    private int baseMaxMana = 400;

    public ArcanaManaManager(PlayerEntity player)
    {
        super(player);
    }

    @Override
    public int getMaxMana()
    {
        return baseMaxMana;
    }
}
