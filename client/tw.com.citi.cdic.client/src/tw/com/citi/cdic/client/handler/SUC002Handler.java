package tw.com.citi.cdic.client.handler;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.wicket.PageParameters;
import org.json.JSONObject;
import org.json.JSONStringer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.aquarius.embedserver.AquariusAjaxDaoHandler;
import tw.com.citi.cdic.client.BatchService;
import tw.com.citi.cdic.client.BatchServiceImpl;
import tw.com.citi.cdic.client.model.CDICFileSts;
import tw.com.citi.cdic.client.model.HostFileSts;
import tw.com.citi.cdic.client.model.LocalFileSts;
import tw.com.citi.cdic.client.model.TableFlow;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author Chih-Liang Chang
 * @since 2010/10/21
 */
public class SUC002Handler extends AquariusAjaxDaoHandler {

    final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Object execute(PageParameters params) throws Exception {
        String actionName = params.getString("actionName");
        if ("getInitInfo".equals(actionName)) {
            return getInitInfo();
        } else if ("init".equals(actionName)) {
            return init(params);
        }
        throw new IllegalArgumentException("Cannot find actionName: " + actionName);
    }

    private Object getInitInfo() {
        TableFlow tableFlow = new TableFlow();
        tableFlow.setInitUserId("test");
        tableFlow.setInitDateTime(new Date());
        tableFlow.setInitStatus("2");
        
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("yyyy/MM/dd HH:mm");
        Gson gson = gsonBuilder.create();
        return gson.toJson(tableFlow, TableFlow.class);
    }

    private Object init(PageParameters params) throws Exception {
        JSONObject actionParam = new JSONObject(params.getString("actionParam"));
        logger.debug("base date = {}", actionParam.getString("baseDate"));

        TableFlow tableFlow = new TableFlow();
        List<TableFlow> tableFlowList = getDao().query("SUC002_QRY_TABLEFLOW", TableFlow.class, new Object());
        if (tableFlowList != null && tableFlowList.size() > 0) {
            tableFlow = tableFlowList.get(0);
            if ("1".equals(tableFlow.getInitStatus())) {
                throw new IllegalStateException("啟動作業正在執行中，請稍後再試。");
            }
        }
        
        tableFlow.setCDICFileStatus(0);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        tableFlow.setCustDate(sdf.parse(actionParam.getString("baseDate")));
        tableFlow.setStarter(null);
        tableFlow.setInitUserId(null);
        tableFlow.setInitDateTime(new Date());
        tableFlow.setInitStatus("1");

        CDICFileSts fileSts = new CDICFileSts();
        fileSts.setStatus("0");
        fileSts.setConfirmer(null);
        fileSts.setConfirmDateTime(null);

        HostFileSts hostFileSts = new HostFileSts();
        hostFileSts.setHostDateTime(null);
        hostFileSts.setCopyDateTime(null);
        hostFileSts.setProcessUser(null);
        hostFileSts.setStatus(null);

        LocalFileSts localFileSts = new LocalFileSts();
        localFileSts.setProcessUser(null);
        localFileSts.setStatus(null);

        getDao().update(
                new String[] { "SUC002_DEL_TABLEFLOW", "SUC002_INS_TABLEFLOW",
                        "SUC002_UPD_CDICFILESTS", "SUC002_UPD_HOSTFILESTS",
                        "SUC002_UPD_LOCALFILESTS" },
                new Object[] { new Object(), tableFlow, fileSts, hostFileSts,
                        localFileSts });
        
        BatchService batchService = new BatchServiceImpl();
        batchService.init();

        return new JSONStringer().object().key("status").value("0")
                .endObject().toString();
    }

}
