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
    public Ichi(){
        Events.on(PlayerConnect.class, e -> {
            new BanDialog(e.player);
        });
    }
}
