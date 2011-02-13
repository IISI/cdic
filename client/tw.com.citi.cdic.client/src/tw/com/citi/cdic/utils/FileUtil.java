package tw.com.citi.cdic.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import jcifs.smb.SmbException;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileFilter;
import jcifs.smb.SmbFileInputStream;
import jcifs.smb.SmbFileOutputStream;

import org.eclipse.core.runtime.Platform;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.aquarius.embedserver.conf.PasswordUtil;

public class FileUtil {

    final static Logger logger = LoggerFactory.getLogger(FileUtil.class);

    public static enum FolderType {
        HOST("host"), ICG("icg"), PROCESS("process"), PROCESS_OUT("processout");
        private String key;

        FolderType(String key) {
            this.setKey(key);
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getKey() {
            return key;
        }
    }

    private static Properties config;
    private static final int BUFF_SIZE = 100000;
    private static final byte[] buffer = new byte[BUFF_SIZE];
    private static boolean init = false;
    private static String uriStringPrefix;

    static {
        config = new Properties();
        try {
            URL url = Platform.getBundle("tw.com.citi.cdic.client.resources").getResource("folders.properties");
            config.load(url.openStream());
            uriStringPrefix = "smb://" + config.getProperty("functionalId") + ":" + getPassword("functionalPwd") + "@";
            init = true;
        } catch (Exception e) {
            e.printStackTrace();
            throw new SecurityException("Load folder settings error.", e);
        }
    }

    private static String getPassword(String propertyKey) throws Exception {
        return PasswordUtil.decodePwd(config.getProperty(propertyKey));
    }

    private static String getURI(FolderType folderType, String fileName) {
        return uriStringPrefix + config.getProperty(folderType.getKey() + ".host")
                + config.getProperty(folderType.getKey() + ".path") + "/" + fileName;
    }

    public static SmbFile getHostFileByName(String fileName) throws MalformedURLException {
        String fileStr = getURI(FolderType.HOST, fileName);
        SmbFile file = new SmbFile(fileStr);
        return file;
    }

    private static void copyFile(SmbFile source, SmbFile target, String prefix, String suffix, String[] sourceFileNames)
            throws SmbException, MalformedURLException {
        List<SmbFile> files = new ArrayList<SmbFile>();
        if (sourceFileNames == null || sourceFileNames.length == 0) {
            // 全部複製
            SmbFile[] objects = source.listFiles();
            files.addAll(Arrays.asList(objects));
        } else {
            for (String fileName : sourceFileNames) {
                SmbFile file = new SmbFile(source.getPath() + "/" + fileName);
                if (file != null) {
                    files.add(file);
                }
            }
        }
        if (files != null && files.size() != 0) {
            final List<String> accept = new ArrayList<String>();
            for (SmbFile file : files) {
                accept.add(file.getName());
            }
            SmbFileFilter filter = new SmbFileFilter() {
                @Override
                public boolean accept(SmbFile info) {
                    if (accept.contains(info.getName())) {
                        return true;
                    } else {
                        return false;
                    }
                }
            };
            SmbFile[] filesToCopy = source.listFiles(filter);
            for (SmbFile file : filesToCopy) {
                file.copyTo(new SmbFile(target.getPath() + "/" + file.getName()));
            }
            if (prefix != null || suffix != null) {
                // 修改檔名
                List<SmbFile> targetFiles = new ArrayList<SmbFile>();
                for (String fileName : sourceFileNames) {
                    SmbFile file = new SmbFile(target.getPath() + "/" + fileName);
                    if (file != null && file.exists()) {
                        targetFiles.add(file);
                    }
                }
                if (targetFiles != null || targetFiles.size() > 0) {
                    for (SmbFile file : targetFiles) {
                        String baseName = file.getName();
                        String extensions = baseName.indexOf(".") > 0 ? baseName.substring(baseName.indexOf(".")) : "";
                        String fileName = baseName.indexOf(".") > 0 ? baseName.substring(0, baseName.indexOf(".") - 1)
                                : baseName;
                        StringBuffer newName = new StringBuffer();
                        newName = newName.append(prefix == null ? "" : prefix).append(fileName)
                                .append(suffix == null ? "" : suffix).append(extensions);
                        file.renameTo(new SmbFile(target.getPath() + "/" + newName.toString()));
                    }
                }
            }
        }
    }

