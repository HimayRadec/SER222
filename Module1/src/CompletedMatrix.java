public class CompletedMatrix implements Matrix {

    private int[][] elements;

    // TODO: Stop empty Matrices from being created
    public CompletedMatrix(int[][] elements) {
        this.elements = elements;
    }

    @Override
    public int getElement(int y, int x) {
        return elements[y][x];
    }

    @Override
    public int getRows() {
        return elements.length;
    }

    @Override
    public int getColumns() {
        return elements[0].length;
    }

    @Override
    public Matrix scale(int scalar) {
        int rows = elements.length;
        int columns = elements[0].length;

        int[][] elementScaled = new int[rows][columns];

        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                elementScaled[row][column] = elements[row][column] * scalar;
            }
        }
        return new CompletedMatrix(elementScaled);
    }

    @Override
    public Matrix plus(Matrix other) {
        return null;
    }

    @Override
    public Matrix minus(Matrix other) {
        return null;
    }

    @Override
    public Matrix multiply(Matrix other) {
        return null;
    }
}
