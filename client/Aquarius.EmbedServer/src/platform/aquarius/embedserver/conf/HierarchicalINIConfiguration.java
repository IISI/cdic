package platform.aquarius.embedserver.conf;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.URL;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.set.ListOrderedSet;
import org.apache.commons.configuration.AbstractHierarchicalFileConfiguration;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.HierarchicalConfiguration;
import org.apache.commons.configuration.SubnodeConfiguration;
import org.apache.commons.configuration.tree.ConfigurationNode;
import org.apache.commons.configuration.tree.DefaultConfigurationNode;
import org.apache.commons.configuration.tree.ViewNode;
import org.apache.commons.lang.StringUtils;

/**
 * copy from org.apache.commons.configuration.HierarchicalINIConfiguration
 * 
 * @author Lancelot
 * 
 */
public class HierarchicalINIConfiguration extends AbstractHierarchicalFileConfiguration {
    /**
     * The characters that signal the start of a comment line.
     */
    protected static final String COMMENT_CHARS = "#";

    /**
     * The characters used to separate keys from values.
     */
    protected static final String SEPARATOR_CHARS = "=:";

    /**
     * The serial version UID.
     */
    private static final long serialVersionUID = 2548006161386850670L;

    /**
     * Constant for the line separator.
     */
    private static final String LINE_SEPARATOR = System.getProperty("line.separator");

    /**
     * The line continuation character.
     */
    private static final String LINE_CONT = "\\";

    /**
     * Create a new empty INI Configuration.
     */
    public HierarchicalINIConfiguration() {
        super();
    }

    /**
     * Create and load the ini configuration from the given file.
     * 
     * @param filename
     *            The name pr path of the ini file to load.
     * @throws ConfigurationException
     *             If an error occurs while loading the file
     */
    public HierarchicalINIConfiguration(String filename) throws ConfigurationException {
        super(filename);
    }

    /**
     * Create and load the ini configuration from the given file.
     * 
     * @param file
     *            The ini file to load.
     * @throws ConfigurationException
     *             If an error occurs while loading the file
     */
    public HierarchicalINIConfiguration(File file) throws ConfigurationException {
        super(file);
    }

    /**
     * Create and load the ini configuration from the given url.
     * 
     * @param url
     *            The url of the ini file to load.
     * @throws ConfigurationException
     *             If an error occurs while loading the file
     */
    public HierarchicalINIConfiguration(URL url) throws ConfigurationException {
        super(url);
    }

    /**
     * Save the configuration to the specified writer.
     * 
     * @param writer
     *            - The writer to save the configuration to.
     * @throws ConfigurationException
     *             If an error occurs while writing the configuration
     */
    public void save(Writer writer) throws ConfigurationException {
        PrintWriter out = new PrintWriter(writer);
        Iterator it = getSections().iterator();
        while (it.hasNext()) {
            String section = (String) it.next();
            if (section != null) {
                out.print("[");
                out.print(section);
                out.print("]");
                out.println();
            }

            Configuration subset = getSection(section);
            Iterator keys = subset.getKeys();
            while (keys.hasNext()) {
                String key = (String) keys.next();
                Object value = subset.getProperty(key);
                if (value instanceof Collection) {
                    Iterator values = ((Collection) value).iterator();
                    while (values.hasNext()) {
                        value = (Object) values.next();
                        out.print(key);
                        out.print(" = ");
                        out.print(formatValue(value.toString()));
                        out.println();
                    }
                } else {
                    out.print(key);
                    out.print(" = ");
                    out.print(formatValue(value.toString()));
                    out.println();
                }
            }

            out.println();
        }

        out.flush();
    }

