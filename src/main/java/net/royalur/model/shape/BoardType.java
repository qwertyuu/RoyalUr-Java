package net.royalur.model.shape;

import net.royalur.name.Name;
import net.royalur.name.NameMap;
import net.royalur.name.UniqueNameMap;

import javax.annotation.Nonnull;

/**
 * The type of board to use in a game.
 */
public enum BoardType implements Name, BoardShapeFactory {

    /**
     * The standard board shape.
     */
    STANDARD(1, "Standard") {
        @Override
        public @Nonnull BoardShape createBoardShape() {
            return new StandardBoardShape();
        }
    },

    /**
     * The Aseb board shape.
     */
    ASEB(2, "Aseb") {
        @Override
        public @Nonnull BoardShape createBoardShape() {
            return new AsebBoardShape();
        }
    };

    /**
     * A store to be used to parse board shapes.
     */
    public static final @Nonnull NameMap<BoardType, BoardShapeFactory> FACTORIES;
    static {
        NameMap<BoardType, BoardShapeFactory> factories = new UniqueNameMap<>();
        for (BoardType type : values()) {
            factories.put(type, type);
        }
        FACTORIES = factories.unmodifiableCopy();
    }

    /**
     * A constant numerical ID representing the board shape.
     * This ID will never change.
     */
    private final int id;

    /**
     * The name of this board shape.
     */
    private final @Nonnull String name;

    /**
     * Instantiates a type of path.
     * @param id   A fixed numerical identifier to represent this board shape.
     * @param name The name of this board shape.
     */
    BoardType(int id, @Nonnull String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public @Nonnull Name getName() {
        return this;
    }

    @Override
    public @Nonnull String getTextName() {
        return name;
    }

    @Override
    public boolean hasID() {
        return true;
    }

    @Override
    public int getID() {
        return id;
    }

    /**
     * Create an instance of the board shape.
     * @return The instance of the board shape.
     */
    public abstract @Nonnull BoardShape createBoardShape();
}
