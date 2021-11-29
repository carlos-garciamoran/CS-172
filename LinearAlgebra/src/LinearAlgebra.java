/** Various functions dealing with vectors and matrices. */
class LinearAlgebra {

    /**
     * Returns the magnitude of the vector v (which may be of any length).
     * This is found by adding up the squares of all of the elements of v
     * and taking the square root of the total.
     */
    static double magnitude(double[] v) {
        double magnitude = 0;

        for (double i: v) {
            magnitude += i*i;
        }

        magnitude = Math.sqrt(magnitude);

        return magnitude;
    }

    /**
     * Returns the sum of vectors v and w. This is a vector of the same
     * length, each of whose elements is the sum of the corresponding
     * elements in v and w.
     */
    static double[] sum(double[] v, double[] w) {
        double[] sum = new double[v.length];

        for (int i = 0; i < v.length; i++) {
            sum[i] = v[i] + w[i];
        }

        return sum;
    }

    /**
     * Returns the difference between vectors v and w. This is a vector
     * of the same length, each of whose elements is the difference
     * between the corresponding elements in v and w.
     */
    static double[] difference(double[] v, double[] w) {
        double[] difference = new double[v.length];

        for (int i = 0; i < v.length; i++) {
            difference[i] = v[i] - w[i];
        }

        return difference;
    }

    /**
     * Returns the element-wise between vectors v and w. This is a vector
     * of the same length, each of whose elements is the product of the
     * corresponding elements in v and w.
     */
    static double[] elementwiseProduct(double[] v, double[] w) {
        double[] eWProduct =  new double[v.length];

        for (int i = 0; i < v.length; i++) {
            eWProduct[i] = v[i] * w[i];
        }

        return eWProduct;
    }

    /**
     * Returns the dot product of vectors v and w. This is the sum of
     * the products of the corresponding elements.
     */
    static double dotProduct(double[] v, double[] w) {
        double product = 0;
        double[] eWProduct = elementwiseProduct(v, w);

        for (double n: eWProduct) {
            product += n;
        }

        return product;
    }

    /**
     * Returns, as an array of two elements, the dimensions of matrix m.
     */
    static int[] dimensions(double[][] m) {
        return new int[]{
                m.length,       // # of rows
                m[0].length     // # of columns
        };
    }

    /**
     * Returns the element-wise sum of matrices m and n.
     */
    static double[][] sum(double[][] m, double[][] n) {
        int rows = m.length;
        int columns = m[0].length;
        double[][] eWSum = new double[rows][columns];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                eWSum[r][c] = m[r][c] + n[r][c];
            }
        }

        return eWSum;
    }

    /**
     * Returns the element-wise product of matrices m and n.
     */
    static double[][] elementwiseProduct(double[][] m, double[][] n) {
        int rows = m.length;
        int columns = m[0].length;
        double[][] eWProduct = new double[rows][columns];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                eWProduct[r][c] = m[r][c] * n[r][c];
            }
        }

        return eWProduct;
    }

    /**
     * Returns the transpose of m, that is, a matrix where element
     * i, j is element j, i from m.
     */
    static double[][] transpose(double[][] m) {
        int rows = m.length;
        int columns = m[0].length;
        double[][] transpose = new double[columns][rows];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                 transpose[c][r] = m[r][c];
            }
        }

        return transpose;
    }

    /**
     * Returns the matrix product of m and n. (Search the web for a
     * definition.)
     */
    static double[][] product(double[][] m, double[][] n) {
        int mRows = m.length,   mColumns = m[0].length;
        int nRows = n.length,   nColumns = n[0].length;

        // Checks if a matrix is compatible.
        // # of columns in m must be equal to # of rows in n.
        if (mColumns != nRows)      return null;

        // The product of an a×b matrix and a b×c matrix has dimensions a×c.
        double[][] product = new double[mRows][nColumns];

        for (int r = 0; r < mRows; r++) {
            for (int c = 0; c < nColumns; c++) {
                for (int z = 0; z < mColumns; z++) {
                    product[r][c] += m[r][z] * n[z][c];
                    // System.out.printf("(%.0f * %.0f) + ", m[r][z], n[z][c]);
                }
                // System.out.printf("\tproduct[%d][%d] = %.0f\n", r, c, product[r][c]);
            }
        }

        return product;
    }

}
