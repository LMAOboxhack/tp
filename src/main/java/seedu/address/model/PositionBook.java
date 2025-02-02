package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.position.Position;
import seedu.address.model.position.UniquePositionList;

/**
 * Wraps all position data at the position-book level
 * Duplicates are not allowed (by .isSamePosition comparison)
 */
public class PositionBook implements ReadOnlyPositionBook {

    private final UniquePositionList positions;

    {
        positions = new UniquePositionList();
    }
    public PositionBook() {}

    /**
     * Creates an PositionBook using the Positions in the {@code toBeCopied}
     */
    public PositionBook(ReadOnlyPositionBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the position list with {@code positions}.
     * {@code positions} must not contain duplicate positions.
     */
    public void setPositions(List<Position> positions) {
        this.positions.setPositions(positions);
    }

    /**
     * Resets the existing data of this {@code PositionBook} with {@code newData}.
     */
    public void resetData(ReadOnlyPositionBook newData) {
        requireNonNull(newData);

        setPositions(newData.getPositionList());
    }

    //// position-level operations
    /**
     * Returns true if a position with the same identity as {@code position} exists in the position book.
     */
    public boolean hasPosition(Position position) {
        requireNonNull(position);
        return positions.contains(position);
    }

    /**
     * Adds a position to the position book.
     * The position must not already exist in the position book.
     */
    public void addPosition(Position p) {
        positions.add(p);
    }

    /**
     * Searches for a position with the same identity as {@code dummyPosition}.
     */
    public Position getPosition(Position dummyPosition) {
        requireNonNull(dummyPosition);
        return positions.getPosition(dummyPosition);
    }

    /**
     * Replaces the given position {@code target} in the list with {@code editedPosition}.
     * {@code target} must exist in the position book.
     * The position identity of {@code editedPosition} must not be the same as
     * another existing position in the position book.
     */
    public void setPosition(Position target, Position editedPosition) {
        requireNonNull(editedPosition);

        positions.setPosition(target, editedPosition);
    }

    /**
     * Removes {@code key} from this {@code PositionBook}.
     * {@code key} must exist in the position book.
     */
    public void removePosition(Position key) {
        positions.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return positions.asUnmodifiableObservableList().size() + " positions";
        // TODO: refine later
    }

    @Override
    public ObservableList<Position> getPositionList() {
        return positions.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PositionBook // instanceof handles nulls
                && positions.equals(((PositionBook) other).positions));
    }

    @Override
    public int hashCode() {
        return positions.hashCode();
    }

}
