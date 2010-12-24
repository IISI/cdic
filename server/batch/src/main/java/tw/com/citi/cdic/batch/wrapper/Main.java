package tw.com.citi.cdic.batch.wrapper;

import org.tanukisoftware.wrapper.WrapperListener;
import org.tanukisoftware.wrapper.WrapperManager;

import tw.com.citi.cdic.batch.remoting.MainService;

/**
 * @author Chih-Liang Chang
 * @since 2010/12/24
 */
public class Main implements WrapperListener {

    private MainService mainService;

    private Main() {}

    @Override
    public void controlEvent(int event) {
        if ((event == WrapperManager.WRAPPER_CTRL_LOGOFF_EVENT)
                && (WrapperManager.isLaunchedAsService() || WrapperManager.isIgnoreUserLogoffs())) {
            // ignore
        } else {
            WrapperManager.stop(0);
        }
    }

    @Override
    public Integer start(String[] args) {
        mainService = new MainService();
        new Thread(mainService).start();
        
        return null;
    }

    @Override
    public int stop(int exitCode) {
        mainService.stop();
        
        return exitCode;
    }

    public static void main(String[] args) {
        WrapperManager.start(new Main(), args);
    }

}
