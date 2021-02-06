package uwu;

import arc.input.KeyCode;
import mindustry.Vars;
import mindustry.gen.*;
import mindustry.ui.dialogs.BaseDialog;

public class BanDialog extends BaseDialog{
    private String time = "60";
    private String reason = "60";
    public Player tgt;

    public BanDialog(Player p){
        super("Ban");
        tgt = p;
        cont.add(tgt.name).width(Vars.mobile ? 400f : 500f).wrap().pad(4f).get().setAlignment(1, 1);
        buttons.defaults().size(200f, 54f).pad(2f);
        cont.row();
        cont.field(time, this::timeSetter).size(320f, 54f).maxTextLength(50).addInputDialog().get();
        cont.row();
        cont.field(reason, this::reasonSetter).size(320f, 54f).maxTextLength(100).addInputDialog().get();

        buttons.button("@cancel", this::hide);
        buttons.button("@ok", () -> {
            hide();
            Call.sendChatMessage("tested " + time + " " + reason);
        });
        keyDown(KeyCode.enter, () -> {
            hide();
            Call.sendChatMessage("tested " + time + " " + reason);
        });
        keyDown(KeyCode.escape, this::hide);
        keyDown(KeyCode.back, this::hide);
        show();
    }

    public void reasonSetter(String s){
        reason = s;
    }

    public void timeSetter(String s){
        time = s;
    }
}
