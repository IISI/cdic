package tw.com.citi.cdic.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;

import org.apache.commons.vfs.FileFilter;
import org.apache.commons.vfs.FileFilterSelector;
import org.apache.commons.vfs.FileObject;
import org.apache.commons.vfs.FileSelectInfo;
import org.apache.commons.vfs.FileSystemException;
import org.apache.commons.vfs.FileSystemManager;
import org.apache.commons.vfs.FileSystemOptions;
import org.apache.commons.vfs.auth.StaticUserAuthenticator;
import org.apache.commons.vfs.impl.DefaultFileSystemConfigBuilder;
import org.apache.commons.vfs.provider.local.LocalFileSystem;
import org.eclipse.core.runtime.Platform;

import platform.aquarius.embedserver.conf.PasswordUtil;
import tw.com.citi.cdic.client.vfs.OSGiFileSystemManager;

public class FileUtil {

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
    private static FileSystemManager fsManager;
    private static FileSystemOptions opts;
    private static final int BUFF_SIZE = 100000;
    private static final byte[] buffer = new byte[BUFF_SIZE];
    private static boolean init = false;

    static {
        config = new Properties();
        try {
            URL url = Platform.getBundle("tw.com.citi.cdic.client.resources").getResource("folders.properties");
            config.load(url.openStream());
            String functionalId = config.getProperty("functionalId");
            StringTokenizer st = new StringTokenizer(functionalId, ";");
            String domain = null;
            String id = "";
            if (st.countTokens() == 2) {
                // 代表有domain
                domain = st.nextToken();
                id = st.nextToken();
            } else {
                id = st.nextToken();
            }
            StaticUserAuthenticator auth = new StaticUserAuthenticator(domain, id, getPassword("functionalPwd"));
            opts = new FileSystemOptions();
            DefaultFileSystemConfigBuilder.getInstance().setUserAuthenticator(opts, auth);
            fsManager = new OSGiFileSystemManager();
            ((OSGiFileSystemManager) fsManager).init();
            jcifs.Config.setProperty("jcifs.netbios.wins", NetUtil.getWins());
            init = true;
        } catch (Exception e) {
            e.printStackTrace();
            throw new SecurityException("Load folder settings error.", e);
        }
    }

    private static String getPassword(String propertyKey) throws Exception {
        return PasswordUtil.decodePwd(config.getProperty(propertyKey));
    }

    public static FileObject getHostFileByName(String fileName) throws FileSystemException {
        FileObject folder = fsManager.resolveFile(
                "smb://" + config.getProperty("host.host") + config.getProperty("host.path"), opts);
        FileObject file = fsManager.resolveFile(folder, fileName);
        fsManager.closeFileSystem(folder.getFileSystem());
        return file;
    }

    public static void copyFile(FileObject source, FileObject target, String prefix, String suffix,
            String[] sourceFileNames) throws FileSystemException {
        List<FileObject> files = new ArrayList<FileObject>();
        if (sourceFileNames == null || sourceFileNames.length == 0) {
            // 全部複製
            FileObject[] objects = source.getChildren();
            files.addAll(Arrays.asList(objects));
        } else {
            for (String fileName : sourceFileNames) {
                FileObject file = fsManager.resolveFile(source, fileName);
                if (file != null) {
                    files.add(file);
                }
            }
        }
        if (files != null && files.size() != 0) {
            final List<String> accept = new ArrayList<String>();
            for (FileObject file : files) {
                accept.add(file.getName().getBaseName());
            }
            FileFilterSelector ffs = new FileFilterSelector(new FileFilter() {
                @Override
                public boolean accept(FileSelectInfo info) {
                    if (accept.contains(info.getFile().getName().getBaseName())) {
                        return true;
                    } else {
                        return false;
                    }
                }
            });
            target.copyFrom(source, ffs);
            if (prefix != null || suffix != null) {
                // 修改檔名
                if (target.getFileSystem() instanceof LocalFileSystem) {
                    List<File> targetFiles = new ArrayList<File>();
                    for (String fileName : sourceFileNames) {
                        File file = new File(target.getURL().getFile() + "/" + fileName);
                        if (file != null && file.exists()) {
                            targetFiles.add(file);
                        }
                    }
                    if (targetFiles != null || targetFiles.size() > 0) {
                        for (File file : targetFiles) {
                            String baseName = file.getName();
                            String extensions = baseName.indexOf(".") > 0 ? baseName.substring(baseName.indexOf("."))
                                    : "";
                            String fileName = baseName.indexOf(".") > 0 ? baseName.substring(0,
                                    baseName.indexOf(".") - 1) : baseName;
                            StringBuffer newName = new StringBuffer();
                            newName = newName.append(prefix == null ? "" : prefix).append(fileName)
                                    .append(suffix == null ? "" : suffix).append(extensions);
                            file.renameTo(new File(target.getURL().getFile() + "/" + newName.toString()));
                        }
                    }
                } else {
                    List<FileObject> targetFiles = new ArrayList<FileObject>();
                    for (String fileName : sourceFileNames) {
                        FileObject file = fsManager.resolveFile(target, fileName);
                        if (file != null) {
                            targetFiles.add(file);
                        }
                    }
                    if (targetFiles != null || targetFiles.size() > 0) {
                        for (FileObject file : targetFiles) {
                            String baseName = file.getName().getBaseName();
                            String extensions = baseName.indexOf(".") > 0 ? baseName.substring(baseName.indexOf("."))
                                    : "";
                            String fileName = baseName.indexOf(".") > 0 ? baseName.substring(0,
                                    baseName.indexOf(".") - 1) : baseName;
                            StringBuffer newName = new StringBuffer();
                            newName = newName.append(prefix == null ? "" : prefix).append(fileName)
                                    .append(suffix == null ? "" : suffix).append(extensions);
                            FileObject temp = fsManager.resolveFile(target, newName.toString());
                            temp.createFile();
                            file.moveTo(temp);
                        }
                    }
                }
            }
        }
    }

