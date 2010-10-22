package tw.com.citi.cdic.client.handler;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.wicket.PageParameters;
import org.json.JSONObject;
import org.json.JSONStringer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.aquarius.embedserver.AquariusAjaxDaoHandler;
import tw.com.citi.cdic.client.model.TableFlow;

/**
 * @author Chih-Liang Chang
 * @since 2010/10/21
 */
public class SUC002Handler extends AquariusAjaxDaoHandler {

    final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Object execute(PageParameters params) throws Exception {
        JSONObject actionParam = new JSONObject(params.getString("actionParam"));
        logger.debug("base date = {}", actionParam.getString("baseDate"));
        
        TableFlow tableFlow = new TableFlow();
        tableFlow.setCDICFileStatus(0);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        tableFlow.setCustDate(sdf.parse(actionParam.getString("baseDate")));
        tableFlow.setStarter(null);
        tableFlow.setInitUserId(null);
        tableFlow.setInitDateTime(new Date());
        
        getDao().update(new String[] {"SUC002_DEL", "SUC002_INS"}, new Object[] {new Object(), tableFlow});
        
        return new JSONStringer().object().key("result").value("success").endObject().toString();
    }

}
