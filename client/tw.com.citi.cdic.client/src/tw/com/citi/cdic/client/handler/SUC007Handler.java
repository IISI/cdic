package tw.com.citi.cdic.client.handler;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.commons.vfs.FileSystemException;
import org.apache.wicket.PageParameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.aquarius.embedserver.AquariusAjaxDaoHandler;
import tw.com.citi.cdic.client.dto.ConfirmDto;
import tw.com.citi.cdic.client.model.CDICFileSts;
import tw.com.citi.cdic.client.model.TableFlow;
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
public class SUC007Handler extends AquariusAjaxDaoHandler {

    final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Object execute(PageParameters params) {
        String actionName = params.getString("actionName");
        if ("getInitInfo".equals(actionName)) {
            return getInitInfo();
        } else if ("sendFile".equals(actionName)) {
            return sendFile();
        }
        throw new IllegalArgumentException("Cannot find actionName: " + actionName);
    }

    private Object sendFile() {
        // 取得基準日，用以產生新檔名
        String custDate = null;
        TableFlow tableFlow = new TableFlow();
        List<TableFlow> tableFlowList = getDao().query("SUC002_QRY_TABLEFLOW", TableFlow.class, new Object());
        if (tableFlowList != null && tableFlowList.size() > 0) {
            tableFlow = tableFlowList.get(0);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            custDate = sdf.format(tableFlow.getCustDate());
        }
        if (custDate != null) {
            // 取得 CDIC File
            List<CDICFileSts> cdicFileList = getDao().query("SUC007_QRY_CDICFILESTS_FOR_OUTPUT", CDICFileSts.class,
                    new Object());
            if (cdicFileList != null && cdicFileList.size() > 0) {
                for (CDICFileSts cdicFile : cdicFileList) {
                    String subFiles = cdicFile.getSubFile();
                    StringTokenizer st = new StringTokenizer(subFiles == null ? "" : subFiles.trim(), " ");
                    while (st.hasMoreElements()) {
                        String file = st.nextToken();
                        if (file != null && !"".equals(file.trim())) {
                            if ("3".equals(cdicFile.getStatus())) {
                                try {
                                    // copy file to icg folder
                                    FileUtil.copyFile(FolderType.PROCESS_OUT, FolderType.ICG, "0210000",
                                            "." + custDate, new String[] { file });
                                } catch (FileSystemException e) {
                                    e.printStackTrace();
                                    throw new SecurityException(Messages.bind(Messages.COPY_CDIC_File_Error,
                                            new Object[] { file }), e);
                                }
                            } else {
                                // 狀態不是已確認的，在 icg folder 產生一個空檔。
                                try {
                                    FileUtil.createFile(FolderType.ICG, "0210000" + file + "." + custDate);
                                } catch (FileSystemException e) {
                                    e.printStackTrace();
                                    throw new SecurityException(Messages.bind(Messages.COPY_CDIC_File_Error,
                                            new Object[] { file }), e);
                                }
                            }
                        }
                    }
                }
            }
            return "";
        } else {
            throw new IllegalArgumentException("Cannot find base date information.");
        }
    }

    private Object getInitInfo() {
        List<CDICFileSts> cdicFileList = getDao().query("SUC007_QRY_CDICFILESTS_FOR_OUTPUT", CDICFileSts.class,
                new Object());
        if (cdicFileList != null && cdicFileList.size() > 0) {
            JsonArray result = new JsonArray();
            for (CDICFileSts cdicFile : cdicFileList) {
                ConfirmDto dto = new ConfirmDto();
                StringBuffer fileSet = new StringBuffer();
                fileSet = fileSet.append(cdicFile.getFileNo().trim()).append("(")
                        .append(cdicFile.getSubFile() == null ? "" : cdicFile.getSubFile().trim()).append(")");
                dto.setFileSet(fileSet.toString());
                dto.setGroup(cdicFile.getFileGroup());
                String status = cdicFile.getStatus();
                if (status != null) {
                    String statusShow = null;
                    String color = "red";
                    if (Integer.parseInt(status) == 3) {
                        color = "green";
                        dto.setConfirmer(cdicFile.getConfirmer());
                        // F12 LCB/F15 LCB 處理
                        if ("F12".equalsIgnoreCase(cdicFile.getFileNo().trim())
                                || "F15".equalsIgnoreCase(cdicFile.getFileNo().trim())) {
                            String lcbFileNo = cdicFile.getFileNo().trim() + " LCB";
                            Map<String, Object> queryParams = new HashMap<String, Object>();
                            queryParams.put("fileNo", lcbFileNo);
                            List<CDICFileSts> cdicFiles = getDao().query("SUC006_QRY_CDICFILESTS_BY_FILENO",
                                    CDICFileSts.class, queryParams);
                            if (cdicFiles != null && cdicFiles.size() > 0) {
                                CDICFileSts lcbFile = cdicFiles.get(0);
                                if (Integer.parseInt(lcbFile.getStatus()) != 3) {
                                    status = lcbFile.getStatus();
                                    color = "red";
                                    dto.setConfirmer(null);
                                }
                            }
                        }
                    }
                    statusShow = getStatusMsg(Integer.parseInt(status));
                    dto.setStatus(status);
                    dto.setStatusShow("<font color='" + color + "'>" + statusShow + "</font>");
                }
                dto.setFileDesc(cdicFile.getFileDesc());
                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                result.add(gson.toJsonTree(dto, ConfirmDto.class));
            }
            return result;
        } else {
            return null;
        }
    }

    private String getStatusMsg(int status) {
        String statusShow = "UNKNOW";
        switch (status) {
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
            statusShow = Messages.STATUS_3;
            break;
        case 4:
            statusShow = Messages.STATUS_4;
            break;
        case 5:
            statusShow = Messages.STATUS_5;
            break;
        }
        return statusShow;
    }
}
