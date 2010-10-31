package tw.com.citi.cdic.client.handler;

import java.text.SimpleDateFormat;
import java.util.EnumSet;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.wicket.PageParameters;
import org.json.JSONStringer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.aquarius.embedserver.AquariusAjaxDaoHandler;
import tw.com.citi.cdic.client.dto.ConfirmDto;
import tw.com.citi.cdic.client.model.CDICFileSts;
import tw.com.citi.cdic.client.model.TableFlow;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;

/**
 * @author Lancelot
 * @since 2010/10/31
 */
public class SUC007Handler extends AquariusAjaxDaoHandler {

    final Logger logger = LoggerFactory.getLogger(getClass());

    enum Status {
        S0("0", "未開始"), S1("1", "待執行"), S2("2", "已完成"), S3("3", "已確認");

        private String code;
        private String description;

        Status(String code, String description) {
            this.setCode(code);
            this.setDescription(description);
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }

        public static String getDescByCode(String code) {
            String ret = null;
            for (final Status element : EnumSet.allOf(Status.class)) {
                if (element.getCode().equals(code)) {
                    ret = element.getDescription();
                    break;
                }
            }
            return ret;
        }
    }

    @Override
    public Object execute(PageParameters params) throws Exception {
        String actionName = params.getString("actionName");
        if ("getInitInfo".equals(actionName)) {
            return getInitInfo();
        } else if ("sendFile".equals(actionName)) {
            return sendFile();
        }
        throw new IllegalArgumentException("Cannot find actionName: " + actionName);
    }

    private Object sendFile() throws Exception {
        // TODO
        // 取得基準日，用以產生新檔名
        String custDate = null;
        TableFlow tableFlow = new TableFlow();
        List<TableFlow> tableFlowList = getDao().query("SUC002_QRY_TABLEFLOW", TableFlow.class, new Object());
        if (tableFlowList != null && tableFlowList.size() > 0) {
            tableFlow = tableFlowList.get(0);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            custDate = sdf.format(tableFlow.getCustDate());
        }
        // 取得 CDIC File
        List<CDICFileSts> cdicFileList = getDao().query("SUC007_QRY_CDICFILESTS", CDICFileSts.class, new Object());
        if (cdicFileList != null && cdicFileList.size() > 0) {
            for (CDICFileSts cdicFile : cdicFileList) {
                // 狀態不是已完成的，在 process folder 產生一個空檔。
                if (!"3".equals(cdicFile.getStatus())) {
                    String subFiles = cdicFile.getSubFile();
                    StringTokenizer st = new StringTokenizer(subFiles, " ");
                    while (st.hasMoreElements()) {
                        String file = st.nextToken();
                        // TODO jcifs create empty file in process folder
                    }
                }
                // TODO jcifs copy file from process folder to icg folder and
                // append custDate
            }
        }
        return new JSONStringer().object().key("status").value("0").endObject().toString();
    }

    private Object getInitInfo() throws Exception {
        List<CDICFileSts> cdicFileList = getDao().query("SUC007_QRY_CDICFILESTS", CDICFileSts.class, new Object());
        if (cdicFileList != null && cdicFileList.size() > 0) {
            JsonArray result = new JsonArray();
            for (CDICFileSts cdicFile : cdicFileList) {
                ConfirmDto dto = new ConfirmDto();
                dto.setConfirmer(cdicFile.getConfirmer());
                StringBuffer fileSet = new StringBuffer();
                fileSet = fileSet.append(cdicFile.getFileNo()).append("(").append(cdicFile.getSubFile()).append(")");
                dto.setFileSet(fileSet.toString());
                dto.setGroup(cdicFile.getFileGroup());
                dto.setStatus(Status.getDescByCode(cdicFile.getStatus()));
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
