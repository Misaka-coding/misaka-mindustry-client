package uwu;

import mindustry.mod.Mod;

import static mindustry.Vars.ui;

public class Ichi extends Mod{

    @Override
    public void init(){
        ui.listfrag = new CPlayerListFragment();
        ui.listfrag.build(ui.hudGroup);
    }
}
