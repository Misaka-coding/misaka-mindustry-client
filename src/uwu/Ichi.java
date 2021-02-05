package uwu;

import arc.util.CommandHandler;
import jdk.incubator.jpackage.internal.Log;
import mindustry.Vars;
import mindustry.gen.Call;
import mindustry.gen.Player;
import mindustry.mod.Mod;
import mindustry.world.Tile;
import mindustry.world.blocks.distribution.Conveyor;

public class Ichi extends Mod {
    @Override
    public void init() {

    }

    @Override
    public void registerClientCommands(CommandHandler handler) {
        handler.<Player>register("conveyor-shiza","Shiza",(args, player) -> findFakeConveyer());
    }

    public void findFakeConveyer() {
        for (int x = 0; x < Vars.world.width(); x++) {
            for (int y = 0; y < Vars.world.width(); y++) {
                Tile t = Vars.world.tile(x, y);
                if (t.block() instanceof Conveyor) {
                    int tgt = t.build.rotation;
                    int lineChecker= 0;
                    if (x - 1 > 0 && Vars.world.tile(x - 1, y).block() instanceof Conveyor && rotationChecker(Vars.world.tile(x - 1, y).build.rotation, tgt)) {
                    lineChecker++;
                    }
                    if (x + 1 < Vars.world.width() && Vars.world.tile(1, y).block() instanceof Conveyor && rotationChecker(Vars.world.tile(x + 1, y).build.rotation, tgt)) {
                        lineChecker++;
                    }
                    if (y - 1 > 0 && Vars.world.tile(x, y + 1).block() instanceof Conveyor && rotationChecker(Vars.world.tile(x, y - 1).build.rotation, tgt)) {
                        lineChecker++;
                    }
                    if (y + 1 < Vars.world.height() && Vars.world.tile(x, y + 1).block() instanceof Conveyor && rotationChecker(Vars.world.tile(x, y + 1).build.rotation, tgt)) {
                        lineChecker++;
                    }
                    if(lineChecker<2){
                        Call.infoMessage("Conveyor shiza at "+x+" "+y);}
                }
            }
        }
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
}
