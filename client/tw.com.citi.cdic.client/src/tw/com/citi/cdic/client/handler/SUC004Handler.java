package tw.com.citi.cdic.client.handler;

import java.util.Date;
import java.util.List;

import org.apache.wicket.PageParameters;
import org.eclipse.core.runtime.Platform;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.aquarius.embedserver.AquariusAjaxDaoHandler;
import tw.com.citi.cdic.client.model.LocalFileSts;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;

/**
 * @author Lancelot
 * @since 2010/10/31
 */
public class SUC004Handler extends AquariusAjaxDaoHandler {

    final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Object execute(PageParameters params) throws Exception {
        String actionName = params.getString("actionName");
        if ("getInitInfo".equals(actionName)) {
            return getInitInfo();
        } else if ("upload".equals(actionName)) {
            return upload(params);
        } else if ("confirm".equals(actionName)) {
            return confirm(params);
        }
        throw new IllegalArgumentException("Cannot find actionName: " + actionName);
    }

    private void saveLocalFileSts(String sql, String name) {
        String[] args = Platform.getApplicationArgs();
        String processUser = args != null && args.length > 0 ? args[0] : null;
        LocalFileSts fileSts = new LocalFileSts();
        fileSts.setName(name);
        fileSts.setProcessUser(processUser);
        fileSts.setStatus("1");
        fileSts.setUploadDateTime(new Date());
        getDao().update(sql, fileSts);
    }

    private Object confirm(PageParameters params) throws Exception {
        JSONObject actionParam = new JSONObject(params.getString("actionParam"));
        saveLocalFileSts("SUC004_UPD_LOCALFILESTS_BY_FILENAME", actionParam.getString("fileName"));
        return "";
    }

    private Object upload(PageParameters params) throws Exception {
        JSONObject actionParam = new JSONObject(params.getString("actionParam"));
        saveLocalFileSts("SUC004_INS_LOCALFILESTS", actionParam.getString("fileName"));
        return "";
    }

    private Object getInitInfo() throws Exception {
        List<LocalFileSts> localFileList = getDao().query("SUC004_QRY_LOCALFILESTS", LocalFileSts.class, new Object());
        if (localFileList != null && localFileList.size() > 0) {
            JsonArray result = new JsonArray();
            for (LocalFileSts localFile : localFileList) {
                // TODO check local file 是否存在於 process folder
                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                result.add(gson.toJsonTree(localFile, LocalFileSts.class));
            }
            return result;
        } else {
            return "";
        }
    }
}
