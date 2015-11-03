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

              assertEquals("[2, 1, 0, 3]", graph.dfsStack(2).getSearchOrder().toString());
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
        graph.dfsStack(6);
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
        assertEquals("[0, 1, 3, 2]", graph.dfsStack(0).getSearchOrder().toString());
    }

    @Test
    public void getPath_startRootEndRoot_0(){
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
        assertEquals("[0]" ,graph.getPath(0, 0).toString());
    }

    @Test
    public void getPath_fromZeroToFour_zeroThreeFour(){
        String[] vertises = {"Oslo", "Bodø", "Tromsø", "Trondheim", "Bergen"};
        int[][] edges = {
                {0,1}, {0,3},
                {1,0}, {1,2}, {1,3},
                {2,1}, {2,3},
                {3,1}, {3,2}, {3,4},
                {4,3}
        };
        AbstractGraph<String> graph = new AbstractGraph<String>(vertises, edges) {
            @Override
            public int getSize() {
                return super.getSize();
            }
        };
        assertEquals("[0, 3, 4]" ,graph.getPath(0, 4).toString());
    }

    @Test
    public void getPath_fromZeroToTwo_zeroOneTwo(){
        String[] vertises = {"Oslo", "Bodø", "Tromsø", "Trondheim", "Bergen"};
        int[][] edges = {
                {0,1}, {0,3},
                {1,0}, {1,2}, {1,3},
                {2,1}, {2,3},
                {3,1}, {3,2}, {3,4},
                {4,3}
        };
        AbstractGraph<String> graph = new AbstractGraph<String>(vertises, edges) {
            @Override
            public int getSize() {
                return super.getSize();
            }
        };
        assertEquals("[0, 1, 2]" ,graph.getPath(0, 2).toString());
    }



}