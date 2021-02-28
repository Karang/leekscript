package leekscript.compiler.expression;

import leekscript.compiler.JavaWriter;
import leekscript.compiler.WordCompiler;
import leekscript.compiler.bloc.FunctionBlock;
import leekscript.compiler.bloc.MainLeekBlock;

public class LeekFunctionValue extends AbstractExpression {

	private final String mFunctionName;
	private final String mFunctionNamespace;

	public LeekFunctionValue(String funcname, String namespace) {
		mFunctionName = funcname;
		mFunctionNamespace = namespace;
	}

	@Override
	public int getType() {
		return 0;
	}

	@Override
	public String getString() {
		return "Fonction[" + mFunctionName + "]";
	}

	@Override
	public void writeJavaCode(MainLeekBlock mainblock, JavaWriter writer) {
		// return "Fonction[" + mFunctionName + "]";
		// FunctionLeekValue
		if (mainblock.isRedefinedFunction(mFunctionName)) {
			writer.addCode("rfunction_" + mFunctionName);
		} else {
			FunctionBlock user_function = mainblock.getUserFunction(mFunctionName);
			if (user_function != null) {
				writer.addCode("new FunctionLeekValue(" + user_function.getId() + ")");
			} else {
				writer.addCode("LeekValueManager.getFunction(" + mFunctionNamespace + "." + mFunctionName + ")");
			}
		}
	}

	@Override
	public boolean validExpression(WordCompiler compiler, MainLeekBlock mainblock) throws LeekExpressionException {
		return true;
	}

	public String getFunctionName() {
		return mFunctionName;
	}

	@Override
	public void analyze(WordCompiler compiler) {

	}
}
