package tw.com.citi.cdic.client.handler;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.wicket.PageParameters;
import org.apache.wicket.util.upload.DiskFileItemFactory;
import org.apache.wicket.util.upload.FileItem;
import org.apache.wicket.util.upload.FileItemFactory;
import org.apache.wicket.util.upload.ServletFileUpload;
import org.eclipse.core.runtime.Platform;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.aquarius.embedserver.AquariusAjaxDaoHandler;
import tw.com.citi.cdic.client.model.LocalFileSts;
import tw.com.citi.cdic.utils.FileUtil;
import tw.com.citi.cdic.utils.FileUtil.FolderType;

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
        HttpServletRequest req = getRequest().getHttpServletRequest();
        boolean isMultipart = ServletFileUpload.isMultipartContent(req);
        if (isMultipart) {
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            List<FileItem> items = upload.parseRequest(req);
            uploadFiles(items);
            return "";
        } else {
            String actionName = params.getString("actionName");
            if ("getInitInfo".equals(actionName)) {
                return getInitInfo();
            } else if ("confirm".equals(actionName) || "upload".equals(actionName)) {
                return confirm(params);
            } else if ("download".equals(actionName)) {
                return download(params);
            }
            throw new IllegalArgumentException("Cannot find actionName: " + actionName);
        }
    }

    private void uploadFiles(List<FileItem> items) throws IOException {
        InputStream is = null;
        String name = null;
        for (Iterator<FileItem> iter = items.iterator(); iter.hasNext();) {
            FileItem item = iter.next();
            if (!item.isFormField()) {
                is = item.getInputStream();
            } else {
                if ("name".equals(item.getFieldName())) {
                    name = item.getString();
                }
            }
        }
        // TODO 檢核
        FileUtil.uploadFile(is, FolderType.PROCESS, name);
    }

    private void saveLocalFileStsByName(String name) {
        String[] args = Platform.getApplicationArgs();
        String processUser = args != null && args.length > 0 ? args[0] : null;
        LocalFileSts fileSts = new LocalFileSts();
        fileSts.setName(name);
        fileSts.setProcessUser(processUser);
        fileSts.setStatus("1");
        fileSts.setUploadDateTime(new Date());
        getDao().update("SUC004_UPD_LOCALFILESTS_BY_FILENAME", fileSts);
    }

    private Object download(PageParameters params) throws Exception {
        JSONObject actionParam = new JSONObject(params.getString("actionParam"));
        String savePath = actionParam.getString("savePath");
        String fileName = actionParam.getString("fileName");
        FileUtil.copyFile(FolderType.PROCESS, savePath, new String[] { fileName });
        return "";
    }

    private Object confirm(PageParameters params) throws Exception {
        JSONObject actionParam = new JSONObject(params.getString("actionParam"));
        saveLocalFileStsByName(actionParam.getString("fileName"));
        return getInitInfo();
    }

    private Object getInitInfo() throws Exception {
        List<LocalFileSts> localFileList = getDao().query("SUC004_QRY_LOCALFILESTS", LocalFileSts.class, new Object());
        if (localFileList != null && localFileList.size() > 0) {
            JsonArray result = new JsonArray();
            for (LocalFileSts localFile : localFileList) {
                localFile.setExist(FileUtil.exist(FolderType.PROCESS, localFile.getName()));
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
