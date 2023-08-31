public class Main {
    public static void main(String[] args) {
        int[][] data1 = {
                {1, 2},
                {3, 4}
        };

        int[][] data2 = {
                {5, 6}
        };

        Matrix matrix1 = new CompletedMatrix(data1);
        Matrix matrix2 = new CompletedMatrix(data2);

        Matrix resultMatrix = matrix1.plus(matrix2);

        System.out.println("Matrix 1:");
        System.out.println(matrix1.getElement(0,0));

        System.out.println("Matrix 2:");
        System.out.println(matrix2);

        System.out.println("Result Matrix:");
        System.out.println(resultMatrix.getElement(0,0));
    }
}