    public static void copyFile(FileObject source, FileObject target, String[] sourceFileNames)
            throws FileSystemException {
        copyFile(source, target, null, null, sourceFileNames);
    }

    public static void copyFile(FolderType sourceFolder, String localPath, String prefix, String suffix,
            String[] sourceFileNames) throws FileSystemException {
        if (sourceFolder == null) {
            throw new IllegalArgumentException("input source folder is invalid.");
        }
        if (localPath == null || "".equals(localPath.trim())) {
            throw new IllegalArgumentException("input local path is invalid.");
        }
        FileObject source = fsManager.resolveFile("smb://" + config.getProperty(sourceFolder.getKey() + ".host")
                + config.getProperty(sourceFolder.getKey() + ".path"), opts);
        FileObject target = fsManager.resolveFile(localPath);
        copyFile(source, target, prefix, suffix, sourceFileNames);
    }

    public static void copyFile(FolderType sourceFolder, String localPath, String[] sourceFileNames)
            throws FileSystemException {
        copyFile(sourceFolder, localPath, null, null, sourceFileNames);
    }

    public static void copyFile(FolderType sourceFolder, FolderType targetFolder, String prefix, String suffix,
            String[] sourceFileNames) throws FileSystemException {
        if (sourceFolder == null) {
            throw new IllegalArgumentException("input source folder is invalid.");
        }
        if (targetFolder == null) {
            throw new IllegalArgumentException("input target folder is invalid.");
        }
        FileObject source = fsManager.resolveFile("smb://" + config.getProperty(sourceFolder.getKey() + ".host")
                + config.getProperty(sourceFolder.getKey() + ".path"), opts);
        FileObject target = fsManager.resolveFile("smb://" + config.getProperty(targetFolder.getKey() + ".host")
                + config.getProperty(targetFolder.getKey() + ".path"), opts);
        copyFile(source, target, prefix, suffix, sourceFileNames);
    }

    public static void copyFile(FolderType sourceFolder, FolderType targetFolder, String[] sourceFileNames)
            throws FileSystemException {
        copyFile(sourceFolder, targetFolder, null, null, sourceFileNames);
    }

    public static void uploadFile(InputStream in, FolderType target, String fileName) throws IOException {
        FileObject folder = fsManager.resolveFile(
                "smb://" + config.getProperty(target.getKey() + ".host")
                        + config.getProperty(target.getKey() + ".path"), opts);
        FileObject file = fsManager.resolveFile(folder, fileName);
        file.createFile();
        OutputStream out = null;
        try {
            out = file.getContent().getOutputStream(false);
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

    public static boolean exist(FolderType target, String fileName) throws FileSystemException {
        boolean tf = false;
        if (fileName != null) {
            FileObject folder = fsManager.resolveFile(
                    "smb://" + config.getProperty(target.getKey() + ".host")
                            + config.getProperty(target.getKey() + ".path"), opts);
            FileObject file = fsManager.resolveFile(folder, fileName);
            tf = file.exists();
        }
        return tf;
    }

    public static void createFile(FolderType target, String fileName) throws FileSystemException {
        FileObject folder = fsManager.resolveFile(
                "smb://" + config.getProperty(target.getKey() + ".host")
                        + config.getProperty(target.getKey() + ".path"), opts);
        FileObject file = fsManager.resolveFile(folder, fileName);
        if (exist(target, fileName)) {
            file.delete();
        }
        file.createFile();
    }

    public static void setInit(boolean init) {
        FileUtil.init = init;
    }

    public static boolean isInit() {
        return init;
    }
}
