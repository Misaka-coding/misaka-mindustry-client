package uwu;

import arc.*;
import arc.scene.ui.Dialog;
import arc.scene.ui.TextField;
import arc.util.*;
import mindustry.*;
import mindustry.content.*;
import mindustry.game.EventType.*;
import mindustry.gen.*;
import mindustry.mod.*;
import mindustry.ui.dialogs.*;

public class Ichi extends Mod{
    private static String time = "60";
    private static String reason = "60";

    public Ichi(){
        Events.on(ClientLoadEvent.class, e -> {
            Vars.ui.listfrag = new CustomPlayerListFragment();
        });
    }

    public static void showTempBanDialog(Player player){
        time="60";
        reason="R U L E S";
        Dialog dialog=new Dialog("BAN");
        dialog.addCloseButton();
        dialog.add(player.name).row();
        dialog.add(player.con.address);
        TextField t = (TextField)dialog.cont.field(time, text->timeSetter(text)).size(320.0F, 54.0F).maxTextLength(100).addInputDialog().get();
        dialog.cont.row();
        TextField r = dialog.cont.field(reason,text->reasonSetter(text)).size(320.0F, 54.0F).maxTextLength(100).addInputDialog().get();
        dialog.button("@ok",()->Call.sendChatMessage("/ban "+time +" "+ player.con().address+ " "+reason));
    }
    public static void timeSetter(String s){
        time=s;
    }
    public static String timeGetter(){
        return time;
    }
    public static void reasonSetter(String s){
        reason=s;
    }
    public static String reasonGetter(){
        return reason;
    }
}
