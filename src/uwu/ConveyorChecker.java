package uwu;

import mindustry.Vars;
import mindustry.content.Blocks;
import mindustry.ui.dialogs.BaseDialog;
import mindustry.world.Block;
import mindustry.world.Tile;
import mindustry.world.blocks.production.Drill;
import mindustry.world.blocks.production.GenericSmelter;

public class ConveyorChecker {
    public ConveyorChecker() {
        BaseDialog d = new BaseDialog("Conveyor shiza");
        d.center();
        for (String s : findFakeConveyor().split("\n")) {
            d.center().add(s).row();
        }
        d.button("Close", () -> d.hide());
        d.closeOnBack();
        d.show();
    }

    public String findFakeConveyor() {
        StringBuilder b = new StringBuilder();
        for (int x = 0; x < Vars.world.width(); x++) {
            for (int y = 0; y < Vars.world.height(); y++) {
                Tile t = Vars.world.tile(x, y);
                if (t.block() != null && isConveyor(t.block())) {
                    int tgt = t.build.rotation;
                    int lineChecker = 0;
                    if ((x - 1 > 0 && Vars.world.tile(x - 1, y).block() != null && isConveyor(Vars.world.tile(x - 1, y).block()) && rotationChecker(Vars.world.tile(x - 1, y).build.rotation, tgt, -1, 0)) || isSubConveyor(Vars.world.tile(x - 1, y).block()) || isContains(Vars.world.tile(x - 1, y).block(), -1, 0, tgt) || isConnect(Vars.world.tile(x - 1, y).block())) {
                        lineChecker++;
                    }
                    if ((x + 1 < Vars.world.width() && Vars.world.tile(x + 1, y).block() != null && isConveyor(Vars.world.tile(x + 1, y).block()) && rotationChecker(Vars.world.tile(x + 1, y).build.rotation, tgt, 1, 0)) || isSubConveyor(Vars.world.tile(x + 1, y).block()) || isContains(Vars.world.tile(x + 1, y).block(), 1, 0, tgt) || isConnect(Vars.world.tile(x + 1, y).block())) {
                        lineChecker++;
                    }
                    if ((y - 1 > 0 && Vars.world.tile(x, y - 1).block() != null && isConveyor(Vars.world.tile(x, y - 1).block()) && rotationChecker(Vars.world.tile(x, y - 1).build.rotation, tgt, 0, -1)) || isSubConveyor(Vars.world.tile(x, y - 1).block()) || isContains(Vars.world.tile(x, y - 1).block(), 0, -1, tgt) || isConnect(Vars.world.tile(x, y - 1).block())) {
                        lineChecker++;
                    }
                    if ((y + 1 < Vars.world.height() && Vars.world.tile(x, y + 1).block() != null && isConveyor(Vars.world.tile(x, y + 1).block()) && rotationChecker(Vars.world.tile(x, y + 1).build.rotation, tgt, 0, 1)) || isSubConveyor(Vars.world.tile(x, y + 1).block()) || isContains(Vars.world.tile(x, y + 1).block(), 0, 1, tgt) || isConnect(Vars.world.tile(x, y + 1).block())) {
                        lineChecker++;
                    }
                    if (lineChecker < 2) {
                        b.append("at " + x + " " + y + " " + Vars.world.tile(x, y).build.rotation + "\n");
                    }
                }
            }
        }
        return b.toString();
    }

    public boolean rotationChecker(int b, int a, int dx, int dy) {
        //Общее направление
        if (a == b && a == 0 && dx != 0) {
            return true;
        }
        if (a == b && a == 1 && dy != 0) {
            return true;
        }
        if (a == b && a == 2 && dx != 0) {
            return true;
        }
        if (a == b && a == 3 && dy != 0) {
            return true;
        }
        //выход
        if (a == 0 && dx == 1 && (b == 1 || b == 3)) {
            return true;
        }
        if (a == 1 && dy == 1 && (b == 0 || b == 2)) {
            return true;
        }
        if (a == 2 && dx == -1 && (b == 1 || b == 3)) {
            return true;
        }
        if (a == 3 && dy == -1 && (b == 0 || b == 2)) {
            return true;
        }
        //вход
        if (a == 0 && dy == 1 && b == 3) {
            return true;
        }
        if (a == 0 && dy == -1 && b == 1) {
            return true;
        }
        if (a == 1 && dx == 1 && b == 2) {
            return true;
        }
        if (a == 1 && dx == -1 && b == 0) {
            return true;
        }
        if (a == 2 && dy == 1 && b == 3) {
            return true;
        }
        if (a == 2 && dy == -1 && b == 1) {
            return true;
        }
        if (a == 3 && dx == 1 && b == 2) {
            return true;
        }
        return a == 3 && dx == -1 && b == 0;
    }

    public boolean isConveyor(Block b) {
        return b == Blocks.conveyor || b == Blocks.titaniumConveyor || b == Blocks.armoredConveyor || b == Blocks.plastaniumConveyor;
    }

    public boolean isSubConveyor(Block b) {
        if (isConveyor(b)) {
            return false;
        }
        return b == Blocks.router || b == Blocks.junction || b == Blocks.sorter || b == Blocks.phaseConveyor || b == Blocks.itemBridge || b == Blocks.underflowGate || b == Blocks.overflowGate || b == Blocks.massDriver || b == Blocks.invertedSorter || b == Blocks.distributor;
    }

    public boolean isContains(Block b, int dx, int dy, int rtt) {
        return ((dy == -1 && rtt == 3) || (dy == 1 && rtt == 1) || (dx == -1 && rtt == 2) || (dx == 1 && rtt == 0)) && b.hasItems;
    }

    public boolean isConnect(Block b) {
        return b instanceof GenericSmelter || b == Blocks.unloader || b instanceof Drill;
    }
}
