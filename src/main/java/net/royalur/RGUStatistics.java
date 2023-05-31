package net.royalur;

import net.royalur.agent.Agent;
import net.royalur.agent.RandomAgent;
import net.royalur.model.PlayerState;
import net.royalur.model.Roll;
import net.royalur.model.path.BellPathPair;
import net.royalur.model.path.MastersPathPair;
import net.royalur.model.path.MurrayPathPair;
import net.royalur.model.path.SkiriukPathPair;
import net.royalur.rules.standard.StandardPiece;
import net.royalur.stats.GameStats;
import net.royalur.stats.GameStatsSummary;
import net.royalur.stats.GameStatsTarget;
import net.royalur.stats.SummaryStat;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.function.Supplier;

/**
 * This file intends to hold tests that can be performed
 * to different sets of game rules and paths to compare them.
 */
public class RGUStatistics {

    /**
     * Instantiate to run statistics about the paths available for the Royal Game of Ur.
     */
    public RGUStatistics() {}

    /**
     * Generates statistics for a single game generated by {@code gameGenerator},
     * and played by two random agents.
     * @param gameGenerator A generator to produce a new game for the random agents to play.
     * @return Statistics about the game that was played between two random agents.
     */
    private @Nonnull GameStats testRandomAgentActions(
            @Nonnull Supplier<Game<StandardPiece, PlayerState, Roll>> gameGenerator
    ) {

        Game<StandardPiece, PlayerState, Roll> game = gameGenerator.get();
        RandomAgent<StandardPiece, PlayerState, Roll> light = new RandomAgent<>();
        RandomAgent<StandardPiece, PlayerState, Roll> dark = new RandomAgent<>();
        Agent.playAutonomously(game, light, dark);
        return GameStats.gather(game);
    }

    /**
     * Runs tests using a random agent on many paths.
     */
    public void testRandomAgentActions() {
        int tests = 10_000;
        List<Supplier<Game<StandardPiece, PlayerState, Roll>>> generators = List.of(
                () -> Game.builder().standard().paths(new BellPathPair()).build(),
                () -> Game.builder().standard().paths(new MastersPathPair()).build(),
                () -> Game.builder().standard().paths(new SkiriukPathPair()).build(),
                () -> Game.builder().standard().paths(new MurrayPathPair()).build(),
                () -> Game.builder().aseb().build()
        );
        for (Supplier<Game<StandardPiece, PlayerState, Roll>> gameGenerator : generators) {
            Game<StandardPiece, PlayerState, Roll> sample = gameGenerator.get();
            String desc = sample.getBoard().getShape().getDebugName()
                    + ", " + sample.getRules().getPaths().getDebugName();

            GameStats[] stats = new GameStats[tests];
            for (int test = 0; test < tests; ++test) {
                stats[test] = testRandomAgentActions(gameGenerator);
            }
            GameStatsSummary summary = GameStats.summarise(stats);

            System.out.println(desc + ":");
            for (GameStatsTarget target : GameStatsTarget.values()) {
                double movesMean = summary.getMovesStatistic(target, SummaryStat.MEAN);
                double movesStd = summary.getMovesStatistic(target, SummaryStat.STD_DEV);
                double rollsMean = summary.getRollsStatistic(target, SummaryStat.MEAN);
                double rollsStd = summary.getRollsStatistic(target, SummaryStat.STD_DEV);
                System.out.printf(
                        "%-15s%-19s%-19s%n",
                        target.getName() + ":",
                        ((int) movesMean) + " moves ± " + ((int) movesStd) + ",",
                        ((int) rollsMean) + " rolls ± " + ((int) rollsStd)
                );
            }
            System.out.println();
        }
    }

    /**
     * The main entrypoint to run statistics about the Royal Game of Ur board shapes and paths.
     * @param args Ignored.
     */
    public static void main(String[] args) {
        new RGUStatistics().testRandomAgentActions();
    }
}
