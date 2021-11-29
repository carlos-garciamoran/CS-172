public class Location {
    int row;
    int column;

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location that = (Location)o;

        return this.row == that.row && this.column == that.column;
    }
}
