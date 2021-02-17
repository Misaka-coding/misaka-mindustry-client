package uwu.dialogs.admin.ban;

import arc.Core;
import arc.input.KeyCode;
import arc.scene.ui.ScrollPane;
import arc.scene.ui.layout.Table;
import mindustry.Vars;
import mindustry.ui.dialogs.BaseDialog;
import uwu.Nya;

public class BanLogDialog extends BaseDialog {
    public BanLogDialog(){
        super("BanLog");
        Table nya = new Table();
        for(String s: Nya.bans){
            nya.button(s,() -> {
                Vars.ui.showInfoFade("@copied");
                Core.app.setClipboardText(s);
            }).row();
        }
        ScrollPane sp = new ScrollPane(nya);
        buttons.button("@cancel", this::hide).row();
        this.add(sp);
        keyDown(KeyCode.escape, this::hide);
        keyDown(KeyCode.back, this::hide);
        show();
    }
}
