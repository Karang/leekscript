package leekscript.compiler.instruction;

import leekscript.compiler.AIFile;
import leekscript.compiler.JavaWriter;
import leekscript.compiler.WordCompiler;
import leekscript.compiler.bloc.MainLeekBlock;
import leekscript.compiler.expression.AbstractExpression;

public class LeekReturnInstruction implements LeekInstruction {

	private final AIFile<?> mAI;
	private final int mLine;
	private AbstractExpression mExpression = null;

	public LeekReturnInstruction(int count, AbstractExpression exp, int line, AIFile<?> ai) {
		mExpression = exp;
		mLine = line;
		mAI = ai;
	}

	@Override
	public String getCode() {
		return "return " + (mExpression == null ? "null" : mExpression.getString()) + ";";
	}

	@Override
	public void writeJavaCode(MainLeekBlock mainblock, JavaWriter writer) {
		writer.addCode("return ");
		if (mExpression == null)
			writer.addCode("LeekValueManager.NULL;");
		else {
			mExpression.writeJavaCode(mainblock, writer);
			writer.addLine(";", mLine, mAI);
		}
	}

	@Override
	public int getEndBlock() {
		return 1;
	}

	@Override
	public boolean putCounterBefore() {
		return true;
	}

	@Override
	public void analyze(WordCompiler compiler) {
		if (mExpression != null) {
			mExpression.analyze(compiler);
		}
	}

	@Override
	public int getOperations() {
		return 0;
	}
}
