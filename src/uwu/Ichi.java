package uwu;

import arc.scene.ui.Dialog;
import arc.util.CommandHandler;
import arc.util.Log;
import mindustry.Vars;
import mindustry.content.Blocks;
import mindustry.gen.Player;
import mindustry.mod.Mod;
import mindustry.ui.dialogs.BaseDialog;
import mindustry.world.Block;
import mindustry.world.Tile;

public class Ichi extends Mod {
    @Override
    public void init() {

    }

    @Override
    public void registerClientCommands(CommandHandler handler) {
        handler.<Player>register("conveyor-shiza", "Shiza", (args, player) -> new ConveyorChecker());
    }


}
