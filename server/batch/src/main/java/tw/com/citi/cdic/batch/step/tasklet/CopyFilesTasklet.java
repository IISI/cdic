package tw.com.citi.cdic.batch.step.tasklet;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.core.io.FileSystemResource;

/**
 * 將檔案從來源資料夾複製至目的資料夾。
 * 
 * @author Chih-Liang Chang
 * @since 2010/9/29
 */
public class CopyFilesTasklet implements Tasklet {

    private FileSystemResource sourceDir;

    private FileSystemResource targetDir;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        File srcDir = sourceDir.getFile();
        File destDir = targetDir.getFile();
        if (srcDir.isDirectory()) {
            FileUtils.copyDirectory(srcDir, destDir, true);
        } else {
            FileUtils.copyFile(srcDir, destDir);
        }
        return RepeatStatus.FINISHED;
    }

    public void setSourceDir(FileSystemResource sourceDir) {
        this.sourceDir = sourceDir;
    }

    public void setTargetDir(FileSystemResource targetDir) {
        this.targetDir = targetDir;
    }

}
