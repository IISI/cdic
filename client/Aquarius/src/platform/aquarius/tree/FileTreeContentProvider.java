package platform.aquarius.tree;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

public class FileTreeContentProvider implements ITreeContentProvider {

    @Override
    public Object[] getChildren(Object parentElement) {
        JsonElement inputElement = ((JsonElement) parentElement)
                .getAsJsonObject().get("nodes");
        if (inputElement instanceof JsonArray) {
            JsonArray input = (JsonArray) inputElement;
            Object[] result = new Object[input.size()];
            for (int i = 0; i < input.size(); i++) {
                result[i] = input.get(i);
            }
            return result;
        } else {
            return new Object[] {};
        }
    }

    @Override
    public Object getParent(Object element) {
        // 目前尚無用處, 先不實作
        return null;
    }

    @Override
    public boolean hasChildren(Object element) {
        JsonElement input = (JsonElement) element;
        JsonElement nodes = input.getAsJsonObject().get("nodes");
        if (nodes == null || !nodes.isJsonArray()
                || (nodes.getAsJsonArray().size() == 0)) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Object[] getElements(Object inputElement) {
        if (inputElement instanceof JsonArray) {
            JsonArray input = (JsonArray) inputElement;
            Object[] result = new Object[input.size()];
            for (int i = 0; i < input.size(); i++) {
                result[i] = input.get(i);
            }
            return result;
        } else {
            return new Object[] {};
        }
    }

    @Override
    public void dispose() {
    }

    @Override
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
    }
}
