package tw.com.citi.cdic.client.handler;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.StringTokenizer;

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
            List<CDICFileSts> cdicFileList = getDao().query("SUC007_QRY_CDICFILESTS", CDICFileSts.class, new Object());
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
                                    FileUtil.copyFile(FolderType.PROCESS_OUT, FolderType.ICG, "", "-" + custDate,
                                            new String[] { file });
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    throw new SecurityException(Messages.bind(Messages.COPY_CDIC_File_Error,
                                            new Object[] { file }), e);
                                }
                            } else {
                                // 狀態不是已確認的，在 icg folder 產生一個空檔。
                                try {
                                    FileUtil.createFile(FolderType.ICG, file + "-" + custDate);
                                } catch (Exception e) {
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
        List<CDICFileSts> cdicFileList = getDao().query("SUC007_QRY_CDICFILESTS", CDICFileSts.class, new Object());
        if (cdicFileList != null && cdicFileList.size() > 0) {
            JsonArray result = new JsonArray();
            for (CDICFileSts cdicFile : cdicFileList) {
                ConfirmDto dto = new ConfirmDto();
                StringBuffer fileSet = new StringBuffer();
                fileSet = fileSet.append(cdicFile.getFileNo()).append("(")
                        .append(cdicFile.getSubFile() == null ? "" : cdicFile.getSubFile().trim()).append(")");
                dto.setFileSet(fileSet.toString());
                dto.setGroup(cdicFile.getFileGroup());
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
            return null;
        }
    }
}
