package net.royalur.rules.state;

import net.royalur.model.*;
import net.royalur.model.dice.Roll;

import javax.annotation.Nonnull;

/**
 * A game state where we are waiting for interactions from a player.
 * @param <P> The type of pieces that are stored on the board in this game state.
 * @param <S> The type of state that is stored for each player.
 * @param <R> The type of rolls that may be stored in this game state.
 */
public abstract class PlayableGameState<
        P extends Piece,
        S extends PlayerState,
        R extends Roll
> extends OngoingGameState<P, S, R> {

    /**
     * Instantiates a game state for an ongoing point in a game.
     * @param board       The state of the pieces on the board.
     * @param lightPlayer The state of the light player.
     * @param darkPlayer  The state of the dark player.
     * @param turn        The player who made an action or that should make an action.
     */
    public PlayableGameState(
            @Nonnull Board<P> board,
            @Nonnull S lightPlayer,
            @Nonnull S darkPlayer,
            @Nonnull PlayerType turn
    ) {
        super(board, lightPlayer, darkPlayer, turn);
    }

    @Override
    public boolean isPlayable() {
        return true;
    }
}
