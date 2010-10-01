package tw.com.citi.cdic.batch.step.tasklet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

/**
 * 檢查檔案是否已經準備好了。
 * 
 * @author Chih-Liang Chang
 * @since 2010/9/30
 */
public class CheckFilesTasklet implements Tasklet {

    protected static final Logger logger = LoggerFactory.getLogger(CheckFilesTasklet.class);

    @Override
    public RepeatStatus execute(StepContribution contribution,
            ChunkContext chunkContext) throws Exception {
        logger.info("this is a test.");
        return RepeatStatus.FINISHED;
    }

}
