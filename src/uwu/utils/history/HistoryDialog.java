package uwu.utils.history;

import arc.scene.ui.ScrollPane;
import arc.scene.ui.layout.Table;
import mindustry.Vars;
import mindustry.gen.Call;
import mindustry.gen.Icon;
import mindustry.gen.Player;
import mindustry.net.Packets;
import mindustry.ui.dialogs.BaseDialog;
import mindustry.world.Tile;
import uwu.Ichi;
import uwu.utils.MenuCaller;

import java.util.ArrayList;

import static mindustry.Vars.net;
import static mindustry.Vars.player;

public class HistoryDialog extends BaseDialog {

    public HistoryDialog(Tile t){
        super("Tile history at "+t.x+" "+t.y);
        button("Close", this::hide);
        button("Disable", ()->{this.hide();MenuCaller.historyEnable=false;}).row();
        closeOnBack();
        Table hentai = new Table();
        Ni.Info info = Ichi.history.getHistory(t.x,t.y);
        if(info!=null&&info.history!=null){
        for(Ni.HAction a : info.history){
            String s = a.player.name;
            if(a.type== Ni.Object.build){
                s+=" build ";
            }
            if(a.type== Ni.Object.destroy){
                s+=" destroy ";
            }
            if(a.type== Ni.Object.config){
                s+=" config ";
            }
            s+=a.block.name;
            if(a.type== Ni.Object.config){
               s+=" to "+a.config;
            }
            hentai.add(s);
            hentai.button(Icon.players,()->{this.hide();new HistoryDialog(a.player);});
            if(net.server() || player.admin){
                hentai.button(Icon.zoom,()->{Call.adminRequest(a.player, Packets.AdminAction.trace);});
            }
            hentai.row();
        }}
        this.add(new ScrollPane(hentai));
        this.show();
    }
    public HistoryDialog(Player p){
        super("Player history for " + p.name);
        button("Close", this::hide);button("Disable", ()->{this.hide();MenuCaller.historyEnable=false;}).row();
        if(net.server() || player.admin){
            button(Icon.zoom,()->{Call.adminRequest(p, Packets.AdminAction.trace);});
        }
        closeOnBack();
        Table hentai = new Table();
        ArrayList<Ni.HAction> nya = Ichi.history.getPlayerHistory(p);
        if(nya!=null){
        for(Ni.HAction a : nya){
            String s = a.x+" "+a.y;
            if(a.type== Ni.Object.build){
                s+=" build ";
            }
            if(a.type== Ni.Object.destroy){
                s+=" destroy ";
            }
            if(a.type== Ni.Object.config){
                s+=" config ";
            }
            s+=a.block.name;
            if(a.type== Ni.Object.config){
                s+=" to "+a.config;
            }
            hentai.add(s);
            hentai.button(Icon.zoom,()->{this.hide();new HistoryDialog(Vars.world.tile(a.x,a.y));});
            hentai.row();
        }}
        this.add(new ScrollPane(hentai));
        this.show();
    };
}
