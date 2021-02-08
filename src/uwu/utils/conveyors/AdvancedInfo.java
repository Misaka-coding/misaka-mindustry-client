package uwu.utils.conveyors;

import arc.math.geom.Vec2;
import mindustry.Vars;
import mindustry.ui.dialogs.BaseDialog;

public class AdvancedInfo {
    public AdvancedInfo(int x, int y) {
        BaseDialog d = new BaseDialog("Conveyor info");
        d.row();
        d.center().add("Info about conveyor at " + x + y).row();
        d.center().add("Type: " + Vars.world.tile(x, y).block().name).row();
        //ToDO обавить интеграцию с историей
        d.center().button("Show history log", () -> {
        });
        d.center().button("Move to this", () -> {
            Vec2 hentai = new Vec2();
            if (Vars.player.unit() == null) {
                return;
            }
            hentai.trns(Vars.player.unit().angleTo(x * 8f, y * 8f), Vars.player.unit().type().speed);
            Vars.player.unit().moveAt(hentai);
        });
        d.button("Close", () -> d.hide());
        d.closeOnBack();
        d.show();
    }
}
