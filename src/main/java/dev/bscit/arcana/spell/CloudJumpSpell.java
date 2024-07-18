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
        var caster = this.getCaster();
        caster.playSound(SoundEvents.BLOCK_AMETHYST_BLOCK_BREAK, 1f, 1f);
        caster.addVelocity(0, 2, 0);
        caster.velocityModified = true;
    }

    @Override
    public void tick()
    {
        var caster = this.getCaster();
        var world = caster.getWorld();
        if(!world.isClient())
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
            caster.playSound(SoundEvents.BLOCK_GLASS_HIT, 2f, -1f);
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
