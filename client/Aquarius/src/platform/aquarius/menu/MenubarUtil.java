package platform.aquarius.menu;

import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.MenuManager;

import aquarius.Activator;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class MenubarUtil {

    private MenuManager menuManagers[];

    private ArrayList<Action> actions = new ArrayList<Action>();

    public ArrayList<Action> getActions() {
        return actions;
    }

    public MenuManager[] getMenuManagers() {
        return menuManagers;
    }

    public MenubarUtil() {
        try {
            URL config = Platform.getBundle(Activator.PLUGIN_ID).getResource(
                    "/configs/menubar.json");
            InputStreamReader reader = new InputStreamReader(
                    config.openStream());

            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(MenuManager.class,
                    new MenuManagerDeserializer());
            Gson gson = gsonBuilder.create();

            menuManagers = gson.fromJson(reader, MenuManager[].class);

            // Gson gson = new Gson();
            // menuManagers = gson.fromJson(reader, MenuManager[].class);
            // System.out.println(menuManagers);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class MenuManagerDeserializer implements
            JsonDeserializer<MenuManager> {

        @Override
        public MenuManager deserialize(JsonElement json, Type type,
                JsonDeserializationContext context) throws JsonParseException {
            return buildMenu(json);
        }

        private MenuManager buildMenu(JsonElement json) {
            MenuManager mgr = new MenuManager(json.getAsJsonObject()
                    .get("text").getAsString(), json.getAsJsonObject()
                    .get("id").getAsString());

            JsonArray items = json.getAsJsonObject().get("actions")
                    .getAsJsonArray();

            for (int i = 0; i < items.size(); i++) {
                try {
                    JsonElement element = items.get(i);
                    String type = element.getAsJsonObject().get("type")
                            .getAsString();
                    if ("action".equals(type)) {
                        String id = element.getAsJsonObject().get("id")
                                .getAsString();
                        String text = element.getAsJsonObject().get("text")
                                .getAsString();
                        String cls = element.getAsJsonObject().get("class")
                                .getAsString();
                        Class<? extends Action> clz = Class.forName(cls)
                                .asSubclass(Action.class);
                        Action action = clz.newInstance();
                        action.setId(id);
                        action.setText(text);
                        mgr.add(action);
                        actions.add(action);
                    } else if ("submenu".equals(type)) {
                        MenuManager sub = buildMenu(element);
                        mgr.add(sub);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            return mgr;
        }
    }
}
