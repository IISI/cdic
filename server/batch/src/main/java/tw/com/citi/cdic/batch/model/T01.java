package tw.com.citi.cdic.batch.model;

/**
 * <pre>
 * T01, CDICT01 Layout
 * T04, CDICT04 Layout
 * T06, CDICT06 Layout
 * T08, CDICT08 Layout
 * T09, CDICT09 Layout
 * T10, CDICT10 Layout
 * T11, CDICT11 Layout
 * T12, CDICT12 Layout
 * T13, CDICT13 Layout
 * T14, CDICT14 Layout
 * T18, CDICT18 Layout
 * T19, CDICT19 Layout
 * T20, CDICT20 Layout
 * T21, CDICT21 Layout
 * T27, Layout
 * </pre>
 * 
 * @author Lancelot
 * @since 2010/10/7
 */
public class T01 {
    private String code;
    private String description;

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