    /**
     * Load the configuration from the given reader. Note that the
     * <code>clear</code> method is not called so the configuration read in will
     * be merged with the current configuration.
     * 
     * @param reader
     *            The reader to read the configuration from.
     * @throws ConfigurationException
     *             If an error occurs while reading the configuration
     */
    public void load(Reader reader) throws ConfigurationException {
        try {
            BufferedReader bufferedReader = new BufferedReader(reader);
            ConfigurationNode sectionNode = getRootNode();

            String line = bufferedReader.readLine();
            while (line != null) {
                line = line.trim();
                if (!isCommentLine(line)) {
                    if (isSectionLine(line)) {
                        String section = line.substring(1, line.length() - 1);
                        sectionNode = getSectionNode(section);
                    }

                    else {
                        String key = "";
                        String value = "";
                        int index = line.indexOf("=");
                        if (index >= 0) {
                            key = line.substring(0, index);
                            value = parseValue(line.substring(index + 1), bufferedReader);
                        } else {
                            index = line.indexOf(":");
                            if (index >= 0) {
                                key = line.substring(0, index);
                                value = parseValue(line.substring(index + 1), bufferedReader);
                            } else {
                                key = line;
                            }
                        }
                        key = key.trim();
                        if (key.length() < 1) {
                            // use space for properties with no key
                            key = " ";
                        }
                        ConfigurationNode node = createNode(key);
                        node.setValue(value);
                        sectionNode.addChild(node);
                    }
                }

                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new ConfigurationException("Unable to load the configuration", e);
        }
    }

    /**
     * Parse the value to remove the quotes and ignoring the comment. Example:
     * 
     * <pre>
     * &quot;value&quot; ; comment -&gt; value
     * </pre>
     * 
     * <pre>
     * 'value' ; comment -&gt; value
     * </pre>
     * 
     * @param val
     *            the value to be parsed
     * @param reader
     *            the reader (needed if multiple lines have to be read)
     * @throws IOException
     *             if an IO error occurs
     */
    private static String parseValue(String val, BufferedReader reader) throws IOException {
        StringBuffer propertyValue = new StringBuffer();
        boolean lineContinues;
        String value = val.trim();

        do {
            boolean quoted = value.startsWith("\"") || value.startsWith("'");
            boolean stop = false;
            boolean escape = false;

            char quote = quoted ? value.charAt(0) : 0;

            int i = quoted ? 1 : 0;

            StringBuffer result = new StringBuffer();
            while (i < value.length() && !stop) {
                char c = value.charAt(i);

                if (quoted) {
                    if ('\\' == c && !escape) {
                        escape = true;
                    } else if (!escape && quote == c) {
                        stop = true;
                    } else if (escape && quote == c) {
                        escape = false;
                        result.append(c);
                    } else {
                        if (escape) {
                            escape = false;
                            result.append('\\');
                        }

                        result.append(c);
                    }
                } else {
                    if (!isCommentChar(c)) {
                        result.append(c);
                    } else {
                        stop = true;
                    }
                }

                i++;
            }

            String v = result.toString();
            if (!quoted) {
                v = v.trim();
                lineContinues = lineContinues(v);
                if (lineContinues) {
                    // remove trailing "\"
                    v = v.substring(0, v.length() - 1).trim();
                }
            } else {
                lineContinues = lineContinues(value, i);
            }
            propertyValue.append(v);

            if (lineContinues) {
                propertyValue.append(LINE_SEPARATOR);
                value = reader.readLine();
            }
        } while (lineContinues && value != null);

        return propertyValue.toString();
    }

    /**
     * Tests whether the specified string contains a line continuation marker.
     * 
     * @param line
     *            the string to check
     * @return a flag whether this line continues
     */
    private static boolean lineContinues(String line) {
        String s = line.trim();
        return s.equals(LINE_CONT)
                || (s.length() > 2 && s.endsWith(LINE_CONT) && Character.isWhitespace(s.charAt(s.length() - 2)));
    }

    /**
     * Tests whether the specified string contains a line continuation marker
     * after the specified position. This method parses the string to remove a
     * comment that might be present. Then it checks whether a line continuation
     * marker can be found at the end.
     * 
     * @param line
     *            the line to check
     * @param pos
     *            the start position
     * @return a flag whether this line continues
     */
    private static boolean lineContinues(String line, int pos) {
        String s;

        if (pos >= line.length()) {
            s = line;
        } else {
            int end = pos;
            while (end < line.length() && !isCommentChar(line.charAt(end))) {
                end++;
            }
            s = line.substring(pos, end);
        }

        return lineContinues(s);
    }

    /**
     * Tests whether the specified character is a comment character.
     * 
     * @param c
     *            the character
     * @return a flag whether this character starts a comment
     */
    private static boolean isCommentChar(char c) {
        return COMMENT_CHARS.indexOf(c) >= 0;
    }

    /**
     * Add quotes around the specified value if it contains a comment character.
     */
    private String formatValue(String value) {
        boolean quoted = false;

        for (int i = 0; i < COMMENT_CHARS.length() && !quoted; i++) {
            char c = COMMENT_CHARS.charAt(i);
            if (value.indexOf(c) != -1) {
                quoted = true;
            }
        }

        if (quoted) {
            return '"' + StringUtils.replace(value, "\"", "\\\"") + '"';
        } else {
            return value;
        }
    }

    /**
     * Determine if the given line is a comment line.
     * 
     * @param line
     *            The line to check.
     * @return true if the line is empty or starts with one of the comment
     *         characters
     */
    protected boolean isCommentLine(String line) {
        if (line == null) {
            return false;
        }
        // blank lines are also treated as comment lines
        return line.length() < 1 || COMMENT_CHARS.indexOf(line.charAt(0)) >= 0;
    }

    /**
     * Determine if the given line is a section.
     * 
     * @param line
     *            The line to check.
     * @return true if the line contains a secion
     */
    protected boolean isSectionLine(String line) {
        if (line == null) {
            return false;
        }
        return line.startsWith("[") && line.endsWith("]");
    }

    /**
     * Return a set containing the sections in this ini configuration. Note that
     * changes to this set do not affect the configuration.
     * 
     * @return a set containing the sections.
     */
    public Set getSections() {
        Set sections = new ListOrderedSet();
        boolean globalSection = false;

        for (Iterator it = getRootNode().getChildren().iterator(); it.hasNext();) {
            ConfigurationNode node = (ConfigurationNode) it.next();
            if (isSectionNode(node)) {
                if (globalSection) {
                    sections.add(null);
                    globalSection = false;
                }
                sections.add(node.getName());
            } else {
                globalSection = true;
            }
        }

        return sections;
    }

    /**
     * Returns a configuration with the content of the specified section. This
     * provides an easy way of working with a single section only. The way this
     * configuration is structured internally, this method is very similar to
     * calling
     * <code>{@link HierarchicalConfiguration#configurationAt(String)}</code>
     * with the name of the section in question. There are the following
     * differences however:
     * <ul>
     * <li>This method never throws an exception. If the section does not exist,
     * an empty configuration is returned.</li>
     * <li>There is special support for the global section: Passing in
     * <b>null</b> as section name returns a configuration with the content of
     * the global section (which may also be empty).</li>
     * </ul>
     * 
     * @param name
     *            the name of the section in question; <b>null</b> represents
     *            the global section
     * @return a configuration containing only the properties of the specified
     *         section
     */
    public SubnodeConfiguration getSection(String name) {
        if (name == null) {
            return getGlobalSection();
        }

        else {
            try {
                return configurationAt(name);
            } catch (IllegalArgumentException iex) {
                // the passed in key does not map to exactly one node
                // return an empty configuration
                return new SubnodeConfiguration(this, new DefaultConfigurationNode());
            }
        }
    }

    /**
     * Obtains the node representing the specified section. This method is
     * called while the configuration is loaded. If a node for this section
     * already exists, it is returned. Otherwise a new node is created.
     * 
     * @param sectionName
     *            the name of the section
     * @return the node for this section
     */
    private ConfigurationNode getSectionNode(String sectionName) {
        List nodes = getRootNode().getChildren(sectionName);
        if (!nodes.isEmpty()) {
            return (ConfigurationNode) nodes.get(0);
        }

        ConfigurationNode node = createNode(sectionName);
        markSectionNode(node);
        getRootNode().addChild(node);
        return node;
    }

    /**
     * Creates a sub configuration for the global section of the represented INI
     * configuration.
     * 
     * @return the sub configuration for the global section
     */
    private SubnodeConfiguration getGlobalSection() {
        ViewNode parent = new ViewNode();

        for (Iterator it = getRootNode().getChildren().iterator(); it.hasNext();) {
            ConfigurationNode node = (ConfigurationNode) it.next();
            if (!isSectionNode(node)) {
                parent.addChild(node);
            }
        }

        return createSubnodeConfiguration(parent);
    }

    /**
     * Marks a configuration node as a section node. This means that this node
     * represents a section header. This implementation uses the node's
     * reference property to store a flag.
     * 
     * @param node
     *            the node to be marked
     */
    private static void markSectionNode(ConfigurationNode node) {
        node.setReference(Boolean.TRUE);
    }

    /**
     * Checks whether the specified configuration node represents a section.
     * 
     * @param node
     *            the node in question
     * @return a flag whether this node represents a section
     */
    private static boolean isSectionNode(ConfigurationNode node) {
        return node.getReference() != null || node.getChildrenCount() > 0;
    }
}
