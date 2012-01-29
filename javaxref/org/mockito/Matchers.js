defineStructure(
"Matchers",function(factory){with(factory) { typeTable(["org.hamcrest.Matcher","it"],["org.mockito.internal.matchers.Any","cl"],["org.mockito.internal.matchers.Contains","cl"],["org.mockito.internal.matchers.EndsWith","cl"],["org.mockito.internal.matchers.Equals","cl"],["org.mockito.internal.matchers.InstanceOf","cl"],["org.mockito.internal.matchers.Matches","cl"],["org.mockito.internal.matchers.NotNull","cl"],["org.mockito.internal.matchers.Null","cl"],["org.mockito.internal.matchers.Same","cl"],["org.mockito.internal.matchers.StartsWith","cl"],["org.mockito.internal.matchers.apachecommons.ReflectionEquals","cl"],["org.mockito.internal.progress.LastArguments","cl"],["org.mockito.internal.progress.EmptyReturnValues","cl"],["org.mockito.Matchers","cl"],["org.mockito.Mockito","cl"],["java.lang.SuppressWarnings","an"],["java.lang.String","cl"],["java.lang.Class","cl"],["java.lang.Object","cl"],["java.lang.Character","cl"],["java.lang.Boolean","cl"],["java.lang.Byte","cl"],["java.lang.Short","cl"],["java.lang.Integer","cl"],["java.lang.Long","cl"],["java.lang.Float","cl"],["java.lang.Double","cl"]),methodTable([14,"anyBoolean",[],"st me"],[14,"reportMatcher",[0],"st me"],[13,"returnFalse",[],"me"],[14,"anyByte",[],"st me"],[13,"returnZero",[],"me"],[14,"anyChar",[],"st me"],[13,"returnChar",[],"me"],[14,"anyInt",[],"st me"],[14,"anyLong",[],"st me"],[14,"anyFloat",[],"st me"],[14,"anyDouble",[],"st me"],[14,"anyShort",[],"st me"],[14,"anyObject",[],"st me"],[13,"returnNull",[],"me"],[14,"anyString",[],"st me"],[14,"isA",[18],"st me"],[5,"InstanceOf",[18],"co"],[14,"eq",["boolean"],"st me"],[4,"Equals",[19],"co"],[14,"eq",["byte"],"st me"],[14,"eq",["char"],"st me"],[14,"eq",["double"],"st me"],[14,"eq",["float"],"st me"],[14,"eq",["int"],"st me"],[14,"eq",["long"],"st me"],[14,"eq",["short"],"st me"],[14,"eq",[19],"st me"],[14,"refEq",[19],"st me"],[11,"ReflectionEquals",[19],"co"],[14,"same",[19],"st me"],[9,"Same",[19],"co"],[14,"isNull",[],"st me"],[14,"notNull",[],"st me"],[14,"contains",[17],"st me"],[2,"Contains",[17],"co"],[14,"matches",[17],"st me"],[6,"Matches",[17],"co"],[14,"endsWith",[17],"st me"],[3,"EndsWith",[17],"co"],[14,"startsWith",[17],"st me"],[10,"StartsWith",[17],"co"],[14,"argThat",[0],"st me"],[14,"charThat",[0],"st me"],[14,"booleanThat",[0],"st me"],[14,"byteThat",[0],"st me"],[14,"shortThat",[0],"st me"],[14,"intThat",[0],"st me"],[14,"longThat",[0],"st me"],[14,"floatThat",[0],"st me"],[14,"doubleThat",[0],"st me"],[12,"instance",[],"st me"],[12,"reportMatcher",[0],"me"]),localVariableTable(["clazz","isA(java.lang.Class)-clazz"],["value","eq(java.lang.Object)-value"],["value","refEq(java.lang.Object)-value"],["value","same(java.lang.Object)-value"],["substring","contains(java.lang.String)-substring"],["regex","matches(java.lang.String)-regex"],["suffix","endsWith(java.lang.String)-suffix"],["prefix","startsWith(java.lang.String)-prefix"],["matcher","argThat(org.hamcrest.Matcher)-matcher"],["matcher","charThat(org.hamcrest.Matcher)-matcher"],["matcher","booleanThat(org.hamcrest.Matcher)-matcher"],["matcher","byteThat(org.hamcrest.Matcher)-matcher"],["matcher","shortThat(org.hamcrest.Matcher)-matcher"],["matcher","intThat(org.hamcrest.Matcher)-matcher"],["matcher","longThat(org.hamcrest.Matcher)-matcher"],["matcher","floatThat(org.hamcrest.Matcher)-matcher"],["matcher","doubleThat(org.hamcrest.Matcher)-matcher"],["matcher","reportMatcher(org.hamcrest.Matcher)-matcher"])
return classDef(O("/*",nl," * Copyright (c) 2007 Mockito contributors",nl," * This program is made available under the terms of the MIT License.",nl," */"),nl,pa," org.mockito;",nl,nl,ip," org.hamcrest.",T(0),";",nl,ip," org.mockito.internal.matchers.",T(1),";",nl,ip," org.mockito.internal.matchers.",T(2),";",nl,ip," org.mockito.internal.matchers.",T(3),";",nl,ip," org.mockito.internal.matchers.",T(4),";",nl,ip," org.mockito.internal.matchers.",T(5),";",nl,ip," org.mockito.internal.matchers.",T(6),";",nl,ip," org.mockito.internal.matchers.",T(7),";",nl,ip," org.mockito.internal.matchers.",T(8),";",nl,ip," org.mockito.internal.matchers.",T(9),";",nl,ip," org.mockito.internal.matchers.",T(10),";",nl,ip," org.mockito.internal.matchers.apachecommons.",T(11),";",nl,ip," org.mockito.internal.progress.",T(12),";",nl,ip," org.mockito.internal.progress.",T(13),";",nl,nl,O("/**",nl," * Allow flexible verification or stubbing. See also {@link AdditionalMatchers}.",nl," * &lt;p>",nl," * {@link Mockito} extends Matchers so to get access to matchers just import Mockito class statically.",nl," * &lt;pre>",nl," *  //stubbing using anyInt() argument matcher",nl," *  stub(mockedList.get(anyInt())).toReturn(\"element\");",nl," *  ",nl," *  //following prints \"element\"",nl," *  System.out.println(mockedList.get(999));",nl," *  ",nl," *  //you can also verify using argument matcher",nl," *  verify(mockedList).get(anyInt());",nl," * &lt;/pre>",nl," * Scroll down to see all methods - full list of matchers.",nl," * &lt;p>",nl," * &lt;b>Warning:&lt;/b>",nl," * &lt;p>",nl," * If you are using argument matchers, &lt;b>all arguments&lt;/b> have to be provided by matchers.",nl," * &lt;p>",nl," * E.g: (example shows verification but the same applies to stubbing):",nl," * &lt;pre>",nl," *   verify(mock).someMethod(anyInt(), anyString(), &lt;b>eq(\"third argument\")&lt;/b>);",nl," *   //above is correct - eq() is also an argument matcher",nl," *   ",nl," *   verify(mock).someMethod(anyInt(), anyString(), &lt;b>\"third argument\"&lt;/b>);",nl," *   //above is incorrect - exception will be thrown because third argument is given without argument matcher.",nl," * &lt;/pre>",nl," * ",nl," * &lt;h1>Custom Argument Matchers&lt;/h1>",nl," * ",nl," * Use {@link Matchers#argThat} method and pass an instance of hamcrest {@link Matcher}.",nl," * &lt;p>",nl," * You can use {@link ArgumentMatcher} which is an hamcrest matcher with predefined describeTo() method.",nl," * In case of failure {@link ArgumentMatcher} generates description based on &lt;b>decamelized class name&lt;/b> - to promote meaningful class names.",nl," * &lt;p>",nl," * Example:",nl," * ",nl," * &lt;pre>",nl," *   class IsListOfTwoElements extends ArgumentMatcher&amp;lt;List&amp;gt; {",nl," *",w(6),"public boolean matches(Object list) {",nl," *",w(10),"return ((List) list).size() == 2;",nl," *",w(6),"}",nl," *   }",nl," *   ",nl," *   List mock = mock(List.class);",nl," *   ",nl," *   stub(mock.addAll(argThat(new IsListOfTwoElements()))).toReturn(true);",nl," *   ",nl," *   mock.addAll(Arrays.asList(\"one\", \"two\"));",nl," *   ",nl," *   verify(mock).addAll(argThat(new IsListOfTwoElements()));",nl," * &lt;/pre>",nl," * ",nl," * To keep it readable you may want to extract method, e.g:",nl," * &lt;pre>",nl," *   verify(mock).addAll(&lt;b>argThat(new IsListOfTwoElements())&lt;/b>);",nl," *   //becomes",nl," *   verify(mock).addAll(&lt;b>listOfTwoElements()&lt;/b>);",nl," * &lt;/pre>",nl," *",nl," * Custom argument matchers can make the test less readable. ",nl," * Sometimes it's better to implement equals() for arguments that are passed to mocks ",nl," * (Mockito naturally uses equals() for argument matching). ",nl," * This can make the test cleaner.",nl," */"),nl,C(14,[15],$(pu,_,c,_,I("Matchers"),_,B(nl,nl,w(4),O("/**",nl,w(5),"* any boolean argument.",nl,w(5),"* &lt;p>",nl,w(5),"* See examples in javadoc for {@link Matchers} class",nl,w(5),"* ",nl,w(5),"* @return &lt;code>false&lt;/code>.",nl,w(5),"*/"),nl,w(4),M(0,[],[],$(pu,_,s,_,b,_,I("anyBoolean"),P(),_,B(nl,w(8),r,_,N(1),P(T(1),".",G(1,"st fi","ANY")),".",N(2),P(),";",nl,w(4)))),nl,nl,w(4),O("/**",nl,w(5),"* any byte argument.",nl,w(5),"* &lt;p>",nl,w(5),"* See examples in javadoc for {@link Matchers} class",nl,w(5),"* ",nl,w(5),"* @return &lt;code>0&lt;/code>.",nl,w(5),"*/"),nl,w(4),M(3,[],[],$(pu,_,s,_,by,_,I("anyByte"),P(),_,B(nl,w(8),r,_,N(1),P(T(1),".",G(1,"st fi","ANY")),".",N(4),P(),";",nl,w(4)))),nl,nl,w(4),O("/**",nl,w(5),"* any char argument.",nl,w(5),"* &lt;p>",nl,w(5),"* See examples in javadoc for {@link Matchers} class",nl,w(5),"* ",nl,w(5),"* @return &lt;code>0&lt;/code>.",nl,w(5),"*/"),nl,w(4),M(5,[],[],$(pu,_,s,_,ch,_,I("anyChar"),P(),_,B(nl,w(8),r,_,N(1),P(T(1),".",G(1,"st fi","ANY")),".",N(6),P(),";",nl,w(4)))),nl,nl,w(4),O("/**",nl,w(5),"* any int argument.",nl,w(5),"* &lt;p>",nl,w(5),"* See examples in javadoc for {@link Matchers} class",nl,w(5),"* ",nl,w(5),"* @return &lt;code>0&lt;/code>.",nl,w(5),"*/"),nl,w(4),M(7,[],[],$(pu,_,s,_,j,_,I("anyInt"),P(),_,B(nl,w(8),r,_,N(1),P(T(1),".",G(1,"st fi","ANY")),".",N(4),P(),";",nl,w(4)))),nl,nl,w(4),O("/**",nl,w(5),"* any long argument.",nl,w(5),"* &lt;p>",nl,w(5),"* See examples in javadoc for {@link Matchers} class",nl,w(5),"* ",nl,w(5),"* @return &lt;code>0&lt;/code>.",nl,w(5),"*/"),nl,w(4),M(8,[],[],$(pu,_,s,_,l,_,I("anyLong"),P(),_,B(nl,w(8),r,_,N(1),P(T(1),".",G(1,"st fi","ANY")),".",N(4),P(),";",nl,w(4)))),nl,nl,w(4),O("/**",nl,w(5),"* any float argument.",nl,w(5),"* &lt;p>",nl,w(5),"* See examples in javadoc for {@link Matchers} class",nl,w(5),"* ",nl,w(5),"* @return &lt;code>0&lt;/code>.",nl,w(5),"*/"),nl,w(4),M(9,[],[],$(pu,_,s,_,fl,_,I("anyFloat"),P(),_,B(nl,w(8),r,_,N(1),P(T(1),".",G(1,"st fi","ANY")),".",N(4),P(),";",nl,w(4)))),nl,nl,w(4),O("/**",nl,w(5),"* any double argument.",nl,w(5),"* &lt;p>",nl,w(5),"* See examples in javadoc for {@link Matchers} class",nl,w(5),"* ",nl,w(5),"* @return &lt;code>0&lt;/code>.",nl,w(5),"*/"),nl,w(4),M(10,[],[],$(pu,_,s,_,db,_,I("anyDouble"),P(),_,B(nl,w(8),r,_,N(1),P(T(1),".",G(1,"st fi","ANY")),".",N(4),P(),";",nl,w(4)))),nl,nl,w(4),O("/**",nl,w(5),"* any short argument.",nl,w(5),"* &lt;p>",nl,w(5),"* See examples in javadoc for {@link Matchers} class",nl,w(5),"* ",nl,w(5),"* @return &lt;code>0&lt;/code>.",nl,w(5),"*/"),nl,w(4),M(11,[],[],$(pu,_,s,_,sh,_,I("anyShort"),P(),_,B(nl,w(8),r,_,N(1),P(T(1),".",G(1,"st fi","ANY")),".",N(4),P(),";",nl,w(4)))),nl,nl,w(4),O("/**",nl,w(5),"* any Object argument.",nl,w(5),"* &lt;p>",nl,w(5),"* See examples in javadoc for {@link Matchers} class",nl,w(5),"* ",nl,w(5),"* @return &lt;code>null&lt;/code>.",nl,w(5),"*/"),nl,w(4),M(12,[],[],$("@",T(16),P(L("\"unchecked\"")),nl,w(4),pu,_,s," &lt;T> T ",I("anyObject"),P(),_,B(nl,w(8),r,_,P("T"),_,N(1),P(T(1),".",G(1,"st fi","ANY")),".",N(13),P(),";",nl,w(4)))),nl,nl,w(4),O("/**",nl,w(5),"* any String argument.",nl,w(5),"* &lt;p>",nl,w(5),"* See examples in javadoc for {@link Matchers} class",nl,w(5),"* ",nl,w(5),"* @return &lt;code>null&lt;/code>.",nl,w(5),"*/"),nl,w(4),M(14,[],[],$(pu,_,s,_,T(17),_,I("anyString"),P(),_,B(nl,w(8),r,_,N(15),P(T(17),".",c),";",nl,w(4)))),nl,nl,w(4),O("/**",nl,w(5),"* Object argument that implements the given class. ",nl,w(5),"* &lt;p>",nl,w(5),"* See examples in javadoc for {@link Matchers} class",nl,w(5),"* ",nl,w(5),"* @param &lt;T>",nl,w(5),"*",w(12),"the accepted type.",nl,w(5),"* @param clazz",nl,w(5),"*",w(12),"the class of the accepted type.",nl,w(5),"* @return &lt;code>null&lt;/code>.",nl,w(5),"*/"),nl,w(4),M(15,[],[],$(pu,_,s," &lt;T> T ",I("isA"),P(V(0,$(T(18),"&lt;T> ",I("clazz")))),_,B(nl,w(8),r,_,N(1),P(n,_,N(16),P(W(0))),".&lt;T>",N(13),P(),";",nl,w(4)))),nl,nl,w(4),O("/**",nl,w(5),"* boolean argument that is equal to the given value.",nl,w(5),"* &lt;p>",nl,w(5),"* See examples in javadoc for {@link Matchers} class",nl,w(5),"* ",nl,w(5),"* @param value",nl,w(5),"*",w(12),"the given value.",nl,w(5),"* @return &lt;code>0&lt;/code>.",nl,w(5),"*/"),nl,w(4),M(17,[],[],$(pu,_,s,_,b,_,I("eq"),P(b," value"),_,B(nl,w(8),r,_,N(1),P(n,_,N(18),P("value")),".",N(2),P(),";",nl,w(4)))),nl,nl,w(4),O("/**",nl,w(5),"* byte argument that is equal to the given value.",nl,w(5),"* &lt;p>",nl,w(5),"* See examples in javadoc for {@link Matchers} class",nl,w(5),"* ",nl,w(5),"* @param value",nl,w(5),"*",w(12),"the given value.",nl,w(5),"* @return &lt;code>0&lt;/code>.",nl,w(5),"*/"),nl,w(4),M(19,[],[],$(pu,_,s,_,by,_,I("eq"),P(by," value"),_,B(nl,w(8),r,_,N(1),P(n,_,N(18),P("value")),".",N(4),P(),";",nl,w(4)))),nl,nl,w(4),O("/**",nl,w(5),"* char argument that is equal to the given value.",nl,w(5),"* &lt;p>",nl,w(5),"* See examples in javadoc for {@link Matchers} class",nl,w(5),"* ",nl,w(5),"* @param value",nl,w(5),"*",w(12),"the given value.",nl,w(5),"* @return &lt;code>0&lt;/code>.",nl,w(5),"*/"),nl,w(4),M(20,[],[],$(pu,_,s,_,ch,_,I("eq"),P(ch," value"),_,B(nl,w(8),r,_,N(1),P(n,_,N(18),P("value")),".",N(6),P(),";",nl,w(4)))),nl,nl,w(4),O("/**",nl,w(5),"* double argument that is equal to the given value.",nl,w(5),"* &lt;p>",nl,w(5),"* See examples in javadoc for {@link Matchers} class",nl,w(5),"* ",nl,w(5),"* @param value",nl,w(5),"*",w(12),"the given value.",nl,w(5),"* @return &lt;code>0&lt;/code>.",nl,w(5),"*/"),nl,w(4),M(21,[],[],$(pu,_,s,_,db,_,I("eq"),P(db," value"),_,B(nl,w(8),r,_,N(1),P(n,_,N(18),P("value")),".",N(4),P(),";",nl,w(4)))),nl,nl,w(4),O("/**",nl,w(5),"* float argument that is equal to the given value.",nl,w(5),"* &lt;p>",nl,w(5),"* See examples in javadoc for {@link Matchers} class",nl,w(5),"* ",nl,w(5),"* @param value",nl,w(5),"*",w(12),"the given value.",nl,w(5),"* @return &lt;code>0&lt;/code>.",nl,w(5),"*/"),nl,w(4),M(22,[],[],$(pu,_,s,_,fl,_,I("eq"),P(fl," value"),_,B(nl,w(8),r,_,N(1),P(n,_,N(18),P("value")),".",N(4),P(),";",nl,w(4)))),nl,w(4),nl,w(4),O("/**",nl,w(5),"* int argument that is equal to the given value.",nl,w(5),"* &lt;p>",nl,w(5),"* See examples in javadoc for {@link Matchers} class",nl,w(5),"* ",nl,w(5),"* @param value",nl,w(5),"*",w(12),"the given value.",nl,w(5),"* @return &lt;code>0&lt;/code>.",nl,w(5),"*/"),nl,w(4),M(23,[],[],$(pu,_,s,_,j,_,I("eq"),P(j," value"),_,B(nl,w(8),r,_,N(1),P(n,_,N(18),P("value")),".",N(4),P(),";",nl,w(4)))),nl,nl,w(4),O("/**",nl,w(5),"* long argument that is equal to the given value.",nl,w(5),"* &lt;p>",nl,w(5),"* See examples in javadoc for {@link Matchers} class",nl,w(5),"* ",nl,w(5),"* @param value",nl,w(5),"*",w(12),"the given value.",nl,w(5),"* @return &lt;code>0&lt;/code>.",nl,w(5),"*/"),nl,w(4),M(24,[],[],$(pu,_,s,_,l,_,I("eq"),P(l," value"),_,B(nl,w(8),r,_,N(1),P(n,_,N(18),P("value")),".",N(4),P(),";",nl,w(4)))),nl,nl,w(4),O("/**",nl,w(5),"* short argument that is equal to the given value.",nl,w(5),"* &lt;p>",nl,w(5),"* See examples in javadoc for {@link Matchers} class",nl,w(5),"* ",nl,w(5),"* @param value",nl,w(5),"*",w(12),"the given value.",nl,w(5),"* @return &lt;code>0&lt;/code>.",nl,w(5),"*/"),nl,w(4),M(25,[],[],$(pu,_,s,_,sh,_,I("eq"),P(sh," value"),_,B(nl,w(8),r,_,N(1),P(n,_,N(18),P("value")),".",N(4),P(),";",nl,w(4)))),nl,nl,w(4),O("/**",nl,w(5),"* Object argument that is equal to the given value.",nl,w(5),"* &lt;p>",nl,w(5),"* See examples in javadoc for {@link Matchers} class",nl,w(5),"* ",nl,w(5),"* @param value",nl,w(5),"*",w(12),"the given value.",nl,w(5),"* @return &lt;code>null&lt;/code>.",nl,w(5),"*/"),nl,w(4),M(26,[],[],$(pu,_,s," &lt;T> T ",I("eq"),P(V(1,$("T ",I("value")))),_,B(nl,w(8),r,_,N(1),P(n,_,N(18),P(W(1))),".&lt;T>",N(13),P(),";",nl,w(4)))),nl,nl,w(4),O("/**",nl,w(5),"* Object argument that is reflection-equal to the given value.",nl,w(5),"* &lt;p>",nl,w(5),"* This matcher can be used when equals() is not implemented on compared objects.",nl,w(5),"* Matcher uses java reflection API to compare fields of wanted and actual object.",nl,w(5),"* &lt;p>",nl,w(5),"* Works similarly to EqualsBuilder.reflectionEquals(this, other) from apache commons library.",nl,w(5),"* &lt;p>",nl,w(5),"* See examples in javadoc for {@link Matchers} class",nl,w(5),"* ",nl,w(5),"* @param value",nl,w(5),"*",w(12),"the given value.",nl,w(5),"* @return &lt;code>null&lt;/code>.",nl,w(5),"*/"),nl,w(4),M(27,[],[],$(pu,_,s," &lt;T> T ",I("refEq"),P(V(2,$("T ",I("value")))),_,B(nl,w(8),r,_,N(1),P(n,_,N(28),P(W(2))),".&lt;T>",N(13),P(),";",nl,w(4)))),nl,w(4),nl,w(4),O("/**",nl,w(5),"* Object argument that is the same as the given value.",nl,w(5),"* &lt;p>",nl,w(5),"* See examples in javadoc for {@link Matchers} class",nl,w(5),"* ",nl,w(5),"* @param &lt;T>",nl,w(5),"*",w(12),"the type of the object, it is passed through to prevent casts.",nl,w(5),"* @param value",nl,w(5),"*",w(12),"the given value.",nl,w(5),"* @return &lt;code>null&lt;/code>.",nl,w(5),"*/"),nl,w(4),M(29,[],[],$(pu,_,s," &lt;T> T ",I("same"),P(V(3,$("T ",I("value")))),_,B(nl,w(8),r,_,N(1),P(n,_,N(30),P(W(3))),".&lt;T>",N(13),P(),";",nl,w(4)))),nl,nl,w(4),O("/**",nl,w(5),"* null argument.",nl,w(5),"* &lt;p>",nl,w(5),"* See examples in javadoc for {@link Matchers} class",nl,w(5),"* ",nl,w(5),"* @return &lt;code>null&lt;/code>.",nl,w(5),"*/"),nl,w(4),M(31,[],[],$(pu,_,s,_,T(19),_,I("isNull"),P(),_,B(nl,w(8),r,_,N(1),P(T(8),".",G(8,"st fi","NULL")),".",N(13),P(),";",nl,w(4)))),nl,nl,w(4),O("/**",nl,w(5),"* not null argument.",nl,w(5),"* &lt;p>",nl,w(5),"* See examples in javadoc for {@link Matchers} class",nl,w(5),"* ",nl,w(5),"* @return &lt;code>null&lt;/code>.",nl,w(5),"*/"),nl,w(4),M(32,[],[],$(pu,_,s,_,T(19),_,I("notNull"),P(),_,B(nl,w(8),r,_,N(1),P(T(7),".",G(7,"st fi","NOT_NULL")),".",N(13),P(),";",nl,w(4)))),nl,nl,w(4),O("/**",nl,w(5),"* String argument that contains the given substring.",nl,w(5),"* &lt;p>",nl,w(5),"* See examples in javadoc for {@link Matchers} class",nl,w(5),"* ",nl,w(5),"* @param substring",nl,w(5),"*",w(12),"the substring.",nl,w(5),"* @return &lt;code>null&lt;/code>.",nl,w(5),"*/"),nl,w(4),M(33,[],[],$(pu,_,s,_,T(17),_,I("contains"),P(V(4,$(T(17),_,I("substring")))),_,B(nl,w(8),r,_,N(1),P(n,_,N(34),P(W(4))),".&lt;",T(17),">",N(13),P(),";",nl,w(4)))),nl,nl,w(4),O("/**",nl,w(5),"* String argument that matches the given regular expression.",nl,w(5),"* &lt;p>",nl,w(5),"* See examples in javadoc for {@link Matchers} class",nl,w(5),"* ",nl,w(5),"* @param regex",nl,w(5),"*",w(12),"the regular expression.",nl,w(5),"* @return &lt;code>null&lt;/code>.",nl,w(5),"*/"),nl,w(4),M(35,[],[],$(pu,_,s,_,T(17),_,I("matches"),P(V(5,$(T(17),_,I("regex")))),_,B(nl,w(8),r,_,N(1),P(n,_,N(36),P(W(5))),".&lt;",T(17),">",N(13),P(),";",nl,w(4)))),nl,nl,w(4),O("/**",nl,w(5),"* String argument that ends with the given suffix.",nl,w(5),"* &lt;p>",nl,w(5),"* See examples in javadoc for {@link Matchers} class",nl,w(5),"* ",nl,w(5),"* @param suffix",nl,w(5),"*",w(12),"the suffix.",nl,w(5),"* @return &lt;code>null&lt;/code>.",nl,w(5),"*/"),nl,w(4),M(37,[],[],$(pu,_,s,_,T(17),_,I("endsWith"),P(V(6,$(T(17),_,I("suffix")))),_,B(nl,w(8),r,_,N(1),P(n,_,N(38),P(W(6))),".&lt;",T(17),">",N(13),P(),";",nl,w(4)))),nl,nl,w(4),O("/**",nl,w(5),"* String argument that starts with the given prefix.",nl,w(5),"* &lt;p>",nl,w(5),"* See examples in javadoc for {@link Matchers} class",nl,w(5),"* ",nl,w(5),"* @param prefix",nl,w(5),"*",w(12),"the prefix.",nl,w(5),"* @return &lt;code>null&lt;/code>.",nl,w(5),"*/"),nl,w(4),M(39,[],[],$(pu,_,s,_,T(17),_,I("startsWith"),P(V(7,$(T(17),_,I("prefix")))),_,B(nl,w(8),r,_,N(1),P(n,_,N(40),P(W(7))),".&lt;",T(17),">",N(13),P(),";",nl,w(4)))),nl,nl,w(4),O("/**",nl,w(5),"* Allows creating custom argument matchers.",nl,w(5),"* &lt;p>",nl,w(5),"* See examples in javadoc for {@link Matchers} class",nl,w(5),"* ",nl,w(5),"* @param matcher decides whether argument matches",nl,w(5),"* @return &lt;code>null&lt;/code>.",nl,w(5),"*/"),nl,w(4),M(41,[],[],$(pu,_,s," &lt;T> T ",I("argThat"),P(V(8,$(T(0),"&lt;T> ",I("matcher")))),_,B(nl,w(8),r,_,N(1),P(W(8)),".&lt;T>",N(13),P(),";",nl,w(4)))),nl,w(4),nl,w(4),O("/**",nl,w(5),"* Allows creating custom argument matchers.",nl,w(5),"* &lt;p>",nl,w(5),"* See examples in javadoc for {@link Matchers} class",nl,w(5),"* ",nl,w(5),"* @param matcher decides whether argument matches",nl,w(5),"* @return &lt;code>0&lt;/code>.",nl,w(5),"*/"),nl,w(4),M(42,[],[],$(pu,_,s,_,ch,_,I("charThat"),P(V(9,$(T(0),"&lt;",T(20),"> ",I("matcher")))),_,B(nl,w(8),r,_,N(1),P(W(9)),".",N(6),P(),";",nl,w(4)))),nl,w(4),nl,w(4),O("/**",nl,w(5),"* Allows creating custom argument matchers.",nl,w(5),"* &lt;p>",nl,w(5),"* See examples in javadoc for {@link Matchers} class",nl,w(5),"* ",nl,w(5),"* @param matcher decides whether argument matches",nl,w(5),"* @return &lt;code>false&lt;/code>.",nl,w(5),"*/"),nl,w(4),M(43,[],[],$(pu,_,s,_,b,_,I("booleanThat"),P(V(10,$(T(0),"&lt;",T(21),"> ",I("matcher")))),_,B(nl,w(8),r,_,N(1),P(W(10)),".",N(2),P(),";",nl,w(4)))),nl,w(4),nl,w(4),O("/**",nl,w(5),"* Allows creating custom argument matchers.",nl,w(5),"* &lt;p>",nl,w(5),"* See examples in javadoc for {@link Matchers} class",nl,w(5),"* ",nl,w(5),"* @param matcher decides whether argument matches",nl,w(5),"* @return &lt;code>0&lt;/code>.",nl,w(5),"*/"),nl,w(4),M(44,[],[],$(pu,_,s,_,by,_,I("byteThat"),P(V(11,$(T(0),"&lt;",T(22),"> ",I("matcher")))),_,B(nl,w(8),r,_,N(1),P(W(11)),".",N(4),P(),";",nl,w(4)))),nl,w(4),nl,w(4),O("/**",nl,w(5),"* Allows creating custom argument matchers.",nl,w(5),"* &lt;p>",nl,w(5),"* See examples in javadoc for {@link Matchers} class",nl,w(5),"* ",nl,w(5),"* @param matcher decides whether argument matches",nl,w(5),"* @return &lt;code>0&lt;/code>.",nl,w(5),"*/"),nl,w(4),M(45,[],[],$(pu,_,s,_,sh,_,I("shortThat"),P(V(12,$(T(0),"&lt;",T(23),"> ",I("matcher")))),_,B(nl,w(8),r,_,N(1),P(W(12)),".",N(4),P(),";",nl,w(4)))),nl,w(4),nl,w(4),O("/**",nl,w(5),"* Allows creating custom argument matchers.",nl,w(5),"* &lt;p>",nl,w(5),"* See examples in javadoc for {@link Matchers} class",nl,w(5),"* ",nl,w(5),"* @param matcher decides whether argument matches",nl,w(5),"* @return &lt;code>0&lt;/code>.",nl,w(5),"*/"),nl,w(4),M(46,[],[],$(pu,_,s,_,j,_,I("intThat"),P(V(13,$(T(0),"&lt;",T(24),"> ",I("matcher")))),_,B(nl,w(8),r,_,N(1),P(W(13)),".",N(4),P(),";",nl,w(4)))),nl,nl,w(4),O("/**",nl,w(5),"* Allows creating custom argument matchers.",nl,w(5),"* &lt;p>",nl,w(5),"* See examples in javadoc for {@link Matchers} class",nl,w(5),"* ",nl,w(5),"* @param matcher decides whether argument matches",nl,w(5),"* @return &lt;code>0&lt;/code>.",nl,w(5),"*/"),nl,w(4),M(47,[],[],$(pu,_,s,_,l,_,I("longThat"),P(V(14,$(T(0),"&lt;",T(25),"> ",I("matcher")))),_,B(nl,w(8),r,_,N(1),P(W(14)),".",N(4),P(),";",nl,w(4)))),nl,w(4),nl,w(4),O("/**",nl,w(5),"* Allows creating custom argument matchers.",nl,w(5),"* &lt;p>",nl,w(5),"* See examples in javadoc for {@link Matchers} class",nl,w(5),"* ",nl,w(5),"* @param matcher decides whether argument matches",nl,w(5),"* @return &lt;code>0&lt;/code>.",nl,w(5),"*/"),nl,w(4),M(48,[],[],$(pu,_,s,_,fl,_,I("floatThat"),P(V(15,$(T(0),"&lt;",T(26),"> ",I("matcher")))),_,B(nl,w(8),r,_,N(1),P(W(15)),".",N(4),P(),";",nl,w(4)))),nl,w(4),nl,w(4),O("/**",nl,w(5),"* Allows creating custom argument matchers.",nl,w(5),"* &lt;p>",nl,w(5),"* See examples in javadoc for {@link Matchers} class",nl,w(5),"* ",nl,w(5),"* @param matcher decides whether argument matches",nl,w(5),"* @return &lt;code>0&lt;/code>.",nl,w(5),"*/"),nl,w(4),M(49,[],[],$(pu,_,s,_,db,_,I("doubleThat"),P(V(16,$(T(0),"&lt;",T(27),"> ",I("matcher")))),_,B(nl,w(8),r,_,N(1),P(W(16)),".",N(4),P(),";",nl,w(4)))),nl,nl,w(4),M(1,[],[],$(pi,_,s,_,T(13),_,I("reportMatcher"),P(V(17,$(T(0),"&lt;?> ",I("matcher")))),_,B(nl,w(8),r,_,T(12),".",N(50),P(),".",N(51),P(W(17)),";",nl,w(4)))),nl))));}});