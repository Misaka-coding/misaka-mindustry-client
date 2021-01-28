package uwu;

import arc.*;
import arc.util.*;
import mindustry.*;
import mindustry.content.*;
import mindustry.game.EventType.*;
import mindustry.gen.*;
import mindustry.mod.*;
import mindustry.ui.dialogs.*;

public class Ichi extends Mod{

    public ExampleJavaMod(){
        Events.on(ClientLoadEvent.class, e -> {
            Vars.ui.listfrag = new CustomPlayerListFragment();
        });
    }
}