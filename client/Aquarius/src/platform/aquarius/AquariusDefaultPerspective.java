package platform.aquarius;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import platform.aquarius.tree.TreeView;

public class AquariusDefaultPerspective implements IPerspectiveFactory {

    public void createInitialLayout(IPageLayout layout) {
        String editorArea = layout.getEditorArea();
        layout.setEditorAreaVisible(true);
        // layout.setFixed(true);

        // layout.addStandaloneView(View.ID, false, IPageLayout.LEFT, 1.0f,
        // editorArea);

        layout.addView(TreeView.ID, IPageLayout.LEFT, 0.25f, editorArea);
        // layout.addView(BrowserView.ID, IPageLayout.RIGHT, 0.75f, editorArea);
    }
}
