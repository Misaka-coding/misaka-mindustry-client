package uwu.dialogs.admin;

import arc.Core;
import arc.scene.ui.layout.Table;
import mindustry.Vars;
import mindustry.gen.Icon;
import mindustry.gen.Player;
import mindustry.gen.Tex;
import mindustry.net.Administration.TraceInfo;
import mindustry.ui.dialogs.TraceDialog;

public class CustomTraceDialog extends TraceDialog {

    public CustomTraceDialog() {
        super();
    }

    public void show(Player player, TraceInfo info) {
        cont.clear();

        Table table = new Table(Tex.clear);
        table.margin(14);
        table.defaults().pad(1);

        table.defaults().left();
        table.add(Core.bundle.format("trace.playername", player.name));
        table.button(Icon.copy, () -> {
            Vars.ui.showInfoFade("@copied");
            Core.app.setClipboardText(player.name);
        });
        table.row();
        table.add(Core.bundle.format("trace.ip", info.ip));
        table.button(Icon.copy, () -> {
            Vars.ui.showInfoFade("@copied");
            Core.app.setClipboardText(info.ip);
        });
        table.row();
        table.add(Core.bundle.format("trace.id", info.uuid));
        table.button(Icon.copy, () -> {
            Vars.ui.showInfoFade("@copied");
            Core.app.setClipboardText(info.uuid);
        });
        table.row();
        table.add(Core.bundle.format("trace.modclient", info.modded));
        table.row();
        table.add(Core.bundle.format("trace.mobile", info.mobile));
        table.row();
        table.button(Icon.hammer, () -> {
            this.hide();
            new BanDialog(player);
        });
        table.row();

        table.add().pad(5);
        table.row();

        cont.add(table);

        show();
    }
}
