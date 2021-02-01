package uwu;

import arc.*;
import arc.graphics.g2d.*;
import arc.scene.*;
import arc.scene.event.*;
import arc.scene.ui.*;
import arc.scene.ui.layout.*;
import arc.struct.*;
import arc.util.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.net.*;
import mindustry.net.Packets.*;
import mindustry.ui.*;
import mindustry.ui.fragments.*;

import static mindustry.Vars.*;

public class CustomPlayerListFragment extends PlayerListFragment{
    public Table content = (new Table()).marginRight(13.0F).marginLeft(13.0F);

    private boolean visible = false;

    private Interval timer = new Interval();

    private TextField sField;

    private Seq<Player> players = new Seq();

    public void build(Group parent) {
        this.content.name = "players";
        parent.fill(cont -> {
            cont.name = "playerlist";
            cont.visible(());
            cont.update(());
            cont.table((Drawable)Tex.buttonTrans, ()).touchable(Touchable.enabled).margin(14.0F);
        });
        rebuild();
    }

    public void rebuild() {
        this.content.clear();
        float h = 74.0F;
        boolean found = false;
        this.players.clear();
        Groups.player.copy(this.players);
        this.players.sort(Structs.comps(Structs.comparing(Player::team), Structs.comparingBool(p -> !p.admin)));
        for (Iterator<Player> iterator = this.players.iterator(); iterator.hasNext(); ) {
            Player user = iterator.next();
            found = true;
            NetConnection connection = user.con;
            if (connection == null && Vars.net.server() && !user.isLocal())
                return;
            if (this.sField.getText().length() > 0 && !user.name().toLowerCase().contains(this.sField.getText().toLowerCase()) && !Strings.stripColors(user.name().toLowerCase()).contains(this.sField.getText().toLowerCase()))
                return;
            Table button = new Table();
            button.left();
            button.margin(5.0F).marginBottom(10.0F);
            Table table = new Table() {
                public void draw() {
                    super.draw();
                    Draw.color(Pal.gray);
                    Draw.alpha(this.parentAlpha);
                    Lines.stroke(Scl.scl(4.0F));
                    Lines.rect(this.x, this.y, this.width, this.height);
                    Draw.reset();
                }
            };
            table.margin(8.0F);
            table.add((Element)(new Image(user.icon())).setScaling(Scaling.bounded)).grow();
            table.name = user.name();
            button.add((Element)table).size(h);
            button.labelWrap("[#" + user.color().toString().toUpperCase() + "]" + user.name()).width(170.0F).pad(10.0F);
            button.add().grow();
            ((Image)button.image((Drawable)Icon.admin).visible(() -> (user.admin && (user.isLocal() || !Vars.net.server()))).padRight(5.0F).get()).updateVisibility();
            if ((Vars.net.server() || Vars.player.admin) && !user.isLocal() && (!user.admin || Vars.net.server())) {
                button.add().growY();
                float bs = h / 2.0F;
                button.table(t -> {
                    t.defaults().size(bs);
                    t.button((Drawable)Icon.hammer, Styles.clearPartiali, ());
                    t.button((Drawable)Icon.cancel, Styles.clearPartiali, ());
                    t.row();
                    t.button((Drawable)Icon.admin, Styles.clearTogglePartiali, ()).update(()).disabled(()).touchable(()).checked(user.admin);
                    t.button((Drawable)Icon.zoom, Styles.clearPartiali, ());
                }).padRight(12.0F).size(bs + 10.0F, bs);
            } else if (!user.isLocal() && !user.admin && Vars.net.client() && Groups.player.size() >= 3 && Vars.player.team() == user.team()) {
                button.add().growY();
                button.button((Drawable)Icon.hammer, Styles.clearPartiali, () -> Vars.ui.showConfirm("@confirm", Core.bundle.format("confirmvotekick", new Object[] { user.name() }), ())).size(h);
            }
            this.content.add((Element)button).padBottom(-6.0F).width(350.0F).maxHeight(h + 14.0F);
            this.content.row();
            this.content.image().height(4.0F).color(Vars.state.rules.pvp ? (user.team()).color : Pal.gray).growX();
            this.content.row();
        }
        if (!found)
            this.content.add(Core.bundle.format("players.notfound", new Object[0])).padBottom(6.0F).width(350.0F).maxHeight(h + 14.0F);
        this.content.marginBottom(5.0F);
    }

    public void toggle() {
        this.visible = !this.visible;
        if (this.visible) {
            rebuild();
        } else {
            Core.scene.setKeyboardFocus(null);
            this.sField.clearText();
        }
    }
}
