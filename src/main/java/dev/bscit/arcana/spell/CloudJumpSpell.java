package dev.bscit.arcana.spell;

import dev.louis.nebula.api.spell.Spell;
import dev.louis.nebula.api.spell.SpellType;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
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
        var caster = this.getCaster();
        if(!caster.getWorld().isClient())
        {
            var serverPlayer = (ServerPlayerEntity)caster;

            serverPlayer.getServerWorld().playSound(null, serverPlayer.getBlockPos(), SoundEvents.BLOCK_AMETHYST_BLOCK_BREAK, SoundCategory.PLAYERS);
        }

        caster.addVelocity(0, 2, 0);
        caster.velocityModified = true;
    }

    @Override
    public void tick()
    {
        var caster = this.getCaster();
        if(!caster.getWorld().isClient())
        {
            var serverPlayer = (ServerPlayerEntity)caster;

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

            serverPlayer.getServerWorld().playSound(null, serverPlayer.getBlockPos(), SoundEvents.BLOCK_GLASS_HIT, SoundCategory.PLAYERS, 1f, 1f);
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
        var caster = this.getCaster();
        if(!caster.getWorld().isClient())
        {
            var serverPlayer = (ServerPlayerEntity)caster;

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

            serverPlayer.getServerWorld().playSound(null, serverPlayer.getBlockPos(), SoundEvents.ENTITY_CAMEL_DASH, SoundCategory.PLAYERS, 1f, 1f);
        }
    }
}