    private static void copyFileToLocal(SmbFile source, String target, String prefix, String suffix,
            String[] sourceFileNames) throws IOException {
        List<SmbFile> files = new ArrayList<SmbFile>();
        if (sourceFileNames == null || sourceFileNames.length == 0) {
            // 全部複製
            SmbFile[] objects = source.listFiles();
            files.addAll(Arrays.asList(objects));
        } else {
            for (String fileName : sourceFileNames) {
                SmbFile file = new SmbFile(source.getPath() + "/" + fileName);
                if (file != null) {
                    files.add(file);
                }
            }
        }
        if (files != null && files.size() != 0) {
            final List<String> accept = new ArrayList<String>();
            for (SmbFile file : files) {
                accept.add(file.getName());
            }
            SmbFileFilter filter = new SmbFileFilter() {
                @Override
                public boolean accept(SmbFile info) {
                    if (accept.contains(info.getName())) {
                        return true;
                    } else {
                        return false;
                    }
                }
            };
            SmbFile[] filesToCopy = source.listFiles(filter);
            for (SmbFile file : filesToCopy) {
                SmbFileInputStream in = null;
                FileOutputStream out = null;
                try {
                    in = new SmbFileInputStream(file);
                    out = new FileOutputStream(new File(target + "/" + file.getName()));
                    byte[] buffer = new byte[1024];
                    int len = 0;
                    while ((len = in.read(buffer)) > 0) {
                        out.write(buffer, 0, len);
                    }
                } finally {
                    try {
                        if (out != null) {
                            out.close();
                        }
                        if (in != null) {
                            in.close();
                        }
                    } catch (IOException e) {
                    }
                }

            }
            if (prefix != null || suffix != null) {
                // 修改檔名
                List<File> targetFiles = new ArrayList<File>();
                for (String fileName : sourceFileNames) {
                    File file = new File(target + "/" + fileName);
                    if (file != null && file.exists()) {
                        targetFiles.add(file);
                    }
                }
                if (targetFiles != null || targetFiles.size() > 0) {
                    for (File file : targetFiles) {
                        String baseName = file.getName();
                        String extensions = baseName.indexOf(".") > 0 ? baseName.substring(baseName.indexOf(".")) : "";
                        String fileName = baseName.indexOf(".") > 0 ? baseName.substring(0, baseName.indexOf(".") - 1)
                                : baseName;
                        StringBuffer newName = new StringBuffer();
                        newName = newName.append(prefix == null ? "" : prefix).append(fileName)
                                .append(suffix == null ? "" : suffix).append(extensions);
                        file.renameTo(new File(target + "/" + newName.toString()));
                    }
                }
            }
        }
    }

    public static void copyFile(SmbFile source, SmbFile target, String[] sourceFileNames) throws SmbException,
            MalformedURLException {
        copyFile(source, target, null, null, sourceFileNames);
    }

    public static void copyFileToLocal(FolderType sourceFolder, String localPath, String prefix, String suffix,
            String[] sourceFileNames) throws IOException {
        if (sourceFolder == null) {
            throw new IllegalArgumentException("input source folder is invalid.");
        }
        if (localPath == null || "".equals(localPath.trim())) {
            throw new IllegalArgumentException("input local path is invalid.");
        }
        SmbFile source = new SmbFile(getURI(sourceFolder, ""));
        copyFileToLocal(source, localPath, prefix, suffix, sourceFileNames);
    }

    public static void copyFileToLocal(FolderType sourceFolder, String localPath, String[] sourceFileNames)
            throws IOException {
        copyFileToLocal(sourceFolder, localPath, null, null, sourceFileNames);
    }

    public static void copyFile(FolderType sourceFolder, FolderType targetFolder, String prefix, String suffix,
            String[] sourceFileNames) throws MalformedURLException, SmbException {
        if (sourceFolder == null) {
            throw new IllegalArgumentException("input source folder is invalid.");
        }
        if (targetFolder == null) {
            throw new IllegalArgumentException("input target folder is invalid.");
        }
        SmbFile source = new SmbFile(getURI(sourceFolder, ""));
        SmbFile target = new SmbFile(getURI(targetFolder, ""));
        copyFile(source, target, prefix, suffix, sourceFileNames);
    }

    public static void copyFile(FolderType sourceFolder, FolderType targetFolder, String[] sourceFileNames)
            throws MalformedURLException, SmbException {
        copyFile(sourceFolder, targetFolder, null, null, sourceFileNames);
    }

    public static void uploadFile(InputStream in, FolderType target, String fileName) throws IOException {
        SmbFile file = new SmbFile(getURI(target, fileName));
        if (file.exists()) {
            file.delete();
        }
        file.createNewFile();
        SmbFileOutputStream out = null;
        try {
            out = new SmbFileOutputStream(file, false);
            while (true) {
                synchronized (buffer) {
                    int amountRead = in.read(buffer);
                    if (amountRead == -1) {
                        break;
                    }
                    out.write(buffer, 0, amountRead);
                }
            }
        } finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }

    public static boolean exist(FolderType target, String fileName) throws MalformedURLException, SmbException {
        boolean tf = false;
        if (fileName != null) {
            SmbFile file = new SmbFile(getURI(target, fileName));
            tf = file.exists();
        }
        return tf;
    }

    public static void createFile(FolderType target, String fileName) throws MalformedURLException, SmbException {
        SmbFile file = new SmbFile(getURI(target, fileName));
        if (exist(target, fileName)) {
            file.delete();
        }
        file.createNewFile();
    }

    public static void setInit(boolean init) {
        FileUtil.init = init;
    }

    public static boolean isInit() {
        return init;
    }
}
