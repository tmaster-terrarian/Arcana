package dev.bscit.arcana.client;

import java.util.concurrent.atomic.AtomicInteger;

import org.lwjgl.glfw.GLFW;

import dev.bscit.arcana.spell.SpellRegistry;

import dev.louis.nebula.api.spell.SpellType;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;

public class ArcanaClient implements ClientModInitializer
{
    public int spellCooldown = 0;

    @Override
    public void onInitializeClient()
    {
        registerKeybind();
        registerKeybindCallback();

        registerRenderCallback();
    }

    private static void registerKeybind()
    {
        var keyBind = new KeyBinding(
            "key.arcana.example",
            GLFW.GLFW_KEY_UNKNOWN,
            "key.categories.arcana"
        );
        KeyBindingHelper.registerKeyBinding(keyBind);
        SpellKeybindManager.addSpellKeyBinding(SpellRegistry.CLOUD_JUMP, keyBind);
    }

    private void registerKeybindCallback()
    {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if(spellCooldown > 0)
            {
                spellCooldown--;
                return;
            }

            for(SpellType<?> spellType : SpellType.REGISTRY)
            {
                var optionalKey = SpellKeybindManager.getKey(spellType);
                if(optionalKey.isPresent())
                {
                    var key = optionalKey.get();
                    if(key.isPressed())
                    {
                        client.player.getSpellManager().cast(spellType);
                        spellCooldown = 10;
                        return;
                    }
                }
            }
        });
    }

    private void registerRenderCallback()
    {
        HudRenderCallback.EVENT.register((drawContext, tickDelta) -> {
            var client = MinecraftClient.getInstance();
            var player = client.player;

            if(player == null) return;

            var manaManager = player.getManaManager();
            var spellManager = player.getSpellManager();

            int mana = manaManager.getMana();
            int maxMana = manaManager.getMaxMana();

            drawContext.drawText(
                client.textRenderer,
                "Mana: " + (mana / 20) + "/" + (maxMana / 20),
                10,
                10,
                0x8080FF,
                false
            );

            drawContext.drawText(
                client.textRenderer,
                "(" + mana + "/" + maxMana + ")",
                100,
                10,
                0x8080FF,
                false
            );

            drawContext.drawText(
                client.textRenderer,
                "Learned Spells:",
                10,
                20,
                0x00FFFF,
                true
            );

            var spells = spellManager.getLearnedSpells();
            AtomicInteger y = new AtomicInteger(30);
            spells.forEach(spellType -> {
                drawContext.drawText(
                    client.textRenderer,
                    spellType.getId().toString(),
                    12,
                    y.get(),
                    0x03F6FF,
                    true
                );
                y.addAndGet(10);
            });
        });
    }
}
