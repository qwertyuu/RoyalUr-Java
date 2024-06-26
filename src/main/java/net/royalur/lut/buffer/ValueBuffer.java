package net.royalur.lut.buffer;

import net.royalur.lut.DataSink;
import net.royalur.lut.DataSource;

import javax.annotation.Nonnull;
import java.io.*;

/**
 * Stores binary values and provides efficient methods to manipulate it.
 */
public abstract class ValueBuffer {

    private final @Nonnull ValueType type;
    private final int capacity;

    public ValueBuffer(@Nonnull ValueType type, int capacity) {
        this.type = type;
        this.capacity = capacity;
    }

    public @Nonnull ValueType getType() {
        return type;
    }

    public int getCapacity() {
        return capacity;
    }

    public abstract long set(int index, long value);

    public abstract int set(int index, int value);

    public abstract short set(int index, short value);

    public abstract byte set(int index, byte value);

    public abstract long getLong(int index);

    public abstract int getInt(int index);

    public abstract short getShort(int index);

    public abstract byte getByte(int index);

    public abstract int indexOf(long value, int startIndex, int endIndex);

    public abstract int indexOf(int value, int startIndex, int endIndex);

    public abstract int indexOf(short value, int startIndex, int endIndex);

    public abstract int indexOf(byte value, int startIndex, int endIndex);

    /**
     * Expects the buffer to be sorted in unsigned ascending order.
     */
    public abstract int indexOfBinarySearch(long value, int startIndex, int endIndex);

    /**
     * Expects the buffer to be sorted in unsigned ascending order.
     */
    public abstract int indexOfBinarySearch(int value, int startIndex, int endIndex);

    /**
     * Expects the buffer to be sorted in unsigned ascending order.
     */
    public abstract int indexOfBinarySearch(short value, int startIndex, int endIndex);

    /**
     * Expects the buffer to be sorted in unsigned ascending order.
     */
    public abstract int indexOfBinarySearch(byte value, int startIndex, int endIndex);

    /**
     * Returns the final index that the value was moved to.
     */
    public abstract int moveIntoSortedPlace(int index);

    /**
     * Moves the value to targetIndex, and shifts all values in between.
     */
    public abstract void moveIntoPlace(int index, int targetIndex);

    public abstract void writeContents(@Nonnull DataSink output) throws IOException;

    public abstract void readContents(@Nonnull DataSource input) throws IOException;
}
