package assignment01;

public class Matrix {
  int numRows;
  int numColumns;
  int data[][];

  // Constructor with data for new matrix (automatically determines dimensions)
  public Matrix(int data[][]) {
    numRows = data.length; // d.length is the number of 1D arrays in the 2D array
    if (numRows == 0) {
      numColumns = 0;
    } else {
      numColumns = data[0].length; // d[0] is the first 1D array
    }
    this.data = new int[numRows][numColumns]; // create a new matrix to hold the data
    // copy the data over
    for (int i = 0; i < numRows; i++) {
      for (int j = 0; j < numColumns; j++) {
        this.data[i][j] = data[i][j];
      }
    }
  }

  @Override // instruct the compiler that we do indeed intend for this method to override
            // the superclass' (Object) version
  public boolean equals(Object other) {
    if (!(other instanceof Matrix)) { // make sure the Object we're comparing to is a Matrix
      return false;
    }
    Matrix matrix = (Matrix) other; // if the above was not true, we know it's safe to treat 'o' as a Matrix


    if(this.numColumns != matrix.numColumns || this.numRows != matrix.numRows){
      return false;
    }

    for(int row = 0; row < this.numRows; row++){
      for(int column = 0; column < this.numColumns; column++){
        if(this.data[row][column] != matrix.data[row][column]){
          return false;
        }
      }

    }

    return true; // placeholder
  }

  @Override // instruct the compiler that we do indeed intend for this method to override
            // the superclass' (Object) version
  public String toString() {

    String toReturn = "";

    for(int row = 0; row < this.numRows; row++) {
      for (int column = 0; column < this.numColumns; column++) {
        toReturn = toReturn + (Integer.toString(this.data[row][column])) + " ";

      }
      toReturn  = toReturn + "\n";
    }

    return toReturn;
  }

  public Matrix times(Matrix matrix) {

    if(this.numColumns != matrix.numRows) {
      return null;
    }

    int[][] dimensions = new int[this.numRows][matrix.numColumns];
    Matrix result = new Matrix(dimensions);


    for(int row = 0; row < this.numRows; row++) {

      for (int column = 0; column < matrix.numColumns; column++) {

        int sum = 0;

        for(int index = 0; index < matrix.numRows; index++) {

          int product = this.data[row][index] * matrix.data[index][column];
          sum += product;

        }

        result.data[row][column] = sum;

      }

    }
    return result;

  }

  public Matrix plus(Matrix matrix) {

    //check compatibility
    if(this.numColumns != matrix.numColumns || this.numRows != matrix.numRows) {
      return null;
    }


    int[][] dimensions = new int[this.numRows][this.numColumns];
    Matrix result = new Matrix(dimensions);

    for(int row = 0; row < this.numRows; row++) {
      for (int column = 0; column < this.numColumns; column++) {
        int sum = this.data[row][column] + matrix.data[row][column];
        result.data[row][column] = sum;

        }
      }

    return result;

  }

}
