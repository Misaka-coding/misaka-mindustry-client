package uwu;

import arc.util.Log;
import mindustry.ui.dialogs.BaseDialog;

import java.util.Date;

public class MenuCaller {
    static int x = 0;
    static int y = 0;
    static Long lastTapTime = 0l;
    static int t = 0;

    public static void tap(int tx, int ty) {
        Log.info("Tapped " + lastTapTime);
        if (tx != x || ty != y || new Date().getTime() - lastTapTime > 500) {
            lastTapTime = new Date().getTime();
            Log.info(lastTapTime);
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
        d.button("Conveyor checker", () -> {
            new ConveyorChecker();
        }).size(250f, 100f).row();
        d.button("Close", () -> {
            d.hide();
        }).row();
        d.show();
    }

}
