package dev.bscit.arcana.manager;

import dev.louis.nebula.api.manager.mana.entrypoint.RegisterManaManagerEntrypoint;
import dev.louis.nebula.api.manager.mana.registerable.ManaManagerRegistrableView;
import dev.louis.nebula.api.manager.spell.entrypoint.RegisterSpellManagerEntrypoint;
import dev.louis.nebula.api.manager.spell.registerable.SpellManagerRegistrableView;
import dev.bscit.arcana.manager.mana.ArcanaManaManager;
import dev.bscit.arcana.manager.spell.ArcanaSpellManager;

public class ManagerRegisterer implements RegisterManaManagerEntrypoint, RegisterSpellManagerEntrypoint {
    @Override
    public void registerManaManager(ManaManagerRegistrableView manaManagerRegistrableView) {
        manaManagerRegistrableView.registerManaManager(ArcanaManaManager::new);
    }

    @Override
    public void registerSpellManager(SpellManagerRegistrableView spellManagerRegistrableView) {
        spellManagerRegistrableView.registerSpellManager(ArcanaSpellManager::new);
    }

    @Override
    public boolean shouldRegister() {
        return true;
    }
}
