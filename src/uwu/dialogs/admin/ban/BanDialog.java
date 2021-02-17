package uwu.dialogs.admin.ban;

import arc.input.KeyCode;
import arc.util.Log;
import mindustry.Vars;
import mindustry.gen.Call;
import mindustry.gen.Player;
import mindustry.net.Packets;
import mindustry.ui.dialogs.BaseDialog;
import uwu.Nya;

public class BanDialog extends BaseDialog{
    private String time = "8days";
    private String reason = "Гриферство";
    public Player target;

    public BanDialog(Player target){
        super("Ban");
        this.target = target;
        cont.add(this.target.name).width(Vars.mobile ? 400f : 500f).wrap().pad(4f).get().setAlignment(1, 1);
        buttons.defaults().size(200f, 54f).pad(2f);
        cont.row();
        cont.field(time, this::timeSetter).size(320f, 54f).maxTextLength(50).addInputDialog().get();
        cont.row();
        cont.field(reason, this::reasonSetter).size(320f, 54f).maxTextLength(100).addInputDialog().get();

        buttons.button("@cancel", this::hide);
        buttons.button("@ok", () -> {
            hide();
            Call.sendChatMessage("/ban " + this.target.id + " " + time + " " + reason);
            log(target);
        });
        buttons.button("Default", () -> {
            Call.adminRequest(this.target, Packets.AdminAction.ban);
            log(target);
        });
        keyDown(KeyCode.enter, () -> {
            hide();
            Call.sendChatMessage("/ban " + this.target.id + " " + time + " " + reason);
            log(target);
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

    public void log(Player p){
        Log.info("Banned \n" +
                p.name+"\n" +
                p.ip()+"\n" +
                p.uuid()
        );
        Nya.bans.add(p.name+" "+p.uuid()+" "+p.ip());
    }
}
