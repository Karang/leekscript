package leekscript.compiler;

import java.util.ArrayList;

public class JavaWriter {
	private final StringBuilder mCode;
	private int mLine;
	private final ArrayList<Line> mLines;
	private final boolean mWithDebug;

	private class Line {
		private final int mJavaLine;
		private final int mCodeLine;
		private final AIFile<?> mAI;

		public Line(int java_line, int code_line, AIFile<?> ai) {
			mJavaLine = java_line;
			mCodeLine = code_line;
			mAI = ai;
		}
	}

	public JavaWriter(boolean debug) {
		mCode = new StringBuilder();
		mLines = new ArrayList<Line>();
		mLine = 1;
		mWithDebug = debug;
	}

	public boolean hasDebug() {
		return mWithDebug;
	}

	public void addLine(String datas, int line, AIFile<?> ai) {
		mCode.append(datas).append("\n");
		mLines.add(new Line(mLine, line, ai));
		mLine++;
	}

	public void addLine(String datas) {
		mCode.append(datas).append("\n");
		mLine++;
	}

	public void addCode(String datas) {
		mCode.append(datas);
	}

	public String getJavaCode() {
		return mCode.toString();
	}

	public String escape(String string) {
		String str = "";
		for (int i = 0; i < string.length(); i++) {
			if (string.charAt(i) == '\n')
				str += "\\n";
			else if (string.charAt(i) == '"')
				str += "\\\"";
			else if (string.charAt(i) == '\\') {
				if (string.charAt(i + 1) == 'n')
					str += "\\";
				else if (string.charAt(i + 1) == 't')
					str += "\\";
				else
					str += "\\\\";
			}
			else
				str += string.charAt(i);
		}
		return str;
	}

	public void writeErrorFunction(IACompiler comp, String ai) {
		mCode.append("protected String[] getErrorString(){ return new String[]{");
		boolean first = true;
		for (Line l : mLines) {
			if (!first)
				mCode.append(",");
			else
				first = false;
			mCode.append("\"[").append(l.mJavaLine).append(",\\\"").append(escape(l.mAI.getPath())).append("\\\",").append(l.mCodeLine).append("]\"");
		}
		mCode.append("};}\n protected String getAItring(){ return \"");
		mCode.append(escape(ai));
		mCode.append("\";}");
	}

	public void addCounter(int id) {
		addCode("mUAI.addOperations(1);");
	}
}
