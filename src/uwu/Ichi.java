package uwu;


import arc.Events;
import mindustry.game.EventType;
import mindustry.mod.Mod;
import mindustry.world.Tile;
import uwu.dialogs.CPlayerListFragment;
import uwu.dialogs.CustomTraceDialog;
import uwu.utils.MenuCaller;

import static mindustry.Vars.ui;

public class Ichi extends Mod {
    @Override
    public void init() {
        Events.on(EventType.TapEvent.class, (event) -> {
            Tile t = event.tile;
            MenuCaller.tap(t.x, t.y);
        });
        ui.listfrag = new CPlayerListFragment();
        ui.listfrag.build(ui.hudGroup);
        ui.traces = new CustomTraceDialog();
    }
}
