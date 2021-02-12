package uwu.utils;

import mindustry.ui.dialogs.BaseDialog;
import uwu.dialogs.admin.ban.BanLogDialog;
import uwu.utils.conveyors.ConveyorChecker;

import java.util.Date;

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
        showMenuDialog();
    }
    public static void showMenuDialog() {
        BaseDialog d = new BaseDialog("Menu");
        d.button("Conveyor checker", ConveyorChecker::new).size(400f, 50f).row();
        d.button("Bans Log", BanLogDialog::new).size(400f, 50f).row();
        //if(historyEnable){
        //    d.button("Disable History",()->{historyEnable=false;d.hide();showMenuDialog();});
        //}else{d.button("Enable History",()->{historyEnable=true;d.hide();showMenuDialog();});}

        d.button("Close", d::hide).row();
        d.show();
    }
}
