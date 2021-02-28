package leekscript.compiler.expression;

import leekscript.compiler.JavaWriter;
import leekscript.compiler.WordCompiler;
import leekscript.compiler.bloc.MainLeekBlock;

public class LeekParenthesis extends AbstractExpression {

	private final AbstractExpression mExpression;

	public LeekParenthesis(AbstractExpression exp) {
		mExpression = exp;
	}

	@Override
	public int getType() {
		return 0;
	}

	@Override
	public AbstractExpression trim() {
		return mExpression;
	}

	@Override
	public String getString() {
		return mExpression.getString();
	}

	@Override
	public void writeJavaCode(MainLeekBlock mainblock, JavaWriter writer) {
		mExpression.writeJavaCode(mainblock, writer);
	}

	@Override
	public boolean validExpression(WordCompiler compiler, MainLeekBlock mainblock) throws LeekExpressionException {
		return mExpression.validExpression(compiler, mainblock);
	}

	@Override
	public void analyze(WordCompiler compiler) {
		mExpression.analyze(compiler);
	}
}
