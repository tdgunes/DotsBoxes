import java.util.List;
import java.util.Random;

public class OneGreedyAgent implements Agent {
    private static final Random random = new Random();

    @Override
    public State doMove(State root) {
        List<State> children = root.getChildren();

        int randomNumber = random.nextInt(children.size());
        State child = children.get(randomNumber);
        int max = child.score[root.turn];

        for (State state : children) {
            if (state.score[root.turn] > max) {
                child = state;
                max = child.score[root.turn];
            }
        }

        return child;
    }
}
