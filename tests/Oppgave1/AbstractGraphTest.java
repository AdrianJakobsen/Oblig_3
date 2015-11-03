package Oppgave1;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AbstractGraphTest {



    @Test
    public void dfsStack_returnTheSameAsRecursiveDFS_true(){
          String[] vertises = {"Oslo", "Bodø", "Tromsø", "Trondheim"};
          int[][] edges = {
                {0,1}, {0,3},
                {1,0}, {1,2}, {1,3},
                {2,1}, {2,3},
                {3,1}, {3,2}
        };
          AbstractGraph<String> graph = new AbstractGraph<String>(vertises, edges) {
              @Override
              public int getSize() {
                  return super.getSize();
              }
          };
              assertEquals(graph.dfs(2).getSearchOrder(), graph.dfsStack(2).getSearchOrder());
    }



    @Test(expected = IndexOutOfBoundsException.class)
    public void dfsStack_sendInAnNonExistentIndex_(){
        String[] vertises = {"Oslo", "Bodø", "Tromsø", "Trondheim"};
        int[][] edges = {
                {0,1}, {0,3},
                {1,0}, {1,2}, {1,3},
                {2,1}, {2,3},
                {3,1}, {3,2}
        };
        AbstractGraph<String> graph = new AbstractGraph<String>(vertises, edges) {
            @Override
            public int getSize() {
                return super.getSize();
            }
        };
        assertEquals(graph.dfs(2).getSearchOrder(), graph.dfsStack(6).getSearchOrder());
    }

    @Test
    public void dfsStack_graphThatIsPointed_(){
        Character[] vertises = {'A', 'B', 'C', 'D' };

        int[][] edges = {
                {0, 1}, {0, 2},
                {1, 0}, {1, 3},
                {2, 0}, {2, 1},
                {3, 1}
        };
        AbstractGraph<Character> graph = new AbstractGraph<Character>(vertises, edges) {
            @Override
            public int getSize() {
                return super.getSize();
            }
        };
        assertEquals(graph.dfs(0).getSearchOrder(), graph.dfsStack(0).getSearchOrder());
    }



}