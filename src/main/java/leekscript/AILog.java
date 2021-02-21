package leekscript;

import java.io.UnsupportedEncodingException;

import com.alibaba.fastjson.JSONArray;

public class AILog {

	public final static int STANDARD = 1;
	public final static int WARNING = 2;
	public final static int ERROR = 3;
	public final static int SSTANDARD = 6;
	public final static int SWARNING = 7;
	public final static int SERROR = 8;

	// Clés
	public static final String DEPRECATED_FUNCTION = "deprecated_function";
	public static final String UNKNOWN_FUNCTION = "unknown_function";
	public static final String DIVISION_BY_ZERO = "division_by_zero";
	public static final String CAN_NOT_EXECUTE_VALUE = "can_not_execute_value";
	public static final String CAN_NOT_EXECUTE_WITH_ARGUMENTS = "can_not_execute_with_arguments";
	public static final String NO_AI_EQUIPPED = "no_ai_equipped";
	public static final String INVALID_AI = "invalid_ai";
	public static final String CAN_NOT_COMPILE_AI = "can_not_compile_ai";
	public static final String AI_DISABLED = "ai_disabled";
	public static final String AI_INTERRUPTED = "ai_interrupted";
	public static final String AI_TIMEOUT = "ai_timeout";
	public static final String CODE_TOO_LARGE = "code_too_large";
	public static final String CODE_TOO_LARGE_FUNCTION = "code_too_large_function";
	public static final String NUMBER_OF_OPERATIONS = "number_of_operations";
	public static final String UNKNOWN_METHOD = "unknown_method";
	public static final String UNKNOWN_STATIC_METHOD = "unknown_static_method";
	public static final String STRING_METHOD_MUST_RETURN_STRING = "string_method_must_return_string";
	public static final String UNKNOWN_FIELD = "unknown_field";
	public static final String UNKNOWN_CONSTRUCTOR = "unknown_constructor";

	public interface Stream {
		public void write(JSONArray a);
	}

	private int mSize = 0;
	private final static int MAX_LENGTH = 500000;
	protected Stream stream;

	public AILog() {
		this.stream = new Stream() {
			@Override
			public void write(JSONArray a) {
				System.out.println(a.toString());
			}
		};
	}

	public void addSystemLog(int type, String trace, String key, String[] parameters) {

		int paramSize = 0;
		if (parameters != null) {
			for (String p : parameters) {
				paramSize += p.length();
			}
		}

		if (!addSize(20 + trace.length() + key.length() + paramSize)) {
			return;
		}

		JSONArray obj = new JSONArray();
		obj.add(0);
		obj.add(type);
		obj.add(trace);
		obj.add(key);
		if (parameters != null)
			obj.add(parameters);

		stream.write(obj);
	}

	public void addLog(int type, String message) {
		message = message.replaceAll("\t", "    ");
		addLog(type, message, 0);
	}

	public void addLog(int type, String message, int color) {

		if (message == null || !addSize(20 + message.length())) {
			return;
		}
		JSONArray obj = new JSONArray();
		obj.add(0);
		obj.add(type);
		try {
			obj.add(new String(message.getBytes("UTF-8"), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			ErrorManager.exception(e);
		}
		if (color != 0) {
			obj.add(color);
		}
		stream.write(obj);
	}

	public boolean addSize(int size) {
		if (mSize + size > MAX_LENGTH) {
			return false;
		}
		mSize += size;
		return true;
	}
}
