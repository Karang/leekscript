package test;

public class TestArray extends TestCommon {

	public void run() throws Exception {

		section("Array.constructor()");
		code_v3_("return Array").equals("<class Array>");
		code_v3_("return Array()").equals("[]");
		code_v3_("return new Array").equals("[]");
		code_v3_("return new Array()").equals("[]");
		code_v3_("return new Array() + 1").equals("[1]");
		code("return [];").equals("[]");
		code("return [1];").equals("[1]");
		code("return [1, 2, 3];").equals("[1, 2, 3]");
		// DISABLED_code("[1l, 2l, 3l]").equals("[1, 2, 3]");
		code_v1("return [1.21, -5, 4.55, 12, -6.7];").equals("[1,21, -5, 4,55, 12, -6,7]");
		code_v2_("return [1.21, -5, 4.55, 12, -6.7];").equals("[1.21, -5, 4.55, 12, -6.7]");
		code("return [true, false, true];").equals("[true, false, true]");
		code_v2_("return [23, true, 'salut', {}, 123]").equals("[23, true, salut, {}, 123]");
		// DISABLED_code("var a = x -> x [1, 2, a]").equals("[1, 2, <function>]");
		// DISABLED_code("[1m, 34324234m, 231232131232132134379897874534243257343341432423m]").equals("[1, 34324234, 231232131232132134379897874534243257343341432423]");
		// DISABLED_code("[true, 'hello', 231232131232132134379897874534243257343341432423m]").equals("[true, 'hello', 231232131232132134379897874534243257343341432423]");

		section("Array & variables");
		code("var a = []; return a;").equals("[]");
		code_v1("var a = @[]; return a;").equals("[]");
		code_v1("var a = [1, 2, 3] var b = a push(b, 4) return a;").equals("[1, 2, 3]");
		code_v1("var a = [1, 2, 3] var b = (function() { return @a })() push(b, 4) return a;").equals("[1, 2, 3, 4]");

		section("Array.operator +");
		code("return [1, 2, 3] + [4, 5, 6];").equals("[1, 2, 3, 4, 5, 6]");
		code("return [] + 1;").equals("[1]");
		code("return [] + 1 + 2 + 3;").equals("[1, 2, 3]");
		code("return [1] + 2;").equals("[1, 2]");
		code_v1("return [1] + 0.5;").equals("[1, 0,5]");
		code_v2_("return [1] + 0.5;").equals("[1, 0.5]");
		// code_v1("return [0.5] + 'a';").equals("[0,5, a]");
		// code_v2_("return [0.5] + 'a';").equals("[0.5, a]");
		code("return ['a'] + ['b'];").equals("[a, b]");
		// code_v1("return [1] + 0.5 + 'a';").equals("[1, 0,5, a]");
		// code_v2_("return [1] + 0.5 + 'a';").equals("[1, 0.5, a]");
		// code_v1("return [1] + 0.5 + 'a' + 'b';").equals("[1, 0,5, a, b]");
		// code_v2_("return [1] + 0.5 + 'a' + 'b';").equals("[1, 0.5, a, b]");
		code("return [1] + null;").equals("[1, null]");
		code("return [1] + true;").equals("[1, true]");
		code("return [1] + [2] + [3];").equals("[1, 2, 3]");
		code_v1("return [1] + [2.5] + ['a'];").equals("[1, 2,5, a]");
		code_v2_("return [1] + [2.5] + ['a'];").equals("[1, 2.5, a]");
		code_v1("return ['a'] + [2.5] + [1];").equals("[a, 2,5, 1]");
		code_v2_("return ['a'] + [2.5] + [1];").equals("[a, 2.5, 1]");
		code("return [1] + ['a'];").equals("[1, a]");
		code("return ['a'] + [1];").equals("[a, 1]");
		// DISABLED_code("return [12.5] + null;").equals("[12.5, null]");
		// DISABLED_code("var a = ['yo'] return a + null;").equals("['yo', null]");
		// DISABLED_code("return [12.5] + true;").equals("[12.5, true]");
		// DISABLED_code("return ['yo'] + true;").equals("['yo', true]");
		// DISABLED_code("var a = ['yo'] return a + true;").equals("['yo', true]");
		// DISABLED_code("return [15.5] + [12, ''][0];").equals("[15.5, 12]");
		// DISABLED_code("var a = [15.5] return a + [12, ''][0];").equals("[15.5, 12]");
		// DISABLED_code("var a = [1] return a + [12, ''][0];").equals("[1, 12]");
		// DISABLED_code("return ['yo'] + '!';").equals("['yo', '!']");
		// DISABLED_code("var a = ['yo'] return a + '!';").equals("['yo', '!']");
		code("var a = [5] var b = ['b'] return a + b;").equals("[5, b]");
		code("var a = ['a'] return a + ['b'];").equals("[a, b]");
		code_v2_("return [1, 2] + {}").equals("[1, 2, {}]");
		code_v2_("var a = [1, 2] return a + {};").equals("[1, 2, {}]");
		code_v2_("return ['a', 'b'] + {}").equals("[a, b, {}]");
		code_v2_("var a = ['a', 'b'] return a + {}").equals("[a, b, {}]");
		// DISABLED_code("return ['a', 'b'] + (x -> x)").equals("['a', 'b', <function>]");
		// DISABLED_code("var a = ['a', 'b'] a + (x -> x)").equals("['a', 'b', <function>]");
		// DISABLED_code("['a', 'b'] + Number").equals("['a', 'b', <class Number>]");
		// DISABLED_code("var a = ['a', 'b'] a + Number").equals("['a', 'b', <class Number>]");
		// DISABLED_code("var a = [1, 2] return a + [3, 4];").equals("[1, 2, 3, 4]");
		// DISABLED_code("var a = ['a'] return [3.5, 4.6] + a;").equals("[3.5, 4.6, 'a']");
		// DISABLED_code("var pq = [] pq = pq + 1 return pq;").equals("[1]");
		// DISABLED_code("[1l] + 12l").equals("[1, 12]");
		// DISABLED_code("[] + 12l").equals("[12]");

		section("null array");
		code("var a = null return a[1]").equals("null");
		code("var a = null return a['a']").equals("null");
		code("var a = null return a[1] = 12").equals("null");

		section("Array misc");
		code("return [1, 2, 3] + null").equals("[1, 2, 3, null]");
		code("var a = [1] return a % 2").equals("1");
		code_v1("var a = [[1], [1]]; return (a[0] + a[2]) / 2").equals("1");
		code_v2_("var a = [[1], [1]]; return (a[0] + a[2]) / 2").equals("1.0");
		code_v1("var effects = [[1], [1]];\n\nreturn (effects[0] + effects[2]) /2;").equals("1");
		code_v2_("var effects = [[1], [1]];\n\nreturn (effects[0] + effects[2]) /2;").equals("1.0");

		section("Array.operator []");
		code("return [1, 2, 3][1]").equals("2");
		code("var a = [1, 2, 3] return a[0]").equals("1");
		code_v1("var a = [1.6, 2.5, 3.4] return a[0]").equals("1,6");
		code_v2_("var a = [1.6, 2.5, 3.4] return a[0]").equals("1.6");
		code("var a = [1, 2, 3] a[0] = 5 return a[0]").equals("5");
		// code("var a = [23, 23, true, '', [], 123]; return |a|").equals("6");
		code("var a = [] return !a").equals("true");
		code("var a = [1, 2, 3] a[1] = 12 return a").equals("[1, 12, 3]");
		code_v1("return [1.2, 321.42, 23.15]").equals("[1,2, 321,42, 23,15]");
		code_v2_("return [1.2, 321.42, 23.15]").equals("[1.2, 321.42, 23.15]");
		// code("return [1, 2, 3, 4, 5][1:3]").equals("[2, 3, 4]");
		code("var a = [5, 'yolo', 12] return a[1]").equals("yolo");
		code("var a = [12]; a[0]++; return a").equals("[13]");
		// code("[1, 2, 'a'][['salut', 2][0]]").exception(ls::vm::Exception::ARRAY_KEY_IS_NOT_NUMBER);
		code("return ['a', 'b', 'c'][[2, ''][0]]").equals("c");
		code("var a = [[12], ''][0]; a[0]++; return a").equals("[13]");
		// code("var a = [[12], ''][0] a[a]++ a").exception(ls::vm::Exception::ARRAY_KEY_IS_NOT_NUMBER);
		code_v1("var a = [[12], [5.5], ['a']] a[0][0] += 1 a[1][0] += 1 a[2][0] += 1 return a").equals("[[13], [6,5], [a1]]");
		code_v2_("var a = [[12], [5.5], ['a']] a[0][0] += 1 a[1][0] += 1 a[2][0] += 1 return a").equals("[[13], [6.5], [a1]]");
		// code("var a = [1, 2, 3] return a[0l]").equals("1");
		// code("var a = [1, 2, 3] return a[1l]").equals("2");
		// code("var a = [1, 2, 3] return a[2m]").equals("3");
		code("var a = ['a', 'b', 'c'] return a[0.5]").equals("a");
		code("var a = ['a', 'b', 'c'] return a[1.9]").equals("b");
		code("return ['', [2][0]]").equals("[, 2]");
		code_v1("return ['', [2.5][0]]").equals("[, 2,5]");
		code_v2_("return ['', [2.5][0]]").equals("[, 2.5]");
		code("var a = [1, 2, 3] return a[true]").equals("2");
		code_v1("return [1, 2.5][1]").equals("2,5");
		code_v2_("return [1, 2.5][1]").equals("2.5");
		code("return [1, true][0]").equals("1");
		code("return [1, true][1]").equals("true");
		// code("return [5l, 7l, 9l][2l]").equals("9");
		code("var a = [12: 5] return a[5] = 7").equals("7");
		code("var a = [12: 5] var b = 7 return a[5] = b").equals("7");
		code("var a = [] return a[0] + 2").equals("2");
		code("var a = 5 return a[0] + 2").equals("2");
		code("var a = [1] return a[0] = 10").equals("10");
		code("var a = null return a[0]").equals("null");

		section("[] operator on unknown arrays");
		code("var v = [['a', 'b'], 12] return v[0][0]").equals("a");
		code("var v = [['a', 'b'], 12] return v[0][1]").equals("b");
		code("var v = [['a', 'b'], 12] return v[0][true]").equals("b");
		// code("[['a', 'b'], 12][0][['yolo', 1][0]]").exception(ls::vm::Exception::ARRAY_KEY_IS_NOT_NUMBER);
		code("return [['a', 'b'], 12][0][2]").equals("null");
		code("var v = [['a', 'b'], 12] v[0][0] = 5 return v").equals("[[5, b], 12]");
		code("var v = [['a', 'b'], 12] v[0][2] = 5 return v").equals("[[a, b, 5], 12]");
		// code("var a = [[12], [1..10]][1] return a[5]").equals("6");

		section("Out of bounds exception");
		code("return [][1]").equals("null");
		code("return [1, 2, 3][100]").equals("null");
		code("var a = [1, 2, 3] return a[10]").equals("null");
		code("return [5.6, 7.2][-5]").equals("null");
		code("return ['hello', true][2]").equals("null");
		code("var a = [1, 2, 3] return a[100] = 12").equals("12");
		code("var a = [1, 2, 3] return a[-100] = 12").equals("12");
		code("var a = [] a[100] = true return a").equals("[100 : true]");
		code("var a = [1, 2, 3] a[100] = true return a").equals("[0 : 1, 1 : 2, 2 : 3, 100 : true]");
		// code("var a = [[12], ''][0]; a[100]++; return a").equals("null");
		// code("var a = [5] var e = a[1] !? 5 return e").equals("5");

		section("Access with booleans");
		code("return [1, 2, 3][false]").equals("1");
		code("return [1, 2, 3][true]").equals("2");
		code("return ['1', '2', '3'][false]").equals("1");
		code_v1("return [1.5, 2.5, 3.5][true]").equals("2,5");
		code_v2_("return [1.5, 2.5, 3.5][true]").equals("2.5");

		// section("Push with empty array access");
		// code("var a = [] a[] = 12 return a").equals("[12]");
		// code("var a = [1, 2] a[] = 3 return a").equals("[1, 2, 3]");
		// code("var a = [1, 2] a[] = 3 return a").equals("[1, 2, 3]");
		// code("var a = [] a[] = 'a' return a").equals("[a]");
		// code("var a = ['a', 'b'] a[] = 'c' return a").equals("[a, b, c]");
		// code("var a = [1, 'b', true] a[] = function(x) { return x } return a").equals("[1, b, true, <function>]");
		// code("var a = ['a', 'b', 'c'] a[] = ['d'][0] return a").equals("[a, b, c, d]");

		section("Methods calls on unknown array");
		code("var a = [1, [1, 2]] return count(a[1])").equals("2");
		code("var a = [1, [1, 2]] push(a[1], 3) return a[1]").equals("[1, 2, 3]");
		code("var a = [1, ['a', 'b']] push(a[1], 'c') return a[1]").equals("[a, b, c]");
		code("var a = [[], ['a']] push(a[1], 'b') return a").equals("[[], [a, b]]");

		section("Array.operator +=");
		// DISABLED_code("var a = [1.55] a += 12.9 return a;").equals("[1.55]");
		// DISABLED_code("var a = ['a'] a += 'b' return a;").equals("['a', 'b']");
		// DISABLED_code("var a = [1, 2, 3] a[0] += 5 return a[0];").equals("6");
		// DISABLED_code("var v = 12 var a = [v, 2, 3] a[0] += 5 return a[0];").equals("17");
		// DISABLED_code("var a = [1, 2, 3] a += 'hello' return a;").equals("[1, 2, 3, 'hello']");
		code_v1("var a = [1.5] a += ['a', 'b'] return a").equals("[1,5, a, b]");
		code_v2_("var a = [1.5] a += ['a', 'b'] return a").equals("[1.5, a, b]");
		// DISABLED_code("var a = [1.5] a += false return a;").equals("[1.5, false]");
		// DISABLED_code("var a = [1] a += <2, 3> a").equals("[1, 2, 3]");
		// DISABLED_code("var a = [1] a += <5.5, 7.314> a").equals("[1, 5.5, 7.314]");
		// DISABLED_code("var a = [1] a += <5, 7.314, 'hello'> a").equals("[1, 5, 7.314, 'hello']");
		// DISABLED_code("var a = [1] a += [<5, 7.314, 'hello'>, ''][0] a").equals("[1, 5, 7.314, 'hello']");
		// DISABLED_code("var a = [1] a += <'z', 'a'> a").equals("[1, 'a', 'z']");
		// DISABLED_code("var a = [1] a += 'a' return a;").equals("[1, 'a']");
		// DISABLED_code("var a = [[1]] a[0] += [12, ''][0] return a[0];").equals("[1, 12]");
		code_v1("var a = [1.11] a += [2, 3] return a").equals("[1,11, 2, 3]");
		code_v2_("var a = [1.11] a += [2, 3] return a").equals("[1.11, 2, 3]");
		// DISABLED_code("var a = [[1.55]] a[0] += [12.9, ''][0] return a[0];").equals("[1.55, 12.9]");
		// DISABLED_code("var a = [[1.55]] a[0] += [-1.5, 6.7] return a[0];").equals("[1.55, -1.5, 6.7]");
		// DISABLED_code("var a = [[1.55]] a[0] += <8, 4> a[0]").equals("[1.55, 4, 8]");
		// DISABLED_code("var a = [[1.55]] a[0] += < -8.5, 4.7> a[0]").equals("[1.55, -8.5, 4.7]");
		// DISABLED_code("var a = [[1.55]] a[0] += < -8.5, 4.7, 'hello'> a[0]").equals("[1.55, -8.5, 4.7, 'hello']");
		code("var a = ['a'] return a += [1, 2]").equals("[a, 1, 2]");
		code_v1("var a = ['a'] return a += [1.5, 2.5]").equals("[a, 1,5, 2,5]");
		code_v2_("var a = ['a'] return a += [1.5, 2.5]").equals("[a, 1.5, 2.5]");
		// DISABLED_code("var a = ['a'] a += <1, 2>").equals("['a', 1, 2]");
		// DISABLED_code("var a = ['a'] a += <1.5, 2.5>").equals("['a', 1.5, 2.5]");
		// DISABLED_code("var a = ['a'] var b = <'b'> a += b a").equals("['a', 'b']");
		code("var a = ['a'] var b = [12] a += b return a").equals("[a, 12]");
		// DISABLED_code("var a = [1, 2, 3] a[1] += 0.5 return a;").equals("[1, 2.5, 3]");
		code("var a = [1, 2, 3] a += [4] return a").equals("[1, 2, 3, 4]");
		// DISABLED_code("var a = [1, 2, 3] a[1] += 500l a").equals("[1, 502, 3]");
		// DISABLED_code("var a = [] if (true) a += 12 return a;").equals("[12]");
		// DISABLED_code("var a = [1] if (true) a += 12 return a;").equals("[1, 12]");
		// DISABLED_code("var a = ['a'] if (true) a += 12 return a;").equals("['a', 12]");
		code_v1("var a = [1.55]; a += 12.9; return a").equals("[1,55, 12,9]");
		code_v2_("var a = [1.55]; a += 12.9; return a").equals("[1.55, 12.9]");

		section("Array.operator += on element");
		code("var a = [5] a[0] += 1 return a;").equals("[6]");
		code("var a = [5, 6, 7] a[0] += 10 a[1] += 10 return a;").equals("[15, 16, 7]");
		code("var a = [[5]] a[0][0] += 1 return a;").equals("[[6]]");
		code("var a = [] a[0] += 1 return a;").equals("[1]");

		section("Array.operator ++ on element");
		code("var a = [5]; a[0]++; return a;").equals("[6]");
		code("var a = [5, 6, 7]; a[0]++; a[1]++; return a;").equals("[6, 7, 7]");
		code("var a = [[5]]; a[0][0]++; return a;").equals("[[6]]");

		section("Array.operator pre ++ on element");
		code("var a = [5]; ++a[0]; return a;").equals("[6]");
		code("var a = [5]; return ++a[0];").equals("6");
		code("var a = [5, 6, 7]; ++a[0]; ++a[1]; return a;").equals("[6, 7, 7]");
		code("var a = [[5]]; ++a[0][0]; return a;").equals("[[6]]");
		code("var a = [[5]]; return ++a[0][0];").equals("6");

		section("Array.operator -- on element");
		code("var a = [5] a[0]-- return a;").equals("[4]");
		code("var a = [5, 6, 7] a[0]-- a[1]-- return a;").equals("[4, 5, 7]");
		code("var a = [[5]] a[0][0]-- return a;").equals("[[4]]");

		section("Array.operator pre -- on element");
		code("var a = [5]; --a[0] return a;").equals("[4]");
		code("var a = [5] return --a[0];").equals("4");
		code("var a = [5, 6, 7]; --a[0]; --a[1] return a;").equals("[4, 5, 7]");
		code("var a = [[5]]; --a[0][0] return a;").equals("[[4]]");
		code("var a = [[5]] return --a[0][0];").equals("4");

		section("Array.operator -= on element");
		code("var a = [5] a[0] -= 1 return a;").equals("[4]");
		code("var a = [5, 6, 7] a[0] -= 10 a[1] -= 10 return a;").equals("[-5, -4, 7]");
		code("var a = [[5]] a[0][0] -= 1 return a;").equals("[[4]]");
		code("var a = [] a[0] -= 1 return a;").equals("[-1]");

		section("Array.operator *= on element");
		code("var a = [5] a[0] *= 10 return a;").equals("[50]");
		code("var a = [5, 6, 7] a[0] *= 10 a[1] *= 10 return a;").equals("[50, 60, 7]");
		code("var a = [[5]] a[0][0] *= 10 return a;").equals("[[50]]");

		section("Array.operator %= on element");
		code("var a = [5] a[0] %= 2 return a;").equals("[1]");
		code("var a = [5, 6, 7] a[0] %= 2 a[1] %= 2 return a;").equals("[1, 0, 7]");
		code("var a = [[5]] a[0][0] %= 2 return a;").equals("[[1]]");

		section("Array.operator **= on element");
		code("var a = [5] a[0] **= 2 return a;").equals("[25]");
		code("var a = [5, 6, 7] a[0] **= 2 a[1] **= 2 return a;").equals("[25, 36, 7]");
		code("var a = [[5]] a[0][0] **= 2 return a;").equals("[[25]]");

		section("Array.operator |= on element");
		code("var a = [5] a[0] |= 2 return a;").equals("[7]");
		code("var a = [5, 6, 7] a[0] |= 8 a[1] |= 8 return a;").equals("[13, 14, 7]");
		code("var a = [[5]] a[0][0] |= 2 return a;").equals("[[7]]");
		code("var a = [] a[0] |= 1 return a;").equals("[1]");

		section("Array.operator &= on element");
		code("var a = [87619] a[0] &= 18431 return a;").equals("[17987]");

		section("Array.operator ^= on element");
		code_v1("var a = [876] a[0] ^= 3 return a;").equals("[672221376]");
		code_v2_("var a = [876] a[0] ^= 3 return a;").equals("[879]");

		section("Array.operator <<= on element");
		code("var a = [123] a[0] <<= 12 return a;").equals("[503808]");

		section("Array.operator >>= on element");
		code("var a = [123123123] a[0] >>= 5 return a;").equals("[3847597]");

		section("Array.operator >>>= on element");
		code("var a = [-155] a[0] >>>= 5 return a;").equals("[134217723]");

		section("Array.count()");
		code("return count([1, 2, 3, 4, 5]);").equals("5");

		section("Array.push()");
		code("var a = [] push(a, 12) return a;").equals("[12]");
		code("var a = [] push(a, 1) push(a, 2) push(a, 3) return a;").equals("[1, 2, 3]");
		code("var a = [1, 2, 3] push(a, 4) return a;").equals("[1, 2, 3, 4]");
		code("var a = [1, 2, 3] push(a, 'hello') return a;").equals("[1, 2, 3, hello]");
		code("return push([], 'hello');").equals("null");
		code("var a = [[]] push(a[0], 'hello') return a;").equals("[[hello]]");
		code("var a = [[]] push(a, [1, 2, 3]) return a;").equals("[[], [1, 2, 3]]");
		code("var a = [1, [1, 2]] push(a[1], 3) return a;").equals("[1, [1, 2, 3]]");
		code("var a = [1, [1, 2]] push(a[1], 3) return a[1];").equals("[1, 2, 3]");
		code("var a = [1, ['a', 'b']] push(a[1], 'c') return a[1];").equals("[a, b, c]");
		code("var a = [[], ['a']] push(a[1], 'b') return a;").equals("[[], [a, b]]");
		code("return function() { var a = []; for (var i = 0; i < 100000; ++i) { push(a, i); } return a[91212]; }();").equals("91212");
		code("return function() { var a = ['a','b','c','d']; push(a, 'b'); return string(a); }();").equals("[a, b, c, d, b]");

		section("Array.pushAll()");
		code("var a = [] pushAll(a, [1, 2, 3]) return a;").equals("[1, 2, 3]");
		code("var a = [1, 2, 3] pushAll(a, [4, 5, 6]) return a;").equals("[1, 2, 3, 4, 5, 6]");
		code("var a = [] var b = [1, 2, 3] pushAll(a, b) return a;").equals("[1, 2, 3]");
		code("var a = [1, 2, 3] var b = [4, 5, 6] pushAll(a, b) return a;").equals("[1, 2, 3, 4, 5, 6]");
		code("var a = [[], ['a']] pushAll(a[1], ['b', 'c']) return a;").equals("[[], [a, b, c]]");
		code("return function() { var a = [1, 2, 3]; pushAll(a, [5, 6, 7]); return a; }();").equals("[1, 2, 3, 5, 6, 7]");

		section("Array.filter() v1.0");
		code_v1("return arrayFilter([1, 2, 3, 4, 5, 6, 7, 8, 9], function(e) { return e > 5; });").equals("[5 : 6, 6 : 7, 7 : 8, 8 : 9]");
		code_v1("return arrayFilter([4, 5, 6, 'test', 8, 9], function(e) { return e == 'test'; });").equals("[3 : test]");
		code_v1("return arrayFilter(['a', 'b', 'c', 'd'], function(k, v) { return k == 3; });").equals("[3 : d]");
		code_v1("return function() { var t = ['a', 'b', 'c', 'd']; arrayFilter(t, function(k, @v) { v = 4; return k == 3; }); return t; }();").equals("[4, 4, 4, 4]");
		code_v1("return arrayFilter(['a', 'b', 'c', 'd'], function(k, @v) { v = 4; return k == 3; });").equals("[3 : 4]");

		section("Array.filter() v1.1");
		code_v2_("return arrayFilter([1, 2, 3, 4, 5, 6, 7, 8, 9], function(e) { return e > 5; });").equals("[6, 7, 8, 9]");
		code_v2_("return arrayFilter([4, 5, 6, 'test', 8, 9], function(e) { return e == 'test'; });").equals("[test]");
		code_v2_("return string(arrayFilter(['a', 'b', 'c', 'd'], function(k, v) { return k == 3; }));").equals("[d]");

		section("Array.flatten()");
		code("return arrayFlatten([6,7,[8,9]],99);").equals("[6, 7, 8, 9]");
		code("return arrayFlatten([6,[[7]],[8,9]],2);").equals("[6, 7, 8, 9]");
		code("return arrayFlatten([6,[[7]],[8,9]]);").equals("[6, [7], 8, 9]");

		section("Array.sort");
		code_v1("return function() { var t = [null, null, 4, 8, 9]; sort(t); return t; }();").equals("[4, 8, 9, null, null]");
		code_v2_("return function() { var t = [null, null, 4, 8, 9]; sort(t); return t; }();").equals("[null, null, 4, 8, 9]");
		code_v1("return function() { var t = [4, null, 4, null, 4]; sort(t); return t; }();").equals("[4, 4, 4, null, null]");
		code_v2_("return function() { var t = [4, null, 4, null, 4]; sort(t); return t; }();").equals("[null, null, 4, 4, 4]");
		code_v1("return function() { var t = [4, null, 5, null, 8]; sort(t, SORT_DESC); return t; }();").equals("[null, null, 8, 5, 4]");
		code_v2_("return function() { var t = [4, null, 5, null, 8]; sort(t, SORT_DESC); return t; }();").equals("[8, 5, 4, null, null]");

		section("Array and references");
		code_v1("var t = [@3, @4, @5]; return t;").equals("[3, 4, 5]");
		code_v1("var t = [3, 4, 5]; var a = 12; t[1] = @a return t;").equals("[3, 12, 5]");
		code_v1("var t = [3, 4, 5]; var a = @t[1] a++ return t;").equals("[3, 4, 5]");
		code_v1("var t = [3, 4, 5]; var a = null a = @t[1] a++ return t;").equals("[3, 4, 5]");
		code_v1("var t = [3, 4, 5]; t[3] = [1, 2, 3, 4]; var r = @t[3]; r[4] = 'coucou'; return t;").equals("[3, 4, 5, [1, 2, 3, 4, coucou]]");

		section("Array.map()");
		code("return arrayMap([1, 2, 3], function(v) { var r = [] return r })").equals("[[], [], []]");
		code("return arrayMap([1, 2, 3], function(v) { var r = [1, 2, 3] return r })").equals("[[1, 2, 3], [1, 2, 3], [1, 2, 3]]");
		code("var r = [] var a = arrayMap([1, 2, 3], function(v) { return r }) push(r, 1) return a").equals("[[1], <...>, <...>]");
		code_v2_("class A { name part constructor(name, part) { this.name = name this.part = part } } var list = [new A('foo', true), new A('bar', false), new A('baz', true)] return arrayMap(list, function(a) { return a.name })").equals("[foo, bar, baz]");

		section("Array.map() v1.0");
		code_v1("return arrayMap([1, 2, 3, 4, 5], function(e) { return e * 2; });").equals("[2, 4, 6, 8, 10]");
		code_v1("return arrayMap([4, 9, 16], sqrt);").equals("[2, 3, 4]");
		code_v1("return arrayMap(['a': 1,'b': 2], function(k, v) { return k + v; });").equals("[a : a1, b : b2]");
		code_v1("return function() { var t = ['a':1,'b':2]; arrayMap(t, function(@k, @v) { v = 'tomate'; k = 'ctus'; return 3; }); return t; }();").equals("[a : tomate, b : tomate]");

		section("Array.map() v1.1");
		code_v2_("return arrayMap([1, 2, 3, 4, 5], function(e) { return e * 2; });").equals("[2, 4, 6, 8, 10]");
		code_v2_("return arrayMap([4, 9, 16], sqrt);").equals("[2.0, 3.0, 4.0]");
		code_v2_("return arrayMap(['a': 1, 'b': 2], function(k, v) { return k + v; });").equals("[a : a1, b : b2]");
		code_v2_("return function() { var t = ['a': 1, 'b': 2]; arrayMap(t, function(k, v) { v = 'tomate'; k = 'ctus'; return 3; }); return t; }();").equals("[a : 1, b : 2]");

		section("Array.foldLeft()");
		code("return arrayFoldLeft([6, 7, 8, 9], function(a, b) { return a + b; }, 0)").equals("30");
		code("return arrayFoldLeft([1,0,1,2,5,7,9], function(a,b){return a+','+b;},'')").equals(",1,0,1,2,5,7,9");

		section("Array.foldRight()");
		code("return arrayFoldRight([6,7,8,9], function(a,b){return a+b;},0)").equals("30");
		code("return arrayFoldRight([1,0,1,2,5,7,9], function(a,b){return a+','+b;},'')").equals("1,0,1,2,5,7,9,");

		section("Array.partition()");
		code("return arrayPartition([6,7,8,9], function(a){return a&1;})").equals("[[1 : 7, 3 : 9], [0 : 6, 2 : 8]]");
		code("return string(arrayPartition([6,7,8,9], function(k,v){return k;}))").equals("[[1 : 7, 2 : 8, 3 : 9], [6]]");
		code("return string(arrayPartition([4,3,2,1], function(k,v){return k<v;}))").equals("[[4, 3], [2 : 2, 3 : 1]]");
		code_v1("return string(function(){var t=[1,2,3]; arrayPartition(t, function(@v){ v=3; }); return t;}())").equals("[3, 3, 3]");
		code_v1("return string(function(){var t=[1,2,3]; arrayPartition(t, function(k, @v){ v=3; }); return t;}())").equals("[3, 3, 3]");
		code_v1("return string(arrayPartition([4,3,2,1], function(k,@v){ v=3; return k<v;}))").equals("[[3, 3, 3], [3 : 3]]");
		code_v2_("class A { name part constructor(name, part) { this.name = name this.part = part } } var list = [new A('foo', true), new A('bar', false), new A('baz', true)] return arrayPartition(list, function(a) { return a.part })").equals("[[0 : A {name: foo, part: true}, 2 : A {name: baz, part: true}], [1 : A {name: bar, part: false}]]");

		section("Array.concat()");
		code("return [0] + [1, 2]").equals("[0, 1, 2]");
		code("return function() { var a = [0, 1]; a += [3]; return a }()").equals("[0, 1, 3]");
		code("return arrayConcat([0], [1, 2])").equals("[0, 1, 2]");

		section("Array.iter()");
		code("var t = [1,2,3,4]; arrayIter(t, function(v){ v=2; }); return t;").equals("[1, 2, 3, 4]");
		code_v1("var t = [1,2,3,4]; arrayIter(t, function(@v){ v=2; }); return t;").equals("[2, 2, 2, 2]");
		code_v1("var t = [1,2,3,4]; arrayIter(t, function(k, @v){ v=k; }); return t;").equals("[0, 1, 2, 3]");
		code("var t = [1,2,3,4]; arrayIter(t, function(k, v){ v=k; }); return t;").equals("[1, 2, 3, 4]");
		code_v2_("class A { name part constructor(name, part) { this.name = name this.part = part } } var list = [new A('foo', true), new A('bar', false), new A('baz', true)] var r = [] arrayIter(list, function(a) { push(r, a.name) }) return r").equals("[foo, bar, baz]");

		section("Array.sort()");
		code("var t = [0,1,2]; return arraySort(t,function(e, f){return (e>f)?(-1):(e<f)?1:0;})").equals("[2, 1, 0]");
		code("var t = [2:0,1:1,0:2]; return arraySort(t,function(k1, v1, k2, v2){return (k1>k2)?(-1):(k1<k2)?1:0;})").equals("[2 : 0, 1 : 1, 0 : 2]");
		code("var t = ['test','t']; return arraySort(t,function(k1, v1, k2, v2){return (k1>k2)?(-1):(k1<k2)?1:0;})").equals("[t, test]");

		section("Array.remove()");
		code("var r = ['a','b','c','d','e']; return remove(r,1);").equals("b");
		code("var r = ['a','b','c','d','e']; return remove(r,55);").equals("null");
		code("var r = ['a','b','c','d','e']; remove(r,1); return r;").equals("[a, c, d, e]");

		section("Array.count()");
		code("return count(['a','b','c','d','e'])").equals("5");

		section("Array.join()");
		code("return join(['a','b','c','d','e'],'_')").equals("a_b_c_d_e");
		code("return join(['a','b','c','d','e'],'_-')").equals("a_-b_-c_-d_-e");

		section("Array.insert()");
		code("var a = ['a','b','c','d']; insert(a, 'b', 1); return a;").equals("[a, b, b, c, d]");

		section("Array.unshift()");
		code("var a = ['a','b','c','d']; unshift(a, 'b'); return a;").equals("[b, a, b, c, d]");

		section("Array.shift()");
		code("var a = ['a','b','c','d']; shift(a); return a;").equals("[b, c, d]");

		section("Array.pop()");
		code("var a = ['a','b','c','d']; pop(a); return a;").equals("[a, b, c]");

		section("Array.removeElement()");
		code("var a = ['a','b','c','d']; removeElement(a,'c'); return a").equals("[0 : a, 1 : b, 3 : d]");

		section("Array.removeKey()");
		code("var a = ['a':'va','b':'vb','c':'vc','d':'vd']; removeKey(a,'a'); return a").equals("[b : vb, c : vc, d : vd]");

		section("Array.sort()");
		code("var a = [8,6,2,3,7,1,0]; sort(a); return a").equals("[0, 1, 2, 3, 6, 7, 8]");
		code("var a = [8,6,2,3,7,1,0]; sort(a, SORT_ASC); return a").equals("[0, 1, 2, 3, 6, 7, 8]");
		code("var a = [8,6,2,3,7,1,0]; sort(a, SORT_DESC); return a").equals("[8, 7, 6, 3, 2, 1, 0]");
		code_v1("var a = [0, 1, 1, 1, 2, 2, 2, 2, 2, null, 3, 3, 3, 3, 3, 3, 3, 3, 3, null, 4, 4, 4, null, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 5, 5, null, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, null, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6] sort(a) return a").equals("[0, 1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, null, null, null, null, null]");
		code_v2_("var a = [0, 1, 1, 1, 2, 2, 2, 2, 2, null, 3, 3, 3, 3, 3, 3, 3, 3, 3, null, 4, 4, 4, null, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 5, 5, null, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, null, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6] sort(a) return a").equals("[null, null, null, null, null, 0, 1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6]");

		section("Array.assocSort()");
		code("var a = ['b':'vb','c':'vc','a':'va','d':'vd']; assocSort(a); return a").equals("[a : va, b : vb, c : vc, d : vd]");
		code("var a = ['b':'vb','c':'vc','a':'va','d':'vd']; assocSort(a, SORT_DESC); return a").equals("[d : vd, c : vc, b : vb, a : va]");
		code("var a = [8,6,2,3,7,1,0]; assocSort(a); return a").equals("[6 : 0, 5 : 1, 2 : 2, 3 : 3, 1 : 6, 4 : 7, 0 : 8]");
		code_v1("var a = [0, 1, 1, 1, 2, 2, 2, 2, 2, null, 3, 3, 3, 3, 3, 3, 3, 3, 3, null, 4, 4, 4, null, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 5, 5, null, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, null, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6] assocSort(a) return a").equals("[0 : 0, 1 : 1, 2 : 1, 3 : 1, 4 : 2, 5 : 2, 6 : 2, 7 : 2, 8 : 2, 10 : 3, 11 : 3, 12 : 3, 13 : 3, 14 : 3, 15 : 3, 16 : 3, 17 : 3, 18 : 3, 20 : 4, 21 : 4, 22 : 4, 24 : 4, 25 : 4, 26 : 4, 27 : 4, 28 : 4, 29 : 4, 30 : 4, 31 : 4, 32 : 4, 33 : 5, 34 : 5, 35 : 5, 37 : 5, 38 : 5, 39 : 5, 40 : 5, 41 : 5, 42 : 5, 43 : 5, 44 : 5, 45 : 5, 46 : 5, 47 : 5, 48 : 5, 49 : 5, 51 : 6, 52 : 6, 53 : 6, 54 : 6, 55 : 6, 56 : 6, 57 : 6, 58 : 6, 59 : 6, 60 : 6, 61 : 6, 62 : 6, 63 : 6, 64 : 6, 65 : 6, 66 : 6, 67 : 6, 68 : 6, 69 : 6, 70 : 6, 9 : null, 19 : null, 23 : null, 36 : null, 50 : null]");
		code_v2_("var a = [0, 1, 1, 1, 2, 2, 2, 2, 2, null, 3, 3, 3, 3, 3, 3, 3, 3, 3, null, 4, 4, 4, null, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 5, 5, null, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, null, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6] assocSort(a) return a").equals("[9 : null, 19 : null, 23 : null, 36 : null, 50 : null, 0 : 0, 1 : 1, 2 : 1, 3 : 1, 4 : 2, 5 : 2, 6 : 2, 7 : 2, 8 : 2, 10 : 3, 11 : 3, 12 : 3, 13 : 3, 14 : 3, 15 : 3, 16 : 3, 17 : 3, 18 : 3, 20 : 4, 21 : 4, 22 : 4, 24 : 4, 25 : 4, 26 : 4, 27 : 4, 28 : 4, 29 : 4, 30 : 4, 31 : 4, 32 : 4, 33 : 5, 34 : 5, 35 : 5, 37 : 5, 38 : 5, 39 : 5, 40 : 5, 41 : 5, 42 : 5, 43 : 5, 44 : 5, 45 : 5, 46 : 5, 47 : 5, 48 : 5, 49 : 5, 51 : 6, 52 : 6, 53 : 6, 54 : 6, 55 : 6, 56 : 6, 57 : 6, 58 : 6, 59 : 6, 60 : 6, 61 : 6, 62 : 6, 63 : 6, 64 : 6, 65 : 6, 66 : 6, 67 : 6, 68 : 6, 69 : 6, 70 : 6]");
		code_v1("var a = [162: 0, 144: 1, 145: 1, 180: 1, 179: 1, 128: 2, 197: 2, 161: 2, 181: null, 143: 3, 110: 3, 146: 3, 214: 3, 234: null, 230: null, 199: null, 125: 4, 92: 4, 164: 4, 232: 4, 160: 4, 93: 4, 231: 4, 77: null, 252: null, 247: null, 217: null, 251: null, 74: 5, 182: 5, 250: 5, 142: 5, 75: 5, 147: 5, 249: 5, 177: 5, 76: 5, 248: 5, 212: null, 54: null, 60: null, 270: null, 235: null, 269: null, 56: 6, 200: 6, 268: 6, 57: 6, 165: 6, 267: 6, 159: 6, 130: 6, 266: 6, 194: 6, 59: 6, 265: 6, 229: null] assocSort(a) return a").equals("[162 : 0, 144 : 1, 145 : 1, 180 : 1, 179 : 1, 128 : 2, 197 : 2, 161 : 2, 143 : 3, 110 : 3, 146 : 3, 214 : 3, 125 : 4, 92 : 4, 164 : 4, 232 : 4, 160 : 4, 93 : 4, 231 : 4, 74 : 5, 182 : 5, 250 : 5, 142 : 5, 75 : 5, 147 : 5, 249 : 5, 177 : 5, 76 : 5, 248 : 5, 56 : 6, 200 : 6, 268 : 6, 57 : 6, 165 : 6, 267 : 6, 159 : 6, 130 : 6, 266 : 6, 194 : 6, 59 : 6, 265 : 6, 181 : null, 234 : null, 230 : null, 199 : null, 77 : null, 252 : null, 247 : null, 217 : null, 251 : null, 212 : null, 54 : null, 60 : null, 270 : null, 235 : null, 269 : null, 229 : null]");
		code_v2_("var a = [162: 0, 144: 1, 145: 1, 180: 1, 179: 1, 128: 2, 197: 2, 161: 2, 181: null, 143: 3, 110: 3, 146: 3, 214: 3, 234: null, 230: null, 199: null, 125: 4, 92: 4, 164: 4, 232: 4, 160: 4, 93: 4, 231: 4, 77: null, 252: null, 247: null, 217: null, 251: null, 74: 5, 182: 5, 250: 5, 142: 5, 75: 5, 147: 5, 249: 5, 177: 5, 76: 5, 248: 5, 212: null, 54: null, 60: null, 270: null, 235: null, 269: null, 56: 6, 200: 6, 268: 6, 57: 6, 165: 6, 267: 6, 159: 6, 130: 6, 266: 6, 194: 6, 59: 6, 265: 6, 229: null] assocSort(a) return a").equals("[181 : null, 234 : null, 230 : null, 199 : null, 77 : null, 252 : null, 247 : null, 217 : null, 251 : null, 212 : null, 54 : null, 60 : null, 270 : null, 235 : null, 269 : null, 229 : null, 162 : 0, 144 : 1, 145 : 1, 180 : 1, 179 : 1, 128 : 2, 197 : 2, 161 : 2, 143 : 3, 110 : 3, 146 : 3, 214 : 3, 125 : 4, 92 : 4, 164 : 4, 232 : 4, 160 : 4, 93 : 4, 231 : 4, 74 : 5, 182 : 5, 250 : 5, 142 : 5, 75 : 5, 147 : 5, 249 : 5, 177 : 5, 76 : 5, 248 : 5, 56 : 6, 200 : 6, 268 : 6, 57 : 6, 165 : 6, 267 : 6, 159 : 6, 130 : 6, 266 : 6, 194 : 6, 59 : 6, 265 : 6]");
		code_v1("var a = [162: 0, 144: 1, 145: 1, 180: 1, 179: 1, 128: 2, 197: 2, 161: 2, 181: null, 143: 3, 110: 3, 146: 3, 214: 3, 234: null, 230: null, 199: null, 125: 4, 92: 4, 164: 4, 232: 4, 160: 4, 93: 4, 231: 4, 77: null, 252: null, 247: null, 217: null, 251: null, 74: 5, 182: 5, 250: 5, 142: 5, 75: 5, 147: 5, 249: 5, 177: 5, 76: 5, 248: 5, 212: null, 54: null, 60: null, 270: null, 235: null, 269: null, 56: 6, 200: 6, 268: 6, 57: 6, 165: 6, 267: 6, 159: 6, 130: 6, 266: 6, 194: 6, 59: 6, 265: 6, 229: null] assocSort(a, SORT_DESC) return a").equals("[181 : null, 234 : null, 230 : null, 199 : null, 77 : null, 252 : null, 247 : null, 217 : null, 251 : null, 212 : null, 54 : null, 60 : null, 270 : null, 235 : null, 269 : null, 229 : null, 56 : 6, 200 : 6, 268 : 6, 57 : 6, 165 : 6, 267 : 6, 159 : 6, 130 : 6, 266 : 6, 194 : 6, 59 : 6, 265 : 6, 74 : 5, 182 : 5, 250 : 5, 142 : 5, 75 : 5, 147 : 5, 249 : 5, 177 : 5, 76 : 5, 248 : 5, 125 : 4, 92 : 4, 164 : 4, 232 : 4, 160 : 4, 93 : 4, 231 : 4, 143 : 3, 110 : 3, 146 : 3, 214 : 3, 128 : 2, 197 : 2, 161 : 2, 144 : 1, 145 : 1, 180 : 1, 179 : 1, 162 : 0]");
		code_v2_("var a = [162: 0, 144: 1, 145: 1, 180: 1, 179: 1, 128: 2, 197: 2, 161: 2, 181: null, 143: 3, 110: 3, 146: 3, 214: 3, 234: null, 230: null, 199: null, 125: 4, 92: 4, 164: 4, 232: 4, 160: 4, 93: 4, 231: 4, 77: null, 252: null, 247: null, 217: null, 251: null, 74: 5, 182: 5, 250: 5, 142: 5, 75: 5, 147: 5, 249: 5, 177: 5, 76: 5, 248: 5, 212: null, 54: null, 60: null, 270: null, 235: null, 269: null, 56: 6, 200: 6, 268: 6, 57: 6, 165: 6, 267: 6, 159: 6, 130: 6, 266: 6, 194: 6, 59: 6, 265: 6, 229: null] assocSort(a, SORT_DESC) return a").equals("[56 : 6, 200 : 6, 268 : 6, 57 : 6, 165 : 6, 267 : 6, 159 : 6, 130 : 6, 266 : 6, 194 : 6, 59 : 6, 265 : 6, 74 : 5, 182 : 5, 250 : 5, 142 : 5, 75 : 5, 147 : 5, 249 : 5, 177 : 5, 76 : 5, 248 : 5, 125 : 4, 92 : 4, 164 : 4, 232 : 4, 160 : 4, 93 : 4, 231 : 4, 143 : 3, 110 : 3, 146 : 3, 214 : 3, 128 : 2, 197 : 2, 161 : 2, 144 : 1, 145 : 1, 180 : 1, 179 : 1, 162 : 0, 181 : null, 234 : null, 230 : null, 199 : null, 77 : null, 252 : null, 247 : null, 217 : null, 251 : null, 212 : null, 54 : null, 60 : null, 270 : null, 235 : null, 269 : null, 229 : null]");

		section("Array.keySort()");
		code("var a = ['b':'vb','c':'vc','a':'va','d':'vd']; keySort(a); return a").equals("[a : va, b : vb, c : vc, d : vd]");
		code("var a = ['b':'vb','c':'vc','a':'va','d':'vd']; keySort(a, SORT_DESC); return a").equals("[d : vd, c : vc, b : vb, a : va]");
		code("var a = [6 : 0, 5 : 1, 2 : 2, 3 : 3, 1 : 6, 4 : 7, 0 : 8]; keySort(a); return a").equals("[8, 6, 2, 3, 7, 1, 0]");

		section("Array.search()");
		code("var a = ['a','b','c','d']; return search(a,'c')").equals("2");
		code("var a = ['cle1':'a','cle2':'b','cle3':'c','cle4':'d']; return search(a,'c')").equals("cle3");
		code("var a = ['cle1':'a','cle2':'b','cle3':'c','cle4':'d']; return search(a,'454')").equals("null");

		section("Array.inArray()");
		code("var a = ['a','b','c','d']; return inArray(a,'c')").equals("true");
		code("var a = ['cle1':'a','cle2':'b','cle3':'c','cle4':'d']; return inArray(a,'c')").equals("true");
		code("var a = ['cle1':'a','cle2':'b','cle3':'c','cle4':'d']; return inArray(a,'cle3')").equals("false");
		code("var a = ['cle1':'a','cle2':'b','cle3':'c','cle4':'d']; return inArray(a,'454')").equals("false");

		section("Array.reverse()");
		code("var a = ['a','b','c','d']; reverse(a); return a").equals("[d, c, b, a]");

		section("Array.arrayMin()");
		code("return arrayMin([])").equals("null");
		code("return arrayMin([8,4,3,-1,8,44])").equals("-1");
		code("return arrayMin([0:7,8:9,'a':2])").equals("2");
		code("return arrayMin([1, 2, 3, 4, 5, null])").equals("null");
		code("var a = arrayMin([1, 2, 3, 4, 5, null]) return a").equals("null");
		code("return arrayMin([1, 2, 3, 4, 5, null])").equals("null");
		code("return arrayMin([1, 2, 3, null, 4, 5])").equals("null");
		code("return arrayMin([1, null, 5, 3, null, 2])").equals("null");
		code("return arrayMin([null, 3, 4, 5, null, 1])").equals("null");
		code("var a = [560 : null, 595 : null, 601 : 15, 566 : 13, 531 : 13] return arrayMin(a)").equals("null");

		section("Array.arrayMax()");
		code("return arrayMax([])").equals("null");
		code("return arrayMax([8,4,3,-1,8,44])").equals("44");
		code("return arrayMax([0:7,8:9,'a':2])").equals("9");
		code("return arrayMax([1, 2, 3, 4, 5, null])").equals("5");
		code("return arrayMax([1, 2, 3, null, 4, 5])").equals("5");
		code("return arrayMax([1, null, 5, 3, null, 2])").equals("5");
		code("return arrayMax([null, 3, 4, 5, null, 1])").equals("5");
		code("var a = arrayMax([1, 2, 3, 4, 5, null]) return a").equals("5");
		code("var a = [560 : null, 595 : null, 601 : 15, 566 : 13, 531 : 13] return arrayMax(a)").equals("15");

		section("Array.sum()");
		code_v1("return sum([1,5,7])").equals("13");
		code_v2_("return sum([1,5,7])").equals("13.0");
		code_v1("return sum([0:1,'a':5,'test':7])").equals("13");
		code_v2_("return sum([0:1,'a':5,'test':7])").equals("13.0");
		code_v1("return sum([])").equals("0");
		code_v2_("return sum([])").equals("0.0");

		section("Array.average()");
		code_v1("return average([2, 4, 6])").equals("4");
		code_v2_("return average([2, 4, 6])").equals("4.0");
		code_v1("return average([0: 2, 'a': 4, 'test': 6])").equals("4");
		code_v2_("return average([0: 2, 'a': 4, 'test': 6])").equals("4.0");
		code_v1("return average([])").equals("0");
		code_v2_("return average([])").equals("0.0");

		section("Array.fill()");
		code("var a = [1,2,3]; fill(a, 'a'); return a").equals("[a, a, a]");
		code("var a = [1,2,3]; fill(a, 'a',2); return a").equals("[a, a, 3]");
		code("var a = []; fill(a, 'a',2); return a").equals("[a, a]");

		section("Array.isEmpty()");
		code("return isEmpty([2,4,6])").equals("false");
		code("return isEmpty([2:8])").equals("false");
		code("return isEmpty([])").equals("true");

		section("Array.subArray()");
		code("return subArray([1,2,3,4,5,6,7,8],1,3)").equals("[2, 3, 4]");
		code("return subArray([1,2,3,4,5,6,7,8],3,3)").equals("[4]");

		section("Array.assocReverse()");
		code("var a = [1,2,3]; assocReverse(a); return a;").equals("[2 : 3, 1 : 2, 0 : 1]");
	}
}
