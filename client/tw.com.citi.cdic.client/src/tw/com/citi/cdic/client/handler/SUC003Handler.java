package tw.com.citi.cdic.client.handler;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.vfs.FileObject;
import org.apache.commons.vfs.FileSystemException;
import org.apache.wicket.PageParameters;
import org.eclipse.core.runtime.Platform;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.aquarius.embedserver.AquariusAjaxDaoHandler;
import tw.com.citi.cdic.client.model.HostFileSts;
import tw.com.citi.cdic.client.model.TableFlow;
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
    public Object execute(PageParameters params) {
        String actionName = params.getString("actionName");
        if ("getInitInfo".equals(actionName)) {
            return getInitInfo();
        } else if ("confirm".equals(actionName)) {
            return confirm(params);
        }
        throw new IllegalArgumentException(Messages.bind(Messages.Handler_Action_Error, new Object[] { actionName }));
    }

    private Object confirm(PageParameters params) {
        String[] names;
        try {
            JSONObject actionParam = new JSONObject(params.getString("actionParam"));
            Gson gson = new Gson();
            names = gson.fromJson(actionParam.get("data").toString(), String[].class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException(Messages.Handler_Params_Error, e);
        }
        String[] args = Platform.getApplicationArgs();
        String processUser = null;
        for (String arg : args) {
            String[] keyValue = arg.split("=", 2);
            if ("userId".equalsIgnoreCase(keyValue[0])) {
                processUser = keyValue[1];
            }
        }
        for (String name : names) {
            try {
                HostFileSts fileSts = new HostFileSts();
                FileObject file = FileUtil.getHostFileByName(name);
                fileSts.setHostDateTime(new Date(file.getContent().getLastModifiedTime()));
                fileSts.setCopyDateTime(new Date());
                fileSts.setProcessUser(processUser);
                fileSts.setStatus("0");
                fileSts.setName(name);
                getDao().update("SUC003_UPD_HOSTFILESTS_BY_NAME", fileSts);
                FileUtil.copyFile(FolderType.HOST, FolderType.PROCESS, new String[] { name });
                fileSts.setStatus("1");
                getDao().update("SUC003_UPD_HOSTFILESTS_BY_NAME", fileSts);
            } catch (FileSystemException e) {
                e.printStackTrace();
                // 前端只能接到 RuntimeException
                throw new SecurityException(Messages.bind(Messages.Access_Host_File_Error, new Object[] { name }), e);
            }
        }
        return "";
    }

    private Object getInitInfo() {
        List<TableFlow> tableFlowList = getDao().query("SUC002_QRY_TABLEFLOW", TableFlow.class, new Object());
        if (tableFlowList != null && tableFlowList.size() > 0) {
            String status = tableFlowList.get(0).getInitStatus();
            if ("1".equals(status)) {
                throw new IllegalStateException(Messages.SUC002Handler_InitStateError);
            }
        } else {
            throw new IllegalStateException(Messages.SUC005Handler_InitFirst);
        }
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
                    // +2 為 0x0D 0x0A 換行字元
                    hostFile.setRecord(hostFile.getSize() / (hostFile.getRecLen() + 2));
                } catch (Exception e) {
                    hostFile.setStatus("6");
                    e.printStackTrace();
                    // 前端只能接到 RuntimeException
                    throw new SecurityException(Messages.bind(Messages.Access_Host_File_Error,
                            new Object[] { hostFile.getName() }), e);
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
