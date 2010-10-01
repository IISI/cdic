package platform.aquarius.embedserver;

import org.apache.wicket.Response;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.protocol.http.WebRequest;
import org.apache.wicket.protocol.http.WebRequestCycle;
import org.apache.wicket.request.target.resource.ResourceStreamRequestTarget;

public class AquariusRequestCycle extends WebRequestCycle {

    /**
     * AquariusRequestCycle
     * 
     * @param application
     * @param request
     * @param response
     */
    public AquariusRequestCycle(WebApplication application, WebRequest request,
            Response response) {
        super(application, request, response);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.wicket.RequestCycle#onEndRequest()
     */
    @Override
    protected void onEndRequest() {
        if (getRequestTarget() instanceof ResourceStreamRequestTarget) {
            // 為了解決PDF Report Page的奇怪Exception
            getResponse().reset();
        }
    }

}
