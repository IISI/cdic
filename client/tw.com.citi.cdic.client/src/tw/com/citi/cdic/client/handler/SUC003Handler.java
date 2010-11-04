package tw.com.citi.cdic.client.handler;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.vfs.FileObject;
import org.apache.wicket.PageParameters;
import org.eclipse.core.runtime.Platform;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.aquarius.embedserver.AquariusAjaxDaoHandler;
import tw.com.citi.cdic.client.model.HostFileSts;
import tw.com.citi.cdic.utils.FileUtil;
import tw.com.citi.cdic.utils.FileUtil.FolderType;
import tw.com.citi.cdic.utils.Messages;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;

/**
 * @author Lancelot
 * @since 2010/11/01
 */
public class SUC003Handler extends AquariusAjaxDaoHandler {

    final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Object execute(PageParameters params) throws Exception {
        String actionName = params.getString("actionName");
        if ("getInitInfo".equals(actionName)) {
            return getInitInfo();
        } else if ("confirm".equals(actionName)) {
            return confirm(params);
        }
        throw new IllegalArgumentException("Cannot find actionName: " + actionName);
    }

    private Object confirm(PageParameters params) throws Exception {
        JSONObject actionParam = new JSONObject(params.getString("actionParam"));
        Gson gson = new Gson();
        String[] names = gson.fromJson(actionParam.get("data").toString(), String[].class);
        String[] args = Platform.getApplicationArgs();
        String processUser = args != null && args.length > 0 ? args[0] : null;
        Date copyDateTime = new Date();
        for (String name : names) {
            HostFileSts fileSts = new HostFileSts();
            FileObject file = FileUtil.getHostFileByName(name);
            fileSts.setHostDateTime(new Date(file.getContent().getLastModifiedTime()));
            fileSts.setCopyDateTime(copyDateTime);
            fileSts.setProcessUser(processUser);
            fileSts.setStatus("1");
            fileSts.setName(name);
            getDao().update("SUC003_UPD_HOSTFILESTS_BY_NAME", fileSts);
            FileUtil.copyFile(FolderType.HOST, FolderType.PROCESS, new String[] { name });
        }
        return "";
    }

    private Object getInitInfo() throws Exception {
        List<HostFileSts> hostFileList = getDao().query("SUC003_QRY_HOSTFILESTS", HostFileSts.class, new Object());
        if (hostFileList != null && hostFileList.size() > 0) {
            JsonArray result = new JsonArray();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
            for (HostFileSts hostFile : hostFileList) {
                try {
                    FileObject file = FileUtil.getHostFileByName(hostFile.getName());
                    hostFile.setHostDateTimeFmt(sdf.format(new Date(file.getContent().getLastModifiedTime())));
                    hostFile.setCopyDateTimeFmt(hostFile.getCopyDateTime() == null ? null : sdf.format(hostFile
                            .getCopyDateTime()));
                    hostFile.setSize((int) file.getContent().getSize());
                    hostFile.setRecord(hostFile.getSize() / hostFile.getRecLen());
                } catch (Exception e) {
                    hostFile.setStatus(Messages.STATUS_6);
                }
                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                result.add(gson.toJsonTree(hostFile, HostFileSts.class));
            }
            return result;
        } else {
            return "";
        }
    }
}
