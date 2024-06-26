package net.royalur.rules;

import net.royalur.model.Piece;
import net.royalur.model.PlayerType;

import javax.annotation.Nonnull;

/**
 * An interface that provides instances of pieces. This may be used
 * to store custom information with each piece, for situations such
 * as adding stacking or unique piece behavior.
 */
public interface PieceProvider<P extends Piece> {

    /**
     * Generate a piece on the board.
     */
    @Nonnull P create(@Nonnull PlayerType owner, int pathIndex);

    /**
     * Generates a new piece to be introduced to the board.
     * @param owner The owner of the new piece.
     * @param newPathIndex The destination index of the piece in the player's path.
     * @return The new piece that may be introduced to the board.
     */
    @Nonnull P createIntroduced(@Nonnull PlayerType owner, int newPathIndex);

    /**
     * Generates a piece that has been moved from another tile on the board.
     * @param originPiece The piece that will be moved.
     * @param newPathIndex The destination index of the piece in the player's path.
     * @return The new piece to be placed on the board.
     */
    @Nonnull P createMoved(@Nonnull P originPiece, int newPathIndex);
}
