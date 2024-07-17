package dev.bscit.arcana.spell;

import dev.louis.nebula.api.spell.Spell;
import dev.louis.nebula.api.spell.SpellType;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvents;

public class CloudJumpSpell extends Spell
{
    public CloudJumpSpell(SpellType<?> spellType, PlayerEntity player)
    {
        super(spellType, player);
    }

    @Override
    public void cast()
    {
        this.getCaster().playSound(SoundEvents.BLOCK_AMETHYST_BLOCK_BREAK, 1f, 1f);
        this.getCaster().addVelocity(0, 2, 0);
        this.getCaster().velocityModified = true;
    }

    @Override
    public void tick()
    {
        var world = this.getCaster().getWorld();
        if(!world.isClient())
        {
            var serverPlayer = (ServerPlayerEntity)this.getCaster();

            serverPlayer.getServerWorld().spawnParticles(
                serverPlayer,
                ParticleTypes.CLOUD,
                false,
                serverPlayer.getX(),
                serverPlayer.getY(),
                serverPlayer.getZ(),
                2,
                0,
                1,
                0,
                0.1
            );
            this.getCaster().playSound(SoundEvents.BLOCK_GLASS_HIT, 2f, -1f);
        }
    }

    @Override
    public int getDuration()
    {
        return 10;
    }

    @Override
    public void finish()
    {
        if(!this.getCaster().getWorld().isClient())
        {
            var serverPlayer = (ServerPlayerEntity)this.getCaster();

            serverPlayer.playSound(SoundEvents.ENTITY_CAMEL_DASH, 2f, -1f);
            serverPlayer.getServerWorld().spawnParticles(
                serverPlayer,
                ParticleTypes.LARGE_SMOKE,
                false,
                serverPlayer.getX(),
                serverPlayer.getY(),
                serverPlayer.getZ(),
                5,
                0,
                1,
                0,
                0.1
            );
        }
    }
}
