package uwu.dialogs.all;

import mindustry.ui.dialogs.*;
import mindustry.ui.dialogs.JoinDialog;
import arc.*;
import arc.Net.*;
import arc.graphics.*;
import arc.input.*;
import arc.math.*;
import arc.scene.ui.*;
import arc.scene.ui.layout.*;
import arc.struct.*;
import arc.util.*;
import arc.util.Timer.*;
import arc.util.serialization.*;
import mindustry.*;
import mindustry.core.*;
import mindustry.game.EventType.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.io.legacy.*;
import mindustry.net.*;
import mindustry.net.Packets.*;
import mindustry.ui.*;

import static mindustry.Vars.*;

public class CustomJoinDialog extends JoinDialog{
    Seq<Server> servers = new Seq<>();
    Dialog add;
    Server renaming;
    Table local = new Table();
    Table remote = new Table();
    Table global = new Table();
    Table hosts = new Table();
    int totalHosts;
    int refreshes;
    boolean showHidden;

    String lastIp;
    int lastPort;
    Task ping;

    public CustomJoinDialog(){
        super();
    }
    @Override
    void setup(){
        local.clear();
        remote.clear();
        global.clear();
        float w = targetWidth();

        hosts.clear();

        section("@servers.local", local, false);
        section("@servers.remote", remote, false);
        section("@servers.global", global, true);

        ScrollPane pane = new ScrollPane(hosts);
        pane.setFadeScrollBars(false);
        pane.setScrollingDisabled(true, false);

        setupRemote();

        cont.clear();
        cont.table(t -> {
            t.add("@name").padRight(10);
            t.field(Core.settings.getString("name"), text -> {
                player.name(text);
                Core.settings.put("name", text);
            }).grow().pad(8).addInputDialog(100);

            ImageButton button = t.button(Tex.whiteui, Styles.clearFulli, 40, () -> {
                new PaletteDialog().show(color -> {
                    player.color().set(color);
                    Core.settings.put("color-0", color.rgba8888());
                });
            }).size(54f).get();
            button.update(() -> button.getStyle().imageUpColor = player.color());
        }).width(w).height(70f).pad(4);
        cont.row();
        cont.add(pane).width(w + 38).pad(0);
        cont.row();
        cont.buttonCenter("@server.add", Icon.add, () -> {
            renaming = null;
            add.show();
        }).marginLeft(10).width(w).height(80f).update(button -> {
            float pw = w;
            float pad = 0f;
            if(pane.getChildren().first().getPrefHeight() > pane.getHeight()){
                pw = w + 30;
                pad = 6;
            }

            var cell = ((Table)pane.parent).getCell(button);

            if(!Mathf.equal(cell.minWidth(), pw)){
                cell.width(pw);
                cell.padLeft(pad);
                pane.parent.invalidateHierarchy();
            }
        });
    }
}