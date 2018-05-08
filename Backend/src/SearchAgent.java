import java.util.List;
import java.util.Random;

public class SearchAgent implements Agent {
    private static final Random random = new Random();
    private int max_depth = 3;

    private int negamax(State state, int depth) {

        // return the leaf value
        if(depth == 0 || state.isGameFinished())
            return state.score[state.turn] - state.score[1 - state.turn];

        // compute the minimax score
        int max_score = -Integer.MIN_VALUE;
        List<State> children = state.getChildren();
        for(State child: children) {
            int curr_score = negamax(child, depth - 1);
            if(child.turn != state.turn)
                curr_score = -curr_score;
            if(curr_score > max_score)
                max_score = curr_score;
        }

        return max_score;
    }

    @Override
    public State doMove(State root) {
        List<State> children = root.getChildren();

        int randomNumber = random.nextInt(children.size());
        State best = children.get(randomNumber);
        int max_score = negamax(best, max_depth);
        if(best.turn != root.turn)
            max_score = -max_score;

        for (State child : children) {
            int curr_score = negamax(child, max_depth - 1);
            if(child.turn != root.turn)
                curr_score = -curr_score;
            if(curr_score > max_score) {
                max_score = curr_score;
                best = child;
            }
        }

        return best;
    }
}
