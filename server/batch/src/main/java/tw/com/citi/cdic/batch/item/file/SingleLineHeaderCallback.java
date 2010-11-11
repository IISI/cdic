package tw.com.citi.cdic.batch.item.file;

import java.io.IOException;
import java.io.Writer;

import org.springframework.batch.item.file.FlatFileHeaderCallback;

/**
 * @author Chih-Liang Chang
 * @since 2010/10/20
 */
public class SingleLineHeaderCallback implements FlatFileHeaderCallback {

    private String line;

    @Override
    public void writeHeader(Writer writer) throws IOException {
        writer.write(line);
    }

    public void setLine(String line) {
        this.line = line;
    }

}
