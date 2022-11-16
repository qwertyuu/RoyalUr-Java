package net.royalur.model;

import javax.annotation.Nullable;

/**
 * Represents the players of a game.
 */
public enum Player {
    /**
     * The light player. Following chess, the light player moves first.
     */
    LIGHT(1, "Light", 'L'),

    /**
     * The dark player. Following chess, the dark player moves second.
     */
    DARK(2, "Dark", 'D');

    /**
     * A constant numerical ID representing the player.
     * This ID will never change.
     */
    public final int id;

    /**
     * An English name for this player.
     */
    public final String name;

    /**
     * A constant character representing the player.
     * This character will never change.
     */
    public final char character;

    Player(int id, String name, char character) {
        this.id = id;
        this.name = name;
        this.character = character;
    }

    /**
     * Converts {@param player} to a single character that can be used
     * to textually represent a piece.
     *
     * @param player The player or {@code null} to convert to a character.
     * @return The character representing {@param player}.
     */
    public static char toChar(@Nullable Player player) {
        if (player == null)
            return '.';

        return player.character;
    }
}
