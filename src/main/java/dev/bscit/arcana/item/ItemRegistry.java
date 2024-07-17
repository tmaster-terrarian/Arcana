package dev.bscit.arcana.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.component.type.FoodComponents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import dev.bscit.arcana.Arcana;
import io.wispforest.accessories.api.components.AccessoriesDataComponents;
import io.wispforest.accessories.api.components.AccessorySlotValidationComponent;

public class ItemRegistry
{
    public static final ManaItem BAND_OF_STARPOWER_ITEM = Registry.register(
        Registries.ITEM,
        Identifier.of(Arcana.MOD_ID, "band_of_starpower"),
        new ManaItem(new Item.Settings()
            .food(FoodComponents.BEEF)
            .component(AccessoriesDataComponents.SLOT_VALIDATION, AccessorySlotValidationComponent.EMPTY.addValidSlot("wrist")))
    );

    public static final RegistryKey<ItemGroup> ARCANA_ITEM_GROUP_KEY = RegistryKey.of(Registries.ITEM_GROUP.getKey(), Identifier.of(Arcana.MOD_ID, "item_group"));
    public static final ItemGroup ARCANA_ITEM_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(BAND_OF_STARPOWER_ITEM))
            .displayName(Text.translatable("itemGroup.arcana"))
            .build();

    public static Item register(Item item, String id)
    {
        // Create the identifier for the item.
        Identifier itemID = Identifier.of(Arcana.MOD_ID, id);

        // Register the item.
        Item registeredItem = Registry.register(Registries.ITEM, itemID, item);

        // Return the registered item!
        return registeredItem;
    }

    public static void init()
    {
        Registry.register(Registries.ITEM_GROUP, ARCANA_ITEM_GROUP_KEY, ARCANA_ITEM_GROUP);

        ItemGroupEvents.modifyEntriesEvent(ARCANA_ITEM_GROUP_KEY).register(itemGroup -> {
            itemGroup.add(BAND_OF_STARPOWER_ITEM);
        });
    }
}
