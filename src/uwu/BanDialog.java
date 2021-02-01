package uwu;

import arc.scene.ui.Dialog;
import arc.scene.ui.TextField;
import mindustry.gen.Call;
import mindustry.gen.Player;
import mindustry.ui.dialogs.BaseDialog;

public class BanDialog extends BaseDialog {
    private String time = "60";
    private String reason = "60";
    public Player tgt=null;

    public BanDialog(Player p) {
        super("Ban");
        this.tgt=p;
        this.addCloseButton();
        this.add(tgt.name).row();
        this.add(tgt.con.address);
        this.cont.field(time, text->timeSetter(text)).size(320.0F, 54.0F).maxTextLength(100).addInputDialog().get();
        this.cont.row();
        this.cont.field(reason,text->reasonSetter(text)).size(320.0F, 54.0F).maxTextLength(100).addInputDialog().get();
        this.button("@ok",()-> Call.sendChatMessage("/ban "+time +" "+ tgt.con().address+ " "+reason));
        this.show();
    }
    public void reasonSetter(String s){
        reason=s;
    }
    public void timeSetter(String s){
        time=s;
    }
}
