package tw.com.citi.cdic.batch.item;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.batch.item.file.transform.AbstractLineTokenizer;
import org.springframework.batch.item.file.transform.IncorrectLineLengthException;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.batch.item.file.transform.RangeArrayPropertyEditor;

public class Big5FixedLengthTokenizer extends AbstractLineTokenizer {

    private Range[] ranges;

    private int maxRange = 0;

    boolean open = false;

    /**
     * Set the column ranges. Used in conjunction with the
     * {@link RangeArrayPropertyEditor} this property can be set in the form of
     * a String describing the range boundaries, e.g. "1,4,7" or "1-3,4-6,7" or
     * "1-2,4-5,7-10". If the last range is open then the rest of the line is
     * read into that column (irrespective of the strict flag setting).
     * 
     * @see #setStrict(boolean)
     * 
     * @param ranges
     *            the column ranges expected in the input
     */
    public void setColumns(Range[] ranges) {
        this.ranges = Arrays.asList(ranges).toArray(new Range[ranges.length]);
        calculateMaxRange(ranges);
    }

    /*
     * Calculate the highest value within an array of ranges. The ranges aren't
     * necessarily in order. For example: "5-10, 1-4,11-15". Furthermore, there
     * isn't always a min and max, such as: "1,4-20, 22"
     */
    private void calculateMaxRange(Range[] ranges) {
        if (ranges == null || ranges.length == 0) {
            maxRange = 0;
            return;
        }

        open = false;
        maxRange = ranges[0].getMin();

        for (int i = 0; i < ranges.length; i++) {
            int upperBound;
            if (ranges[i].hasMaxValue()) {
                upperBound = ranges[i].getMax();
            } else {
                upperBound = ranges[i].getMin();
                if (upperBound > maxRange) {
                    open = true;
                }
            }

            if (upperBound > maxRange) {
                maxRange = upperBound;
            }
        }
    }

    /**
     * Yields the tokens resulting from the splitting of the supplied
     * <code>line</code>.
     * 
     * @param line
     *            the line to be tokenised (can be <code>null</code>)
     * 
     * @return the resulting tokens (empty if the line is null)
     * @throws IncorrectLineLengthException
     *             if line length is greater than or less than the max range
     *             set.
     */
    protected List<String> doTokenize(String line) {
        List<String> tokens = new ArrayList<String>(ranges.length);
        int lineLength = 0;
        String token = "";

        try {
            lineLength = line.getBytes("ms950").length;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        if (lineLength < maxRange && isStrict()) {
            throw new IncorrectLineLengthException("Line is shorter than max range " + maxRange, maxRange, lineLength);
        }

        if (!open && lineLength > maxRange && isStrict()) {
            throw new IncorrectLineLengthException("Line is longer than max range " + maxRange, maxRange, lineLength);
        }

        byte[] realToken;
        for (int i = 0; i < ranges.length; i++) {

            int startPos = ranges[i].getMin() - 1;
            int endPos = ranges[i].getMax();
            if (lineLength >= endPos) {
                realToken = new byte[endPos - startPos];
                try {
                    System.arraycopy(line.getBytes("ms950"), startPos, realToken, 0, endPos - startPos);
                    token = new String(realToken, "ms950");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            } else if (lineLength >= startPos) {
                realToken = new byte[lineLength - startPos];
                try {
                    System.arraycopy(line.getBytes("ms950"), startPos, realToken, 0, lineLength - startPos);
                    token = new String(realToken, "ms950");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            } else {
                token = "";
            }

            tokens.add(token);
        }

        return tokens;
    }
}
