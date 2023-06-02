package de.romanamo.fractolio.model.color;

/**
 * <h1>BiColorMap</h1>
 * <P>
 * The interface BiColorMap specifies a color mapping of 2 <code>int</code> values to an <code>int</code> RGB-value.
 * </p>
 */
public interface BiColorMap {

    /**
     * Maps 2 <code>int</code> values to an <code>int</code> RGB-value.
     *
     * @param a first <code>int</code> value
     * @param b second <code>int</code> value
     * @return the mapped <code>int</code> RGB-value
     */
    int map(int a, int b);
}
