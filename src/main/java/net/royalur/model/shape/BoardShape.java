package net.royalur.model.shape;

import net.royalur.model.Tile;
import net.royalur.model.path.PathPair;
import net.royalur.name.Name;
import net.royalur.name.Named;
import net.royalur.name.TextName;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;

/**
 * A type of board shape available for the Royal Game of Ur.
 */
public class BoardShape implements Named<Name> {

    /**
     * The name of this board shape.
     */
    private final @Nonnull Name name;

    /**
     * The set of tiles that fall within the bounds of this board shape.
     */
    private final @Nonnull Set<Tile> tiles;

    /**
     * The set of tiles that represent rosette tiles in this board shape.
     */
    private final @Nonnull Set<Tile> rosetteTiles;

    /**
     * The number of x-coordinates that exist in this board shape.
     */
    private final int width;

    /**
     * The number of y-coordinates that exist in this board shape.
     */
    private final int height;

    /**
     * The tiles that fall within the bounds of this board shape,
     * ordered by ascending row number and then ascending column number.
     */
    private @Nullable List<Tile> tilesByRow = null;

    /**
     * The tiles that fall within the bounds of this board shape,
     * ordered into columns with ascending row number.
     */
    private @Nullable List<Tile> tilesByColumn = null;

    /**
     * Instantiates a board shape with {@code tiles} representing the tiles on the board.
     * @param name         The name of this board shape.
     * @param tiles        The set of tiles that fall within the bounds of this board shape.
     * @param rosetteTiles The set of tiles that represent rosette tiles in this board shape.
     */
    public BoardShape(
            @Nonnull Name name,
            @Nonnull Set<Tile> tiles,
            @Nonnull Set<Tile> rosetteTiles
    ) {
        if (tiles.isEmpty())
            throw new IllegalArgumentException("A board shape requires at least one tile");

        this.name = name;
        this.tiles = Collections.unmodifiableSet(new HashSet<>(tiles));
        this.rosetteTiles = Collections.unmodifiableSet(new HashSet<>(rosetteTiles));

        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int maxY = Integer.MIN_VALUE;
        for (Tile tile : tiles) {
            minX = Math.min(minX, tile.getX());
            minY = Math.min(minY, tile.getY());
            maxX = Math.max(maxX, tile.getX());
            maxY = Math.max(maxY, tile.getY());
        }
        if (minX != 1 || minY != 1) {
            // This is done in an attempt to standardise board shapes.
            throw new IllegalArgumentException(
                    "The board shape must be translated such that it has tiles " +
                    "at an x-coordinate of 1, and at a y-coordinate of 1. " +
                    "Minimum X = " + minX + ", Minimum Y = " + minY
            );
        }

        this.width = maxX;
        this.height = maxY;

        for (Tile tile : rosetteTiles) {
            if (!contains(tile)) {
                throw new IllegalArgumentException(
                        "rosetteTiles should not include any tiles that are off of the game board," +
                                "but it contains " + tile
                );
            }
        }
    }

    @Override
    public @Nonnull Name getName() {
        return name;
    }

    /**
     * Gets the set of tiles that fall within the bounds of this board shape.
     * @return The set of tiles that fall within the bounds of this board shape.
     */
    public @Nonnull Set<Tile> getTiles() {
        return tiles;
    }

    /**
     * Gets the set of tiles that represent rosette tiles in this board shape.
     * @return The set of tiles that represent rosette tiles in this board shape.
     */
    public @Nonnull Set<Tile> getRosetteTiles() {
        return rosetteTiles;
    }

    /**
     * Gets the width of the board shape.
     * @return The number of x-coordinates that exist in this board shape.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Gets the height of the board shape.
     * @return The number of y-coordinates that exist in this board shape.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Gets the number of tiles contained in this board shape.
     * @return The number of tiles contained in this board shape.
     */
    public int getArea() {
        return tiles.size();
    }

    /**
     * Returns the tiles that fall within the bounds of this board shape,
     * ordered by ascending row number, and then ascending column number.
     * @return The tiles of this board ordered by ascending row, and then
     *         ascending column number.
     */
    public final @Nonnull List<Tile> getTilesByRow() {
        if (this.tilesByRow == null) {
            List<Tile> tilesByRow = new ArrayList<>();
            for (int iy = 0; iy < height; ++iy) {
                for (int ix = 0; ix < width; ++ix) {
                    Tile tile = Tile.fromIndices(ix, iy);
                    if (contains(tile)) {
                        tilesByRow.add(tile);
                    }
                }
            }
            this.tilesByRow = Collections.unmodifiableList(tilesByRow);
        }
        return this.tilesByRow;
    }

