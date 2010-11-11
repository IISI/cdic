package tw.com.citi.cdic.client.handler;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.wicket.PageParameters;
import org.eclipse.core.runtime.Platform;
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
    public Object execute(PageParameters params) throws Exception {
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

    private Object confirm(PageParameters params) throws Exception {
        JSONObject actionParam = new JSONObject(params.getString("actionParam"));
        Gson gson = new Gson();
        String[] fileNos = gson.fromJson(actionParam.get("data").toString(), String[].class);
        Date now = new Date();
        for (String fileNo : fileNos) {
            CDICFileSts fileSts = new CDICFileSts();
            fileSts.setFileNo(fileNo);
            fileSts.setStatus("3");
            fileSts.setConfirmDateTime(now);
            String[] args = Platform.getApplicationArgs();
            if (args != null && args.length > 0) {
                fileSts.setConfirmer(args[0]);
            }
            getDao().update("SUC006_UPD_CDICFILESTS_BY_FILENO", fileSts);
        }
        return "";
    }

    private Object downloadSample(PageParameters params) throws Exception {
        JSONObject actionParam = new JSONObject(params.getString("actionParam"));
        String savePath = actionParam.getString("savePath");
        String fileNo = actionParam.getString("fileNo");
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
                    String file = st.nextToken();
                    files.add(file);
                }
            }
            if (files != null && files.size() > 0) {
                String[] temp = new String[files.size()];
                FileUtil.copyFile(FolderType.PROCESS, savePath, files.toArray(temp));
            }
        }
        return "";
    }

    private Object getInitInfo() throws Exception {
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
