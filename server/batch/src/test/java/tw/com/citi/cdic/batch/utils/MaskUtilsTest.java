package tw.com.citi.cdic.batch.utils;

import org.testng.annotations.Test;

/**
 * @author Chih-Liang Chang
 * @since 2010/11/22
 */
public class MaskUtilsTest {

    @Test
    public void testMaskWithWrongSplitPos() {
        String str = "abcdefg";
        String result = MaskUtils.mask(str, 20);
        assert str.equals(result);
    }

    @Test
    public void testMask() {
        String str = "A123456789";
        String result = MaskUtils.mask(str, 6);
        assert "A12345XXXX".equals(result) : "Masked result = " + result;
    }

}
