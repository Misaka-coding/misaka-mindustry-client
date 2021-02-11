package uwu.utils.history;

import arc.Events;
import mindustry.game.EventType;
import mindustry.gen.Player;
import mindustry.gen.Unit;
import mindustry.world.Block;
import mindustry.world.blocks.logic.LogicBlock;

import java.util.ArrayList;
import java.util.Date;

public class Ni {
    public static ArrayList<Info> history = new ArrayList<>();

    public Ni() {
        Events.on(EventType.WorldLoadEvent.class, worldLoadEvent -> {
            history = new ArrayList<>();
        });
        Events.on(EventType.BlockBuildEndEvent.class, event -> {
            if (event.breaking) {
                addAction(event.tile.x, event.tile.y, new HAction(event.tile.block(),event.tile.x, event.tile.y, event.unit, Object.destroy, null));
            }
            if (!event.breaking) {
                addAction(event.tile.x, event.tile.y, new HAction(event.tile.block(),event.tile.x, event.tile.y, event.unit, Object.build, null));
            }
        });
        Events.on(EventType.ConfigEvent.class, event -> {
            addAction(event.tile.tileX(), event.tile.tileY(), new HAction(event.tile.block(),event.tile.tileX(), event.tile.tileY(), event.player.lastReadUnit, Object.config, event.value));
        });
    }

    public void addAction(int x, int y, HAction a) {
        for (Info i : history) {
            if (i.x == x && i.y == y) {
                i.history.add(a);
                return;
            }
        }
        Info i = new Info(x, y);
        i.history.add(a);
        history.add(i);
    }
    public Info getHistory(int x,int y){
        for (Info i : history) {
            if (i.x == x && i.y == y) {
                return i;
            }
        }
        return null;
    }

    public static ArrayList<HAction> getPlayerHistory(Player player) {
        ArrayList<HAction> rtn=new ArrayList<>();
        for (Info i : history) {
            for(HAction s:i.history){
                if(s.player==player){
                    rtn.add(s);
                }
            }
        }
        return rtn;
    }

    public enum Object {
        build, destroy, config
    }

    public class Info {
        int x;
        int y;
        ArrayList<HAction> history;

        public Info(int x, int y) {
            this.x = x;
            this.y = y;
            history = new ArrayList<>();
        }
    }
    public class HAction {
        Block block;
        Player player;
        Unit unit;
        Object type;
        Date date;
        String config;
        int x;
        int y;

        public HAction(Block b, int x, int y, Unit u, Object type, java.lang.Object config) {
            this.type = type;
            this.x=x;
            this.y=y;
            this.block = b;
            this.unit = u;
            if (u.isPlayer() || u.controller().unit().isPlayer()) {
                this.player = u.getPlayer();
            } else {
                player = null;
            }
            this.date = new Date();
            if (type != Object.config) {
                return;
            }
            if (b instanceof LogicBlock) {
                this.config = "Logic config changed";
            } else {
                this.config = config.toString();
            }
        }
    }
}
