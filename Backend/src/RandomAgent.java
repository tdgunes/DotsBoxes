import java.util.List;
import java.util.Random;

public class RandomAgent implements Agent {

    private static final Random random = new Random();

    @Override
    public State doMove(State root) {
        List<State> children = root.getChildren();
        int randomNumber = random.nextInt(children.size());

        return children.get(randomNumber);
    }

}
