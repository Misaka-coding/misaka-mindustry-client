package uwu;


import arc.Events;
import arc.util.CommandHandler;
import mindustry.game.EventType;
import mindustry.gen.Player;
import mindustry.mod.Mod;

public class Ichi extends Mod {
    @Override
    public void init() {
        Events.on(EventType.TapEvent.class, (event) -> {
        });
    }

    @Override
    public void registerClientCommands(CommandHandler handler) {
        handler.<Player>register("conveyor-shiza", "Shiza", (args, player) -> new ConveyorChecker());
    }


}
