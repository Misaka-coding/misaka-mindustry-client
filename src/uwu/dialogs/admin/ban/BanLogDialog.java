package uwu.dialogs.admin.ban;

import arc.Core;
import arc.input.KeyCode;
import mindustry.Vars;
import mindustry.gen.Icon;
import mindustry.gen.Player;
import mindustry.ui.dialogs.BaseDialog;
import uwu.Nya;

public class BanLogDialog extends BaseDialog {
    public BanLogDialog(){
        super("Ban");
        for(String s: Nya.bans){
            buttons.button(s,() -> {
                Vars.ui.showInfoFade("@copied");
                Core.app.setClipboardText(s);
            }).row();
        }
        buttons.button("@cancel", this::hide);
        keyDown(KeyCode.escape, this::hide);
        keyDown(KeyCode.back, this::hide);
        show();
    }
}
