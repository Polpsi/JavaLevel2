package Lesson2;

class BadLength extends Exception {
    BadLength(String mess) {
        super(mess);
    }
}

class BadString extends Exception {
    BadString(String mess) {
        super(mess);
    }
}

public class Lesson2 {
    public static void main(String[] args) {
        int verticalLength=4;
        int horizontalLength=4;
        String originString = "10 3 1 2\n2 3 2 2\n5 6 7 1\n300 3 1 0";
        String[][] matrix;
        int calcVal = 0;
        try {
            matrix = splitToMatrix(originString, verticalLength, horizontalLength, "\n"," ");
            calcVal = calculateMatrix(matrix);
            System.out.println(calcVal);
        } catch (BadLength | BadString e) {
            e.printStackTrace();
        }
    }

    private static String[][] splitToMatrix(String str, int vertLength, int horizLength,String rowSplitter, String colSplitter) throws BadLength {
        String[] tmpMatr = str.split(rowSplitter);
        if (tmpMatr.length != vertLength) throw new BadLength("Bad vertical length " + tmpMatr.length);
        String[][] matrix = new String[vertLength][0];
        for (int i = 0; i < tmpMatr.length; i++) {
            matrix[i] = tmpMatr[i].split(colSplitter);
            if (matrix[i].length != horizLength) throw new BadLength("Bad horizontal length "+matrix[i].length+" in row " + i);
        }
        return matrix;
    }

    private static int calculateMatrix(String[][] matrix) throws BadString {
        int value = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (!isNumber(matrix[i][j]))
                    throw new BadString("String " + matrix[i][j] + " is not number! Row " + i + ", column " + j);
                value += Integer.parseInt(matrix[i][j]);
            }
        }
        value /= 2;
        return value;
    }

    private static boolean isNumber(String str) {
        if (str == null || str.isEmpty()) return false;
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) return false;
        }
        return true;
    }
}