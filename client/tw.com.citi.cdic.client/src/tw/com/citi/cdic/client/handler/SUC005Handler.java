package tw.com.citi.cdic.client.handler;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.wicket.PageParameters;
import org.eclipse.core.runtime.Platform;
import org.json.JSONException;
import org.json.JSONObject;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.launch.JobOperator;

import platform.aquarius.embedserver.Activator;
import platform.aquarius.embedserver.AquariusAjaxDaoHandler;
import tw.com.citi.cdic.client.dto.BatchDto;
import tw.com.citi.cdic.client.dto.BatchDto.BatchType;
import tw.com.citi.cdic.client.dto.DepInfoDto;
import tw.com.citi.cdic.client.model.CDICFileSts;
import tw.com.citi.cdic.client.model.FileDepend;
import tw.com.citi.cdic.client.model.HostFileSts;
import tw.com.citi.cdic.client.model.LocalFileSts;
import tw.com.citi.cdic.client.model.TableFlow;
import tw.com.citi.cdic.utils.Messages;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;

/**
 * @author Lancelot
 * @since 2010/11/01
 */
public class SUC005Handler extends AquariusAjaxDaoHandler {

    final Logger logger = LoggerFactory.getLogger(getClass());

    private JobOperator jobOperator;

    @Override
    public Object execute(PageParameters params) {
        String actionName = params.getString("actionName");
        if ("getInitInfo".equals(actionName)) {
            return getInitInfo();
        } else if ("start".equals(actionName)) {
            return start(params);
        } else if ("getFileInfo".equals(actionName)) {
            return getFileInfo(params);
        }
        throw new IllegalArgumentException("Cannot find actionName: " + actionName);
    }

