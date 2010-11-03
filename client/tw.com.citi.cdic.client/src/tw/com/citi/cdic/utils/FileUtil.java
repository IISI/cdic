package tw.com.citi.cdic.utils;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.commons.vfs.FileFilter;
import org.apache.commons.vfs.FileFilterSelector;
import org.apache.commons.vfs.FileObject;
import org.apache.commons.vfs.FileSelectInfo;
import org.apache.commons.vfs.FileSystemException;
import org.apache.commons.vfs.FileSystemManager;
import org.apache.commons.vfs.FileSystemOptions;
import org.apache.commons.vfs.auth.StaticUserAuthenticator;
import org.apache.commons.vfs.impl.DefaultFileSystemConfigBuilder;
import org.eclipse.core.runtime.Platform;

import tw.com.citi.cdic.client.vfs.OSGiFileSystemManager;

public class FileUtil {
    public static enum FolderType {
        HOST("host"), ICG("icg"), PROCESS("process");
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
    static {
        config = new Properties();
        try {
            URL url = Platform.getBundle("tw.com.citi.cdic.client.resources").getResource("folders.properties");
            config.load(url.openStream());
            StaticUserAuthenticator auth = new StaticUserAuthenticator(null, config.getProperty("host.user"),
                    getPassword("host.password"));
            opts = new FileSystemOptions();
            DefaultFileSystemConfigBuilder.getInstance().setUserAuthenticator(opts, auth);
            fsManager = new OSGiFileSystemManager();
            ((OSGiFileSystemManager) fsManager).init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getPassword(String propertyKey) {
        // TODO 如果有其他處理
        return config.getProperty(propertyKey);
    }

    public static FileObject getHostFileByName(String fileName) throws FileSystemException {
        FileObject folder = fsManager.resolveFile(
                "smb://" + config.getProperty("host.host") + config.getProperty("host.path"), opts);
        FileObject file = fsManager.resolveFile(folder, fileName);
        fsManager.closeFileSystem(folder.getFileSystem());
        return file;
    }

    public static void copyFile(FolderType sourceFolder, FolderType targetFolder, String... sorceFileNames)
            throws Exception {
        if (sourceFolder == null) {
            throw new IllegalArgumentException("input source folder is invalid.");
        }
        if (targetFolder == null) {
            throw new IllegalArgumentException("input target folder is invalid.");
        }
        List<FileObject> files = new ArrayList<FileObject>();
        FileObject source = fsManager.resolveFile("smb://" + config.getProperty(sourceFolder.getKey() + ".host")
                + config.getProperty(sourceFolder.getKey() + ".path"), opts);
        FileObject target = fsManager.resolveFile("smb://" + config.getProperty(targetFolder.getKey() + ".host")
                + config.getProperty(targetFolder.getKey() + ".path"), opts);
        if (sorceFileNames == null || sorceFileNames.length == 0) {
            // 全部複製
            FileObject[] objects = source.getChildren();
            files.addAll(Arrays.asList(objects));
        } else {
            for (String fileName : sorceFileNames) {
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
        }
    }
}
