package com.sessionknip;

import com.sessionknip.parser.expressions.names.NamesExpression;
import com.sessionknip.parser.expressions.names.StringOperandExpression;
import com.sessionknip.parser.expressions.values.OperandExpression;
import com.sessionknip.parser.names.NamesParser;
import com.sessionknip.parser.names.NamesProcessor;
import com.sessionknip.parser.names.NamesToken;
import com.sessionknip.parser.values.Parser;
import com.sessionknip.parser.values.Processor;
import com.sessionknip.parser.values.Token;

import java.util.*;

public class Executor {

    public static void sortByLength(List<NamesExpression> expressions) {
        int length = expressions.size();
        for (int j = 0; j < length; j++) {
            for (int i = 1; i < length; i++) {
                if (expressions.get(i).toString().length() < expressions.get(i - 1).toString().length()) {
                    NamesExpression temp = expressions.get(i);
                    expressions.set(i, expressions.get(i - 1));
                    expressions.set(i - 1, temp);
                }
            }
        }
    }

    public static List<NamesExpression> removeRepeats(List<NamesExpression> expressions) {
        List<NamesExpression> newList = new ArrayList<>();

        newList.add(expressions.get(0));

        for (NamesExpression e : expressions) {
            boolean isUnique = true;
            for (NamesExpression newListExpr : newList) {
                if (((StringOperandExpression) e).getName().equals(((StringOperandExpression) newListExpr).getName())) {
                    isUnique = false;
                }
            }
            if (isUnique && newList.size() != 0) {
                newList.add(e);
            }
        }

        return newList;
    }

    public static List<char[]> generateOperandValues(int operandCnt) {
        List<char[]> result = new ArrayList<>();
        int rowCnt = (int) Math.pow(2, operandCnt);

        for (int i = 0; i < rowCnt; i++) {
            String booleanRow = formatStringToByteRow(String.format(Integer.toBinaryString(i)), operandCnt);

            result.add(booleanRow.toCharArray());
        }

        return result;
    }

    private static String formatStringToByteRow(String string, int operandCnt) {

        int addingZeros = operandCnt - string.length();
        String zeros = "";

        for (int i = 0; i < addingZeros; i++) {
            zeros += "0";
        }

        if (!zeros.equals("")) {
            return zeros + string;
        }
        return string;
    }

    public static class ListComparator implements Comparator<NamesExpression> {

        @Override
        public int compare(NamesExpression t1, NamesExpression t2) {
            return t1.toString().compareTo(t2.toString());
        }
    }

    public static NamesProcessor getExpressionProcessor(String input) {
        String trimmed = input.toUpperCase().replaceAll(" ", "");
        List<NamesToken> namesTokens = new NamesParser(trimmed).getTokens();

        NamesProcessor processor = new NamesProcessor(namesTokens);
        processor.parse();

        return processor;

    }

    public static List<String> getSubExpressions(NamesProcessor processor) {
        List<NamesExpression> allExpressions = removeRepeats(processor.getAllExpressions());

        Collections.sort(allExpressions, new ListComparator());
        sortByLength(allExpressions);

        List<String> expressions = new ArrayList<>();
        for(NamesExpression e : allExpressions) {
            expressions.add(e.toString());
        }
        return expressions;
    }

    public static List<String> getOperandNames(NamesProcessor processor) {
        List<String> names = processor.getOperandExpressionNames();
        Collections.sort(names);
        return names;
    }


    public static void execute(String input, List<String> names) {

        List<char[]> operandValues = generateOperandValues(names.size());

        for (char[] c : operandValues) {
            String[] namesArray = names.toArray(new String[0]);
            AbstractMap.SimpleEntry<String[], char[]> pair = new AbstractMap.SimpleEntry<>(namesArray, c);

            String expression = input;
            for (int i = 0; i < pair.getKey().length; i++) {
                expression = expression.replaceAll(pair.getKey()[i], "" + pair.getValue()[i]);
            }
            List<Token> tokens = new Parser(expression).getTokens();
            Processor valuesProcessor = new Processor(tokens);
            OperandExpression operandExpression = valuesProcessor.parse();


            System.out.println(expression);
        }

    }

}
