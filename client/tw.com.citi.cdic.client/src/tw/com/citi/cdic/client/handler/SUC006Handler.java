package tw.com.citi.cdic.client.handler;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.commons.vfs.FileSystemException;
import org.apache.wicket.PageParameters;
import org.eclipse.core.runtime.Platform;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.aquarius.embedserver.AquariusAjaxDaoHandler;
import tw.com.citi.cdic.client.dto.ConfirmDto;
import tw.com.citi.cdic.client.model.CDICFileSts;
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
public class SUC006Handler extends AquariusAjaxDaoHandler {

    final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Object execute(PageParameters params) {
        String actionName = params.getString("actionName");
        if ("getInitInfo".equals(actionName)) {
            return getInitInfo();
        } else if ("downloadSample".equals(actionName)) {
            return downloadSample(params);
        } else if ("confirm".equals(actionName)) {
            return confirm(params);
        }
        throw new IllegalArgumentException("Cannot find actionName: " + actionName);
    }

    private Object confirm(PageParameters params) {
        String[] fileNos;
        try {
            JSONObject actionParam = new JSONObject(params.getString("actionParam"));
            Gson gson = new Gson();
            fileNos = gson.fromJson(actionParam.get("data").toString(), String[].class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException(Messages.Handler_Params_Error);
        }
        Date now = new Date();
        for (String fileNo : fileNos) {
            CDICFileSts fileSts = new CDICFileSts();
            fileSts.setFileNo(fileNo);
            fileSts.setStatus("3");
            fileSts.setConfirmDateTime(now);
            String[] args = Platform.getApplicationArgs();
            String processUser = null;
            for (String arg : args) {
                String[] keyValue = arg.split("=", 2);
                if ("userId".equalsIgnoreCase(keyValue[0])) {
                    processUser = keyValue[1];
                }
            }
            fileSts.setConfirmer(processUser);
            getDao().update("SUC006_UPD_CDICFILESTS_BY_FILENO", fileSts);
        }
        return "";
    }

    private Object downloadSample(PageParameters params) {
        String savePath;
        String fileNo;
        try {
            JSONObject actionParam = new JSONObject(params.getString("actionParam"));
            savePath = actionParam.getString("savePath");
            fileNo = actionParam.getString("fileNo");
        } catch (JSONException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(Messages.Handler_Params_Error);
        }
        // 根據 fileNo 取得 subFile
        Map<String, Object> queryParams = new HashMap<String, Object>();
        queryParams.put("fileNo", fileNo);
        List<CDICFileSts> cdicFileList = getDao().query("SUC006_QRY_CDICFILESTS_BY_FILENO", CDICFileSts.class,
                queryParams);
        if (cdicFileList != null && cdicFileList.size() > 0) {
            List<String> files = new ArrayList<String>();
            for (CDICFileSts cdicFile : cdicFileList) {
                String subFiles = cdicFile.getSubFile();
                StringTokenizer st = new StringTokenizer(subFiles, " ");
                while (st.hasMoreElements()) {
                    String file = st.nextToken() + "_sample.csv";
                    files.add(file);
                }
            }
            if (files != null && files.size() > 0) {
                String[] temp = new String[files.size()];
                try {
                    FileUtil.copyFile(FolderType.PROCESS, savePath, files.toArray(temp));
                } catch (FileSystemException e) {
                    e.printStackTrace();
                    throw new SecurityException(Messages.bind(Messages.Download_Sample_File_Error,
                            new Object[] { fileNo }));
                }
            }
        }
        return "";
    }

    private Object getInitInfo() {
        List<CDICFileSts> cdicFileList = getDao().query("SUC007_QRY_CDICFILESTS", CDICFileSts.class, new Object());
        if (cdicFileList != null && cdicFileList.size() > 0) {
            JsonArray result = new JsonArray();
            for (CDICFileSts cdicFile : cdicFileList) {
                ConfirmDto dto = new ConfirmDto();
                dto.setConfirmer(cdicFile.getConfirmer());
                StringBuffer fileSet = new StringBuffer();
                fileSet = fileSet.append(cdicFile.getFileNo()).append("(")
                        .append(cdicFile.getSubFile() != null ? cdicFile.getSubFile().trim() : "").append(")");
                dto.setFileSet(fileSet.toString());
                dto.setGroup(cdicFile.getFileGroup());
                dto.setFileNo(cdicFile.getFileNo());
                if (cdicFile.getStatus() != null) {
                    String status = null;
                    switch (Integer.parseInt(cdicFile.getStatus())) {
                    case 0:
                        status = Messages.STATUS_0;
                        break;
                    case 1:
                        status = Messages.STATUS_1;
                        break;
                    case 2:
                        status = Messages.STATUS_2;
                        break;
                    case 3:
                        status = Messages.STATUS_3;
                        break;
                    case 4:
                        status = Messages.STATUS_4;
                        break;
                    case 5:
                        status = Messages.STATUS_5;
                        break;
                    }
                    dto.setStatus(status);
                }
                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                result.add(gson.toJsonTree(dto, ConfirmDto.class));
            }
            return result;
        } else {
            return "";
        }
    }
}
