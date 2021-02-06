package uwu;

import arc.util.CommandHandler;
import arc.util.Log;
import mindustry.Vars;
import mindustry.content.Blocks;
import mindustry.gen.Player;
import mindustry.mod.Mod;
import mindustry.world.Block;
import mindustry.world.Tile;

public class Ichi extends Mod {
    @Override
    public void init() {

    }

    @Override
    public void registerClientCommands(CommandHandler handler) {
        handler.<Player>register("conveyor-shiza", "Shiza", (args, player) -> findFakeConveyer());
    }

    public void findFakeConveyer() {

        for (int x = 0; x < Vars.world.width(); x++) {
            for (int y = 0; y < Vars.world.height(); y++) {
                Tile t = Vars.world.tile(x, y);
                if (t.block() != null && isConveyor(t.block())) {
                    int tgt = t.build.rotation;
                    int lineChecker = 0;
                    if (x - 1 > 0 && Vars.world.tile(x - 1, y).block() != null && isConveyor(Vars.world.tile(x - 1, y).block()) && rotationChecker(Vars.world.tile(x - 1, y).build.rotation, tgt)) {
                        lineChecker++;
                    }
                    if (x + 1 < Vars.world.width() && Vars.world.tile(x + 1, y).block() != null && isConveyor(Vars.world.tile(x + 1, y).block()) && rotationChecker(Vars.world.tile(x + 1, y).build.rotation, tgt)) {
                        lineChecker++;
                    }
                    if (y - 1 > 0 && Vars.world.tile(x, y - 1).block() != null && isConveyor(Vars.world.tile(x, y - 1).block()) && rotationChecker(Vars.world.tile(x, y - 1).build.rotation, tgt)) {
                        lineChecker++;
                    }
                    if (y + 1 < Vars.world.height() && Vars.world.tile(x, y + 1).block() != null && isConveyor(Vars.world.tile(x, y + 1).block()) && rotationChecker(Vars.world.tile(x, y + 1).build.rotation, tgt)) {
                        lineChecker++;
                    }
                    if (lineChecker < 2) {
                        Log.info("Conveyor shiza at " + x + " " + y);
                    }
                }
            }
        }
        Log.info("end of parsing");
        return;
    }

    public boolean rotationChecker(int a, int b) {
        if (a + 1 == b || b + 1 == a || a == b) {
            return true;
        }
        if ((a == 3 && b == 0) || (a == 0 && b == 3)) {
            return true;
        }
        return false;
    }

    public boolean isConveyor(Block b) {
        return b == Blocks.conveyor || b == Blocks.titaniumConveyor || b == Blocks.armoredConveyor || b == Blocks.plastaniumConveyor;
    }
}