    /**
     * The tiles that fall within the bounds of this board shape,
     * ordered by ascending column number, and then ascending row number.
     * @return The tiles of this board ordered by ascending column, and then
     *         ascending row.
     */
    public final @Nonnull List<Tile> getTilesByColumn() {
        if (this.tilesByColumn == null) {
            List<Tile> tilesByColumn = new ArrayList<>();
            for (int ix = 0; ix < width; ++ix) {
                for (int iy = 0; iy < height; ++iy) {
                    Tile tile = Tile.fromIndices(ix, iy);
                    if (contains(tile)) {
                        tilesByColumn.add(tile);
                    }
                }
            }
            this.tilesByColumn = Collections.unmodifiableList(tilesByColumn);
        }
        return this.tilesByColumn;
    }

    /**
     * Determines whether {@code tile} falls within the bounds of this shape of board.
     * @param tile The tile to be bounds-checked.
     * @return Whether the given tile falls within the bounds of this board.
     */
    public boolean contains(@Nonnull Tile tile) {
        return tiles.contains(tile);
    }

    /**
     * Determines whether the tile at the indices ({@code x}, {@code y}),
     * 0-based, falls within the bounds of this shape of board.
     * @param ix The x-index of the tile to be bounds-checked. This coordinate is 0-based.
     * @param iy The y-index of the tile to be bounds-checked. This coordinate is 0-based.
     * @return Whether the given tile falls within the bounds of this shape of board.
     */
    public boolean contains(int ix, int iy) {
        if (!Tile.isValidIndices(ix, iy))
            return false;
        return contains(Tile.fromIndices(ix, iy));
    }

    /**
     * Determines whether {@code tile} is a rosette tile on this board.
     * @param tile The tile to check if it is a rosette.
     * @return Whether the given tile is a rosette tile on this board.
     */
    public boolean isRosette(@Nonnull Tile tile) {
        return rosetteTiles.contains(tile);
    }

    /**
     * Determines whether the tile at the indices ({@code ix}, {@code iy}),
     * 0-based, is a rosette tile on this board.
     * @param ix The x-index of the tile to be checked for being a rosette.
     *           This coordinate is 0-based.
     * @param iy The y-index of the tile to be checked for being a rosette.
     *           This coordinate is 0-based.
     * @return Whether the given tile is a rosette tile on this board.
     */
    public boolean isRosette(int ix, int iy) {
        if (!Tile.isValidIndices(ix, iy))
            return false;
        return isRosette(Tile.fromIndices(ix, iy));
    }

    /**
     * Determines whether all tiles in {@code tiles} are included
     * in this board shape.
     * @param tiles The tiles to check for.
     * @return Whether all of {@code tiles} exist on this board shape.
     */
    public boolean containsAll(@Nonnull List<Tile> tiles) {
        for (Tile tile : tiles) {
            if (!contains(tile))
                return false;
        }
        return true;
    }

    /**
     * Determines whether the pair of paths, {@code paths}, could be
     * played on this shape of board.
     * @param paths The pair of paths.
     * @return Whether the pair of paths could be played on this shape
     *         of board.
     */
    public boolean isCompatible(@Nonnull PathPair paths) {
        return this.containsAll(paths.getLight()) && this.containsAll(paths.getDark());
    }

    /**
     * Determines whether this board shape covers the same tiles,
     * and has the same rosettes, as {@code other}.
     * @param other The board shape to compare with for equivalence.
     * @return Whether this board shape is equivalent to {@param other}.
     */
    public boolean isEquivalent(@Nonnull BoardShape other) {
        return tiles.equals(other.tiles) && rosetteTiles.equals(other.rosetteTiles);
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == null || !getClass().equals(obj.getClass()))
            return false;

        BoardShape other = (BoardShape) obj;
        return isEquivalent(other);
    }

    /**
     * Create a new board shape with the name {@code name}, the tiles
     * {@code tiles}, and the rosettes on {@code rosetteTiles}.
     * @param name         The name of the board shape.
     * @param tiles        The set of tiles that fall within the bounds of this board shape.
     * @param rosetteTiles The set of tiles that represent rosette tiles in this board shape.
     * @return A new board shape with the given name.
     */
    public static @Nonnull BoardShape create(
            @Nonnull String name,
            @Nonnull Set<Tile> tiles,
            @Nonnull Set<Tile> rosetteTiles
    ) {
        return new BoardShape(new TextName(name), tiles, rosetteTiles);
    }
}