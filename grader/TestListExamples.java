import static org.junit.Assert.*;
import org.junit.*;
import java.util.LinkedList;
import java.util.List;

public class TestListExamples {
  // Write your grading tests here!
  public class LongerThanSix implements StringChecker {
    public boolean checkString(String s) {
        return s.length() < 7;
    }
  }
  @Test
  public void testFilter(){
    List<String> input = new LinkedList();
    input.add("I");
    input.add("like");
    input.add("big");
    input.add("computers");
    input.add("butts");
    input.add("I");
    input.add("cannot");
    input.add("lie");

    List<String> expected = new LinkedList();
    expected.add("I");
    expected.add("like");
    expected.add("big");
    expected.add("butts");
    expected.add("I");
    expected.add("cannot");
    expected.add("lie");

    assertArrayEquals(expected.toArray(), ListExamples.filter(input, new LongerThanSix()).toArray());
  }

  @Test
  public void testMergeReg(){
    // Regular case
    List<String> input1 = new LinkedList();
    input1.add("1");
    input1.add("4");
    input1.add("8");
    List<String> input2 = new LinkedList();
    input2.add("2");
    input2.add("6");
    input2.add("7");
    List<String> expected = new LinkedList();
    expected.add("1");
    expected.add("2");
    expected.add("4");
    expected.add("6");
    expected.add("7");
    expected.add("8");
    assertArrayEquals(expected.toArray(), ListExamples.merge(input1, input2).toArray());
  }
  @Test
  public void testMergeDupe(){
    List<String> input1 = new LinkedList();
    input1.add("a");
    input1.add("c");
    input1.add("e");

    List<String> input2 = new LinkedList();
    input2.add("a");
    input2.add("b");
    input2.add("d");

    List<String>  expected = new LinkedList();
    expected.add("a");
    expected.add("a");
    expected.add("b");
    expected.add("c");
    expected.add("d");
    expected.add("e");

    System.out.println(ListExamples.merge(input1, input2).toArray());
    System.out.println(expected.toArray());

    assertArrayEquals(expected.toArray(), ListExamples.merge(input1, input2).toArray());
  } 
}
