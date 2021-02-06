package uwu;

import arc.scene.ui.Dialog;
import arc.util.Log;

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
        Dialog d = new Dialog("Menu");
        d.button("Conveyor checker", () -> {
            new ConveyorChecker();
        }).row();
        d.addCloseButton();
        d.show();
    }

}
