package tw.com.citi.cdic.client.handler;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.wicket.PageParameters;
import org.eclipse.core.runtime.Platform;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.aquarius.embedserver.AquariusAjaxDaoHandler;
import tw.com.citi.cdic.client.dto.BatchDto;
import tw.com.citi.cdic.client.dto.BatchDto.BatchType;
import tw.com.citi.cdic.client.model.CDICFileSts;
import tw.com.citi.cdic.client.model.FileDepend;
import tw.com.citi.cdic.client.model.HostFileSts;
import tw.com.citi.cdic.client.model.LocalFileSts;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;

/**
 * @author Lancelot
 * @since 2010/11/01
 */
public class SUC005Handler extends AquariusAjaxDaoHandler {

    final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Object execute(PageParameters params) throws Exception {
        String actionName = params.getString("actionName");
        if ("getInitInfo".equals(actionName)) {
            return getInitInfo();
        } else if ("start".equals(actionName)) {
            return start(params);
        }
        throw new IllegalArgumentException("Cannot find actionName: " + actionName);
    }

    private Object start(PageParameters params) throws Exception {
        JSONObject actionParam = new JSONObject(params.getString("actionParam"));
        Gson gson = new Gson();
        String[] names = gson.fromJson(actionParam.get("data").toString(), String[].class);
        String[] args = Platform.getApplicationArgs();
        String processUser = args != null && args.length > 0 ? args[0] : null;
        Date copyDateTime = new Date();
        List<String> batches = new ArrayList<String>();
        for (String name : names) {
            if (name.startsWith("Group")) {
                String group = name.substring(5);
                // TODO 查出 GROUP = :group 的 CDICFileSts fileNo，加到 batches 中
            } else {
                batches.add(name);
            }
        }
        for (String batch : batches) {
            // TODO trigger batch
        }
        // 更新 CDICFileSts 狀態為執行中
        return "";
    }

    private BatchDto generateGroupBatchDto(String group) {
        BatchDto dto = new BatchDto();
        dto.setAllowExecution(true);
        dto.setBatchId("Group" + group);
        dto.setBatchName("Group" + group);
        dto.setBatchType(BatchType.GROUP);
        dto.setHasGroup(false);
        dto.setSourceReady("");
        dto.setSourceNotReady("");
        dto.setStatus("");
        return dto;
    }

    private List<String> getReadyFiles() {
        List<String> files = new ArrayList<String>();
        List<CDICFileSts> cdicReadyFiles = getDao().query("SUC005_QRY_CDICFILESTS_READY", CDICFileSts.class,
                new Object());
        if (cdicReadyFiles != null && cdicReadyFiles.size() > 0) {
            for (CDICFileSts file : cdicReadyFiles) {
                files.add("CDIC" + file.getFileNo());
            }
        }
        List<LocalFileSts> localReadyFiles = getDao().query("SUC005_QRY_LOCALFILESTS_READY", LocalFileSts.class,
                new Object());
        if (localReadyFiles != null && localReadyFiles.size() > 0) {
            for (LocalFileSts file : localReadyFiles) {
                files.add("LOCAL" + file.getName());
            }
        }
        List<HostFileSts> hostReadyFiles = getDao().query("SUC005_QRY_HOSTFILESTS_READY", HostFileSts.class,
                new Object());
        if (hostReadyFiles != null && hostReadyFiles.size() > 0) {
            for (HostFileSts file : hostReadyFiles) {
                files.add("HOST" + file.getName());
            }
        }
        return files;
    }

    private Object getInitInfo() throws Exception {
        List<String> readyFiles = getReadyFiles();
        List<CDICFileSts> cdicFileList = getDao().query("SUC007_QRY_CDICFILESTS", CDICFileSts.class, new Object());
        if (cdicFileList != null && cdicFileList.size() > 0) {
            JsonArray result = new JsonArray();
            Map<String, BatchDto> batchMap = new TreeMap<String, BatchDto>();
            for (CDICFileSts cdicFile : cdicFileList) {
                // 判斷有無 group
                String batchId = "";
                BatchType type = null;
                String batchName = null;
                boolean hasGroup = false;
                String group = cdicFile.getFileGroup();
                if (group != null && !"".equals(group.trim())) {
                    batchId = group;
                    hasGroup = true;
                    if (batchMap.get("group") == null) {
                        batchMap.put(group, generateGroupBatchDto(group));
                    }
                }
                batchName = cdicFile.getFileNo();
                batchId += batchName;
                if ("F99".equals(batchName)) {
                    type = BatchType.REPORT;
                } else {
                    type = BatchType.FILE;
                }
                BatchDto dto = new BatchDto();
                dto.setBatchId(batchId);
                dto.setBatchName(batchName);
                dto.setBatchType(type);
                dto.setHasGroup(hasGroup);
                // 查詢 FILEDEPEND 取得資料來源
                StringBuffer sourceReady = new StringBuffer();
                StringBuffer sourceNotReady = new StringBuffer();
                FileDepend parameters = new FileDepend();
                parameters.setName(batchName);
                List<FileDepend> sources = getDao()
                        .query("SUC005_QRY_FILEDEPEND_BY_NAME", FileDepend.class, parameters);
                if (sources != null && sources.size() > 0) {
                    for (FileDepend depend : sources) {
                        String temp = depend.getDepType().trim() + depend.getDependency().trim();
                        if (readyFiles.contains(temp)) {
                            sourceReady.append(depend.getDependency().trim()).append(" ");
                        } else {
                            sourceNotReady.append(depend.getDependency().trim()).append(" ");
                        }
                    }
                }
                dto.setSourceReady(sourceReady.toString());
                dto.setSourceNotReady(sourceNotReady.toString());
                if (sourceNotReady.length() == 0) {
                    dto.setAllowExecution(true);
                } else {
                    dto.setAllowExecution(false);
                }
                // 有未準備好的來源，將 group 的 allowExecution 設為 false
                if (sourceNotReady.length() != 0 && hasGroup) {
                    BatchDto groupDto = batchMap.get(group);
                    groupDto.setAllowExecution(false);
                    batchMap.put(group, groupDto);
                }
                dto.setStatus(cdicFile.getStatus());
                batchMap.put(batchId, dto);
            }

            Iterator<String> it = batchMap.keySet().iterator();
            while (it.hasNext()) {
                String batchId = it.next();
                BatchDto dto = batchMap.get(batchId);
                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                result.add(gson.toJsonTree(dto, BatchDto.class));
            }
            return result;
        } else {
            return "";
        }
    }
}
