package tw.com.citi.cdic.batch.item;

import java.io.UnsupportedEncodingException;
import java.util.Locale;

import org.springframework.batch.item.file.transform.FormatterLineAggregator;
import org.springframework.util.Assert;

import tw.com.citi.cdic.batch.utils.Formatter;

public class Big5FormatterLineAggregator<T> extends FormatterLineAggregator<T> {

    private String format;

    private Locale locale = Locale.getDefault();

    private int maximumLength = 0;

    private int minimumLength = 0;

    /**
     * Public setter for the minimum length of the formatted string. If this is
     * not set the default is to allow any length.
     * 
     * @param minimumLength
     *            the minimum length to set
     */
    public void setMinimumLength(int minimumLength) {
        this.minimumLength = minimumLength;
    }

    /**
     * Public setter for the maximum length of the formatted string. If this is
     * not set the default is to allow any length.
     * 
     * @param maximumLength
     *            the maximum length to set
     */
    public void setMaximumLength(int maximumLength) {
        this.maximumLength = maximumLength;
    }

    /**
     * Set the format string used to aggregate items.
     * 
     * @see Formatter
     */
    public void setFormat(String format) {
        this.format = format;
    }

    /**
     * Public setter for the locale.
     * 
     * @param locale
     *            the locale to set
     */
    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    @Override
    protected String doAggregate(Object[] fields) {

        Assert.notNull(format);

        String value = new Formatter(locale).format(format, fields).toString();

        try {
            if (maximumLength > 0) {
                Assert.state(value.getBytes("ms950").length <= maximumLength, String.format(
                        "String overflowed in formatter -" + " longer than %d characters: [%s", maximumLength, value));
            }

            if (minimumLength > 0) {
                Assert.state(value.getBytes("ms950").length >= minimumLength, String.format(
                        "String underflowed in formatter -" + " shorter than %d characters: [%s", minimumLength, value));
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return value;
    }
}
