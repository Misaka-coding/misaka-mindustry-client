package uwu;

import arc.scene.ui.Dialog;

import java.util.Date;

public class MenuCaller {
    static int x = 0;
    static int y = 0;
    static Long lastTapTime = 0l;
    static int t = 0;

    public void tap(int tx, int ty) {
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

    public void showMenuDialog() {
        Dialog d = new Dialog("Menu");
        d.button("Conveyor checker", () -> {
            new ConveyorChecker();
        }).row();
        d.addCloseButton();
        d.show();
    }

}