    private Object start(PageParameters params) {
        String[] names;
        try {
            JSONObject actionParam = new JSONObject(params.getString("actionParam"));
            Gson gson = new Gson();
            names = gson.fromJson(actionParam.get("data").toString(), String[].class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException(Messages.Handler_Params_Error, e);
        }
        try {
            if (jobOperator == null) {
                BundleContext bundleContext = Platform.getBundle(Activator.PLUGIN_ID).getBundleContext();
                ServiceReference ref = bundleContext
                        .getServiceReference("org.springframework.batch.core.launch.JobOperator");
                jobOperator = (JobOperator) bundleContext.getService(ref);
            }
        } catch (Exception e) {
            throw new IllegalStateException(Messages.SUC002Handler_BatchServiceNotAvaliable, e);
        }
        List<String> batches = new ArrayList<String>();
        Date now = new Date();
        String[] args = Platform.getApplicationArgs();
        String executor = null;
        for (String arg : args) {
            String[] keyValue = arg.split("=", 2);
            if ("userId".equalsIgnoreCase(keyValue[0])) {
                executor = keyValue[1];
            }
        }
        for (String name : names) {
            if (name.startsWith("Group")) {
                String group = name.substring(5);
                CDICFileSts param = new CDICFileSts();
                param.setFileGroup(group);
                List<CDICFileSts> groupNos = getDao()
                        .query("SUC005_QRY_CDICFILESTS_BY_GROUP", CDICFileSts.class, param);
                if (groupNos != null && groupNos.size() > 0) {
                    // 加入是否為執行中的檢核，若狀態為執行中/待執行， Group 中的檔案全部略過。
                    boolean execute = true;
                    for (CDICFileSts file : groupNos) {
                        if ("4".equals(file.getStatus()) || "1".equals(file.getStatus())) {
                            execute = false;
                            break;
                        }
                    }
                    if (execute) {
                        for (CDICFileSts file : groupNos) {
                            batches.add(file.getFileNo());
                            CDICFileSts pojo = new CDICFileSts();
                            pojo.setFileNo(file.getFileNo());
                            pojo.setStatus("1");
                            pojo.setExecutor(executor);
                            pojo.setExecuteDateTime(now);
                            getDao().update("SUC005_UPD_CDICFILESTS_STATUS_BY_FILENO", pojo);
                        }
                    }
                }
            } else {
                // 加入是否為執行中的檢核，若狀態為執行中/待執行，略過。
                Map<String, Object> queryParams = new HashMap<String, Object>();
                queryParams.put("fileNo", name);
                List<CDICFileSts> cdicFileList = getDao().query("SUC006_QRY_CDICFILESTS_BY_FILENO", CDICFileSts.class,
                        queryParams);
                if (cdicFileList != null && cdicFileList.size() > 0) {
                    for (CDICFileSts cdicFile : cdicFileList) {
                        if (!"4".equals(cdicFile.getStatus()) && !"1".equals(cdicFile.getStatus())) {
                            batches.add(name);
                            CDICFileSts pojo = new CDICFileSts();
                            pojo.setFileNo(name);
                            pojo.setStatus("1");
                            pojo.setExecutor(executor);
                            pojo.setExecuteDateTime(now);
                            getDao().update("SUC005_UPD_CDICFILESTS_STATUS_BY_FILENO", pojo);
                        }
                    }
                }
            }
        }
        if (batches.size() > 0) {
            try {
                jobOperator.start("convertJob", "schedule.timestamp(long)=" + new Date().getTime());
            } catch (Exception e) {
                // 失敗時還原狀態
                for (String name : batches) {
                    CDICFileSts pojo = new CDICFileSts();
                    pojo.setFileNo(name);
                    pojo.setStatus("0");
                    pojo.setExecutor(executor);
                    pojo.setExecuteDateTime(now);
                    getDao().update("SUC005_UPD_CDICFILESTS_STATUS_BY_FILENO", pojo);
                }
                e.printStackTrace();
                throw new UnsupportedOperationException(Messages.Start_Batch_Error, e);
            }
        }
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

    private Object getFileInfo(PageParameters params) {
        String fileNo;
        try {
            JSONObject actionParam = new JSONObject(params.getString("actionParam"));
            fileNo = actionParam.getString("fileNo").trim();
        } catch (JSONException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(Messages.Handler_Params_Error, e);
        }
        FileDepend parameters = new FileDepend();
        parameters.setName(fileNo);
        List<FileDepend> sources = getDao().query("SUC005_QRY_FILEDEPEND_BY_NAME", FileDepend.class, parameters);
        if (sources != null && sources.size() > 0) {
            JsonArray result = new JsonArray();
            for (FileDepend depend : sources) {
                DepInfoDto dto = new DepInfoDto();
                String name = depend.getDependency().trim();
                dto.setName(name);
                String depType = depend.getDepType().trim();
                dto.setDepType(depType);
                if ("LOCAL".equals(depType)) {
                    LocalFileSts param = new LocalFileSts();
                    param.setName(name);
                    List<LocalFileSts> localFileList = getDao().query("SUC005_QRY_LOCALFILESTS_BY_NAME",
                            LocalFileSts.class, param);
                    if (localFileList != null && localFileList.size() > 0) {
                        dto.setLastDateTime(localFileList.get(0).getUploadDateTime());
                        dto.setUser(localFileList.get(0).getProcessUser());
                    }
                } else if ("HOST".equals(depType)) {
                    HostFileSts param = new HostFileSts();
                    param.setName(name);
                    List<HostFileSts> hostFileList = getDao().query("SUC005_QRY_HOSTFILESTS_BY_NAME",
                            HostFileSts.class, param);
                    if (hostFileList != null && hostFileList.size() > 0) {
                        dto.setLastDateTime(hostFileList.get(0).getHostDateTime());
                        dto.setUser(hostFileList.get(0).getProcessUser());
                    }
                } else if ("CDIC".equals(depType)) {
                    CDICFileSts param = new CDICFileSts();
                    param.setFileNo(name);
                    List<CDICFileSts> cdicFileList = getDao().query("SUC006_QRY_CDICFILESTS_BY_FILENO",
                            CDICFileSts.class, param);
                    if (cdicFileList != null && cdicFileList.size() > 0) {
                        dto.setLastDateTime(cdicFileList.get(0).getConfirmDateTime());
                        dto.setUser(cdicFileList.get(0).getConfirmer());
                    }
                }
                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                result.add(gson.toJsonTree(dto, DepInfoDto.class));
            }
            return result;
        } else {
            return "";
        }
    }

    private Object getInitInfo() {
        List<TableFlow> tableFlowList = getDao().query("SUC002_QRY_TABLEFLOW", TableFlow.class, new Object());
        if (tableFlowList != null && tableFlowList.size() > 0) {
            String status = tableFlowList.get(0).getInitStatus();
            if ("1".equals(status)) {
                throw new IllegalStateException(Messages.SUC002Handler_InitStateError);
            }
        } else {
            throw new IllegalStateException(Messages.SUC005Handler_InitFirst);
        }
        List<String> readyFiles = getReadyFiles();
        List<CDICFileSts> cdicFileList = getDao().query("SUC005_QRY_CDICFILESTS_ALL", CDICFileSts.class, new Object());
        if (cdicFileList != null && cdicFileList.size() > 0) {
            JsonArray result = new JsonArray();
            Map<String, BatchDto> batchMap = new TreeMap<String, BatchDto>();
            BatchDto all = new BatchDto();
            String allBatchId = "0All";
            all.setBatchId(allBatchId);
            all.setBatchName("All");
            all.setBatchType(BatchType.All);
            all.setHasGroup(false);
            all.setSourceNotReady("");
            all.setSourceReady("");
            all.setStatus("");
            all.setAllowExecution(true);
            batchMap.put(allBatchId, all);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
            for (CDICFileSts cdicFile : cdicFileList) {
                // 判斷有無 group
                String batchId = "";
                BatchType type = null;
                String batchName = null;
                boolean hasGroup = false;
                String group = cdicFile.getFileGroup();
                if (group != null && !"".equals(group.trim())) {
                    batchId = group + "_";
                    hasGroup = true;
                    if (batchMap.get(batchId) == null) {
                        batchMap.put(batchId, generateGroupBatchDto(group));
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
                        // 若 depType 為 CDIC，則再撈出其 dependency
                        // if
                        // (DepType.CDIC.toString().equals(depend.getDepType().trim()))
                        // {
                        // FileDepend param = new FileDepend();
                        // param.setName(depend.getDependency());
                        // List<FileDepend> subSources =
                        // getDao().query("SUC005_QRY_FILEDEPEND_BY_NAME",
                        // FileDepend.class, param);
                        // boolean ready = true;
                        // for (FileDepend subDepend : subSources) {
                        // String subTemp = subDepend.getDepType().trim() +
                        // subDepend.getDependency().trim();
                        // if (!readyFiles.contains(subTemp)) {
                        // ready = false;
                        // break;
                        // }
                        // }
                        // if (ready) {
                        // sourceReady.append(depend.getDependency().trim()).append(" ");
                        // } else {
                        // sourceNotReady.append(depend.getDependency().trim()).append(" ");
                        // }
                        // } else {
                        if (readyFiles.contains(temp)) {
                            sourceReady.append(depend.getDependency().trim()).append(" ");
                        } else {
                            sourceNotReady.append(depend.getDependency().trim()).append(" ");
                        }
                        // }
                    }
                }
                dto.setSourceReady(sourceReady.toString());
                dto.setSourceNotReady(sourceNotReady.toString());
                // 加入 cdic file 狀態判斷
                if (sourceNotReady.length() == 0 && !"4".equals(cdicFile.getStatus())
                        && !"1".equals(cdicFile.getStatus())) {
                    dto.setAllowExecution(true);
                } else {
                    dto.setAllowExecution(false);
                    BatchDto allDto = batchMap.get(allBatchId);
                    allDto.setAllowExecution(false);
                    batchMap.put(allBatchId, allDto);
                }
                // 有未準備好的來源，將 group 的 allowExecution 設為 false
                // 加入 cdic file 狀態判斷
                if (hasGroup
                        && (sourceNotReady.length() != 0 || "4".equals(cdicFile.getStatus()) || "1".equals(cdicFile
                                .getStatus()))) {
                    BatchDto groupDto = batchMap.get(group + "_");
                    groupDto.setAllowExecution(false);
                    batchMap.put(group + "_", groupDto);
                }
                dto.setStatus(cdicFile.getStatus());
                dto.setExecutor(cdicFile.getExecutor());
                dto.setExecuteTime(cdicFile.getExecuteDateTime() == null ? "" : sdf.format(cdicFile
                        .getExecuteDateTime()));
                dto.setFileDesc(cdicFile.getFileDesc());
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
