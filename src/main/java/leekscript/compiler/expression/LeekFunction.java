package leekscript.compiler.expression;

import java.util.ArrayList;

import leekscript.compiler.JavaWriter;
import leekscript.compiler.WordCompiler;
import leekscript.compiler.bloc.FunctionBlock;
import leekscript.compiler.bloc.MainLeekBlock;
import leekscript.compiler.exceptions.LeekCompilerException;
import leekscript.runner.ILeekFunction;
import leekscript.runner.LeekFunctions;

public class LeekFunction extends AbstractExpression {

	private final boolean isClass;
	private final String mName;
	private final ArrayList<AbstractExpression> mParameters = new ArrayList<AbstractExpression>();

	public LeekFunction(String name, boolean isClass) {
		mName = name;
		this.isClass = isClass;
	}

	public void addParameter(AbstractExpression param) {
		mParameters.add(param);
	}

	@Override
	public int getType() {
		return FUNCTION;
	}

	@Override
	public String getString() {
		String str = mName + "(";
		for (int i = 0; i < mParameters.size(); i++) {
			if (i > 0)
				str += ", ";
			str += mParameters.get(i).getString();
		}
		return str + ")";
	}

	@Override
	public boolean validExpression(WordCompiler compiler, MainLeekBlock mainblock) throws LeekExpressionException {
		if (!isClass) {
			int nb_params = LeekFunctions.isFunction(mName);
			if (nb_params == -1) {
				nb_params = mainblock.getUserFunctionParametersCount(mName);
				if (nb_params == -1) {
					throw new LeekExpressionException(this, LeekCompilerException.FUNCTION_NOT_EXISTS);
				} else if (mParameters.size() != nb_params) {
					throw new LeekExpressionException(this, LeekCompilerException.INVALID_PAREMETER_COUNT);
				}
			}
			else {
				ILeekFunction f = LeekFunctions.getValue(mName);
				if (mParameters.size() > nb_params || mParameters.size() < f.getArgumentsMin())
					throw new LeekExpressionException(this, LeekCompilerException.INVALID_PAREMETER_COUNT);
			}
		}

		// Vérification de chaque paramètre
		for (AbstractExpression parameter : mParameters) {
			parameter.validExpression(compiler, mainblock);
		}
		return true;
	}

	@Override
	public void writeJavaCode(MainLeekBlock mainblock, JavaWriter writer) {
		if (isClass) {
			writer.addCode("user_" + mName + ".executeFunction(mUAI, new AbstractLeekValue[] {");
			for (int i = 0; i < mParameters.size(); i++) {
				if (i > 0)
					writer.addCode(", ");
				mParameters.get(i).writeJavaCode(mainblock, writer);
			}
			writer.addCode("})");
			return;
		}
		if (mainblock.isRedefinedFunction(mName)) {
			writer.addCode("rfunction_" + mName);
			writer.addCode(".executeFunction(mUAI, new AbstractLeekValue[]{");
			for (int i = 0; i < mParameters.size(); i++) {
				if (i > 0)
					writer.addCode(", ");
				if (i < mParameters.size())
					mParameters.get(i).writeJavaCode(mainblock, writer);
				else
					writer.addCode("LeekValueManager.NULL");
			}
			writer.addCode("})");
		}
		else {
			FunctionBlock user_function = mainblock.getUserFunction(mName);
			if (user_function != null) {
				writer.addCode("user_function_" + mName + "(");

				for (int i = 0; i < mParameters.size(); i++) {
					if (i > 0)
						writer.addCode(", ");
					if (user_function.isReference(i))
						mParameters.get(i).writeJavaCode(mainblock, writer);
					else {
						writer.addCode("LeekOperations.clone(mUAI, ");
						mParameters.get(i).writeJavaCode(mainblock, writer);
						writer.addCode(".getValue())");
					}
				}
				writer.addCode(")");
			}
			else {
				ILeekFunction fun = LeekFunctions.getValue(mName);
				int nb_params = fun.getArguments();
				if (!writer.hasDebug() && (mName.equals("debug") || mName.equalsIgnoreCase("mark") || mName.equals("pause"))) {
					writer.addCode("nothing(LeekValueManager.NULL)");
					return;
				}
				writer.addCode("LeekFunctions.executeFunction(mUAI, " + fun.getNamespace() + "." + mName + ", new AbstractLeekValue[]{");
				for (int i = 0; i < nb_params; i++) {
					if (i > 0)
						writer.addCode(", ");
					if (i < mParameters.size()) {
						mParameters.get(i).writeJavaCode(mainblock, writer);
						writer.addCode(".getValue()");
					}
					else
						writer.addCode("LeekValueManager.NULL");
				}
				writer.addCode("}, " + mParameters.size() + ")");
			}
		}
	}

	@Override
	public void analyze(WordCompiler compiler) {

	}
}
