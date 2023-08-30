public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        int matrixElements[][] = {
                {1,2,3},
                {4,5,6,6},
                {7,8,9}
        };

        CompletedMatrix matrix = new CompletedMatrix(matrixElements);

        int element = matrix.getElement(1,1);
        int scaledElement = matrixElements.scale(3);

        System.out.println("Element at (1, 1) is " + element);

        System.out.println("Scaled element at (1,1) is");
    }
}