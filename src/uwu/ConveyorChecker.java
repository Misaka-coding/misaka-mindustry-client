package uwu;

import arc.scene.ui.Dialog;
import mindustry.Vars;
import mindustry.content.Blocks;
import mindustry.world.Block;
import mindustry.world.Tile;

public class ConveyorChecker {
    public ConveyorChecker() {
        Dialog d = new Dialog("Conveyor shiza");
        d.add(findFakeConveyer());
        d.button("Close",()->d.hide());
        d.show();
    }
    public String findFakeConveyer() {
        StringBuilder b =new StringBuilder();
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
                        b.append("at " + x + " " + y+"\n");
                    }
                }
            }

        }
        return b.toString();
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
