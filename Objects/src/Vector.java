public class Vector {
    private double x;
    private double y;

    /**
     * Initializes the newly created Vector to <0, 0>.
     */
    public Vector() {
        this.x = 0.0;   this.y = 0.0;
    }

    /**
     * Initializes the newly created Vector with the given x and
     * y coordinates.
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public Vector(double x, double y) {
        this.x = x;     this.y = y;
    }

    /**
     * Returns the x coordinate of this Vector.
     * @return the x coordinate of this Vector
     */
    public double getX() {
        return x;
    }

    /**
     * Returns the y coordinate of this Vector.
     * @return the y coordinate of this Vector
     */
    public double getY() {
        return y;
    }

    /**
     * Sets the x coordinate of this Vector.
     * @param x the x coordinate
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Sets the y coordinate of this Vector.
     * @param y the y coordinate
     */
    public void setY(double y) {
        this.y = y;
    }

    public String toString() {
        return "<" + this.x + ", " + this.y + ">";
    }

    /**
     * Compares this Vector to the given object
     * @param o an object, possibly a Vector
     * @return true if o is a Vector and has the same content as this Vector
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector that = (Vector)o;
        return this.x == that.x && this.y == that.y;
    }

    /**
     * Adds Vector b to this Vector number
     * Vector addition: x + y = [(x1 + x1), (y2 + y2)]
     * @param that a Vector to add
     * @return the sum of this Vector and b
     */
    public Vector plus(Vector that) {
        return new Vector(this.x + that.x, this.y + that.y);
    }

    /**
     * Subtracts Vector b to this Vector number
     * Vector subtraction: x - y = [(x1 - x1), (y2 - y2)]
     * @param that a Vector to subtract
     * @return the difference of this Vector and b
     */
    public Vector minus(Vector that) {
        return new Vector(this.x - that.x, this.y - that.y);
    }

    /**
     * Computes the dot product of this Vector with Vector that
     * @param that a Vector to multiply
     * @return the dot product of this Vector and that Vector
     */
    public int dot(Vector that) {
        return (int) (this.x * that.x + this.y * that.y);
    }

    /**
     * Computes the scalar product of this Vector with z
     * @param z an integer multiply
     * @return this Vector which equals <this.x * z, this.y * z>
     */
    public Vector times(int z) {
        this.x *= z;    this.y *= z;
        return this;
    }

    /**
     * Computes the distance between this Vector with that Vector
     * @param that a Vector to calculate the distance to
     * @return the distance between this and that using Pythagoras' Theorem
     */
    public double distanceTo(Vector that) {
        return Math.sqrt(Math.pow(this.x - that.x, 2) + Math.pow(this.y - that.y, 2));
    }
}
