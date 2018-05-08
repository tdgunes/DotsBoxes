

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AvoidMakingBoxesAgent {
	
	private Random random = new Random();

	public State generateNextState(State state) {
		int opponent = 1 - state.turn;
		int enemyScore = state.score[opponent];
		List<State> allChildStates =  state.getChildren();
		List<State> goodChildStates =  new ArrayList<>();
		for (State child : allChildStates){
			List<State> grandChildStates =  child.getChildren();
			for (State grandChild : grandChildStates){
				if (grandChild.score[opponent] == enemyScore){
					goodChildStates.add(grandChild);
				}
			}
		}
		if (goodChildStates.size() == 0){
			int child = random.nextInt(allChildStates.size());
			return allChildStates.get(child);
		} else {
			int child = random.nextInt(goodChildStates.size());
			return goodChildStates.get(child);
		}
	}
}
