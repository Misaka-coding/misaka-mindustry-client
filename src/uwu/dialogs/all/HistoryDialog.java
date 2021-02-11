package uwu.dialogs.all;

import mindustry.ui.dialogs.BaseDialog;

public class HistoryDialog extends BaseDialog {
    int x=0;
    int y=0;

    public HistoryDialog(String title) {
        super(title);
    }

    public HistoryDialog(int x, int y, boolean admin){
        super("History of "+x+" "+y);
    };

    public void runner(){

    }
}
