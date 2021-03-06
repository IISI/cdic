package tw.com.citi.cdic.client.handler;

import java.text.SimpleDateFormat;
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
            throw new IllegalArgumentException(Messages.Handler_Params_Error, e);
        }
        Date now = new Date();
        String[] args = Platform.getApplicationArgs();
        String processUser = null;
        for (String arg : args) {
            String[] keyValue = arg.split("=", 2);
            if ("userId".equalsIgnoreCase(keyValue[0])) {
                processUser = keyValue[1];
            }
        }
        for (String fileNo : fileNos) {
            CDICFileSts fileSts = new CDICFileSts();
            fileSts.setFileNo(fileNo);
            fileSts.setStatus("3");
            fileSts.setConfirmDateTime(now);
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
            throw new IllegalArgumentException(Messages.Handler_Params_Error, e);
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
                    String file = "f99".equalsIgnoreCase(fileNo) ? st.nextToken() : st.nextToken() + "_sample.csv";
                    files.add(file);
                }
            }
            if (files != null && files.size() > 0) {
                String[] temp = new String[files.size()];
                try {
                    FileUtil.copyFile(FolderType.PROCESS_OUT, savePath, files.toArray(temp));
                } catch (FileSystemException e) {
                    e.printStackTrace();
                    throw new SecurityException(Messages.bind(Messages.Download_Sample_File_Error,
                            new Object[] { fileNo }), e);
                }
            }
        }
        return "";
    }

    private Object getInitInfo() {
        List<CDICFileSts> cdicFileList = getDao().query("SUC005_QRY_CDICFILESTS_ALL", CDICFileSts.class, new Object());
        if (cdicFileList != null && cdicFileList.size() > 0) {
            JsonArray result = new JsonArray();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
            for (CDICFileSts cdicFile : cdicFileList) {
                ConfirmDto dto = new ConfirmDto();
                StringBuffer fileSet = new StringBuffer();
                fileSet = fileSet.append(cdicFile.getFileNo().trim()).append("(")
                        .append(cdicFile.getSubFile() != null ? cdicFile.getSubFile().trim() : "").append(")");
                dto.setFileSet(fileSet.toString());
                dto.setGroup(cdicFile.getFileGroup());
                dto.setFileNo(cdicFile.getFileNo());
                if (cdicFile.getStatus() != null) {
                    String statusShow = null;
                    String color = "red";
                    switch (Integer.parseInt(cdicFile.getStatus())) {
                    case 0:
                        statusShow = Messages.STATUS_0;
                        break;
                    case 1:
                        statusShow = Messages.STATUS_1;
                        break;
                    case 2:
                        statusShow = Messages.STATUS_2;
                        break;
                    case 3:
                        color = "green";
                        statusShow = Messages.STATUS_3;
                        dto.setConfirmer(cdicFile.getConfirmer());
                        dto.setConfirmDateTime(cdicFile.getConfirmDateTime() == null ? "" : sdf.format(cdicFile
                                .getConfirmDateTime()));
                        break;
                    case 4:
                        statusShow = Messages.STATUS_4;
                        break;
                    case 5:
                        statusShow = Messages.STATUS_5;
                        break;
                    }
                    dto.setStatus(cdicFile.getStatus());
                    dto.setStatusShow("<font color='" + color + "'>" + statusShow + "</font>");
                }
                dto.setFileDesc(cdicFile.getFileDesc());
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
