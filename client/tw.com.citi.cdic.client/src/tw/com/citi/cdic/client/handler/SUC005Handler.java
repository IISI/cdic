package tw.com.citi.cdic.client.handler;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.wicket.PageParameters;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.aquarius.embedserver.AquariusAjaxDaoHandler;
import tw.com.citi.cdic.client.dto.BatchDto;
import tw.com.citi.cdic.client.dto.BatchDto.BatchType;
import tw.com.citi.cdic.client.dto.DepInfoDto;
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
        } else if ("getFileInfo".equals(actionName)) {
            return getFileInfo(params);
        }
        throw new IllegalArgumentException("Cannot find actionName: " + actionName);
    }

    private Object start(PageParameters params) throws Exception {
        JSONObject actionParam = new JSONObject(params.getString("actionParam"));
        Gson gson = new Gson();
        String[] names = gson.fromJson(actionParam.get("data").toString(), String[].class);
        List<String> batches = new ArrayList<String>();
        for (String name : names) {
            if (name.startsWith("Group")) {
                String group = name.substring(5);
                CDICFileSts param = new CDICFileSts();
                param.setFileGroup(group);
                List<CDICFileSts> groupNos = getDao()
                        .query("SUC005_QRY_CDICFILESTS_BY_GROUP", CDICFileSts.class, param);
                if (groupNos != null && groupNos.size() > 0) {
                    for (CDICFileSts file : groupNos) {
                        batches.add(file.getFileNo());
                        CDICFileSts pojo = new CDICFileSts();
                        pojo.setFileNo(file.getFileNo());
                        pojo.setStatus("1");
                        getDao().update("SUC005_UPD_CDICFILESTS_STATUS_BY_FILENO", pojo);
                    }
                }
            } else {
                batches.add(name);
                CDICFileSts pojo = new CDICFileSts();
                pojo.setFileNo(name);
                pojo.setStatus("1");
                getDao().update("SUC005_UPD_CDICFILESTS_STATUS_BY_FILENO", pojo);
            }
        }
        if (batches.size() > 0) {
            // TODO trigger batch
            String[] cmd = new String[] { "cmd", "/C", "PsExe.exe" };
            Runtime rt = Runtime.getRuntime();
            int exitVal = 0;
            Process p = rt.exec(cmd);
            exitVal = p.waitFor();
            if (exitVal != 0) {
                throw new UnsupportedOperationException("trigger batch failed.");
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

    private Object getFileInfo(PageParameters params) throws Exception {
        JSONObject actionParam = new JSONObject(params.getString("actionParam"));
        String fileNo = actionParam.getString("fileNo").trim();
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
//                        if (DepType.CDIC.toString().equals(depend.getDepType().trim())) {
//                            FileDepend param = new FileDepend();
//                            param.setName(depend.getDependency());
//                            List<FileDepend> subSources = getDao().query("SUC005_QRY_FILEDEPEND_BY_NAME",
//                                    FileDepend.class, param);
//                            boolean ready = true;
//                            for (FileDepend subDepend : subSources) {
//                                String subTemp = subDepend.getDepType().trim() + subDepend.getDependency().trim();
//                                if (!readyFiles.contains(subTemp)) {
//                                    ready = false;
//                                    break;
//                                }
//                            }
//                            if (ready) {
//                                sourceReady.append(depend.getDependency().trim()).append(" ");
//                            } else {
//                                sourceNotReady.append(depend.getDependency().trim()).append(" ");
//                            }
//                        } else {
                            if (readyFiles.contains(temp)) {
                                sourceReady.append(depend.getDependency().trim()).append(" ");
                            } else {
                                sourceNotReady.append(depend.getDependency().trim()).append(" ");
                            }
//                        }
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
                    BatchDto groupDto = batchMap.get(group + "_");
                    groupDto.setAllowExecution(false);
                    batchMap.put(group + "_", groupDto);
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
