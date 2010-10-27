package platform.aquarius.browser;

import org.eclipse.jface.action.ContributionItem;
import org.eclipse.jface.action.StatusLineLayoutData;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class BrowserProgressIcon extends ContributionItem {
    public static final String ID = "platform.aquarius.browser.progress.icon";
    Label label;

    public BrowserProgressIcon(String id) {
        super(id);

    }

    public void fill(Composite parent) {
        label = new Label(parent, SWT.NONE);
        Image img = new Image(parent.getDisplay(), this.getClass()
                .getResourceAsStream("ProgressIcon.png"));
        label.setImage(img);
        StatusLineLayoutData sd = new StatusLineLayoutData();
        sd.widthHint = 20;
        label.setLayoutData(sd);
        this.setVisible(false);
    }

    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        label.setVisible(visible);
    }
}
