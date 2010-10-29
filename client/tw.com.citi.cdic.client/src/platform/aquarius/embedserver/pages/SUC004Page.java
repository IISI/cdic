package platform.aquarius.embedserver.pages;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.wicket.PageParameters;
import org.apache.wicket.protocol.http.WebRequest;
import org.apache.wicket.util.upload.DiskFileItemFactory;
import org.apache.wicket.util.upload.FileItem;
import org.apache.wicket.util.upload.FileItemFactory;
import org.apache.wicket.util.upload.FileUploadException;
import org.apache.wicket.util.upload.ServletFileUpload;

/**
 * @author Chih-Liang Chang
 * @since 2010/10/28
 */
public class SUC004Page extends AbstractAquariusPage {

    public SUC004Page(PageParameters parameters) throws FileUploadException {
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
    }

}
