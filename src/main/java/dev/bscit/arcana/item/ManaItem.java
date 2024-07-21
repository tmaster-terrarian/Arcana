package dev.bscit.arcana.item;

import java.util.List;

import dev.bscit.arcana.component.ArcanaComponents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class ManaItem extends Item
{
    public ManaItem(Item.Settings settings)
    {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type)
    {
        if(!stack.contains(ArcanaComponents.MAX_MANA)) return;

        int count = stack.getOrDefault(ArcanaComponents.MAX_MANA, 0);
        if(count != 0)
            tooltip.add(Text.translatable("item.arcana.mana_item.info", count / 20).formatted(Formatting.BLUE));
    }
}
