package platform.aquarius.tree;

import java.io.IOException;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.PlatformUI;

import aquarius.Activator;

import com.google.gson.JsonElement;

public class TreeViewLabelProvider implements ILabelProvider {

    @Override
    public Image getImage(Object element) {
        Image img = null;
        try {
            img = new Image(PlatformUI.getWorkbench().getDisplay(), Platform
                    .getBundle(Activator.PLUGIN_ID)
                    .getResource("/icons/citi_arror.gif").openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

    @Override
    public String getText(Object element) {
        JsonElement json = (JsonElement) element;
        return json.getAsJsonObject().get("text").getAsString();
    }

    @Override
    public void addListener(ILabelProviderListener listener) {
        // TODO Auto-generated method stub
    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean isLabelProperty(Object element, String property) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void removeListener(ILabelProviderListener listener) {
        // TODO Auto-generated method stub

    }

}
