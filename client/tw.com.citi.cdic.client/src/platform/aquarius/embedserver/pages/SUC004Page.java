package platform.aquarius.embedserver.pages;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.vfs.FileObject;
import org.apache.commons.vfs.FileSystemManager;
import org.apache.commons.vfs.FileSystemOptions;
import org.apache.commons.vfs.auth.StaticUserAuthenticator;
import org.apache.commons.vfs.impl.DefaultFileSystemConfigBuilder;
import org.apache.wicket.PageParameters;
import org.apache.wicket.protocol.http.WebRequest;
import org.apache.wicket.util.upload.DiskFileItemFactory;
import org.apache.wicket.util.upload.FileItem;
import org.apache.wicket.util.upload.FileItemFactory;
import org.apache.wicket.util.upload.FileUploadException;
import org.apache.wicket.util.upload.ServletFileUpload;

import tw.com.citi.cdic.client.vfs.OSGiFileSystemManager;

/**
 * @author Chih-Liang Chang
 * @since 2010/10/28
 */
public class SUC004Page extends AbstractAquariusPage {

    static final int BUFF_SIZE = 100000;
    static final byte[] buffer = new byte[BUFF_SIZE];

    public SUC004Page(PageParameters parameters) throws FileUploadException, IOException {
        super(parameters);
        
        HttpServletRequest req = ((WebRequest) getRequest()).getHttpServletRequest();
        boolean isMultipart = ServletFileUpload.isMultipartContent(req);
        if (!isMultipart) {
            return;
        }
        
        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        List<FileItem> items = upload.parseRequest(req);
        for (Iterator<FileItem> iter = items.iterator(); iter.hasNext();) {
            FileItem item = iter.next();
            if (item.isFormField()) {
                // do nothing
            } else {
                String fieldName = item.getFieldName();
                String filename = item.getName();
                String contentType = item.getContentType();
                boolean isInMemory = item.isInMemory();
                long sizeInBytes = item.getSize();
            }
        }
        uploadFiles(items);
    }

    private void uploadFiles(List<FileItem> items) throws IOException {
        for (Iterator<FileItem> iter = items.iterator(); iter.hasNext();) {
            FileItem item = iter.next();
            if (item.isFormField()) {
                // do nothing
            } else {
                StaticUserAuthenticator auth = new StaticUserAuthenticator(null, "Administrator", "p@ssw0rd");
                FileSystemOptions opts = new FileSystemOptions();
                DefaultFileSystemConfigBuilder.getInstance().setUserAuthenticator(opts, auth);
                
                FileSystemManager fsManager = new OSGiFileSystemManager();
                ((OSGiFileSystemManager) fsManager).init();
                FileObject folder = fsManager.resolveFile("smb://bath/test", opts);
                FileObject file = fsManager.resolveFile(folder, item.getName());
                file.createFile();
                
                InputStream in = null;
                OutputStream out = null;
                try {
                    in = item.getInputStream();
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
                    fsManager.closeFileSystem(folder.getFileSystem());
                }
            }
        }
    }

}
