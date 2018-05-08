
import java.util.List;
import java.util.Random;


public class RandomAgent {
	
	private Random random = new Random();
	
	public State generateNextState(State state) {
		List<State> childStates =  state.getChildren();
		int child = random.nextInt(childStates.size());
		return childStates.get(child);
	}

}
