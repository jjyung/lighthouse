package tw.kgips.util;

import java.math.BigDecimal;

public class ConvertUtil {
	public ConvertUtil() {
	}

	public static int parseIntOrDefault(String s, int defaultVal) {
		if (s != null && s.length() > 0) {
			try {
				return Integer.parseInt(s);
			} catch (Exception var2) {
				return defaultVal;
			}
		} else {
			return defaultVal;
		}
	}

	public static long parseLongOrDefault(String s, long defaultVal) {
		if (s != null && s.length() > 0) {
			try {
				return Long.parseLong(s);
			} catch (Exception var3) {
				return defaultVal;
			}
		} else {
			return defaultVal;
		}
	}

	public static double parseDoubleOrDefault(String s, double defaultVal) {
		if (s != null && s.length() > 0) {
			try {
				return Double.parseDouble(s);
			} catch (Exception var3) {
				return defaultVal;
			}
		} else {
			return defaultVal;
		}
	}

	public static Integer toIntOrDefault(String s, Integer defaultVal) {
		if (s != null && s.length() > 0) {
			try {
				return Integer.parseInt(s);
			} catch (Exception var2) {
				return defaultVal;
			}
		} else {
			return defaultVal;
		}
	}

	public static Long toLongOrDefault(String s, Long defaultVal) {
		if (s != null && s.length() > 0) {
			try {
				return Long.parseLong(s);
			} catch (Exception var2) {
				return defaultVal;
			}
		} else {
			return defaultVal;
		}
	}

	public static Double toDoubleOrDefault(String s, Double defaultVal) {
		if (s != null && s.length() > 0) {
			try {
				return Double.parseDouble(s);
			} catch (Exception var2) {
				return defaultVal;
			}
		} else {
			return defaultVal;
		}
	}

	public static Integer toIntOrDefault(Object obj, Integer defaultVal) {
		try {
			return toInt(obj);
		} catch (Exception var2) {
			return defaultVal;
		}
	}

	public static Long toLongOrDefault(Object obj, Long defaultVal) {
		try {
			return toLong(obj);
		} catch (Exception var2) {
			return defaultVal;
		}
	}

	public static Double toDoubleOrDefault(Object obj, Double defaultVal) {
		try {
			return toDouble(obj);
		} catch (Exception var2) {
			return defaultVal;
		}
	}

	public static Integer toInt(Object obj) {
		if (obj == null) {
			return null;
		} else if (obj instanceof BigDecimal) {
			return ((BigDecimal) obj).intValueExact();
		} else if (obj instanceof Number) {
			return ((Number) obj).intValue();
		} else if (obj instanceof String) {
			return ((String) obj).length() <= 0 ? null : Integer.parseInt((String) obj);
		} else {
			return (Integer) obj;
		}
	}

	public static Integer toIntOrNull(Object obj) {
		try {
			return toInt(obj);
		} catch (Exception var1) {
			return null;
		}
	}

	public static Integer toInt(String s) {
		return s != null && s.length() > 0 ? Integer.parseInt(s) : null;
	}

	public static Integer toIntOrNull(String s) {
		if (s != null && s.length() > 0) {
			try {
				return Integer.parseInt(s);
			} catch (Exception var1) {
				return null;
			}
		} else {
			return null;
		}
	}

	public static Long toLong(Object obj) {
		if (obj == null) {
			return null;
		} else if (obj instanceof Long) {
			return (Long) obj;
		} else if (obj instanceof BigDecimal) {
			return ((BigDecimal) obj).longValueExact();
		} else if (obj instanceof Number) {
			return ((Number) obj).longValue();
		} else if (obj instanceof String) {
			String obj1;
			return (obj1 = (String) obj).length() <= 0 ? null : Long.parseLong(obj1);
		} else {
			return (Long) obj;
		}
	}

	public static Long toLong(String s) {
		return s != null && s.length() > 0 ? Long.parseLong(s) : null;
	}

	public static Long toLongOrNull(Object obj) {
		try {
			return toLong(obj);
		} catch (Exception var1) {
			return null;
		}
	}

	public static Long toLongOrNull(String s) {
		if (s != null && s.length() > 0) {
			try {
				return Long.parseLong(s);
			} catch (Exception var1) {
				return null;
			}
		} else {
			return null;
		}
	}

	public static Double toDouble(Object obj) {
		if (obj == null) {
			return null;
		} else if (obj instanceof Double) {
			return (Double) obj;
		} else if (obj instanceof BigDecimal) {
			return ((BigDecimal) obj).doubleValue();
		} else if (obj instanceof Number) {
			return ((Number) obj).doubleValue();
		} else if (obj instanceof String) {
			String obj1;
			return (obj1 = (String) obj).length() <= 0 ? null : Double.parseDouble(obj1);
		} else {
			return (Double) obj;
		}
	}

	public static Double toDouble(String s) {
		return s != null && s.length() > 0 ? Double.parseDouble(s) : null;
	}

	public static Double toDoubleOrNull(Object obj) {
		try {
			return toDouble(obj);
		} catch (Exception var1) {
			return null;
		}
	}

	public static Double toDoubleOrNull(String s) {
		if (s != null && s.length() > 0) {
			try {
				return Double.parseDouble(s);
			} catch (Exception var1) {
				return null;
			}
		} else {
			return null;
		}
	}

	public static BigDecimal toDecimal(Object o) {
		if (o == null) {
			return null;
		} else if (o instanceof BigDecimal) {
			return (BigDecimal) o;
		} else {
			String o1;
			return (o1 = o.toString()).length() == 0 ? null : new BigDecimal(o1);
		}
	}

	public static String toString(Object o) {
		return o == null ? null : o.toString();
	}

	public static String toString(String s) {
		return s;
	}
}
