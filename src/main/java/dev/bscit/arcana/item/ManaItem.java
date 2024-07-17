package dev.bscit.arcana.item;

import net.minecraft.item.Item;

public class ManaItem extends Item
{
    public ManaItem(Item.Settings settings)
    {
        super(settings);
    }

    public int getMaxManaBonus()
    {
        return 0;
    }
}
