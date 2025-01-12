import static org.junit.Assert.*;
import org.junit.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TestDocSearch {
	@Test 
	public void testIndex() throws URISyntaxException, IOException {
    Handler h = new Handler("./technical/");
    URI rootPath = new URI("http://localhost/");
    assertEquals("There are 10 total files to search.", h.handleRequest(rootPath));
	}
	@Test 
	public void testSearch() throws URISyntaxException, IOException {
    Handler h = new Handler("./technical/");
    URI rootPath = new URI("http://localhost/search?q=taxation");
    Path path1 = Paths.get("./technical/biomed/1471-2296-3-3.txt");
    Path path2 = Paths.get("./technical/government/Gen_Account_Office/d01591sp.txt");
    Path path3 = Paths.get("./technical/plos/journal.pbio.0020052.txt");
    String expect = String.format("Found 3 paths:\n%s\n%s\n%s", path1, path2, path3);
    assertEquals(expect, h.handleRequest(rootPath));
	}
}

