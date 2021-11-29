public class Starship {
    private final String name;
    private Vector position;
    private Vector velocity;

    /**
     * Initializes the newly created Starship with the given name and
     * position and velocity to <0, 0>.
     * @param name the Starship's name
     * @param position the Starship's position Vector
     */
    public Starship(String name, Vector position) {
        this.name = name;
        this.position = position;
        this.velocity = new Vector();
    }

    /**
     * Returns the name of this Starship.
     * @return the name this Starship
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the position of this Starship.
     * @return the position this Starship
     */
    public Vector getPosition() {
        return position;
    }

    /**
     * Returns the velocity of this Starship.
     * @return the velocity this Starship
     */
    public Vector getVelocity() {
        return velocity;
    }

    public String toString() {
        return this.name + " at " + this.position.toString() + " moving "
                + this.velocity.toString();
    }

    /**
     * Compares this Complex number to the given object
     * @param o an object, possibly a Complex number
     * @return true if o is a Complex number and has the same content as this Complex number
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Starship that = (Starship)o;

        return  this.name.equals(that.name) &&
                this.position.equals(that.position) &&
                this.velocity.equals(that.velocity);
    }

    /**
     * Adds Vector v to this Starship's velocity
     * @param v a Vector
     */
    public void accelerate(Vector v) {
        this.velocity = this.velocity.plus(v);
    }

    /**
     * Adds this Starship's velocity to this Starship's position
     */
    public void drift() {
        this.position = this.position.plus(this.velocity);
    }
}
