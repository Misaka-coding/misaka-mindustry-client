package uwu.utils;

import mindustry.Vars;
import mindustry.ui.dialogs.BaseDialog;
import uwu.dialogs.admin.ban.BanLogDialog;
import uwu.utils.conveyors.ConveyorChecker;
import uwu.utils.history.HistoryDialog;

import java.util.Date;

import static mindustry.Vars.net;
import static mindustry.Vars.player;

public class MenuCaller {
    static int x = 0;
    static int y = 0;
    static Long lastTapTime = 0l;
    static int t = 0;
    public static boolean historyEnable = false;

    public static void tap(int tx, int ty) {
        if (tx != x || ty != y || new Date().getTime() - lastTapTime > 500) {
            lastTapTime = new Date().getTime();
            x = tx;
            y = ty;
            t = 1;
            return;
        }
        t++;
        if (t < 3) {
            return;
        }
        if(historyEnable){
            new HistoryDialog(Vars.world.tile(tx,ty));
            return;
        }
        showMenuDialog();
    }
    public static void showMenuDialog() {
        BaseDialog d = new BaseDialog("Menu");
        d.center().button("Conveyor checker", ConveyorChecker::new).size(400f, 50f).row();
        if(net.server() || player.admin){
        d.center().button("Bans Log", BanLogDialog::new).size(400f, 50f).row();
        }
        d.center().button("Enable History",()->{historyEnable=true;d.hide();}).size(400f, 50f).row();
        d.button("Close", d::hide);
        d.closeOnBack();
        d.show();
    }
}
