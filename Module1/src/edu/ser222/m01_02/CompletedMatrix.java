package edu.ser222.m01_02;

public class CompletedMatrix implements Matrix {

    private int[][] elements;

    // TODO: Stop empty Matrices from being created
    public CompletedMatrix(int[][] elements) {
        if (elements == null) {
            throw new IllegalArgumentException("Input Elements Can Not Be Empty");
        }
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
        int rows = elements.length; // Could use getRows
        int columns = elements[0].length; // Could use getColumns

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
        // Check if it exists
        // Check if the dimensions match

        if (other == null || other.getRows() != getRows() || other.getColumns() != getColumns()) {
            throw new IllegalArgumentException("Matrices Dimensions Must Match");
        }

        int[][] summedMatrix = new int[getRows()][getColumns()];

        for (int row = 0; row < getRows(); row++) {
            for (int column = 0; column < getColumns(); column++) {
                summedMatrix[row][column] = elements[row][column] + other.getElement(row,column);
            }
        }

        return new CompletedMatrix(summedMatrix);
    }

    @Override
    public Matrix minus(Matrix other) {
        // Check if it exists
        // Check if the dimensions match

        if (other == null || other.getRows() != getRows() || other.getColumns() != getColumns()) {
            throw new IllegalArgumentException("Matrices Dimensions Must Match");
        }

        int[][] subtractedMatrix = new int[getRows()][getColumns()];

        for (int row = 0; row < getRows(); row++) {
            for (int column = 0; column < getColumns(); column++) {
                subtractedMatrix[row][column] = elements[row][column] - other.getElement(row,column);
            }
        }

        return new CompletedMatrix(subtractedMatrix);
    }

    @Override
    public Matrix multiply(Matrix other) {
        // Check if it exists
        // Check if the dimensions match

        if (other == null || getColumns() != other.getRows()) {
            throw new IllegalArgumentException("Matrix A's column must match Matrix B's Row");
        }

        int[][] multipliedMatrix = new int[getRows()][other.getColumns()];

        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < other.getColumns(); j++) {
                int dotProduct = 0;
                for (int k = 0; k < getColumns(); k++) {
                    dotProduct += elements[i][k] * other.getElement(k, j);
                }
                multipliedMatrix[i][j] = dotProduct;
            }
        }

        return new CompletedMatrix(multipliedMatrix);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        else return false;
    }

    @Override
    public String toString() {
        String matrixToString = "";
        for (int row = 0; row < getRows(); row++) {
            for (int column = 0; column < getColumns(); column++) {
                matrixToString += elements[row][column] + " ";
            }
            matrixToString += "\n";
        }
        return matrixToString;
    }


}
