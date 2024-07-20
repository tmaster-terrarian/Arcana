package dev.bscit.arcana.compat;

import dev.bscit.arcana.Arcana;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = Arcana.MOD_ID)
public class ArcanaConfig implements ConfigData
{
    @ConfigEntry.Gui.CollapsibleObject(startExpanded = true)
    public ClientData clientData = new ClientData();

    public static class ClientData
    {
        @ConfigEntry.BoundedDiscrete(min = 0, max = 100)
        @ConfigEntry.Gui.Tooltip
        public int soundVolume = 100;
    }

    @ConfigEntry.Gui.CollapsibleObject
    InnerStuff stuff = new InnerStuff();

    @ConfigEntry.Gui.Excluded
    @ConfigEntry.Gui.CollapsibleObject
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
