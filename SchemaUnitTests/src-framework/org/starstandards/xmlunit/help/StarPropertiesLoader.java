/***************************************************************************
 *  This code is licensed under the Eclipse Public License 1.0.  See
 *  License.txt for more information.
 *   
 *   Contributor(s):
 *   David Carver - Standards for Technology in Automotive Retail - Initial Code, 2007.
 ****************************************************************************/

/*
 * Created on Mar 24, 2005
 *
 * This class contains the methods necessary to retrieve the STAR Path.
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.starstandards.xmlunit.help;
 
/**
 * @author David Carver
 * 
 * 
 */
import java.io.*;
import java.lang.ClassLoader;
import java.util.Properties;
import java.util.*;

public abstract class StarPropertiesLoader {
	private static final boolean THROW_ON_LOAD_FAILURE = true;

	private static final boolean LOAD_AS_RESOURCE_BUNDLE = false;

	private static final String SUFFIX = ".properties";

	/**
	 * 
	 */
	private static Properties loadProperties(String name, ClassLoader loader) {
		if (name == null)
			throw new IllegalArgumentException("null input: name");

		if (name.startsWith("/"))
			name = name.substring(1);

		if (name.endsWith(SUFFIX))
			name = name.substring(0, name.length() - SUFFIX.length());

		Properties result = null;

		InputStream in = null;
		try {
			if (loader == null)
				loader = ClassLoader.getSystemClassLoader();

			if (LOAD_AS_RESOURCE_BUNDLE) {
				name = name.replace('/', '.');

				// Throws MissingResourceException on lookup failures:
				final ResourceBundle rb = ResourceBundle.getBundle(name, Locale
						.getDefault(), loader);

				result = new Properties();
				for (Enumeration keys = rb.getKeys(); keys.hasMoreElements();) {
					final String key = (String) keys.nextElement();
					final String value = rb.getString(key);

					result.put(key, value);
				}
			} else {
				name = name.replace('.', '/');

				if (!name.endsWith(SUFFIX))
					name = name.concat(SUFFIX);

				// Returns null on lookup failures:
				in = loader.getResourceAsStream(name);
				if (in != null) {
					result = new Properties();
					result.load(in); // Can throw IOException
				}
			}
		} catch (Exception e) {
			result = null;
		} finally {
			if (in != null)
				try {
					in.close();
				} catch (Throwable ignore) {
				}
		}

		if (THROW_ON_LOAD_FAILURE && (result == null)) {
			throw new IllegalArgumentException("could not load ["
					+ name
					+ "]"
					+ " as "
					+ (LOAD_AS_RESOURCE_BUNDLE ? "a resource bundle"
							: "a classloader resource"));
		}

		return result;
	}

	/**
	 * A convenience overload of {@link #loadProperties(String, ClassLoader)}
	 * that uses the current thread's context classloader.
	 */
	public static Properties loadProperties(final String name) {
		return loadProperties(name, Thread.currentThread()
				.getContextClassLoader());
	}

	public static String getStarPath() {
		Properties properties = StarPropertiesLoader
				.loadProperties("/org/starstandards/xmlunit/help/mypaths.properties");
		return properties.getProperty("STARPath");
	}
	public static String getStarPath(String starPath) {
		Properties properties = StarPropertiesLoader
				.loadProperties("/org/starstandards/xmlunit/help/mypaths.properties");
		return properties.getProperty(starPath);
	}
	
	
	public static String getStarPathByClassPath(String classPath, String fileName) {
		Properties properties = StarPropertiesLoader
		.loadProperties(classPath + "/" + fileName);
		return properties.getProperty("STARPath");
		
	}

}
