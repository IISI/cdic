package tw.com.citi.cdic.client.handler;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.wicket.PageParameters;
import org.eclipse.core.runtime.Platform;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.aquarius.embedserver.AquariusAjaxDaoHandler;
import tw.com.citi.cdic.client.BatchService;
import tw.com.citi.cdic.client.BatchServiceImpl;
import tw.com.citi.cdic.client.model.CDICFileSts;
import tw.com.citi.cdic.client.model.HostFileSts;
import tw.com.citi.cdic.client.model.LocalFileSts;
import tw.com.citi.cdic.client.model.TableFlow;
import tw.com.citi.cdic.utils.Messages;

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
        String actionName = params.getString("actionName"); //$NON-NLS-1$
        if ("getInitInfo".equals(actionName)) { //$NON-NLS-1$
            return getInitInfo();
        } else if ("init".equals(actionName)) { //$NON-NLS-1$
            return init(params);
        }
        throw new IllegalArgumentException("Cannot find actionName: " + actionName); //$NON-NLS-1$
    }

    private Object getInitInfo() {
        TableFlow tableFlow = new TableFlow();
        List<TableFlow> tableFlowList = getDao().query("SUC002_QRY_TABLEFLOW", TableFlow.class, new Object()); //$NON-NLS-1$
        if (tableFlowList != null && tableFlowList.size() > 0) {
            tableFlow = tableFlowList.get(0);
        }
        
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("yyyy/MM/dd HH:mm"); //$NON-NLS-1$
        Gson gson = gsonBuilder.create();
        return gson.toJson(tableFlow, TableFlow.class);
    }

    private Object init(PageParameters params) throws Exception {
        JSONObject actionParam = new JSONObject(params.getString("actionParam")); //$NON-NLS-1$
        logger.debug("base date = {}", actionParam.getString("baseDate")); //$NON-NLS-1$ //$NON-NLS-2$

        TableFlow tableFlow = new TableFlow();
        List<TableFlow> tableFlowList = getDao().query("SUC002_QRY_TABLEFLOW", TableFlow.class, new Object()); //$NON-NLS-1$
        if (tableFlowList != null && tableFlowList.size() > 0) {
            tableFlow = tableFlowList.get(0);
            if ("1".equals(tableFlow.getInitStatus())) { //$NON-NLS-1$
                throw new IllegalStateException(Messages.SUC002Handler_InitStateError);
            }
        }
        
        tableFlow.setCDICFileStatus(0);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd"); //$NON-NLS-1$
        tableFlow.setCustDate(sdf.parse(actionParam.getString("baseDate"))); //$NON-NLS-1$
        tableFlow.setStarter(null);
        tableFlow.setInitUserId(null);
        String[] args = Platform.getApplicationArgs();
        for (String arg : args) {
            String[] keyValue = arg.split("=", 2); //$NON-NLS-1$
            if ("userId".equalsIgnoreCase(keyValue[0])) { //$NON-NLS-1$
                tableFlow.setInitUserId(keyValue[1]);
            }
        }
        tableFlow.setInitDateTime(new Date());
        tableFlow.setInitStatus("1"); //$NON-NLS-1$

        CDICFileSts fileSts = new CDICFileSts();
        fileSts.setStatus("0"); //$NON-NLS-1$
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
                new String[] { "SUC002_DEL_TABLEFLOW", "SUC002_INS_TABLEFLOW", //$NON-NLS-1$ //$NON-NLS-2$
                        "SUC002_UPD_CDICFILESTS", "SUC002_UPD_HOSTFILESTS", //$NON-NLS-1$ //$NON-NLS-2$
                        "SUC002_UPD_LOCALFILESTS" }, //$NON-NLS-1$
                new Object[] { new Object(), tableFlow, fileSts, hostFileSts,
                        localFileSts });
        
        BatchService batchService = new BatchServiceImpl();
        batchService.init();

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("yyyy/MM/dd HH:mm"); //$NON-NLS-1$
        Gson gson = gsonBuilder.create();
        return gson.toJson(tableFlow, TableFlow.class);
    }

}
