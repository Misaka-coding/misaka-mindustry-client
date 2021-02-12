package uwu;

import arc.Events;
import mindustry.game.EventType;
import mindustry.mod.Mod;
import mindustry.world.Tile;
import uwu.dialogs.CPlayerListFragment;
import uwu.dialogs.admin.CustomTraceDialog;
import uwu.utils.MenuCaller;
import uwu.utils.history.Ni;

import static mindustry.Vars.ui;

public class Ichi extends Mod {
    public Ni history;
    @Override
    public void init() {
        //history = new Ni();
        Events.on(EventType.TapEvent.class, (event) -> {
            Tile t = event.tile;
            MenuCaller.tap(t.x, t.y);
        });
        ui.listfrag = new CPlayerListFragment();
        ui.listfrag.build(ui.hudGroup);
        ui.traces = new CustomTraceDialog();
    }
}
