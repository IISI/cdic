package tw.com.citi.cdic.client.handler;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.vfs.FileSystemException;
import org.apache.wicket.PageParameters;
import org.apache.wicket.util.upload.DiskFileItemFactory;
import org.apache.wicket.util.upload.FileItem;
import org.apache.wicket.util.upload.FileItemFactory;
import org.apache.wicket.util.upload.FileUploadException;
import org.apache.wicket.util.upload.ServletFileUpload;
import org.eclipse.core.runtime.Platform;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.aquarius.embedserver.AquariusAjaxDaoHandler;
import tw.com.citi.cdic.client.model.LocalFileSts;
import tw.com.citi.cdic.utils.FileUtil;
import tw.com.citi.cdic.utils.FileUtil.FolderType;
import tw.com.citi.cdic.utils.Messages;

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
    public Object execute(PageParameters params) {
        HttpServletRequest req = getRequest().getHttpServletRequest();
        boolean isMultipart = ServletFileUpload.isMultipartContent(req);
        if (isMultipart) {
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            List<FileItem> items;
            try {
                items = upload.parseRequest(req);
                uploadFiles(items);
            } catch (FileUploadException e) {
                e.printStackTrace();
                throw new SecurityException(Messages.Upload_Local_File_Error, e);
            }
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
            throw new IllegalArgumentException(
                    Messages.bind(Messages.Handler_Action_Error, new Object[] { actionName }));
        }
    }

    private void uploadFiles(List<FileItem> items) {
        InputStream is = null;
        String name = null;
        try {
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
        } catch (IOException e) {
            e.printStackTrace();
            throw new SecurityException(Messages.bind(Messages.Upload_Local_File_Error, new Object[] { name }), e);
        }
    }

    private void saveLocalFileStsByName(String name) {
        String[] args = Platform.getApplicationArgs();
        String processUser = null;
        for (String arg : args) {
            String[] keyValue = arg.split("=", 2);
            if ("userId".equalsIgnoreCase(keyValue[0])) {
                processUser = keyValue[1];
            }
        }
        LocalFileSts fileSts = new LocalFileSts();
        fileSts.setName(name);
        fileSts.setProcessUser(processUser);
        fileSts.setStatus("1");
        fileSts.setUploadDateTime(new Date());
        getDao().update("SUC004_UPD_LOCALFILESTS_BY_FILENAME", fileSts);
    }

    private Object download(PageParameters params) {
        String savePath;
        String fileName;
        try {
            JSONObject actionParam = new JSONObject(params.getString("actionParam"));
            savePath = actionParam.getString("savePath");
            fileName = actionParam.getString("fileName");
        } catch (JSONException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Cannot find params.", e);
        }
        try {
            FileUtil.copyFile(FolderType.PROCESS, savePath, new String[] { fileName });
        } catch (FileSystemException e) {
            e.printStackTrace();
            throw new SecurityException(Messages.bind(Messages.Access_Host_File_Error, new Object[] { fileName }), e);
        }
        return "";
    }

    private Object confirm(PageParameters params) {
        try {
            JSONObject actionParam = new JSONObject(params.getString("actionParam"));
            saveLocalFileStsByName(actionParam.getString("fileName"));
        } catch (JSONException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(Messages.Handler_Params_Error, e);
        }
        return getInitInfo();
    }

    private Object getInitInfo() {
        List<LocalFileSts> localFileList = getDao().query("SUC004_QRY_LOCALFILESTS", LocalFileSts.class, new Object());
        if (localFileList != null && localFileList.size() > 0) {
            JsonArray result = new JsonArray();
            for (LocalFileSts localFile : localFileList) {
                try {
                    boolean exist = FileUtil.exist(FolderType.PROCESS, localFile.getName());
                    localFile.setExist(exist);
                    if (!exist) {
                        localFile.setStatus("0");
                    }
                } catch (FileSystemException e) {
                    e.printStackTrace();
                    throw new SecurityException(Messages.bind(Messages.Get_Local_File_Info_Error,
                            new Object[] { localFile.getName() }), e);
                }
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
