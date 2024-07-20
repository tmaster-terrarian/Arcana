package dev.bscit.arcana.compat;

import dev.bscit.arcana.Arcana;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = Arcana.MOD_ID)
public class ArcanaConfig implements ConfigData
{
    @ConfigEntry.Gui.CollapsibleObject(startExpanded = true)
    @ConfigEntry.Gui.Tooltip()
    public ClientData clientData = new ClientData();

    public static class ClientData
    {
        public boolean toggleA = true;
        public boolean toggleB = false;

        @ConfigEntry.BoundedDiscrete(max = 1)
        public float factorA = 1f;
    }

    @ConfigEntry.Gui.CollapsibleObject
    InnerStuff stuff = new InnerStuff();

    @ConfigEntry.Gui.Excluded
    HiddenStuff invisibleStuff = new HiddenStuff();

    static class InnerStuff
    {
        int a = 0;
        int b = 1;
    }

    static class HiddenStuff
    {
        int c = 2;
        int d = 3;
    }
}
