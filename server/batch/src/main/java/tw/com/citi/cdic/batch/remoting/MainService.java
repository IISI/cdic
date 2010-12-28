package tw.com.citi.cdic.batch.remoting;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Chih-Liang Chang
 * @since 2010/12/24
 */
public class MainService implements Runnable {

    private boolean stopped = false;

    @Override
    public void run() {
        stopped = false;
        ConfigurableApplicationContext context = null;
        context = new ClassPathXmlApplicationContext("batch.xml");
        context.getAutowireCapableBeanFactory().autowireBeanProperties(this,
                AutowireCapableBeanFactory.AUTOWIRE_BY_TYPE, false);
        
        while (!stopped) {
            synchronized (this) {
                try {
                    this.wait(60000);
                } catch (InterruptedException e) {
                }
            }
        }
    }

    public void stop() {
        stopped = true;
        synchronized (this) {
            this.notify();
        }
    }

}
