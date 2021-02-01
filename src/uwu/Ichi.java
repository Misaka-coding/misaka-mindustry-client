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
}
