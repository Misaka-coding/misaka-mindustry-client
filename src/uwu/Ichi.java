package uwu;


import arc.Events;
import arc.util.CommandHandler;
import mindustry.game.EventType;
import mindustry.gen.Player;
import mindustry.mod.Mod;
import mindustry.world.Tile;
import mindustry.mod.Mod;

import static mindustry.Vars.ui;

public class Ichi extends Mod {
    @Override
    public void init() {
        Events.on(EventType.TapEvent.class, (event) -> {
            Tile t = event.tile;
            MenuCaller.tap(t.x, t.y);
        });
public class Ichi extends Mod{

    @Override
    public void init(){
        ui.listfrag = new CPlayerListFragment();
        ui.listfrag.build(ui.hudGroup);
    }

    @Override
    public void registerClientCommands(CommandHandler handler) {
        handler.<Player>register("conveyor-shiza", "Shiza", (args, player) -> new ConveyorChecker());
    }


}
