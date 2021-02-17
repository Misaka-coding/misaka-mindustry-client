package uwu.utils.finder;

import arc.scene.ui.ScrollPane;
import arc.scene.ui.layout.Table;
import mindustry.Vars;
import mindustry.ui.dialogs.BaseDialog;
import mindustry.world.Block;
import mindustry.world.Tile;
import uwu.utils.MenuCaller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class BlockFinder extends BaseDialog {
    ArrayList<Blocker> blockers;
    Set<Block> hentai;

    public BlockFinder(){
        super("Block Finder");
        blockers = new ArrayList<>();
        hentai = new HashSet<>();
        button("Close", this::hide);
        for(int x=0;x< Vars.world.width();x++){
            for(int y=0;y< Vars.world.height();y++){
                if(Vars.world.tile(x,y).block()==null){
                    continue;
                }
                hentai.add(Vars.world.tile(x,y).block());
                blockers.add(new Blocker(Vars.world.tile(x,y).block(),x,y));
            }
        }
        Table nya = new Table();
        for(Block b : hentai){
            nya.button(b.name,()->{this.hide();finded(b);}).size(400,50).row();
        }
        this.add(new ScrollPane(nya));
        this.show();
    }

    public void finded(Block block){
        ArrayList<Blocker> baka = new ArrayList<>();
        for(Blocker b : blockers){
            if(b.b==block){
                baka.add(b);
            }
        }
        BaseDialog d=new BaseDialog("Traced: " + block.name);
        d.button("Close", this::hide);
        Table tbl = new Table();
        for(Blocker b : baka){
            tbl.add(b.b.name+" "+b.x+" "+b.y).row();
        }
        d.add(new ScrollPane(tbl));
        d.show();
    }


    public class Blocker{
        Block b;
        int x;
        int y;
        public Blocker(Block b,int x, int y){
            this.b=b;
            this.x=x;
            this.y=y;
        }
    }
}
