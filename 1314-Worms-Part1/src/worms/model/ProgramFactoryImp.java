package worms.model;

import java.util.List;

import worms.model.expressions.*;
import worms.model.programs.ProgramFactory;
import worms.model.statements.*;
import worms.model.types.*;

/**
 * Implementation of ProgramFactory..

 * @version 1.0
 * @author 	Kristof Achten <kristof.achten@student.kuleuven.be>
 * GitHub: https://github.com/Divyak156/OGPProject.git
 * StudentNr: r0462748 - 1ste Bachelor informatica
 *
 */

public class ProgramFactoryImp implements ProgramFactory<Expression, Statement, Type>{

	@Override
	public Expression createDoubleLiteral(int line, int column, double d) {
		return new DoubleLit(d);
	}

	@Override
	public Expression createBooleanLiteral(int line, int column, boolean b) {
		return new BooleanLit(b);
	}

	@Override
	public Expression createAnd(int line, int column, Expression e1,
			Expression e2) {
		return new AndExp(e1, e2);
	}

	@Override
	public Expression createOr(int line, int column, Expression e1,
			Expression e2) {
		return new OrExp(e1, e2);
	}

	@Override
	public Expression createNot(int line, int column, Expression e) {
		return new NotExp(e);
	}

	@Override
	public Expression createNull(int line, int column) {
		return new NullLit();
	}

	@Override
	public Expression createSelf(int line, int column) {
		return new WormSelf();
	}

	@Override
	public Expression createGetX(int line, int column, Expression e) {
		return new GetX(e);
	}

	@Override
	public Expression createGetY(int line, int column, Expression e) {
		return new GetY(e);
	}

	@Override
	public Expression createGetRadius(int line, int column, Expression e) {
		return new GetRadius(e);
	}

	@Override
	public Expression createGetDir(int line, int column, Expression e) {
		return new GetDirection(e);
	}

	@Override
	public Expression createGetAP(int line, int column, Expression e) {
		return new GetAP(e);
	}

	@Override
	public Expression createGetMaxAP(int line, int column, Expression e) {
		return new GetMaxAP(e);
	}

	@Override
	public Expression createGetHP(int line, int column, Expression e) {
		return new GetHP(e);
	}

	@Override
	public Expression createGetMaxHP(int line, int column, Expression e) {
		return new GetMaxHP(e);
	}

	@Override
	public Expression createSameTeam(int line, int column, Expression e) {
		return new SameTeam(e);
	}

	@Override
	public Expression createSearchObj(int line, int column, Expression e) {
		return new SearchObject(e);
	}

	@Override
	public Expression createIsWorm(int line, int column, Expression e) {
		return new IsWorm(e);
	}

	@Override
	public Expression createIsFood(int line, int column, Expression e) {
		return new IsFood(e);
	}

	@Override
	public Expression createVariableAccess(int line, int column, String name) {
		return new VariableAccess(name);
	}

	@Override
	public Expression createVariableAccess(int line, int column, String name,
			Type type) {
		return null;
	}

	@Override
	public Expression createLessThan(int line, int column, Expression e1,
			Expression e2) {
		return new SmallerThan(e1, e2);
	}

	@Override
	public Expression createGreaterThan(int line, int column, Expression e1,
			Expression e2) {
		return new BiggerThan(e1, e2);
	}

	@Override
	public Expression createLessThanOrEqualTo(int line, int column,
			Expression e1, Expression e2) {
		return new SmallerOrEqual(e1, e2);
	}

	@Override
	public Expression createGreaterThanOrEqualTo(int line, int column,
			Expression e1, Expression e2) {
		return new BiggerOrEqual(e1, e2);
	}

	@Override
	public Expression createEquality(int line, int column, Expression e1,
			Expression e2) {
		return new Equals(e1, e2);
	}

	@Override
	public Expression createInequality(int line, int column, Expression e1,
			Expression e2) {
		return new NotEquals(e1, e2);
	}

	@Override
	public Expression createAdd(int line, int column, Expression e1,
			Expression e2) {
		return new Addition(e1, e2);
	}

	@Override
	public Expression createSubtraction(int line, int column, Expression e1,
			Expression e2) {
		return new Subtraction(e1, e1);
	}

	@Override
	public Expression createMul(int line, int column, Expression e1,
			Expression e2) {
		return new Multiplication(e1,  e2);
	}

	@Override
	public Expression createDivision(int line, int column, Expression e1,
			Expression e2) {
		return new Division(e1, e2);
	}

	@Override
	public Expression createSqrt(int line, int column, Expression e) {
		return new Sqrt(e);
	}

	@Override
	public Expression createSin(int line, int column, Expression e) {
		return null; // Not for students that work alone.
	}

	@Override
	public Expression createCos(int line, int column, Expression e) {
		return null; // Not for students that work alone.
	}

	@Override
	public Statement createTurn(int line, int column, Expression angle) {
		return new Turn(angle);
	}

	@Override
	public Statement createMove(int line, int column) {
		return new Move();
	}

	@Override
	public Statement createJump(int line, int column) {
		return new Jump();
	}

	@Override
	public Statement createToggleWeap(int line, int column) {
		return new ToggleWeapon();
	}

	@Override
	public Statement createFire(int line, int column, Expression yield) {
		return new Fire(yield);
	}

	@Override
	public Statement createSkip(int line, int column) {
		return new Skip();
	}

	@Override
	public Statement createAssignment(int line, int column,
			String variableName, Expression rhs) {
		return new Assign(variableName, rhs);
	}

	@Override
	public Statement createIf(int line, int column, Expression condition,
			Statement then, Statement otherwise) {
		return new If(condition, then, otherwise);
	}

	@Override
	public Statement createWhile(int line, int column, Expression condition,
			Statement body) {
		return new While(condition, body);
	}

	@Override
	public Statement createForeach(int line, int column,
			worms.model.programs.ProgramFactory.ForeachType type,
			String variableName, Statement body) {
		return new ForEach(type, variableName, body);
	}

	@Override
	public Statement createSequence(int line, int column,
			List<Statement> statements) {
		return new Sequence(statements);
	}

	@Override
	public Statement createPrint(int line, int column, Expression e) {
		return new Print(e);
	}

	@Override
	public Type createDoubleType() {
		return new DoubleSort();
	}

	@Override
	public Type createBooleanType() {
		return new BooleanSort();
	}

	@Override
	public Type createEntityType() {
		return new EntitySort<>();
	}
	

}
